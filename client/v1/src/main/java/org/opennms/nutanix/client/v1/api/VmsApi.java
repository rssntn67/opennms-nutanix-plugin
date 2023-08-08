package org.opennms.nutanix.client.v1.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;

import org.opennms.nutanix.client.v1.ApiClientExtention;
import org.opennms.nutanix.client.v1.handler.ApiException;
import org.opennms.nutanix.client.v1.handler.Pair;
import org.opennms.nutanix.client.v1.model.VMEntity;
import org.opennms.nutanix.client.v1.model.VMCollectionEntity;

public class VmsApi {
    private final ApiClientExtention apiClient;

    public VmsApi(ApiClientExtention apiClient) {
        this.apiClient = apiClient;
    }

    public VMEntity getVM(String uuid) throws ApiException {
        String localVarPath = "/vms/"+uuid;
        List<Pair> localVarQueryParams = new ArrayList<>();
        Map<String, String> localVarHeaderParams = new HashMap<>();

        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        GenericType<VMEntity> localVarReturnType = new GenericType<>() {};

        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, null, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);


    }
    public VMCollectionEntity getVMs(int page, int count) throws ApiException {

        String localVarPath = "/vms/";

        // query params
        List<Pair> localVarQueryParams = new ArrayList<>();
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "count", count));
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", page));
        Map<String, String> localVarHeaderParams = new HashMap<>();

        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        GenericType<VMCollectionEntity> localVarReturnType = new GenericType<>() {};

        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, null, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);

    }

}
