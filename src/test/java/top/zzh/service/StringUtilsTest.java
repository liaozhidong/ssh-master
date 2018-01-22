package top.zzh.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;;


public class StringUtilsTest {

    @Test
    public void testStringUtils() {
        System.out.println(StringUtils.isEmpty(" "));
        System.out.println(StringUtils.isBlank(" "));
        System.out.println(StringUtils.capitalize("abcd"));
    }
}
