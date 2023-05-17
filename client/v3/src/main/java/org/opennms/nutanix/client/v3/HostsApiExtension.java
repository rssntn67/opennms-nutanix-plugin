package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;

import org.opennms.nutanix.client.v3.api.HostsApi;
import org.opennms.nutanix.client.v3.handler.ApiClient;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.handler.Pair;
import org.opennms.nutanix.client.v3.model.HostListIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListMetadata;

public class HostsApiExtension extends HostsApi {
  public HostsApiExtension(ApiClient apiClient) {
    super(apiClient);
  }

  /**
   * Get a list of existing Hosts
   * This operation gets a list of Hosts, allowing for sorting and pagination. Note: Entities that have not been created successfully are not listed.
   * @param body  (required)
   * @return HostListIntentResponse
   * @throws ApiException if fails to make API call
   */
  @Override
  public HostListIntentResponse hostsListPost(HostListMetadata body) throws ApiException {
    Object localVarPostBody = body;
    ApiClient apiClient = getApiClient();
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling hostsListPost");
    }
    // create path and map variables
    String localVarPath = "/hosts/list";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
            "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
            "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basicAuth" };

    GenericType<HostListIntentResponse> localVarReturnType = new GenericType<HostListIntentResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
