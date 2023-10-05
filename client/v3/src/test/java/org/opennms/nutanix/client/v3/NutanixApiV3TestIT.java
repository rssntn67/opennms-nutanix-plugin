package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.model.Entity;
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
import org.opennms.nutanix.client.v3.model.ClusterIntentResponse;
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
import org.opennms.nutanix.client.v3.model.VmListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListMetadata;
import org.opennms.nutanix.client.v3.model.VmRecoveryPointListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmRecoveryPointListMetadata;
import org.opennms.nutanix.client.v3.model.VolumeGroupListIntentResponse;
import org.opennms.nutanix.client.v3.model.VolumeGroupListMetadata;
import org.opennms.nutanix.client.v3.model.WebhookListIntentResponse;
import org.opennms.nutanix.client.v3.model.WebhookListMetadata;

public class NutanixApiV3TestIT {

    private ApiClientExtension getApiClient() {
        ApiClientExtension apiClient = new ApiClientExtension();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/api/nutanix/v3");
        apiClient.setUsername(System.getenv("NTX_USER"));
        apiClient.setPassword(System.getenv("NTX_PASS"));
        apiClient.setDebugging(true);
        apiClient.setIgnoreSslCertificateValidation(true);
        apiClient.setPageSize(20);
        return apiClient;

    }

    @Test
    public void testEnvVariable() {
        Assert.assertNotNull(System.getenv("NTX_USER"));
        Assert.assertNotNull(System.getenv("NTX_PASS"));
    }

    @Test
    public void testVmsWithFilterApi() {

        ApiClientExtension apiClient = getApiClient();
        VmsApi vmsApi = new VmsApi(apiClient);
        int offset = 0;
        try {
            VmListMetadata body = new VmListMetadata().length(apiClient.getPageSize()).offset(offset).filter("vm_name==ELEWEB-ASTEST");
            VmListIntentResponse vmListIntentResponse = vmsApi.vmsListPost(body);
            System.out.println(vmListIntentResponse);
        }  catch (ApiException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    public void testVmsApi() {

        ApiClientExtension apiClient = getApiClient();
        VmsApi vmsApi = new VmsApi(apiClient);
        int offset = 0;
        Set<String> vmNames = new HashSet<>();
        Set<String> onVms = new HashSet<>();
        Set<String> offVms = new HashSet<>();
        Set<String> stateVms = new HashSet<>();
        Set<String> powerStateVms = new HashSet<>();
        Set<String> clusterUuids = new HashSet<>();
        Set<String> clusterNames = new HashSet<>();
        Set<String> clusterKinds = new HashSet<>();
        Set<String> nicType = new HashSet<>();
        Set<String> diskType = new HashSet<>();
        List<VmIntentResource> vmWithoutHostReference = new ArrayList<>();
        List<VmIntentResource> errorVms = new ArrayList<>();
        int total;
            do {
                VmListMetadata body = new VmListMetadata().length(apiClient.getPageSize()).offset(offset);
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
                        vmNames.add(vm.getStatus().getName());
                        stateVms.add((vm.getStatus().getState()));
                        clusterUuids.add(vm.getStatus().getClusterReference().getUuid());
                        clusterNames.add(vm.getStatus().getClusterReference().getName());
                        clusterKinds.add(vm.getStatus().getClusterReference().getKind());
                        powerStateVms.add(vm.getStatus().getResources().getPowerState());
                    });

                    vmListIntentResponse.getEntities().stream().filter(vm -> vm.getStatus().getResources().getHostReference() == null).forEach(vmWithoutHostReference::add);
                    offset+=vmListIntentResponse.getEntities().size();
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            } while (vmNames.size() < total );
            System.out.println("----------------------------------");

            vmWithoutHostReference.forEach(vm -> Assert.assertEquals("OFF", vm.getStatus().getResources().getPowerState()));
            System.out.println("----------------------------------");
            System.out.println("no host vms: " + vmWithoutHostReference.size());
            System.out.println("total vms: " + vmNames.size());
            System.out.println("error vms: " + errorVms.size());
            System.out.println("off vms: " + offVms.size());
            System.out.println("on vms: " + onVms.size());
            System.out.println("----------------------------------");
            System.out.println(stateVms);
            System.out.println(powerStateVms);
            System.out.println(clusterKinds);
            System.out.println(clusterNames);
            System.out.println(clusterUuids);
            System.out.println(nicType);
            System.out.println(diskType);


        Assert.assertEquals(vmNames.size(),total);


    }

    @Test
    public void testHostApi() {

        HostsApi hostsApi = new HostsApi(getApiClient());
        int offset = 0;
        int length = 20;
        Set<String> hostNames = new HashSet<>();
        Set<String> hostUuids= new HashSet<>();
        Set<String> stateHost = new HashSet<>();
        Set<String> hyperConvergentHostTypes = new HashSet<>();
        Set<String> notHyperConvergentHostTypes = new HashSet<>();
        int total;

        do {
        try {
                HostListMetadata body = new HostListMetadata().length(length).offset(offset);
                HostListIntentResponse hostListIntentResponse = hostsApi.hostsListPost(body);
                total = hostListIntentResponse.getMetadata().getTotalMatches();

                hostListIntentResponse.getEntities().forEach(h -> {
                    hostNames.add(h.getStatus().getName());
                    hostUuids.add(h.getMetadata().getUuid());
                });
                hostListIntentResponse.getEntities().forEach(System.out::println);
                System.out.println(hostListIntentResponse.getMetadata());
                System.out.println(hostListIntentResponse.getApiVersion());
                hostListIntentResponse.getEntities()
                        .stream().filter(h -> h.getStatus().getResources().getHostType().equalsIgnoreCase("HYPER_CONVERGED"))
                        .forEach(h -> hyperConvergentHostTypes.add(h.getStatus().getName()));
                hostListIntentResponse.getEntities()
                        .stream().filter(h -> !h.getStatus().getResources().getHostType().equalsIgnoreCase("HYPER_CONVERGED"))
                        .forEach(h -> notHyperConvergentHostTypes.add(h.getStatus().getName()));
                length = hostListIntentResponse.getEntities().size();
                hostListIntentResponse.getEntities().forEach(h -> stateHost.add(h.getStatus().getState()));
                offset+=length;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        } while (hostNames.size() < total );

        System.out.println(hostNames);
        System.out.println(hostUuids);
        System.out.println(stateHost);
        System.out.println("total hosts: " + hostNames.size());
        System.out.println("hyper_converged hosts: " + hyperConvergentHostTypes.size());
        System.out.println("not hyper_converged hosts: " + notHyperConvergentHostTypes.size());
        Assert.assertEquals(hostNames.size(),total);


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
        int length = 20;
        Set<String> policies = new HashSet<>();
        int total;
        do {
            try {
                AccessControlPolicyListMetadata body = new AccessControlPolicyListMetadata().length(length).offset(offset);

                AccessControlPolicyListIntentResponse accessControlPolicyListIntentResponse = accessControlPoliciesApi.accessControlPoliciesListPost(body);
                total = accessControlPolicyListIntentResponse.getMetadata().getTotalMatches();
                accessControlPolicyListIntentResponse.getEntities().forEach(policy -> policies.add(policy.getStatus().getName()));
                length = accessControlPolicyListIntentResponse.getEntities().size();
                offset+=length;
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
        int length = 30;
        int total;
        Map<String, Set<String>> severityTypeMap = new HashMap<>();
        Map<String, Set<String>> typeSeverityMap = new HashMap<>();
        Map<String, Set<String>> typeDefaultMessageMap = new HashMap<>();
        Set<String> alarmUuids = new HashSet<>();
        do {
            try {
                AlertListMetadata body = new AlertListMetadata().length(length).offset(offset);

                AlertListIntentResponse response = api.alertsListPost(body);
                total = response.getMetadata().getTotalMatches();
                System.out.println(response.getMetadata());
                response.getEntities().forEach(item -> {
                    String alarmUuid = item.getMetadata().getUuid();
                    alarmUuids.add(alarmUuid);
                    String name = item.getMetadata().getName();
                    Assert.assertNull(name);
                    String severity = item.getStatus().getResources().getSeverity();
                    String alertType = item.getStatus().getResources().getType();
                    System.out.println(item.getStatus().getResources().getCreationTime());
                    System.out.println(item.getStatus().getResources().getLastUpdateTime());
                    System.out.println(item.getStatus().getResources().getLatestOccurrenceTime());

                    if (!severityTypeMap.containsKey(severity)) {
                        severityTypeMap.put(severity, new HashSet<>());
                    }
                    severityTypeMap.get(severity).add(alertType);

                    if (!typeSeverityMap.containsKey(alertType)) {
                        typeSeverityMap.put(alertType, new HashSet<>());
                    }
                    typeSeverityMap.get(alertType).add(severity);

                    String dm = item.getStatus().getResources().getDefaultMessage();
                    if (!typeDefaultMessageMap.containsKey(alertType)) {
                        typeDefaultMessageMap.put(alertType,new HashSet<>());
                    }
                    typeDefaultMessageMap.get(alertType).add(dm);
                    System.out.println("----------");
                    System.out.println(alarmUuid);
                    System.out.println(severity);
                    System.out.println(alertType);
                    System.out.println(V3ApiClientService.getDescrFromAlert(item));
                    if (item.getStatus().getResources().getResolutionStatus().isIsTrue()) {
                        System.out.println("--Resolved--");
                    }
                    Assert.assertEquals(1,item.getStatus().getResources().getAffectedEntityList().size());
                    item.getStatus().getResources().getAffectedEntityList().forEach(entityInfo -> {
                        System.out.println("----entity affected------");
                        System.out.println(entityInfo.getName());
                        System.out.println(entityInfo.getUuid());
                        System.out.println(entityInfo.getType());
                        Assert.assertNotNull(V3ApiClientService.TYPE_MAP.get(entityInfo.getType()));
                        Entity entity = new Entity(
                                "COMPLETE",
                                entityInfo.getName(),
                                entityInfo.getName(),
                                V3ApiClientService.TYPE_MAP.get(entityInfo.getType()));
                        Assert.assertNotNull(entity);
                    });
                    if (item.getStatus().getResources().getAcknowledgedStatus().isIsTrue()) {
                        System.out.println("--Ack--");
                    }

                });
                offset+=response.getEntities().size();
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (offset < total );

        System.out.println(severityTypeMap);
        System.out.println(typeSeverityMap);
        typeSeverityMap.values().forEach(set -> Assert.assertEquals(1,set.size()));
        System.out.println(typeDefaultMessageMap);
        typeDefaultMessageMap.values().forEach(set -> Assert.assertEquals(1,set.size()));
        Assert.assertEquals(total, alarmUuids.size());
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                CitrixAdapterListMetadata body = new CitrixAdapterListMetadata().length(length).offset(offset);

                CitrixAdapterListIntentResponse response = api.citrixAdaptersListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        int total;
        do {
            try {
                ClusterListMetadata body = new ClusterListMetadata().length(length).offset(offset);
                ClusterListIntentResponse response = api.clustersListPost(body);
                System.out.println(response);
                total = response.getMetadata().getTotalMatches();
                length = response.getEntities().size();
                offset+=length;
                response.getEntities().forEach( e -> System.out.println(e.getStatus().getResources().getConfig().getEnabledFeatureList()));
                response.getEntities().forEach( e -> System.out.println(e.getStatus().getState()));
                response.getEntities().forEach( e -> System.out.println(e.getStatus().getResources().getConfig().isIsAvailable()));
                response.getEntities().forEach( e -> System.out.println(e.getStatus().getResources().getConfig().getOperationMode()));
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (offset < total );

    }

    @Test
    public void testClustersApiGet() {
        ClustersApi api = new ClustersApi(getApiClient());
        try {
            ClusterIntentResponse response = api.clustersUuidGet("00059dd3-26be-0d72-5228-ac1f6b357222");
            System.out.println(response);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                DirectoryServiceListMetadata body = new DirectoryServiceListMetadata().length(length).offset(offset);

                DirectoryServiceListIntentResponse response = api.directoryServicesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                DiskListMetadata body = new DiskListMetadata().length(length).offset(offset);

                DiskListIntentResponse response = api.disksListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                DockerRegistryListMetadata body = new DockerRegistryListMetadata().length(length).offset(offset);

                DockerRegistryListIntentResponse response = api.dockerRegistriesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                ExternalRepositoryListMetadata body = new ExternalRepositoryListMetadata().length(length).offset(offset);

                ExternalRepositoryListIntentResponse response = api.externalRepositoriesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                FileItemListMetadata body = new FileItemListMetadata().length(length).offset(offset);

                FileItemListIntentResponse response = api.fileStoreListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                length = response.getEntities().size();
                offset+=length;
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
            api.idempotenceIdentifiersClientIdentifierGet("test");
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                ImageListMetadata body = new ImageListMetadata().length(length).offset(offset);

                ImageListIntentResponse response = api.imagesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                length = response.getEntities().size();
                offset+=length;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testIpFixExporterApi() {
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                NetworkFunctionChainListMetadata body = new NetworkFunctionChainListMetadata().length(length).offset(offset);

                NetworkFunctionChainListIntentResponse response = api.networkFunctionChainsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.getStatus().toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                OauthClientListMetadata body = new OauthClientListMetadata().length(length).offset(offset);

                OauthClientList response = api.oauthClientListPost(body);
                total = response.getMetadata().getLength();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                NgtListMetadata body = new NgtListMetadata().length(length).offset(offset);

                NgtListResponse response = api.ngtListPost(body);
                total = response.getMetadata().getTotalMatches();
                if (total == 0) {
                    break;
                }
                response.getEntitiesList().forEach(item -> outputs.add(item.toString()));
                length = response.getEntitiesList().size();
                offset+=length;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testOvaSApi() {
        OvasApi api = new OvasApi(getApiClient());
        System.out.println("This is for create or update OVA file: Not supporting: " + api);
    }

    @Test
    public void testPermissionsApi() {
        PermissionsApi api = new PermissionsApi(getApiClient());
        int offset = 0;
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                PermissionListMetadata body = new PermissionListMetadata().length(length).offset(offset);

                PermissionListIntentResponse response = api.permissionsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                SoftwareListMetadata body = new SoftwareListMetadata().length(length).offset(offset);

                SoftwareListIntentResponse response = api.portalServicesSoftwareSoftwareTypeListPost("Microsoft",body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                ProjectListMetadata body = new ProjectListMetadata().length(length).offset(offset);

                ProjectListIntentResponse response = api.projectsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

    @Test
    public void testRackAbleUnitApi() {
        RackableUnitApi api = new RackableUnitApi(getApiClient());
        int offset = 0;
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RackableUnitListMetadata body = new RackableUnitListMetadata().length(length).offset(offset);

                RackableUnitListIntentResponse response = api.rackableUnitsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RackListMetadata body = new RackListMetadata().length(length).offset(offset);

                RackListIntentResponse response = api.racksListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RemoteSyslogModuleListMetadata body = new RemoteSyslogModuleListMetadata().length(length).offset(offset);

                RemoteSyslogModuleListIntentResponse response = api.remoteSyslogModulesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RemoteSyslogServerListMetadata body = new RemoteSyslogServerListMetadata().length(length).offset(offset);

                RemoteSyslogServerListIntentResponse response = api.remoteSyslogServersListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                RoleListMetadata body = new RoleListMetadata().length(length).offset(offset);

                RoleListIntentResponse response = api.rolesListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                SshUserListMetadata body = new SshUserListMetadata().length(length).offset(offset);

                SshUserList response = api.sshUserListPost(body);
                total = response.getMetadata().getLength();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                SubnetListMetadata body = new SubnetListMetadata().length(length).offset(offset);

                SubnetListIntentResponse response = api.subnetsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                UserGroupListMetadata body = new UserGroupListMetadata().length(length).offset(offset);

                UserGroupListIntentResponse response = api.userGroupsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset += length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
        try {
            UserListMetadata body = new UserListMetadata().length(length).offset(offset);

            UserListIntentResponse response = api.usersListPost(body);
            total = response.getMetadata().getTotalMatches();
            response.getEntities().forEach(item -> outputs.add(item.toString()));
            length = response.getEntities().size();
            offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
        try {
            VmRecoveryPointListMetadata body = new VmRecoveryPointListMetadata().length(length).offset(offset);

            VmRecoveryPointListIntentResponse response = api.vmRecoveryPointsListPost(body);
            total = response.getMetadata().getTotalMatches();
            response.getEntities().forEach(item -> outputs.add(item.toString()));
            length = response.getEntities().size();
            offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                VolumeGroupListMetadata body = new VolumeGroupListMetadata().length(length).offset(offset);

                VolumeGroupListIntentResponse response = api.volumeGroupsListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
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
        int length = 20;
        Set<String> outputs = new HashSet<>();
        int total;
        do {
            try {
                WebhookListMetadata body = new WebhookListMetadata().length(length).offset(offset);

                WebhookListIntentResponse response = api.webhooksListPost(body);
                total = response.getMetadata().getTotalMatches();
                response.getEntities().forEach(item -> outputs.add(item.toString()));
                length = response.getEntities().size();
                offset+=length;
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        } while (outputs.size() < total );

        System.out.println("total outputs: " + outputs.size());
        outputs.forEach(System.out::println);
    }

   @Test
   public void testMatcherGraphs() {
       List<String> matchList = new ArrayList<>();
       Pattern regex = Pattern.compile("\\{(.*?)\\}");
       Matcher regexMatcher = regex.matcher("Hello This is {Java} Not {.NET}");

       while (regexMatcher.find()) {//Finds Matching Pattern in String
           matchList.add(regexMatcher.group(1));//Fetching Group from String
       }

       for(String str:matchList) {
           System.out.println(str);
       }
   }
}
