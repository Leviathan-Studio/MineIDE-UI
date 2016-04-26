package fr.zeamateis.mineide.json.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public abstract class JsonGenerator
{
    public static final String BASE_LOCATION = "/minecraft/template";
    
    // key :  #key -> value
    private HashMap<String,String> mapPattern = new HashMap<String, String>();
    // name -> final location
    // ex : blockstate -> /assets/$modid/blockstates/$file_name
    private HashMap<String, String> fileLocation = new HashMap<String, String>();
    // name -> pattern location
    private HashMap<String, String> patternLocation = new HashMap<String, String>();
    
    private final String modid;
    private final String type;
    
    public JsonGenerator(String modid, String type)
    {
        this.modid = modid;
        this.type = type;
    }
    
    public void addBlockstate(String name, String fileName)
    {
        String base = "/assets/$modid/blockstates/$name_$type.json";
        String file_location = base.replace("$modid", modid).replace("$type", type).replace("$name", fileName);
        String pattern_location = BASE_LOCATION + "/blocks/blockstates";
        fileLocation.put(name, file_location);
        patternLocation.put(name, pattern_location);
    }
    
    public void addModelBlock(String name)
    {
        
    }
    
    public void addModelItem(String name)
    {
        
    }
    
    public void process()
    {
        Set<Entry<String, String>> entries = fileLocation.entrySet();

        for(Entry<String, String> entry : entries)
        {
            // get Infos
            String name = entry.getKey();
            String file_location = entry.getValue();
            String pattern = patternLocation.get(name);
            
            // get pattern
            
            // replace pattern
            
            // create file
            File file = new File(file_location);
        }
    }
    
    public abstract void addPatternKeys();
    
    public abstract void addPatternFiles();

}
