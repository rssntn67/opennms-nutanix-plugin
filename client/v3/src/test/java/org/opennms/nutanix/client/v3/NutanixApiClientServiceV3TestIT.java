package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private ApiClientCredentials getCredentials(String prismUrl) {
        return ApiClientCredentials.builder()
                .withUsername(System.getenv("NTX_USER"))
                .withPassword(System.getenv("NTX_PASS"))
                .withPrismUrl(prismUrl)
                .withIgnoreSslCertificateValidation(true)
                .withLength(50)
                .build();
    }

    @Test
    public void testValidate() {
        V3ApiClientProvider provider = new V3ApiClientProvider();
        Assert.assertTrue(provider.validate(getCredentials("https://nutanix.arsinfo.it:9440/")));
        Assert.assertTrue(provider.validate(getCredentials("https://nutanix-prod.arsinfo.it:9440/")));
        Assert.assertTrue(provider.validate(getCredentials("https://nutanix-ntx.arsinfo.it:9440/")));
        Assert.assertTrue(provider.validate(getCredentials("https://nutanix-ctx.arsinfo.it:9440/")));
        Assert.assertTrue(provider.validate(getCredentials("https://nutanix-esxi.arsinfo.it:9440/")));
    }

    @Test
    public void testApiProvider() throws NutanixApiException {
        V3ApiClientProvider provider = new V3ApiClientProvider();
        //testService(provider.client(getCredentials("https://nutanix.arsinfo.it:9440/")));
        //testService(provider.client(getCredentials("https://nutanix-prod.arsinfo.it:9440/")));
        //testService(provider.client(getCredentials("https://nutanix-ntx.arsinfo.it:9440/")));
        //testService(provider.client(getCredentials("https://nutanix-ctx.arsinfo.it:9440/")));
        //testService(provider.client(getCredentials("https://nutanix-esxi.arsinfo.it:9440/")));

    }

    public void testService(ApiClientService service) throws NutanixApiException {
        Map<String, String> clusterExternalSubnetMap = new HashMap<>();
        Map<String, String> clusterInternalSubnetMap = new HashMap<>();
        for (Cluster cluster : service.getClusters()) {
            System.out.println("----------Cluster---------");
            System.out.println("name->"+cluster.name);
            System.out.println("uuid->"+cluster.uuid);
            System.out.println("external->subnet->"+cluster.externalSubnet);
            System.out.println("external->dataServicesIp->"+cluster.externalDataServicesIp);
            System.out.println("external->clusterIp->"+cluster.externalIp);
            System.out.println("internal->subnet->"+cluster.internalSubnet);
            System.out.println("----------");
            clusterExternalSubnetMap.put(cluster.uuid, cluster.externalSubnet);
            clusterInternalSubnetMap.put(cluster.uuid, cluster.internalSubnet);
        }
        for (Host host : service.getHosts()) {
            System.out.println("----------Host---------");
            System.out.println("name->"+host.name);
            System.out.println("uuid->"+host.uuid);
            System.out.println("controllerVmIp->"+host.controllerVmIp);
            System.out.println("hypervisorIp->"+host.hypervisorIp);
            System.out.println("ipmi->"+host.ipmi);
            System.out.println("----------");
            String extNet = clusterExternalSubnetMap.get(host.clusterUuid);
            Assert.assertTrue(Utils.isIpInSubnet(host.controllerVmIp, extNet));
            Assert.assertTrue(Utils.isIpInSubnet(host.hypervisorIp, extNet));
        }

        List<String> addresses = new ArrayList<>();
        Map<String, List<String>> vlanIpMap = new HashMap<>();
        Set<String> vmwithduplicatedipaddress = new HashSet<>();
        for (VM vm : service.getVMS()) {
            if (!vm.powerState.equalsIgnoreCase("ON")) {
                continue;
            }
            System.out.println("------------VM-------------");
            System.out.println("name->"+vm.name);
            System.out.println("uuid->"+vm.uuid);
            System.out.println("powerState->"+vm.powerState);
            //String extNet = clusterExternalSubnetMap.get(vm.clusterUuid);
            //String intNet = clusterInternalSubnetMap.get(vm.clusterUuid);
            for (VMNic nic : vm.nics) {
                if (!vlanIpMap.containsKey(nic.name)) {
                    vlanIpMap.put(nic.name, new ArrayList<>());
                }
                System.out.println(nic);
//                Assert.assertTrue(nic.isConnected);
                Assert.assertEquals(nic.vlanMode,"ACCESS");
                Assert.assertEquals(nic.nicType,"NORMAL_NIC");
                Assert.assertEquals(nic.kind,"subnet");
                vlanIpMap.get(nic.name).addAll(nic.ipList);
                for (String ipAddress : nic.ipList) {
                    if (addresses.contains(ipAddress)) {
                        vmwithduplicatedipaddress.add(vm.uuid);
                    }
                    //Assert.assertFalse(Utils.isIpInSubnet(ipAddress,intNet));
                    //Assert.assertFalse(Utils.isIpInSubnet(ipAddress,extNet));
                    //Assert.assertFalse(addresses.contains(ipAddress));
                    addresses.add(ipAddress);
                }
            }
            System.out.println("----------");
        }
        System.out.println(vmwithduplicatedipaddress);
        System.out.println(vlanIpMap);
    }
    @Test
    public void testApiServiceVmGet() throws NutanixApiException {
        V3ApiClientProvider provider = new V3ApiClientProvider();
        ApiClientService service = provider.client(getCredentials("https://nutanix.arsinfo.it:9440/"));
        VM vm = service.getVM("c6b636e7-c69a-42dd-89f8-5d237e6e8f52");
        Assert.assertEquals("TVL-GEOPASS-AS1", vm.name);
        Assert.assertEquals("ON", vm.powerState);
    }

    @Test
    public void testApiServiceHostGet() throws NutanixApiException {
        V3ApiClientProvider provider = new V3ApiClientProvider();
        ApiClientService service = provider.client(getCredentials("https://nutanix.arsinfo.it:9440/"));
        Host host = service.getHost("cd6c6e07-39f2-4381-ba5d-ec854a76b02d");
        Assert.assertEquals("NTX02B-TEST", host.name);
        Assert.assertEquals("HYPER_CONVERGED", host.hostType);
    }
    @Test
    public void testApiProviderGetCluster() throws NutanixApiException {
        V3ApiClientProvider provider = new V3ApiClientProvider();
        ApiClientService service = provider.client(getCredentials("https://nutanix.arsinfo.it:9440/"));
        Cluster cluster = service.getCluster("00059dd3-26be-0d72-5228-ac1f6b357222");
        Assert.assertTrue(cluster.isAvailable);
    }

}
