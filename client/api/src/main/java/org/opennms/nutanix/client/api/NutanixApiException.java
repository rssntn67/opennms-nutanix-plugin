package org.opennms.nutanix.client.api;

import java.util.List;
import java.util.Map;

public class NutanixApiException extends Exception {

    private int code = 0;
    private Map<String, List<String>> responseHeaders = null;
    private String responseBody = null;

    public NutanixApiException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable);
        this.code = code;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (code != 0) {
            StringBuilder sb = new StringBuilder();
            if (msg != null && !msg.isEmpty()) sb.append(msg).append(" ");
            sb.append("[HTTP ").append(code).append("]");
            if (responseBody != null && !responseBody.isEmpty()) sb.append(" ").append(responseBody);
            return sb.toString();
        }
        return msg;
    }

    public NutanixApiException(String message, Throwable throwable) {
        super(message,throwable);
    }

    /**
     * Get the HTTP status code.
     *
     * @return HTTP status code
     */
    public int getCode() {
        return code;
    }

    /**
     * Get the HTTP response headers.
     *
     * @return A map of list of string
     */
    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * Get the HTTP response body.
     *
     * @return Response body in the form of string
     */
    public String getResponseBody() {
        return responseBody;
    }
}
