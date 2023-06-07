package org.opennms.nutanix.client.v08;

import org.junit.Assert;
import org.junit.Test;

public class NutanixApiClientV1Test {


    @Test
    public void testEnvVariable() {
        Assert.assertNotNull(System.getenv("NTX_USER"));
        Assert.assertNotNull(System.getenv("NTX_PASS"));
    }

}
