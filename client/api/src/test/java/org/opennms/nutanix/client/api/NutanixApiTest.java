package org.opennms.nutanix.client.api;

import java.net.InetAddress;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.internal.Utils;

public class NutanixApiTest {
    @Test
    public void testUtilsGetValidAddress() {
        InetAddress ip = Utils.getValidInetAddress("10.63.30.166");
        Assert.assertNotNull(ip);
    }

    @Test
    public void testAlarmReductionKey() {
        String NUTANIX_ALARM_UEI = "uei.opennms.org/plugin/nutanix/alert";
        String REDUCTION_KEY="uei.opennms.org/plugin/nutanix/alert:148:ed62a501-87f7-46be-ade2-9061b2bfacc9";
        Assert.assertTrue(REDUCTION_KEY.contains(NUTANIX_ALARM_UEI));
        Assert.assertEquals(0, REDUCTION_KEY.indexOf(NUTANIX_ALARM_UEI));
        Assert.assertEquals("ed62a501-87f7-46be-ade2-9061b2bfacc9", REDUCTION_KEY.substring(REDUCTION_KEY.lastIndexOf(":")+1));
    }

    @Test
    public void testUtilsIsIpInSubnet() {
        boolean vero = Utils.isIpInSubnet("10.63.30.166", "10.63.30.0/255.255.255.0");
        Assert.assertTrue(vero);
        boolean falso = Utils.isIpInSubnet("10.62.30.166", "10.63.30.0/255.255.255.0");
        Assert.assertFalse(falso);
    }

    @Test
    public void testRe() {
        Assert.assertTrue(Pattern.compile("[ABe][SE-][\\d]").matcher("PVW-CTXLIC-BE1").find());
        Assert.assertTrue(Pattern.compile("[ABe][SE-][\\d]").matcher("PVW-CTXLIC-AS4").find());
        Assert.assertTrue(Pattern.compile("[ABe][SE-][\\d]").matcher("PVW-CTXLIe-4").find());
    }
}
