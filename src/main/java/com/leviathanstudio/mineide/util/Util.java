package com.leviathanstudio.mineide.util;

import java.io.File;
import java.io.InputStream;

public class Util
{
    public static final String APP_NAME      = "MineIDE";

    public static final int    BASE_WIDTH    = 854;
    public static final int    BASE_HEIGHT   = 480;
    public static final int    SPLASH_WIDTH  = 704;
    public static final int    SPLASH_HEIGHT = 250;

    public static final String IMG_DIR       = "/mineIDE/img/";
    public static final String CSS_DIR       = "/mineIDE/css/";
    public static final String JSON_DIR      = "/mineIDE/json/";
    public static final String HTML_DIR      = "/mineIDE/html/";
    public static final String LANG_DIR      = "/mineIDE/lang/";
    public static final String TEMPLATE_DIR  = "/minecraft/template/";

    public static final File   ROOT          = OSHelper.getWorkingDirectory();

    public static void checkDir(File file)
    {
        if (!file.exists())
            file.mkdirs();
    }

    public static String getResource(String path)
    {
        return Util.class.getResource(path).toString();
    }

    public static InputStream getStream(String path)
    {
        return Util.class.getResourceAsStream(path);
    }
}
