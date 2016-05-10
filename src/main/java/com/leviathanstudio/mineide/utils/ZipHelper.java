package com.leviathanstudio.mineide.utils;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipHelper
{
    private static File zip;
    
    public static File unpackArchive(URL url, File targetDir) throws IOException
    {
        if(!targetDir.exists())
            targetDir.mkdirs();
        InputStream in = new BufferedInputStream(url.openStream(), 1024);
        ZipHelper.zip = File.createTempFile("mineide", ".zip", targetDir);
        OutputStream out = new BufferedOutputStream(new FileOutputStream(ZipHelper.zip));
        copyInputStream(in, out);
        out.close();
        return unpackArchive(ZipHelper.zip, targetDir);
    }
    
    /**
     * Unpack a zip file
     *
     * @param theFile
     * @param targetDir
     * @return the file
     * @throws IOException
     */
    public static File unpackArchive(File theFile, File targetDir) throws IOException
    {
        if(!theFile.exists())
            throw new IOException(theFile.getAbsolutePath() + " does not exist");
        if(!buildDirectory(targetDir))
            throw new IOException("Could not create directory: " + targetDir);
        ZipFile zipFile = new ZipFile(theFile);
        for(Enumeration entries = zipFile.entries(); entries.hasMoreElements();)
        {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            File file = new File(targetDir, File.separator + entry.getName());
            if(!buildDirectory(file.getParentFile()))
                throw new IOException("Could not create directory: " + file.getParentFile());
            if(!entry.isDirectory())
                copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(file)));
            else if(!buildDirectory(file))
                throw new IOException("Could not create directory: " + file);
        }
        zipFile.close();
        return theFile;
    }
    
    public static void copyInputStream(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        while(len >= 0)
        {
            out.write(buffer, 0, len);
            len = in.read(buffer);
        }
        in.close();
        out.close();
    }
    
    public static boolean buildDirectory(File file)
    {
        return file.exists() || file.mkdirs();
    }
    
    public static void deleteTempFile()
    {
        if(ZipHelper.zip.exists())
            ZipHelper.zip.delete();
    }
    
}