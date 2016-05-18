package com.leviathanstudio.mineide.main;

import java.util.Locale;

import com.leviathanstudio.mineide.utils.Util;

import utybo.minkj.locale.MinkJ;

public class Translation
{
    public static final MinkJ LANG = new MinkJ();

    public static void init()
    {
        try
        {
            Translation.LANG.loadTranslationsFromFile(Locale.FRENCH,
                    Translation.class.getResourceAsStream(Util.LANG_DIR + "fr_FR.lang"));
            Translation.LANG.loadTranslationsFromFile(Locale.ENGLISH,
                    Translation.class.getResourceAsStream(Util.LANG_DIR + "en_US.lang"));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
