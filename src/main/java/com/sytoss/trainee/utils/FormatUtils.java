package com.sytoss.trainee.utils;

public class FormatUtils {
    
    private FormatUtils() {
    }

    public static boolean isCsv(String filename) {
        StringBuffer fileType = new StringBuffer();
        for (int i = filename.length() - 4; i < filename.length(); i++) {
            fileType.append(filename.charAt(i));
        }
        return fileType.toString().equals(".csv");
    }

    public static boolean isXml(String filename) {
        StringBuffer fileType = new StringBuffer();
        for (int i = filename.length() - 4; i < filename.length(); i++) {
            fileType.append(filename.charAt(i));
        }
        return fileType.toString().equals(".xml");
    }
}
