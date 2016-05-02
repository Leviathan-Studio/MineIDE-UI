package com.leviathanstudio.mineide.main;

import java.util.Locale;

import com.leviathanstudio.mineide.utils.Utils;

import utybo.minkj.locale.MinkJ;

public class Translation
{
    public static final MinkJ LANG = new MinkJ();
    
    public static void init()
    {
        try
        {
            LANG.loadTranslationsFromFile(Locale.FRENCH, Translation.class.getResourceAsStream(Utils.LANG_DIR + "fr_FR.lang"));
            LANG.loadTranslationsFromFile(Locale.ENGLISH, Translation.class.getResourceAsStream(Utils.LANG_DIR + "en_US.lang"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
