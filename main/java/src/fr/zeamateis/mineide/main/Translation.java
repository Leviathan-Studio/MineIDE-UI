package fr.zeamateis.mineide.main;

import java.util.Locale;

import utybo.minkj.locale.MinkJ;

public class Translation
{
    public static final MinkJ LANG = new MinkJ();
    
    public static void init()
    {
        try
        {
            LANG.loadTranslationsFromFile(Locale.FRENCH, Translation.class.getResourceAsStream("/files/lang/fr_FR.lang"));
            LANG.loadTranslationsFromFile(Locale.ENGLISH, Translation.class.getResourceAsStream("/files/lang/en_US.lang"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
