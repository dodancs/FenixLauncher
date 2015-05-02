
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.json.simple.JSONObject;

/*
 |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
 | Author: Dominik Dancs                                                           |
 | Website: http://dodancs.moow.info                                               |
 | E-Mail: dodancs@moow.info                                                       |
 |                                                                                 |
 | Support me on Patreon: https://www.patreon.com/dodancs                          |
 |                                                                                 |
 | Copyright: 2015 Dominik Dancs & FenixPortal.eu | All rights reserved.           |
 |                                                                                 |
 | License: GNU                                                                    |
 |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
 */

/**
 *
 * @author Dominik
 */
public class UTILITY_downloader {

    private Thread downloadThread = new Thread();
    private String DOWNLOADER_url = "";
    private String DOWNLOADER_filename = "";
    private String DOWNLOADER_outputdir = "";
    private String DOWNLOADER_modpack = "";
    private boolean isDownloading = false;
    private boolean downloadComplete = false;
    
    private long DOWNLOADER_onlineSize = 0;
    private long DOWNLOADER_offlineSize = 0;
    
    DateFormat dateFormat = new SimpleDateFormat("HHmmss");
    Date date = new Date();
    
    public UTILITY_downloader() {}
    
    public void setUrl(String url) {
        DOWNLOADER_url = url;
    }
    
    public void setFilename(String filename) {
        DOWNLOADER_filename = filename;
    }
    
    public void setOutputDir(String dir) {
        DOWNLOADER_outputdir = dir;
    }
    
    public void setModpackName(String name) {
        DOWNLOADER_modpack = name;
    }
    
    public boolean checkUrl() {
        boolean bool = false;
        if (Launcher.filehelper.onlineFileExists(DOWNLOADER_url)) {bool = true;}
        return bool;
    }
    
    public void startDownload() {
        if (checkUrl()) {
            try {
            downloadThread = new Thread(){
                public void run(){
                    try {
                        URL url= new URL(DOWNLOADER_url);
                        downloadFromUrl(url, DOWNLOADER_filename);
                        isDownloading = true;
                    }
                    catch (Exception e) {
                        Launcher.console.error("UTILITY_downloader.startDownload() : " + e.toString());
                        e.printStackTrace();
                    }
                }
            };
            downloadThread.start();//start the thread
            }
            catch (Exception e) {
                Launcher.console.error("UTILITY_downloader.startDownload() : " + e.toString());
                e.printStackTrace();
            }
        }
        else {
            Launcher.console.error("Unable to download modpack. File does not exist: "+DOWNLOADER_url);
            isDownloading = false;
            stopDownload();
            Launcher.gui_downloader.setFileInfo("Nie je možné stiahnúť modpack.");
            Launcher.gui_downloader.finished();
        }
    }
    
    public void quickDownload(String url, String filename, String outputdir, String name) {
        Launcher.gui_downloader.setModpack(name);
        setUrl(url);
        setFilename(filename);
        setModpackName(name);
        setOutputDir(outputdir);
        startDownload();
    }
    
    public void stopDownload() {
        downloadThread.stop();
        isDownloading = false;
        Launcher.console.info("Download cancelled.");
    }
    
    public void updateGUI() {
        Launcher.gui_downloader.setProgress(DOWNLOADER_offlineSize);
        Launcher.gui_downloader.setFileInfo(DOWNLOADER_url + " ("+(DOWNLOADER_offlineSize/1024)+"kB/"+(DOWNLOADER_onlineSize/1024)+"kB)");
        
        //System.err.println("Downloading: "+DOWNLOADER_url+" ("+(DOWNLOADER_offlineSize/1024)+"kB/"+(DOWNLOADER_onlineSize/1024)+"kB)");
    }
    
    public void downloadFromUrl(URL url, String localFilename) throws IOException {
        InputStream is = null;
        FileOutputStream fos = null;
        File fin = null;

        try {
            URLConnection urlConn = url.openConnection();//connect

            DOWNLOADER_onlineSize = urlConn.getContentLength();
            Launcher.gui_downloader.setMaxProgress(DOWNLOADER_onlineSize);
            Launcher.gui_downloader.setFileInfo(url.toString() + " (0/"+DOWNLOADER_onlineSize+")");
            Launcher.console.info("Downloading: "+DOWNLOADER_url+" ("+(DOWNLOADER_onlineSize/1024)+"kb); stored in: "+DOWNLOADER_filename);
            
            is = urlConn.getInputStream();               //get connection inputstream
            fos = new FileOutputStream(localFilename);   //open outputstream to local file

            byte[] buffer = new byte[4096];              //declare 4KB buffer
            int len;

            //while we have availble data, continue downloading and storing to local file
            while ((len = is.read(buffer)) > 0) {  
                fos.write(buffer, 0, len);
                try {
                    fin = new File(localFilename);
                    DOWNLOADER_offlineSize = fin.length();
                }
                catch (Exception e) {
                    Launcher.console.error("UTILITY_downloader.downloadFromUrl() : " + e.toString());
                    e.printStackTrace();
                }
                updateGUI();
            }
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } finally {
                if (fos != null) {
                    fos.close();
                    isDownloading = false;
                    unzipFile(new File(DOWNLOADER_filename), new File(DOWNLOADER_outputdir));
                }
            }
        }
    }
    
    public void unzipFile(File zipFile, File outputFolder) {
        File source = zipFile;
        File destination = outputFolder;
        
        try {
            //System.out.println("Unzipping - " + source.getName());
            int BUFFER = 2048;

            ZipFile zip = new ZipFile(source);

            destination.getParentFile().mkdirs();
            Enumeration zipFileEntries = zip.entries();

            Launcher.console.info("Unzipping "+zipFile+" to "+outputFolder);
            
            // Process each entry
            while (zipFileEntries.hasMoreElements())
            {
                // grab a zip file entry
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();
                File destFile = new File(destination, currentEntry);
                //destFile = new File(newPath, destFile.getName());
                File destinationParent = destFile.getParentFile();

                // create the parent directory structure if needed
                destinationParent.mkdirs();
                
                Launcher.gui_downloader.setFileInfo("UnZip: "+destFile.toString());
                
                if (!entry.isDirectory())
                {
                    BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
                    int currentByte;
                    // establish buffer for writing file
                    byte data[] = new byte[BUFFER];

                    // write the current file to disk
                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

                    // read and write until last byte is encountered
                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.close();
                    fos.close();
                    is.close();
                }else{
                    //Create directory
                    destFile.mkdirs();
                }

                if (currentEntry.endsWith(".zip"))
                {
                    // found a zip file, try to open
                    unzipFile(destFile, destinationParent);
                    if(!destFile.delete()){
                        System.out.println("Could not delete zip");
                    }
                }
            }
            zip.close();
            
            //Done
            downloadComplete = true;
            addNewModpack();
        }
        catch (Exception e) {
            Launcher.console.error("UTILITY_downloader.unzipFile() : " + e.toString());
            e.printStackTrace();
        }

    }
    
    public void addNewModpack() {
        Launcher.gui_downloader.setFileInfo("Modpack sa pripravuje...");
        Launcher.console.info("Adding modpack: "+DOWNLOADER_modpack);
        
        JSONObject o = Launcher.modpacks.getOnlineModpackInfo(DOWNLOADER_modpack);
        
        String code_name = Launcher.jsonhelper.oGetKeyValueString(o,"code_name");
        String name = Launcher.jsonhelper.oGetKeyValueString(o,"name");
        String version = Launcher.jsonhelper.oGetKeyValueString(o,"current_version");
        String mc_version = Launcher.jsonhelper.oGetKeyValueString(o,"mc_version");
        boolean is_forge = Launcher.jsonhelper.oGetKeyValueString(o,"is_forge").equals("true");
        String forge_version = Launcher.jsonhelper.oGetKeyValueString(o,"forge_version");
        String folder = DOWNLOADER_outputdir;
        String args = Launcher.jsonhelper.oGetKeyValueString(o,"args");
        
        Launcher.modpacks.addModpack(code_name, name, version, mc_version, is_forge, forge_version, folder, args);
    
        Launcher.gui_downloader.setFileInfo("Hotovo.");
        Launcher.gui_downloader.finished();
        
        Launcher.loadModpacks(Launcher.modpacks.getOnlineModpacks());
    }
}
