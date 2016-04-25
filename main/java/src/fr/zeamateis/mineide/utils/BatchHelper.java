package fr.zeamateis.mineide.utils;

import fr.zeamateis.mineide.json.MineIDEConfig;

public class BatchHelper
{
    
    public static void initBatchFile()
    {
        Utils.writeFile(Utils.FORGE_DIR, "MineIDEInstall", MineIDEConfig.getForgeInstallCommand(), "bat");
        Utils.writeFile(Utils.FORGE_DIR, "MineIDEBuild", MineIDEConfig.getForgeBuildCommand(), "bat");
    }
}