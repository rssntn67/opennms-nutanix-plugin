package org.opennms.nutanix.client.v3;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class ApiClientExtention extends org.opennms.nutanix.client.v3.handler.ApiClient {


    private boolean ignoreSslCertificateValidation=false;
    private int length = 20;

    public void setIgnoreSslCertificateValidation(boolean ignoreSslCertificateValidation) {
        this.ignoreSslCertificateValidation = ignoreSslCertificateValidation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    protected void ignoreCertificateValididation(ClientBuilder clientBuilder) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[] {};
                        }
                    }
            }, null);
        } catch (NoSuchAlgorithmException e) {
            //logger.debug("Ignoring 'NoSuchAlgorithmException' while ignoring ssl certificate validation.");
        } catch (KeyManagementException e) {
            //logger.debug("Ignoring 'KeyManagementException' while ignoring ssl certificate validation.");
        }

        clientBuilder.sslContext(sslContext);
        clientBuilder.hostnameVerifier(
                (hostname, sslSession) -> true);
        }


    /**
     * Build the Client used to make HTTP requests.
     * @param debugging Debug setting
     * @return Client
     */
    protected Client buildHttpClient(boolean debugging) {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(MultiPartFeature.class);
        clientConfig.register(getJSON());
        clientConfig.register(JacksonFeature.class);
        clientConfig.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        if (debugging) {
            clientConfig.register(new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), java.util.logging.Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 1024*50 /* Log payloads up to 50K */));
            clientConfig.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY, LoggingFeature.Verbosity.PAYLOAD_ANY);
            // Set logger to ALL
            java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME).setLevel(java.util.logging.Level.ALL);
        }
        ClientBuilder builder =  ClientBuilder.newBuilder();
        if (ignoreSslCertificateValidation)
            ignoreCertificateValididation(builder);
        performAdditionalClientConfiguration(clientConfig);
        builder.withConfig(clientConfig);
        return builder.build();
    }

    @Override
    public String selectHeaderContentType(String[] contentTypes) {
        if (contentTypes.length == 0 || contentTypes[0].equals("*/*")) {
            return "application/json";
        }
        for (String contentType : contentTypes) {
            if (isJsonMime(contentType)) {
                return contentType;
            }
        }
        return contentTypes[0];
    }

}
