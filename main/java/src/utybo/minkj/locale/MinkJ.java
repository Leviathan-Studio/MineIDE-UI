package utybo.minkj.locale;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The whole system of MinkJ!<br/>
 * <br/>
 * 
 * You need to redefine the translations if you want to use the serialization with MinkJ
 * 
 * @author utybo
 * @see {@link java.util.Locale} (MinkJ uses Locale intances)
 * 
 */
@SuppressWarnings("serial")
public final class MinkJ implements Serializable
{
    /**
     * Stores the values. This HashMap can be very big. The first one (Locale, hashMap) stores the Locale and its corresponding HashMap (String, String) which stores the key (first String) and its associated translation (second String).
     */
    private transient Map<Locale, Map<String, String>> map = new HashMap<Locale, Map<String, String>>();
    
    /**
     * The default language. It is used in case of an unknown translation for a specific key.<br/>
     * <br/>
     * e.g : you have the key hello.world which has a translation in English which is the default language (Hello World) but, for some reasons, the translation in German does not exist. If you ask for the German translation for the key hello.world, the English translation will be returned. If the translation from the default translation is not available/does not exist, the key will be returned.
     */
    private Locale defaultLanguage = Locale.ENGLISH;
    
    /**
     * The selected language. The methods getting a translation will look for a translation for the Locale specified here. The default value is the system language.
     */
    private Locale selectedLanguage = new Locale(System.getProperty("user.language"));
    
    /**
     * Defines if MinkJ will output anything such as errors, info...
     */
    private boolean muted = false;
    
    public MinkJ()
    {}
    
    /**
     * With this constructor, you can specify the default language. Using the default constructor with the method {@link #setDefaultLanguage(Locale)} does the same thing.
     * 
     * @param defaultLanguage
     */
    public MinkJ(Locale defaultLanguage)
    {
        this.defaultLanguage = defaultLanguage;
    }
    
    /**
     * Sets the HashMap for the specified Locale object. <strong>This will delete <i>any</i> translation already registered, use this method carefully!</strong>
     * 
     * @param translations
     * @param locale
     * @return
     */
    public synchronized MinkJ setLocaleMap(Map<String, String> translations, Locale locale)
    {
        map.put(locale, translations);
        return this;
    }
    
    /**
     * Merges the given map with the current map for the given Locale object. This will overwrite any translation already registered.
     * 
     * @param toMerge
     * @param locale
     * @return
     */
    public synchronized MinkJ mergeMapWithLocaleMap(Map<String, String> toMerge, Locale locale)
    {
        for(String s : toMerge.keySet())
            map.get(locale).put(s, toMerge.get(s));
        return this;
    }
    
    /**
     * @param locale
     * @return The entire map for the given locale
     */
    public Map<String, String> getLocaleMap(Locale locale)
    {
        return map.get(locale);
    }
    
    /**
     * Sets the default language
     * 
     * @param locale
     * @return
     * @see {@link #defaultLanguage}
     * @see {@link #MinkJ(Locale)}
     */
    public synchronized MinkJ setDefaultLanguage(Locale locale)
    {
        defaultLanguage = locale;
        return this;
    }
    
    /**
     * @return {@link #defaultLanguage}
     * @see {@link #setDefaultLanguage(Locale)}
     */
    public Locale getDefaultLanguage()
    {
        return defaultLanguage;
    }
    
    /**
     * Does the same thing as {@link #addTranslation(Locale, String, String)} with the {@link #selectedLanguage}
     * 
     * @param key
     * @param translation
     * @return
     */
    public MinkJ addTranslation(String key, String translation)
    {
        return addTranslation(selectedLanguage, key, translation);
    }
    
    /**
     * Does the same thing as {@link #removeTranslation(Locale, String)} with the {@link #selectedLanguage}
     * 
     * @param key
     * @return
     */
    public MinkJ removeTranslation(String key)
    {
        return removeTranslation(selectedLanguage, key);
    }
    
    /**
     * Adds a for the given key for the given locale. If the locale's translations were not initiated, this method will initiate them
     * 
     * @param locale
     * @param key
     * @param translation
     * @return
     */
    public MinkJ addTranslation(Locale locale, String key, String translation)
    {
        if(!(map.containsKey(locale)))
            map.put(locale, new HashMap<String, String>());
        map.get(locale).put(key, translation);
        return this;
    }
    
    /**
     * Removes the translation of the given key for the given locale
     * 
     * @param locale
     * @param key
     * @return
     */
    public MinkJ removeTranslation(Locale locale, String key)
    {
        if(map.containsKey(locale) && map.get(locale).containsKey(key))
            map.get(locale).remove(key);
        return this;
    }
    
    /**
     * Imports the translation from the given file. <br/>
     * <br/>
     * Model :<blockquote><code>
     * key.that.must.not.contain.spaces=Translation that can contain spaces</code> </blockquote>
     * 
     * 
     * @param locale
     * @param file
     * @return
     * @throws NullPointerException
     *             if a parameter is missing
     * @throws FileNotFoundException
     * @throws IOException
     */
    public synchronized MinkJ loadTranslationsFromFile(Locale locale, File file) throws UnrespectedModelException, NullPointerException, FileNotFoundException, IOException
    {
        if(locale == null || file == null)
            throw new NullPointerException();
        
        if(map.get(locale) == null)
            map.put(locale, new HashMap<String, String>());
        
        loadTranslationFromBufferedReader(new BufferedReader(new FileReader(file)), locale, file.getName());
        
        return this;
    }
    
    /**
     * Imports the translation from the given file (given as an InputStream). <br/>
     * <br/>
     * Model :<blockquote><code>
     * key.that.must.not.contain.spaces=Translation that can contain spaces</code> </blockquote>
     * 
     * 
     * @param locale
     * @param input
     * @return
     * @throws NullPointerException
     *             if a parameter is missing
     * @throws FileNotFoundException
     * @throws IOException
     */
    public synchronized MinkJ loadTranslationsFromFile(Locale locale, InputStream input) throws UnrespectedModelException, NullPointerException, FileNotFoundException, IOException
    {
        if(locale == null || input == null)
            throw new NullPointerException();
        
        if(map.get(locale) == null)
            map.put(locale, new HashMap<String, String>());
        
        loadTranslationFromBufferedReader(new BufferedReader(new InputStreamReader(input, "UTF-8")), locale, input.toString());
        
        return this;
    }
    
    /**
     * This is used by the readers to provide a common method, making code modification easier
     * 
     * @param br
     * @param locale
     * @param fileName
     * @return
     * @throws IOException
     * @throws UnrespectedModelException
     * @since 1.0_pre6
     */
    private synchronized MinkJ loadTranslationFromBufferedReader(BufferedReader br, Locale locale, String fileName) throws IOException, UnrespectedModelException
    {
        if(br == null)
            throw new NullPointerException();
        assert br != null;
        try
        {
            String line;
            while((line = br.readLine()) != null)
            {
                if(!(line.startsWith("#") || line.isEmpty()))
                {
                    String[] translation = line.split("=");
                    if(!(translation.length == 2))
                    {
                        log("Errrored String : " + line + ". Here is the index :", true);
                        for(int i = 0; i < translation.length; i++)
                            log(translation[i] + "          @ index " + i, true);
                        throw new UnrespectedModelException(line);
                    }
                    if(map.get(locale).containsKey(translation[0]))
                        log("WARNING : File " + fileName + " overwrites a translation @ " + translation[0], true);
                    this.addTranslation(locale, translation[0], translation[1]);
                }
            }
        }
        finally
        {
            br.close();
        }
        log("Successfully read file : " + fileName, false);
        return this;
    }
    
    /**
     * Sets the selected language to the given locale
     * 
     * @param locale
     * @return
     */
    public synchronized MinkJ setSelectedLanguage(Locale locale)
    {
        this.selectedLanguage = locale;
        return this;
    }
    
    /**
     * @return {@link #selectedLanguage}
     */
    public Locale getSelectedLanguage()
    {
        return selectedLanguage;
    }
    
    /**
     * Gets the translation for the {@link #selectedLanguage}
     * 
     * @param key
     * @see {@link #getTranslation(String, Locale)}
     */
    public String getTranslation(String key)
    {
        return getTranslation(key, selectedLanguage);
    }
    
    /**
     * @param key
     * @param locale
     * @return The translation for the given locale, or, if it doesn't exist, the translation for the default language, or, if there isn't any translation for the default language, the given key.
     */
    public String getTranslation(String key, Locale locale)
    {
        return isTranslated(key, locale) ? map.get(locale).get(key) : isTranslated(key, defaultLanguage) ? map.get(defaultLanguage).get(key) : key;
    }
    
    /**
     * @param key
     * @param locale
     * @return If {@link #map} has the key locale and if key is translated
     */
    public boolean isTranslated(String key, Locale locale)
    {
        return map.containsKey(locale) && map.get(locale).containsKey(key);
    }
    
    /**
     * Mutes MinkJ. It won't output anything.
     * 
     * @return
     */
    public MinkJ mute()
    {
        muted = true;
        return this;
    }
    
    /**
     * Unmutes MinkJ. It will output stuff again.
     * 
     * @return
     */
    public MinkJ unmute()
    {
        muted = false;
        return this;
    }
    
    /**
     * Logs something, checking if MinkJ can output stuff.
     */
    private void log(String str, boolean error)
    {
        if(!muted)
        {
            if(error)
                System.err.println(str);
            if(!error)
                System.out.println(str);
        }
    }
    
    /**
     * Thrown by {@link MinkJ#loadTranslationsFromFile(Locale, File)} when a file does not respect the translation model
     * 
     * @author utybo
     * 
     */
    public class UnrespectedModelException extends Exception
    {
        private static final long serialVersionUID = -1539821762590369248L;
        private File file;
        private String line;
        
        public UnrespectedModelException(File f, String line)
        {
            this.line = line;
            this.file = f;
        }
        
        public UnrespectedModelException(String line)
        {
            this.line = line;
        }
        
        @Override
        public String getMessage()
        {
            String message = "Unrespected model (" + line + ") on file";
            if(file != null)
                message = message + file.getName();
            return message;
        }
    }
}