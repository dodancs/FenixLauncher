import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

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
            Launcher.console.error(e.toString());
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
            Launcher.console.error(e.toString());
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
            Launcher.console.error(e.toString());
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
            Launcher.console.error(e.toString());
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
            Launcher.console.error(e.toString());
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
            Launcher.console.error(e.toString());
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
            Launcher.console.error(e.toString());
            e.printStackTrace();
        }
        
        return returnVal;
    }
}
