/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2023 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2023 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.nutanix.client.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
    public void testUtilsIsIpInSubnet() throws UnknownHostException {
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
