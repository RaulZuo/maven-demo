package org.example;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.DateUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String appId = "21371601@android";
        String type = "ALL_CRASH";
        String version = "0";
        String begin = "1698034180759";
        String end = "1698638980759";
        System.out.println(getDayKeys(appId, type, version, begin, end));
    }

    public static List<String> getDayKeys(String appId, String type, String version, String begin, String end) {
        List<String> keys = new ArrayList<String>();
        Date beginTime = DateUtils.getZeroDay(new Date(Long.valueOf(begin)));
        Date endTime = DateUtils.getZeroDay((new Date(Long.valueOf(end))));
        for (long t = beginTime.getTime(); t <= endTime.getTime(); t += DateUtils.ONE_DAY_TIME) {
            Date date = new Date(t);
            String ds = DateUtils.format(DateUtils.COMPACT_DAY_PATTERN_LOCAL, date);
            StringBuilder  sb = new StringBuilder();
            sb.append(DigestUtils.md5Hex(appId + type + ds + version), 0, 4).append("|a|");
            sb.append(ds).append("|").append(appId).append("|").append(type).append("|").append(version);
            keys.add(sb.toString());
        }
        return keys;
    }
}
