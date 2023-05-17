package org.opennms.nutanix.client.v3;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
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
    protected void performAdditionalClientConfiguration(ClientBuilder clientBuilder) {
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
                new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession sslSession) {
                        return true;
                    }
                });
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
        performAdditionalClientConfiguration(builder);
        performAdditionalClientConfiguration(clientConfig);
        builder.withConfig(clientConfig);
        return builder.build();
    }


}
