package org.opennms.nutanix.requisition;

import java.util.Map;

import org.opennms.integration.api.v1.config.requisition.Requisition;
import org.opennms.integration.api.v1.requisition.RequisitionProvider;
import org.opennms.integration.api.v1.requisition.RequisitionRequest;

public class NutanixRequisitionProvider implements RequisitionProvider {

    public final static String TYPE = "nutanix";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public RequisitionRequest getRequest(Map<String, String> map) {
        return null;
    }

    @Override
    public Requisition getRequisition(RequisitionRequest requisitionRequest) {
        return null;
    }

    @Override
    public byte[] marshalRequest(RequisitionRequest requisitionRequest) {
        return new byte[0];
    }

    @Override
    public RequisitionRequest unmarshalRequest(byte[] bytes) {
        return null;
    }
}
