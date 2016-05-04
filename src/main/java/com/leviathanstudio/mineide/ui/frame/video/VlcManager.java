package com.leviathanstudio.mineide.ui.frame.video;

import java.io.File;

import com.leviathanstudio.mineide.utils.OSHelper;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.version.LibVlcVersion;

/***
 * Copyright(c)Wytrem 2015. L'utilisation de ce code source ne peut se faire sans l'autorisation explicite de son auteur.**Tout partage implique une autorisation expresse de l'auteur original,sans quoi il est interdit d'utiliser ce code source.**
 * 
 * @author Wytrem
 */

public class VlcManager
{
    public static boolean init = false;
    
    public static void loadNatives()
    {
        String nativesDir = new File("").getAbsolutePath();
        
        System.out.println("nativesDir={" + nativesDir + "}");
        
        String vlcNativesLocation = null;
        
        OSHelper.OS os = OSHelper.getPlatform();
        String arch = System.getProperty("os.arch").contains("64") ? "64" : "32";
        
        System.out.println("os={" + os.name() + "}, arch={" + arch + "}");
        
        if(os == OSHelper.OS.SOLARIS || os == OSHelper.OS.UNKNOWN)
        {
            VlcManager.init = false;
            System.out.println("Impossible de charger les natives VLC pour l'OS : {" + os.name() + "}.");
            return;
        }
        else if(os == OSHelper.OS.LINUX)
        {
            if(arch.equals("64"))
                vlcNativesLocation = nativesDir + File.separator + "vlc" + File.separator + "linux-64" + File.separator;
            else
                vlcNativesLocation = nativesDir + File.separator + "vlc" + File.separator + "linux-32" + File.separator;
        }
        else if(os == OSHelper.OS.MACOS)
        {
            if(arch.equals("64"))
                vlcNativesLocation = nativesDir + File.separator + "vlc" + File.separator + "macos-64" + File.separator;
            else
                vlcNativesLocation = nativesDir + File.separator + "vlc" + File.separator + "macos-32" + File.separator;
        }
        else if(os == OSHelper.OS.WINDOWS)
            if(arch.equals("64"))
                vlcNativesLocation = nativesDir + File.separator + "vlc" + File.separator + "win-64" + File.separator;
            else
                vlcNativesLocation = nativesDir + File.separator + "vlc" + File.separator + "win-32" + File.separator;
                
        System.out.println("Chargement des natives VLC depuis '{" + vlcNativesLocation + "}'.");
        
        if(vlcNativesLocation != null)
        {
            if(!new File(vlcNativesLocation).exists())
                System.out.println("Le dossier des natives n'existe pas '{" + vlcNativesLocation + "}'.");
                
            try
            {
                NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), vlcNativesLocation);
                Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
                
                VlcManager.init = true;
                
                System.out.println("Natives VLC chargées avec succès. (version: {" + LibVlcVersion.getVersion().toString() + "})");
            }
            catch(Throwable thrown)
            {
                System.out.println("\n\nErreur au chargement des natives VLC : " + thrown);
                thrown.printStackTrace();
            }
        }
    }
}