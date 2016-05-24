package com.leviathanstudio.mineide.forge;

import com.leviathanstudio.mineide.json.MineIDEConfig;
import com.leviathanstudio.mineide.util.Util;
import com.leviathanstudio.mineide.utils.ZipHelper;

class ForgeDownloader
{
    private static volatile ForgeDownloader instance = null;

    public static final ForgeDownloader getInstance()
    {
        if (ForgeDownloader.instance == null)
            synchronized (ForgeDownloader.class)
            {
                if (ForgeDownloader.instance == null)
                    ForgeDownloader.instance = new ForgeDownloader();
            }
        return ForgeDownloader.instance;
    }

    private boolean downloadTerminated;

    private ForgeDownloader()
    {

    }

    public void initDownload()
    {
        try
        {
            ZipHelper.buildDirectory(Util.FORGE_DIR);
            ZipHelper.unpackArchive(MineIDEConfig.getForgeDownloadLink(), Util.FORGE_DIR);
            ZipHelper.deleteTempFile();
            this.setDownloadTerminated(true);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isDownloadTerminated()
    {
        return this.downloadTerminated;
    }

    private void setDownloadTerminated(boolean isTerminated)
    {
        this.downloadTerminated = isTerminated;
    }
}