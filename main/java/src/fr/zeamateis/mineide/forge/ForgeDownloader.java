package fr.zeamateis.mineide.forge;

import fr.zeamateis.mineide.helper.ZipHelper;
import fr.zeamateis.mineide.json.MineIDEInfo;
import fr.zeamateis.mineide.utils.Utils;

class ForgeDownloader
{
    static boolean downloadTerminated;
    
    public static void initDownload()
    {
        try
        {
            ZipHelper.buildDirectory(Utils.FORGE_DIR);
            ZipHelper.unpackArchive(MineIDEInfo.getForgeDownloadLink(), Utils.FORGE_DIR);
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