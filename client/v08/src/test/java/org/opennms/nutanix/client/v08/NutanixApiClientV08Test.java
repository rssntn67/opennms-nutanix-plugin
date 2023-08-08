package org.opennms.nutanix.client.v08;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.v08.api.HaApi;
import org.opennms.nutanix.client.v08.api.ImagesApi;
import org.opennms.nutanix.client.v08.api.NetworksApi;
import org.opennms.nutanix.client.v08.api.SnapshotsApi;
import org.opennms.nutanix.client.v08.api.TasksApi;
import org.opennms.nutanix.client.v08.api.VdisksApi;
import org.opennms.nutanix.client.v08.api.VmsApi;
import org.opennms.nutanix.client.v08.api.VolumeGroupsApi;
import org.opennms.nutanix.client.v08.handler.ApiClient;
import org.opennms.nutanix.client.v08.handler.ApiException;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisImageInfoDTOgt;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisNetworkConfigDTOgt;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisSnapshotInfoDTOgt;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisTasksTaskDTOgt;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisVMInfoDTOgt;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoNdfsFileDTOgt;
import org.opennms.nutanix.client.v08.model.GetDtoAcropolisHaConfigDTO;
import org.opennms.nutanix.client.v08.model.GetDtoAcropolisVolumegroupsVolumeGroupConfigDTO;

public class NutanixApiClientV08Test {

    private ApiClient getApiClient() {

        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/api/nutanix/v0.8");
        String auth = System.getenv("NTX_USER") + ":" + System.getenv("NTX_PASS");
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
        apiClient.setDebugging(true);
        apiClient.setIgnoreSslCertificateValidation(true);


        return apiClient;

    }


    @Test
    public void testEnvVariable() {
        Assert.assertNotNull(System.getenv("NTX_USER"));
        Assert.assertNotNull(System.getenv("NTX_PASS"));
        System.out.println(System.getenv("NTX_USER"));
        System.out.println(System.getenv("NTX_PASS"));

    }

    @Test
    public void testVmsApi()  {

        VmsApi vmsApi = new VmsApi(getApiClient());

        try {
            GetBaseEntityCollectionltgetDtoAcropolisVMInfoDTOgt dto = vmsApi.getVMs(true,true,true);
            System.out.println(dto.getMetadata());
            dto.getEntities().forEach(e -> System.out.println(e.getUuid()+ ":" +e.getState() +":" + e.getHostUuid()));
            dto.getEntities().forEach(e -> System.out.println(e.getConfig()));
            System.out.println(dto.getEntities().size());
            Assert.assertEquals(dto.getMetadata().getTotalEntities().intValue(), dto.getEntities().size());
        } catch (ApiException e) {
            System.out.println("err");
        }
    }

    @Test
    public void testHaApi()  {

        HaApi haApi = new HaApi(getApiClient());
        try {
           GetDtoAcropolisHaConfigDTO haConfig = haApi.getHaConfig();
            System.out.println(haConfig);
        } catch (ApiException e) {
            System.out.println("err");
        }
    }


    @Test
    public void testImagesApi()  {

        //OS Images stored on Nutanix Cluster
        ImagesApi imagesApi = new ImagesApi(getApiClient());
        try {
            GetBaseEntityCollectionltgetDtoAcropolisImageInfoDTOgt imagesCollection = imagesApi.getImages(true,true);
            System.out.println(imagesCollection.getMetadata());
            imagesCollection.getEntities().forEach(System.out::println);
        } catch (ApiException e) {
            System.out.println("err");
        }
    }

    @Test
    public void testNetworksApi()  {

        //OS Images stored on Nutanix Cluster
        NetworksApi networksApi = new NetworksApi(getApiClient());
        try {
            GetBaseEntityCollectionltgetDtoAcropolisNetworkConfigDTOgt networksConfig = networksApi.getNetworks();
            System.out.println(networksConfig.getMetadata());
            networksConfig.getEntities().forEach(System.out::println);
        } catch (ApiException e) {
            System.out.println("err");
        }
    }

    @Test
    public void testSnapshotsApi()  {

        //snapshots stored on Nutanix Cluster
        SnapshotsApi snapshotsApi = new SnapshotsApi(getApiClient());
        try {
            GetBaseEntityCollectionltgetDtoAcropolisSnapshotInfoDTOgt snapshots = snapshotsApi.getSnapshots();
            System.out.println(snapshots.getMetadata());
            snapshots.getEntities().forEach(System.out::println);
        } catch (ApiException e) {
            System.out.println("err");
        }
    }

    @Test
    public void testTasksApi()  {

        //tasks not using
        TasksApi tasksApi = new TasksApi(getApiClient());
        try {
            GetBaseEntityCollectionltgetDtoAcropolisTasksTaskDTOgt tasks = tasksApi.getTasks("VM",null,null, null, null,10, true);
            System.out.println(tasks.getMetadata());
            tasks.getEntities().forEach(System.out::println);
        } catch (ApiException e) {
            System.out.println("err");
        }
    }

    @Test
    public void testVdisksApi()  {

        //Vdisks not using because want to mount the vdisk
        VdisksApi vdisksApi = new VdisksApi(getApiClient());
        try {
            GetBaseEntityCollectionltgetDtoNdfsFileDTOgt vdisks = vdisksApi.getVdisks("/SelfServiceContainer/.acropolis/vmdisk/30c8f253-c410-4641-9141-3f3c27ae7600");
            System.out.println(vdisks.getMetadata());
            vdisks.getEntities().forEach(System.out::println);
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testVolumeGroupApi()  {

        //tasks not using
        VolumeGroupsApi volumeGroupsApi = new VolumeGroupsApi(getApiClient());
        try {
            List<GetDtoAcropolisVolumegroupsVolumeGroupConfigDTO>  vgs = volumeGroupsApi.getVolumeGroups(true,true);
            vgs.forEach(System.out::println);
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
    }

}
