package org.opennms.nutanix.client.v3;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.v3.api.HostsApi;
import org.opennms.nutanix.client.v3.api.VmsApi;
import org.opennms.nutanix.client.v3.handler.ApiClient;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.model.HostListIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListMetadata;
import org.opennms.nutanix.client.v3.model.VmListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListMetadata;

public class NutanixApiClientV3Test {

    private ApiClient getApiClient() {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/api/nutanix/v3");
        apiClient.setUsername("username");
        apiClient.setPassword("password");
        apiClient.setDebugging(true);
        apiClient.setIgnoreSslCertificateValidation(true);
        return apiClient;

    }
    @Test
    public void testVmsApi() {

        VmsApi vmsApi = new VmsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> vmnames = new HashSet<>();
        Set<String> onVms = new HashSet<>();
        Set<String> offVms = new HashSet<>();
        int total;
            do {
                VmListMetadata body = new VmListMetadata().length(lenght).offset(offset);
                try {
                VmListIntentResponse vmListIntentResponse = vmsApi.vmsListPost(body);
                total = vmListIntentResponse.getMetadata().getTotalMatches();

                vmListIntentResponse.getEntities()
                        .stream()
                        .filter(vm -> vm.getStatus().getResources().getPowerState().equalsIgnoreCase("off"))
                        .forEach(vm -> offVms.add(vm.getStatus().getName()));
                vmListIntentResponse.getEntities()
                        .stream()
                        .filter(vm -> vm.getStatus().getResources().getPowerState().equalsIgnoreCase("on"))
                        .forEach(vm -> onVms.add(vm.getStatus().getName()));
                vmListIntentResponse.getEntities().forEach(vm -> vmnames.add(vm.getStatus().getName()));
                lenght = vmListIntentResponse.getEntities().size();
                offset+=lenght;
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            } while (vmnames.size() < total );
            System.out.println("total vms: " + vmnames.size());
            System.out.println("off vms: " + offVms.size());
            System.out.println("on vms: " + onVms.size());

            Assert.assertEquals(vmnames.size(),total);


    }

    @Test
    public void testHostApi() {

        HostsApi hostsApi = new HostsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> hostnames = new HashSet<>();
        Set<String> hiperConvergentHostTypes = new HashSet<>();
        Set<String> notHiperConvergentHostTypes = new HashSet<>();
        int total;

        do {
        try {
                HostListMetadata hostListMetadata = new HostListMetadata().length(lenght).offset(offset);
                HostListIntentResponse hostListIntentResponse = hostsApi.hostsListPost(hostListMetadata);
                total = hostListIntentResponse.getMetadata().getTotalMatches();

                hostListIntentResponse.getEntities().forEach(h -> hostnames.add(h.getStatus().getName()));
                hostListIntentResponse.getEntities()
                        .stream().filter(h -> h.getStatus().getResources().getHostType().equalsIgnoreCase("HYPER_CONVERGED"))
                        .forEach(h -> hiperConvergentHostTypes.add(h.getStatus().getName()));
                hostListIntentResponse.getEntities()
                        .stream().filter(h -> !h.getStatus().getResources().getHostType().equalsIgnoreCase("HYPER_CONVERGED"))
                        .forEach(h -> notHiperConvergentHostTypes.add(h.getStatus().getName()));
                lenght = hostListIntentResponse.getEntities().size();
                offset+=lenght;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        } while (hostnames.size() < total );

        System.out.println("total hosts: " + hostnames.size());
        System.out.println("hyper_converged hosts: " + hiperConvergentHostTypes.size());
        System.out.println("not hyper_converged hosts: " + notHiperConvergentHostTypes.size());
        Assert.assertEquals(hostnames.size(),total);


    }


}
