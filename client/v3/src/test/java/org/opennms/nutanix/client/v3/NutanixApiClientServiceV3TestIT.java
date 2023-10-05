package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.internal.Utils;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.api.model.VMNic;

public class NutanixApiClientServiceV3TestIT {

    @Test
    public void testApiProvider() throws NutanixApiException {
        V3ApiClientProvider provider = new V3ApiClientProvider();
        ApiClientCredentials credentials = ApiClientCredentials.builder()
                .withUsername(System.getenv("NTX_USER"))
                .withPassword(System.getenv("NTX_PASS"))
                .withPrismUrl("https://nutanix.arsinfo.it:9440/")
                .withIgnoreSslCertificateValidation(true)
                .withLength(20)
                .build();
        ApiClientService service = provider.client(credentials);
        Map<String, String> clusterExternalSubnetMap = new HashMap<>();
        for (Cluster cluster : service.getClusters()) {
            System.out.println(cluster.name);
            System.out.println(cluster.uuid);
            System.out.println("-----external-----");
            System.out.println(cluster.externalSubnet);
            System.out.println(cluster.externalDataServicesIp);
            System.out.println(cluster.externalIp);
            System.out.println("-----internal-----");
            System.out.println(cluster.internalSubnet);
            System.out.println("----------");
            clusterExternalSubnetMap.put(cluster.uuid, cluster.externalSubnet);
        }
        for (Host host : service.getHosts()) {
            System.out.println(host.uuid);
            String extNet = clusterExternalSubnetMap.get(host.clusterUuid);
            System.out.println(extNet);
            System.out.println(host.controllerVmIp);
            Assert.assertTrue(Utils.isIpInSubnet(host.controllerVmIp, extNet));
            System.out.println(host.hypervisorIp);
            Assert.assertTrue(Utils.isIpInSubnet(host.hypervisorIp, extNet));
            System.out.println(host.ipmi);
            System.out.println("----------");
        }

        List<String> addresses = new ArrayList<>();
        Map<String, List<String>> vlanIpMap = new HashMap<>();
        for (VM vm : service.getVMS()) {
            if (!vm.powerState.equalsIgnoreCase("ON")) {
                continue;
            }
            String extNet = clusterExternalSubnetMap.get(vm.clusterUuid);
            int internalSubnet = 0;
            int externalSubnet = 0;
            List<String> netCards = new ArrayList<>();
            for (VMNic nic : vm.nics) {
                if (!vlanIpMap.containsKey(nic.name)) {
                    vlanIpMap.put(nic.name, new ArrayList<>());
                }
                vlanIpMap.get(nic.name).addAll(nic.ipList);
                netCards.add(nic.toString());
                for (String ipAddress : nic.ipList) {
                    Assert.assertFalse(addresses.contains(ipAddress));
                    addresses.add(ipAddress);
                    if (Utils.isIpInSubnet(ipAddress,extNet)) {
                        internalSubnet++;
                    } else {
                        externalSubnet++;
                    }
                }
            }
            Assert.assertEquals(0,internalSubnet);
            if (externalSubnet > 1) {
                System.out.println("----VM with multiple ip------");
                System.out.println(vm.uuid);
                System.out.println(vm.name);
                System.out.println(vm.powerState);
                System.out.println(netCards);
                System.out.println("-out-"+externalSubnet+"---");
                System.out.println("----------");
            } else {
                Assert.assertEquals(1,externalSubnet);
            }
        }
        System.out.println(vlanIpMap);
    }
    @Test
    public void testValidate() {
        V3ApiClientProvider provider = new V3ApiClientProvider();
        ApiClientCredentials credentials = ApiClientCredentials.builder()
                .withUsername(System.getenv("NTX_USER"))
                .withPassword(System.getenv("NTX_PASS"))
                .withPrismUrl("https://nutanix.arsinfo.it:9440/")
                .withIgnoreSslCertificateValidation(true)
                .withLength(20)
                .build();
        Assert.assertTrue(provider.validate(credentials));

    }

    @Test
    public void testApiServiceVmGet() throws NutanixApiException {
        String uuid="c6b636e7-c69a-42dd-89f8-5d237e6e8f52";
        V3ApiClientProvider provider = new V3ApiClientProvider();
        ApiClientCredentials credentials = ApiClientCredentials.builder()
                .withUsername(System.getenv("NTX_USER"))
                .withPassword(System.getenv("NTX_PASS"))
                .withPrismUrl("https://nutanix.arsinfo.it:9440/")
                .withIgnoreSslCertificateValidation(true)
                .withLength(20)
                .build();
        ApiClientService service = provider.client(credentials);
        VM vm = service.getVM(uuid);
        Assert.assertEquals("ON", vm.powerState);
    }
    @Test
    public void testApiProviderGetCluster() throws NutanixApiException {
        V3ApiClientProvider provider = new V3ApiClientProvider();
        ApiClientCredentials credentials = ApiClientCredentials.builder()
                .withUsername(System.getenv("NTX_USER"))
                .withPassword(System.getenv("NTX_PASS"))
                .withPrismUrl("https://nutanix.arsinfo.it:9440/")
                .withIgnoreSslCertificateValidation(true)
                .withLength(20)
                .build();
        ApiClientService service = provider.client(credentials);
        Cluster cluster = service.getCluster("00059dd3-26be-0d72-5228-ac1f6b357222");
        Assert.assertTrue(cluster.isAvailable);
    }

}
