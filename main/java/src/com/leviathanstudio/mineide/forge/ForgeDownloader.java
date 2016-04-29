package com.leviathanstudio.mineide.forge;

import com.leviathanstudio.mineide.json.MineIDEConfig;
import com.leviathanstudio.mineide.utils.Utils;
import com.leviathanstudio.mineide.utils.ZipHelper;

class ForgeDownloader
{
    static boolean downloadTerminated;
    
    public static void initDownload()
    {
        try
        {
            ZipHelper.buildDirectory(Utils.FORGE_DIR);
            ZipHelper.unpackArchive(MineIDEConfig.getForgeDownloadLink(), Utils.FORGE_DIR);
            ZipHelper.deleteTempFile();
            setDownloadTerminated(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean isDownloadTerminated()
    {
        return downloadTerminated;
    }
    
    private static void setDownloadTerminated(boolean isTerminated)
    {
        downloadTerminated = isTerminated;
    }
}