package fr.zeamateis.mineide.forge;

import fr.zeamateis.mineide.json.MineIDEConfig;
import fr.zeamateis.mineide.utils.Utils;
import fr.zeamateis.mineide.utils.ZipHelper;

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
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            setDownloadTerminated(true);
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