package org.opennms.nutanix.client.api.internal;

import java.net.InetAddress;

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
}