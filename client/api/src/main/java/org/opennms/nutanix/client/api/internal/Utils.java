package org.opennms.nutanix.client.api.internal;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.common.base.Strings;
import com.google.common.net.InetAddresses;

public class Utils {
    public static InetAddress getValidInetAddress(final String ip) {
        if (Strings.isNullOrEmpty(ip)) {
            return null;
        }

        try {
            final InetAddress address = InetAddresses.forString(ip);
            return address;
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public static boolean isIpInSubnet(String ip, String subnet) throws UnknownHostException {
        int a = subnet.lastIndexOf("/");
        InetAddress network = getValidInetAddress(subnet.substring(0,a));
        InetAddress netmask = getValidInetAddress(subnet.substring(a+1));
        InetAddress address = getValidInetAddress(ip);

        return InetAddressToInt(network) == (InetAddressToInt(address) & InetAddressToInt(netmask));
    }

    public static int InetAddressToInt(InetAddress ip) {
        if (ip == null)
            return -1;
        byte[] adr = ip.getAddress();

        int[] i = new int[4];
        for (int j = 0; j < 4; j++) {
            i[j] = (int) ((adr[j] < 0) ? (256 + adr[j]) : adr[j]);
        }
        return i[3] + (i[2] << 8) + (i[1] << 16) + (i[0] << 24);
    }

}