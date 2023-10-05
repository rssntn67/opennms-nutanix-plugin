package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class NutanixApiV3Test {

   @Test
   public void testMatcherGraphs() {
       List<String> matchList = new ArrayList<>();
       Pattern regex = Pattern.compile("\\{(.*?)\\}");
       Matcher regexMatcher = regex.matcher("Hello This is {Java} Not {.NET}");

       while (regexMatcher.find()) {//Finds Matching Pattern in String
           matchList.add(regexMatcher.group(1));//Fetching Group from String
       }

       for(String str:matchList) {
           System.out.println(str);
       }
   }
}
