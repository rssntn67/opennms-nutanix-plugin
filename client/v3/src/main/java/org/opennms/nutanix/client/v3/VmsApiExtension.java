package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;

import org.opennms.nutanix.client.v3.handler.ApiClient;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.handler.Configuration;
import org.opennms.nutanix.client.v3.handler.Pair;
import org.opennms.nutanix.client.v3.model.CapabilityInformation;
import org.opennms.nutanix.client.v3.model.CapabilityResponse;
import org.opennms.nutanix.client.v3.model.ProceduralResponse;
import org.opennms.nutanix.client.v3.model.VmIntentInput;
import org.opennms.nutanix.client.v3.model.VmIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListMetadata;
import org.opennms.nutanix.client.v3.model.VmRevertInput;
import org.opennms.nutanix.client.v3.model.VmSnapshotInput;

public class VmsApiExtension extends org.opennms.nutanix.client.v3.api.VmsApi {
  public VmsApiExtension(ApiClient apiClient) {
    super(apiClient);
  }

  /**
   * Get a list of existing VMs
   * This operation gets a list of VMs, allowing for sorting and pagination. Note: Entities that have not been created successfully are not listed. 
   * @param body  (required)
   * @return VmListIntentResponse
   * @throws ApiException if fails to make API call
   */
  @Override
  public VmListIntentResponse vmsListPost(VmListMetadata body) throws ApiException {
    Object localVarPostBody = body;
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling vmsListPost");
    }
    // create path and map variables
    String localVarPath = "/vms/list";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();



    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = getApiClient().selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = getApiClient().selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basicAuth" };

    GenericType<VmListIntentResponse> localVarReturnType = new GenericType<VmListIntentResponse>() {};
    return getApiClient().invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
