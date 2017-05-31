package com.assassin.utils;

import java.io.File;
import java.net.URL;

/**
 * Created by Administrator on 2015/11/4.
 */
public class PathUtils {
    private static final String urlPath="xmlconfig";
    public static String GetXmlDirectory(){
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        String path = '/' + urlPath.replace('.', '/');
        URL resource = cld.getResource(path);
        File directory = new File(resource.getFile());
        String xmlPath=directory.getPath();
        return xmlPath;
    }
}
