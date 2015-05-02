import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author Dominik
 */
public class UTILITY_filehelper {
    public boolean fileExists(String filename) {
        File currentFile = new File(filename);
        if(!currentFile.exists() || currentFile.isDirectory()) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public boolean onlineFileExists(String filename){
        try {
            HttpURLConnection.setFollowRedirects(false);
          // note : you may also need
          //        HttpURLConnection.setInstanceFollowRedirects(false)
          HttpURLConnection con =
             (HttpURLConnection) new URL(filename).openConnection();
          con.setRequestMethod("HEAD");
          return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
           Launcher.console.error("UTILITY_filehelper.onlineFileExists() : " + e.toString());
           e.printStackTrace();
           return false;
        }
    }
    
    public String getFirstLine(String filename) {
        String returnVal = "";
        
        try
        {       
            BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
            String currentLine = in.readLine();
            in.close();
            
            if (currentLine != null) {
                returnVal = currentLine;
            }
            else {
                returnVal = null;
            }		
        }
        // Catches any error conditions
        catch (Exception e)
        {
            Launcher.console.error("UTILITY_filehelper.getFirstLine() : " + e.toString());
            e.printStackTrace();
        }
        
        return returnVal;
    }
    public ArrayList<String> getLines(String filename) {
        ArrayList<String> returnVal = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                returnVal.add(line);
            }
            
            br.close();
        }
        // Catches any error conditions
        catch (Exception e)
        {
            Launcher.console.error("UTILITY_filehelper.getLines() : " + e.toString());
            e.printStackTrace();
        }
        
        return returnVal;
    }
    public void createFile(String filename) {
        // Stream to write file
        FileOutputStream fout;		
        try
        {
            // Open an output stream
            fout = new FileOutputStream(filename);

            // Print a line of text
            PrintStream stream = new PrintStream(fout);
            stream.print("");

            // Close our output stream
            fout.close();		
        }
        // Catches any error conditions
        catch (Exception e)
        {
            Launcher.console.error("UTILITY_filehelper.createFile() : " + e.toString());
            e.printStackTrace();
        }
    }
    public void createDir(String dirname) {
        File theDir = new File(dirname);
        if (!theDir.exists()) {theDir.mkdir();}
    }
    public void appendFile(String filename, String text) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            out.println(text);
            out.close();
        } catch (Exception e) {
            Launcher.console.error("UTILITY_filehelper.appendFile() : " + e.toString());
            e.printStackTrace();
        }
    }
    public void emptyFile(String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.print("");
            writer.close();
        }
        catch (Exception e) {
            Launcher.console.error("UTILITY_filehelper.emptyFile() : " + e.toString());
            e.printStackTrace();
        }
        
    }
    
    public String getRemoteFileFirstLine(String url) {
        String returnVal = "";

        try {
            URL currentURL = new URL(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(currentURL.openStream()));
            String line = br.readLine();
            
            if(line != null) {
                // process the line.
                returnVal = line;
            }
            
            br.close();
        }
        // Catches any error conditions
        catch (Exception e)
        {
            Launcher.console.error("UTILITY_filehelper.getRemoteFileFirstLine() : " + e.toString());
            e.printStackTrace();
        }
        
        return returnVal;
    }
    public ArrayList<String> getRemoteFileLines(String url) {
        ArrayList<String> returnVal = new ArrayList<>();

        try {
            URL currentURL = new URL(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(currentURL.openStream()));
            
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                returnVal.add(line);
            }
            
            br.close();
        }
        // Catches any error conditions
        catch (Exception e)
        {
            Launcher.console.error("UTILITY_filehelper.getRemoteFileLines() : " + e.toString());
            e.printStackTrace();
        }
        
        return returnVal;
    }
    
    public Image getImage(String source) {
        ImageIcon image = new ImageIcon(getClass().getResource(source));
        return image.getImage();
    }
    public ImageIcon getIcon(String source) {
        ImageIcon icon = new ImageIcon(getClass().getResource(source));
        return icon;
    }
    
    public void initFiles() {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        Launcher.accountsFile = Launcher.LAUNCHER_path + Launcher.slash +  "accounts.json"; //define accounts file path based on startDir and slash
        Launcher.modpacksFile = Launcher.LAUNCHER_path + Launcher.slash +  "modpacks.json"; //define modpacks file path based on startDir and slash
        Launcher.modpacksDir = Launcher.LAUNCHER_path + Launcher.slash + "modpacks";
        Launcher.downloadsDir = Launcher.LAUNCHER_path + Launcher.slash + "cache";
        Launcher.CONSOLE_logsdir = Launcher.LAUNCHER_path + Launcher.slash + "logs";
        Launcher.CONSOLE_logFile = Launcher.CONSOLE_logsdir + Launcher.slash + "launcher-" + currentTimestamp.toString().replace("-", ".").replace(" ", "-").replace(":", "-") + ".log"; //define console log file path based on startDir and slash
        Launcher.filehelper.createDir(Launcher.LAUNCHER_path);
        if (!Launcher.filehelper.fileExists(Launcher.launcherFile)) {Launcher.filehelper.createFile(Launcher.launcherFile);}
        Launcher.filehelper.createDir(Launcher.CONSOLE_logsdir);
        Launcher.filehelper.createDir(Launcher.modpacksDir);
        Launcher.filehelper.createDir(Launcher.downloadsDir);
        if (!Launcher.filehelper.fileExists(Launcher.CONSOLE_logFile)) {Launcher.filehelper.createFile(Launcher.CONSOLE_logFile);}
        if (!Launcher.filehelper.fileExists(Launcher.modpacksFile)) {Launcher.filehelper.createFile(Launcher.modpacksFile);Launcher.filehelper.appendFile(Launcher.modpacksFile,"[]");}
    }
    
    public void downloadFile(String url, String location) {
        try {
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(location);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        catch(Exception e) {
            Launcher.console.error("UTILITY_filehelper.downloadFile() : " + e.toString());
            e.printStackTrace();
        }
    }
    
    public void unzipFile(File zipFile, File outputFolder){
        File source = zipFile;
        File destination = outputFolder;
        
        try {
            //System.out.println("Unzipping - " + source.getName());
            int BUFFER = 2048;

            ZipFile zip = new ZipFile(source);

            destination.getParentFile().mkdirs();
            Enumeration zipFileEntries = zip.entries();
            
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
        }
        catch (Exception e) {
            Launcher.console.error("UTILITY_downloader.unzipFile() : " + e.toString());
            e.printStackTrace();
        }

    }    
}
