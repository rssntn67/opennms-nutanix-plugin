package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.v3.api.AccessControlPoliciesApi;
import org.opennms.nutanix.client.v3.api.AlertsApi;
import org.opennms.nutanix.client.v3.api.AppBlueprintApi;
import org.opennms.nutanix.client.v3.api.BatchApi;
import org.opennms.nutanix.client.v3.api.CitrixAdaptersApi;
import org.opennms.nutanix.client.v3.api.ClustersApi;
import org.opennms.nutanix.client.v3.api.DataApi;
import org.opennms.nutanix.client.v3.api.DefaultApi;
import org.opennms.nutanix.client.v3.api.DirectoryServicesApi;
import org.opennms.nutanix.client.v3.api.DisksApi;
import org.opennms.nutanix.client.v3.api.DockerRegistryApi;
import org.opennms.nutanix.client.v3.api.ExternalRepositoriesApi;
import org.opennms.nutanix.client.v3.api.ExternalRepositoryApi;
import org.opennms.nutanix.client.v3.api.FileStoreApi;
import org.opennms.nutanix.client.v3.api.GroupsApi;
import org.opennms.nutanix.client.v3.api.HostsApi;
import org.opennms.nutanix.client.v3.api.IdempotenceIdentifiersApi;
import org.opennms.nutanix.client.v3.api.ImagesApi;
import org.opennms.nutanix.client.v3.api.IpfixExportersApi;
import org.opennms.nutanix.client.v3.api.MhVmsApi;
import org.opennms.nutanix.client.v3.api.NccApi;
import org.opennms.nutanix.client.v3.api.NetworkFunctionChainsApi;
import org.opennms.nutanix.client.v3.api.NgtApi;
import org.opennms.nutanix.client.v3.api.OauthApi;
import org.opennms.nutanix.client.v3.api.OvasApi;
import org.opennms.nutanix.client.v3.api.PermissionsApi;
import org.opennms.nutanix.client.v3.api.PortalServicesApi;
import org.opennms.nutanix.client.v3.api.PrismCentralApi;
import org.opennms.nutanix.client.v3.api.ProjectsApi;
import org.opennms.nutanix.client.v3.api.RackApi;
import org.opennms.nutanix.client.v3.api.RackableUnitApi;
import org.opennms.nutanix.client.v3.api.RemoteConnectionsApi;
import org.opennms.nutanix.client.v3.api.RemoteSyslogModulesApi;
import org.opennms.nutanix.client.v3.api.RemoteSyslogServersApi;
import org.opennms.nutanix.client.v3.api.RolesApi;
import org.opennms.nutanix.client.v3.api.SshUserApi;
import org.opennms.nutanix.client.v3.api.SubnetsApi;
import org.opennms.nutanix.client.v3.api.TasksApi;
import org.opennms.nutanix.client.v3.api.TenantsApi;
import org.opennms.nutanix.client.v3.api.UserGroupsApi;
import org.opennms.nutanix.client.v3.api.UsersApi;
import org.opennms.nutanix.client.v3.api.VersionsApi;
import org.opennms.nutanix.client.v3.api.VmRecoveryPointsApi;
import org.opennms.nutanix.client.v3.api.VmSnapshotApi;
import org.opennms.nutanix.client.v3.api.VmsApi;
import org.opennms.nutanix.client.v3.api.VolumeGroupSnapshotsApi;
import org.opennms.nutanix.client.v3.api.VolumeGroupsApi;
import org.opennms.nutanix.client.v3.api.WebhooksApi;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.model.AccessControlPolicyListIntentResponse;
import org.opennms.nutanix.client.v3.model.AccessControlPolicyListMetadata;
import org.opennms.nutanix.client.v3.model.AlertListIntentResponse;
import org.opennms.nutanix.client.v3.model.AlertListMetadata;
import org.opennms.nutanix.client.v3.model.AppBlueprintRenderInfoInput;
import org.opennms.nutanix.client.v3.model.AppBlueprintRenderInfoOutput;
import org.opennms.nutanix.client.v3.model.BatchRequest;
import org.opennms.nutanix.client.v3.model.BatchResponse;
import org.opennms.nutanix.client.v3.model.ChangedRegions;
import org.opennms.nutanix.client.v3.model.ChangedRegionsQuery;
import org.opennms.nutanix.client.v3.model.CitrixAdapterListIntentResponse;
import org.opennms.nutanix.client.v3.model.CitrixAdapterListMetadata;
import org.opennms.nutanix.client.v3.model.ClusterListIntentResponse;
import org.opennms.nutanix.client.v3.model.ClusterListMetadata;
import org.opennms.nutanix.client.v3.model.DirectoryServiceListIntentResponse;
import org.opennms.nutanix.client.v3.model.DirectoryServiceListMetadata;
import org.opennms.nutanix.client.v3.model.DiskListIntentResponse;
import org.opennms.nutanix.client.v3.model.DiskListMetadata;
import org.opennms.nutanix.client.v3.model.DockerRegistryListIntentResponse;
import org.opennms.nutanix.client.v3.model.DockerRegistryListMetadata;
import org.opennms.nutanix.client.v3.model.ExternalRepositoryListIntentResponse;
import org.opennms.nutanix.client.v3.model.ExternalRepositoryListMetadata;
import org.opennms.nutanix.client.v3.model.FileItemListIntentResponse;
import org.opennms.nutanix.client.v3.model.FileItemListMetadata;
import org.opennms.nutanix.client.v3.model.GroupsGetEntitiesRequest;
import org.opennms.nutanix.client.v3.model.GroupsGetEntitiesResponse;
import org.opennms.nutanix.client.v3.model.HostListIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListMetadata;
import org.opennms.nutanix.client.v3.model.ImageListIntentResponse;
import org.opennms.nutanix.client.v3.model.ImageListMetadata;
import org.opennms.nutanix.client.v3.model.NccTagsInput;
import org.opennms.nutanix.client.v3.model.NccTagsResponse;
import org.opennms.nutanix.client.v3.model.NetworkFunctionChainListIntentResponse;
import org.opennms.nutanix.client.v3.model.NetworkFunctionChainListMetadata;
import org.opennms.nutanix.client.v3.model.NgtListMetadata;
import org.opennms.nutanix.client.v3.model.NgtListResponse;
import org.opennms.nutanix.client.v3.model.OauthClientList;
import org.opennms.nutanix.client.v3.model.OauthClientListMetadata;
import org.opennms.nutanix.client.v3.model.PermissionListIntentResponse;
import org.opennms.nutanix.client.v3.model.PermissionListMetadata;
import org.opennms.nutanix.client.v3.model.ProjectListIntentResponse;
import org.opennms.nutanix.client.v3.model.ProjectListMetadata;
import org.opennms.nutanix.client.v3.model.RackListIntentResponse;
import org.opennms.nutanix.client.v3.model.RackListMetadata;
import org.opennms.nutanix.client.v3.model.RackableUnitListIntentResponse;
import org.opennms.nutanix.client.v3.model.RackableUnitListMetadata;
import org.opennms.nutanix.client.v3.model.RemoteSyslogModuleListIntentResponse;
import org.opennms.nutanix.client.v3.model.RemoteSyslogModuleListMetadata;
import org.opennms.nutanix.client.v3.model.RemoteSyslogServerListIntentResponse;
import org.opennms.nutanix.client.v3.model.RemoteSyslogServerListMetadata;
import org.opennms.nutanix.client.v3.model.RoleListIntentResponse;
import org.opennms.nutanix.client.v3.model.RoleListMetadata;
import org.opennms.nutanix.client.v3.model.SoftwareListIntentResponse;
import org.opennms.nutanix.client.v3.model.SoftwareListMetadata;
import org.opennms.nutanix.client.v3.model.SshUserList;
import org.opennms.nutanix.client.v3.model.SshUserListMetadata;
import org.opennms.nutanix.client.v3.model.SubnetListIntentResponse;
import org.opennms.nutanix.client.v3.model.SubnetListMetadata;
import org.opennms.nutanix.client.v3.model.TaskListIntentResponse;
import org.opennms.nutanix.client.v3.model.TaskListMetadata;
import org.opennms.nutanix.client.v3.model.UserGroupListIntentResponse;
import org.opennms.nutanix.client.v3.model.UserGroupListMetadata;
import org.opennms.nutanix.client.v3.model.UserListIntentResponse;
import org.opennms.nutanix.client.v3.model.UserListMetadata;
import org.opennms.nutanix.client.v3.model.Versions;
import org.opennms.nutanix.client.v3.model.VmIntentResource;
import org.opennms.nutanix.client.v3.model.VmIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListMetadata;
import org.opennms.nutanix.client.v3.model.VmRecoveryPointListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmRecoveryPointListMetadata;
import org.opennms.nutanix.client.v3.model.VolumeGroupListIntentResponse;
import org.opennms.nutanix.client.v3.model.VolumeGroupListMetadata;
import org.opennms.nutanix.client.v3.model.WebhookListIntentResponse;
import org.opennms.nutanix.client.v3.model.WebhookListMetadata;

public class NutanixApiClientV3Test {

    private ApiClientExtention getApiClient() {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/api/nutanix/v3");
        apiClient.setUsername(System.getenv("NTX_USER"));
        apiClient.setPassword(System.getenv("NTX_PASS"));
        apiClient.setDebugging(true);
        apiClient.setIgnoreSslCertificateValidation(true);
        apiClient.setLength(20);
        return apiClient;

    }

    @Test
    public void testEnvVariable() {
        Assert.assertNotNull(System.getenv("NTX_USER"));
        Assert.assertNotNull(System.getenv("NTX_PASS"));
    }
    @Test
    public void testVmsApi() {

        ApiClientExtention apiClient = getApiClient();
        VmsApi vmsApi = new VmsApi(apiClient);
        int offset = 0;
        Set<String> vmnames = new HashSet<>();
        Set<String> onVms = new HashSet<>();
        Set<String> offVms = new HashSet<>();
        Set<String> stateVms = new HashSet<>();
        Set<String> powerStateVms = new HashSet<>();
        Set<String> clusteruuids = new HashSet<>();
        Set<String> clusternames = new HashSet<>();
        Set<String> clusterkinds = new HashSet<>();
        Set<String> nicType = new HashSet<>();
        Set<String> diskType = new HashSet<>();
        List<VmIntentResource> errorVms = new ArrayList<>();
        int total;
            do {
                VmListMetadata body = new VmListMetadata().length(apiClient.getLength()).offset(offset);
                try {
                    VmListIntentResponse vmListIntentResponse = vmsApi.vmsListPost(body);
                    total = vmListIntentResponse.getMetadata().getTotalMatches();
                    vmListIntentResponse.getEntities().forEach(System.out::println);

                    vmListIntentResponse.getEntities()
                            .stream()
                            .filter(vm -> vm.getStatus().getResources().getPowerState().equalsIgnoreCase("off"))
                            .forEach(vm -> offVms.add(vm.getStatus().getName()));
                    vmListIntentResponse.getEntities()
                            .stream()
                            .filter(vm -> vm.getStatus().getResources().getPowerState().equalsIgnoreCase("on"))
                            .forEach(vm -> onVms.add(vm.getStatus().getName()));
                    vmListIntentResponse.getEntities()
                            .stream()
                            .filter(vm -> vm.getStatus().getState().equalsIgnoreCase("error"))
                            .forEach(errorVms::add);
                    vmListIntentResponse.getEntities().forEach(vm -> {
                        vm.getStatus().getResources().getDiskList().forEach(dsk -> diskType.add(dsk.getDeviceProperties().getDeviceType()));
                        vm.getStatus().getResources().getNicList().forEach(nic -> nicType.add(nic.getNicType()));
                        vmnames.add(vm.getStatus().getName());
                        stateVms.add((vm.getStatus().getState()));
                        clusteruuids.add(vm.getStatus().getClusterReference().getUuid());
                        clusternames.add(vm.getStatus().getClusterReference().getName());
                        clusterkinds.add(vm.getStatus().getClusterReference().getKind());
                        powerStateVms.add(vm.getStatus().getResources().getPowerState());
                    });

                    offset+=vmListIntentResponse.getEntities().size();
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            } while (vmnames.size() < total );
            System.out.println(stateVms);
            System.out.println(powerStateVms);
            System.out.println(clusterkinds);
            System.out.println(clusternames);
            System.out.println(clusteruuids);
            System.out.println(nicType);
            System.out.println(diskType);

            System.out.println("total vms: " + vmnames.size());
            System.out.println("error vms: " + errorVms.size());
            System.out.println("off vms: " + offVms.size());
            System.out.println("on vms: " + onVms.size());

            Assert.assertEquals(vmnames.size(),total);


    }
    @Test
    public void testVmsApiGetVm() throws NutanixApiException {
        ApiClientExtention apiClient = getApiClient();
        VmsApi vmsApi = new VmsApi(apiClient);
        String uuid = "d04f11c9-690b-4fa9-8d17-0c0de2e92c77";

        try {
            VmIntentResponse vmEntity = vmsApi.vmsUuidGet(uuid);
            Assert.assertNotNull(vmEntity);
            Assert.assertEquals(uuid,vmEntity.getMetadata().getUuid());
            Assert.assertEquals("SVL-TOMCATS2-AS2",vmEntity.getStatus().getName());
            System.out.println(uuid);
            System.out.println(vmEntity);
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }

    }

    @Test
    public void testHostApi() {

        HostsApi hostsApi = new HostsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> hostnames = new HashSet<>();
        Set<String> hyperConvergentHostTypes = new HashSet<>();
        Set<String> notHyperConvergentHostTypes = new HashSet<>();
        int total;

        do {
        try {
                HostListMetadata body = new HostListMetadata().length(lenght).offset(offset);
                HostListIntentResponse hostListIntentResponse = hostsApi.hostsListPost(body);
                total = hostListIntentResponse.getMetadata().getTotalMatches();

                hostListIntentResponse.getEntities().forEach(h -> hostnames.add(h.getStatus().getName()));
                hostListIntentResponse.getEntities().forEach(System.out::println);
                System.out.println(hostListIntentResponse.getMetadata());
                System.out.println(hostListIntentResponse.getApiVersion());
                hostListIntentResponse.getEntities()
                        .stream().filter(h -> h.getStatus().getResources().getHostType().equalsIgnoreCase("HYPER_CONVERGED"))
                        .forEach(h -> hyperConvergentHostTypes.add(h.getStatus().getName()));
                hostListIntentResponse.getEntities()
                        .stream().filter(h -> !h.getStatus().getResources().getHostType().equalsIgnoreCase("HYPER_CONVERGED"))
                        .forEach(h -> notHyperConvergentHostTypes.add(h.getStatus().getName()));
                lenght = hostListIntentResponse.getEntities().size();
                offset+=lenght;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        } while (hostnames.size() < total );

        System.out.println(hostnames);
        System.out.println("total hosts: " + hostnames.size());
        System.out.println("hyper_converged hosts: " + hyperConvergentHostTypes.size());
        System.out.println("not hyper_converged hosts: " + notHyperConvergentHostTypes.size());
        Assert.assertEquals(hostnames.size(),total);


    }

    @Test
    public void testVersionsApi() {
        VersionsApi versionApi = new VersionsApi(getApiClient());

        try {
            Versions versions= versionApi.versionsGet();
            System.out.println(versions);
            Assert.assertEquals("3.1", versions.getMajorVersion()+"."+versions.getMinorVersion());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAccessControlPoliciesApi() {
        AccessControlPoliciesApi accessControlPoliciesApi = new AccessControlPoliciesApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> policies = new HashSet<>();
        int total;
        do {
            try {
                AccessControlPolicyListMetadata body = new AccessControlPolicyListMetadata().length(lenght).offset(offset);

                AccessControlPolicyListIntentResponse accessControlPolicyListIntentResponse = accessControlPoliciesApi.accessControlPoliciesListPost(body);
                total = accessControlPolicyListIntentResponse.getMetadata().getTotalMatches();
                accessControlPolicyListIntentResponse.getEntities().forEach(policy -> policies.add(policy.getStatus().getName()));
                lenght = accessControlPolicyListIntentResponse.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        } while (policies.size() < total );

        System.out.println("total policies: " + policies.size());
        policies.forEach(System.out::println);
    }

    @Test
    public void testAlertsApi() {
        AlertsApi api = new AlertsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> alerts = new HashSet<>();
        int total;
        do {
            try {
                AlertListMetadata body = new AlertListMetadata().length(lenght).offset(offset);

                AlertListIntentResponse response = api.alertsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> alerts.add(item.toString()));
                response.getEntities().forEach(System.out::println);
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (alerts.size() < total );

        System.out.println("total : " + alerts.size());
        alerts.forEach(System.out::println);
    }

    @Test
    public void testAppBluePrintApi() {
        AppBlueprintApi api = new AppBlueprintApi(getApiClient());
            try {
                AppBlueprintRenderInfoInput body = new AppBlueprintRenderInfoInput();

                AppBlueprintRenderInfoOutput response = api.appBlueprintsRenderInfoPost(body);
                System.out.println(response.toString());
            } catch (ApiException e) {
                System.out.println(e.getMessage());
                Assert.assertEquals(403,e.getCode());
            }
    }

    @Test
    public void testBatchApi() {
        BatchApi api = new BatchApi(getApiClient());
        try {
            BatchRequest body = new BatchRequest();

            BatchResponse response = api.batchPost(body);
            System.out.println(response.toString());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCitrixAdaptersApi() {
        CitrixAdaptersApi api = new CitrixAdaptersApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                CitrixAdapterListMetadata body = new CitrixAdapterListMetadata().length(lenght).offset(offset);

                CitrixAdapterListIntentResponse response = api.citrixAdaptersListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testClustersApi() {
        ClustersApi api = new ClustersApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                ClusterListMetadata body = new ClusterListMetadata().length(lenght).offset(offset);

                ClusterListIntentResponse response = api.clustersListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                response.getEntities().forEach(item -> System.out.println(item.getStatus().getResources().getConfig().getOperationMode()));
                response.getEntities().forEach(item -> System.out.println(item.getStatus().getResources().getConfig().isIsAvailable()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testDataApi() {
        DataApi api = new DataApi(getApiClient());
        try {
            ChangedRegionsQuery body = new ChangedRegionsQuery().snapshotFilePath("/");

            ChangedRegions response = api.dataChangedRegionsPost(body);
            System.out.println(response.toString());
        } catch (ApiException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(400, e.getCode());
        }
    }


    @Test
    public void testDefaultApi() {
        DefaultApi api = new DefaultApi(getApiClient());
        System.out.println("This is for read and write directly from VM disks: Not supporting: " + api);
    }

    @Test
    public void testDirectoryServicesApi() {
        DirectoryServicesApi api = new DirectoryServicesApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                DirectoryServiceListMetadata body = new DirectoryServiceListMetadata().length(lenght).offset(offset);

                DirectoryServiceListIntentResponse response = api.directoryServicesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testDisksApi() {
        DisksApi api = new DisksApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                DiskListMetadata body = new DiskListMetadata().length(lenght).offset(offset);

                DiskListIntentResponse response = api.disksListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testDockerRegistryApi() {
        DockerRegistryApi api = new DockerRegistryApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                DockerRegistryListMetadata body = new DockerRegistryListMetadata().length(lenght).offset(offset);

                DockerRegistryListIntentResponse response = api.dockerRegistriesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                System.out.println(e.getMessage());
                Assert.assertEquals(403,e.getCode());
                total=-1;
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testExternalRepositoriesApi() {
        ExternalRepositoriesApi api = new ExternalRepositoriesApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                ExternalRepositoryListMetadata body = new ExternalRepositoryListMetadata().length(lenght).offset(offset);

                ExternalRepositoryListIntentResponse response = api.externalRepositoriesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testExternalRepositoryApi() {
        ExternalRepositoryApi api = new ExternalRepositoryApi(getApiClient());
        System.out.println("This is for create e delete external repository: Not supporting: " + api);
    }

    @Test
    public void testFileStoreApi() {
        FileStoreApi api = new FileStoreApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                FileItemListMetadata body = new FileItemListMetadata().length(lenght).offset(offset);

                FileItemListIntentResponse response = api.fileStoreListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testGroupsApi() {
        GroupsApi api = new GroupsApi(getApiClient());
        try {
            GroupsGetEntitiesRequest body = new GroupsGetEntitiesRequest().addEntityIdsItem("vm").addEntityIdsItem("host").addEntityIdsItem("disk");

            GroupsGetEntitiesResponse response = api.groupsPost(body);
            System.out.println(response.toString());
        } catch (ApiException e) {
            System.err.println(e.getMessage());
            Assert.assertEquals(500,e.getCode());
        }

    }

    @Test
    public void testIdempotenceIdentifiersApi() {
        IdempotenceIdentifiersApi api = new IdempotenceIdentifiersApi(getApiClient());
        try {
            api.idempotenceIdentifiersClientIdentifierGet("prova");
        } catch (ApiException e) {
            System.err.println(e.getMessage());
            Assert.assertEquals(404,e.getCode());
        }
        System.out.println("This is for get create or delete: Not supporting");
    }

    @Test
    public void testImagesApi() {
        ImagesApi api = new ImagesApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                ImageListMetadata body = new ImageListMetadata().length(lenght).offset(offset);

                ImageListIntentResponse response = api.imagesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testIpfixExporterApi() {
        IpfixExportersApi api = new IpfixExportersApi(getApiClient());
        System.out.println("This is for create or delete: Not supporting: "+ api);
    }

    @Test
    public void testMhVmsApi() {
        MhVmsApi api = new MhVmsApi(getApiClient());
        System.out.println("This is for create or update a request for create a VM: Not supporting: " + api);
    }

    @Test
    public void testNccApi() {
        NccApi api = new NccApi(getApiClient());
        try {
            NccTagsInput body = new NccTagsInput();

            NccTagsResponse response = api.nccTagsListPost(body.filter("acropolis"));
            System.out.println(response);
        } catch (ApiException e) {
            throw new RuntimeException();
        }

    }

    @Test
    public void testNetworkFunctionChainsApi() {
        NetworkFunctionChainsApi api = new NetworkFunctionChainsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                NetworkFunctionChainListMetadata body = new NetworkFunctionChainListMetadata().length(lenght).offset(offset);

                NetworkFunctionChainListIntentResponse response = api.networkFunctionChainsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                total=-1;
                System.out.println("Not Supported");
                System.err.println(e.getMessage());
                Assert.assertEquals(500,e.getCode());
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testOauthApi() {
        OauthApi api = new OauthApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                OauthClientListMetadata body = new OauthClientListMetadata().length(lenght).offset(offset);

                OauthClientList response = api.oauthClientListPost(body);
                total = response.getMetadata().getLength();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testNgtApi() {
        NgtApi api = new NgtApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                NgtListMetadata body = new NgtListMetadata().length(lenght).offset(offset);

                NgtListResponse response = api.ngtListPost(body);
                total = response.getMetadata().getTotalMatches();
                if (total == 0) {
                    break;
                }
                response.getEntitiesList().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntitiesList().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testOvasApi() {
        OvasApi api = new OvasApi(getApiClient());
        System.out.println("This is for create or update OVA file: Not supporting: " + api);
    }

    @Test
    public void testPermissionsApi() {
        PermissionsApi api = new PermissionsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                PermissionListMetadata body = new PermissionListMetadata().length(lenght).offset(offset);

                PermissionListIntentResponse response = api.permissionsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testPortalServicesApi() {
        PortalServicesApi api = new PortalServicesApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                SoftwareListMetadata body = new SoftwareListMetadata().length(lenght).offset(offset);

                SoftwareListIntentResponse response = api.portalServicesSoftwareSoftwareTypeListPost("Microsoft",body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                Assert.assertEquals(500,e.getCode());
                System.err.println(e.getMessage());
                total = -1;
                //what king of software is good
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testPrismCentralApi() {
        PrismCentralApi api = new PrismCentralApi(getApiClient());
        System.out.println("This is for create prism central: Not supporting: " + api);
    }

    @Test
    public void testProjectsApi() {
        ProjectsApi api = new ProjectsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                ProjectListMetadata body = new ProjectListMetadata().length(lenght).offset(offset);

                ProjectListIntentResponse response = api.projectsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testRackableUnitApi() {
        RackableUnitApi api = new RackableUnitApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RackableUnitListMetadata body = new RackableUnitListMetadata().length(lenght).offset(offset);

                RackableUnitListIntentResponse response = api.rackableUnitsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testRackApi() {
        RackApi api = new RackApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RackListMetadata body = new RackListMetadata().length(lenght).offset(offset);

                RackListIntentResponse response = api.racksListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testRemoteConnectionsApi() {
        RemoteConnectionsApi api = new RemoteConnectionsApi(getApiClient());
        System.out.println("This is for create use and manage remote connection proxy : Not supporting: " + api);
    }

    @Test
    public void testRemoteSyslogModulesApi() {
        RemoteSyslogModulesApi api = new RemoteSyslogModulesApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RemoteSyslogModuleListMetadata body = new RemoteSyslogModuleListMetadata().length(lenght).offset(offset);

                RemoteSyslogModuleListIntentResponse response = api.remoteSyslogModulesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testRemoteSyslogServersApi() {
        RemoteSyslogServersApi api = new RemoteSyslogServersApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RemoteSyslogServerListMetadata body = new RemoteSyslogServerListMetadata().length(lenght).offset(offset);

                RemoteSyslogServerListIntentResponse response = api.remoteSyslogServersListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testRolesApi() {
        RolesApi api = new RolesApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RoleListMetadata body = new RoleListMetadata().length(lenght).offset(offset);

                RoleListIntentResponse response = api.rolesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testSshUserApi() {
        SshUserApi api = new SshUserApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                SshUserListMetadata body = new SshUserListMetadata().length(lenght).offset(offset);

                SshUserList response = api.sshUserListPost(body);
                total = response.getMetadata().getLength();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testSubnetsApi() {
        SubnetsApi api = new SubnetsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                SubnetListMetadata body = new SubnetListMetadata().length(lenght).offset(offset);

                SubnetListIntentResponse response = api.subnetsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }
    @Test
    public void testTasksApi() {
        TasksApi api = new TasksApi(getApiClient());
        Set<String> outputs = new HashSet<>();
        int total;
        try {
            TaskListMetadata body = new TaskListMetadata().length(20).offset(0);

            TaskListIntentResponse response = api.tasksListPost(body);
            total = response.getMetadata().getTotalMatches();
            response.getEntities().forEach(item -> outputs.add(item.toString()));
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        System.out.println("total outputs: " + total);
        outputs.forEach(System.out::println);
    }

    @Test
    public void testTenantsApi() {
        TenantsApi api = new TenantsApi(getApiClient());
        System.out.println("This is for create tenants : Not supporting: " + api);
    }

    @Test
    public void testUserGroupsApi() {
        UserGroupsApi api = new UserGroupsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                UserGroupListMetadata body = new UserGroupListMetadata().length(lenght).offset(offset);

                UserGroupListIntentResponse response = api.userGroupsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset += lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );
        System.out.println("total outputs: " + total);
        outputs.forEach(System.out::println);
    }

    @Test
    public void testUsersApi() {
        UsersApi api = new UsersApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
        try {
            UserListMetadata body = new UserListMetadata().length(lenght).offset(offset);

            UserListIntentResponse response = api.usersListPost(body);
            total = response.getMetadata().getTotalMatches();
            response.getEntities().forEach(item -> outputs.add(item.toString()));
            lenght = response.getEntities().size();
            offset+=lenght;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        } while (outputs.size() < total );


        System.out.println("total outputs: " + total);
        outputs.forEach(System.out::println);
    }

    @Test
    public void testVmRecoveryPointsApi() {
        VmRecoveryPointsApi api = new VmRecoveryPointsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
        try {
            VmRecoveryPointListMetadata body = new VmRecoveryPointListMetadata().length(lenght).offset(offset);

            VmRecoveryPointListIntentResponse response = api.vmRecoveryPointsListPost(body);
            total = response.getMetadata().getTotalMatches();
            response.getEntities().forEach(item -> outputs.add(item.toString()));
            lenght = response.getEntities().size();
            offset+=lenght;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + total);
        outputs.forEach(System.out::println);
    }

    @Test
    public void testVmSnapshotApi() {
        VmSnapshotApi api = new VmSnapshotApi(getApiClient());
        System.out.println("This is for create get delete vm snapshot : Not supporting: " + api);
    }

    @Test
    public void testVolumeGroupsApi() {
        VolumeGroupsApi api = new VolumeGroupsApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                VolumeGroupListMetadata body = new VolumeGroupListMetadata().length(lenght).offset(offset);

                VolumeGroupListIntentResponse response = api.volumeGroupsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testVolumeGroupSnapshotApi() {
        VolumeGroupSnapshotsApi api = new VolumeGroupSnapshotsApi(getApiClient());
        System.out.println("This is for create get delete volume group snapshot : Not supporting: " + api);
    }

    @Test
    public void testWebhooksApi() {
        WebhooksApi api = new WebhooksApi(getApiClient());
        int offset = 0;
        int lenght = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                WebhookListMetadata body = new WebhookListMetadata().length(lenght).offset(offset);

                WebhookListIntentResponse response = api.webhooksListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                lenght = response.getEntities().size();
                offset+=lenght;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

}
