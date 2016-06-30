package com.leviathanstudio.mineide.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import utybo.minkj.locale.MinkJ;
import utybo.minkj.locale.MinkJ.UnrespectedModelException;

public class Translation
{
    public static final MinkJ LANG = new MinkJ();

    public static void init()
    {
        try
        {
            loadTranslation(Locale.ENGLISH, "en_US");
            loadTranslation(Locale.FRENCH, "fr_FR");

            setLanguage(Locale.ENGLISH);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void setLanguage(Locale lang)
    {
        Translation.LANG.setSelectedLanguage(lang);
    }

    public static String getTranslation(String key)
    {
        return Translation.LANG.getTranslation(key);
    }

    private static void loadTranslation(Locale lang, String name)
            throws UnrespectedModelException, NullPointerException, FileNotFoundException, IOException
    {
        Translation.LANG.loadTranslationsFromFile(lang, Util.getStream(Util.LANG_DIR + name + ".lang"));
    }
}
