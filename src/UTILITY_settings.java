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

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author dodan_000
 */
public class UTILITY_settings {
    
    public void load() {
        
        if (Launcher.filehelper.fileExists(Launcher.launcherFile)) {
            JSONParser parser = new JSONParser();
            
            try {
                    Object obj = parser.parse(new FileReader(Launcher.launcherFile));

                    JSONObject jsonObject = (JSONObject) obj;

                    //Launcher.launcherVersion = jsonObject.get("version").toString();
                    Launcher.LAUNCHER_path = jsonObject.get("launcher-path").toString();
                    Launcher.JAVA_path = jsonObject.get("java-path").toString();
                    Launcher.JAVA_ram = Integer.parseInt(jsonObject.get("max-ram").toString());
                    Launcher.CONSOLE_enabled = (boolean) jsonObject.get("console-enabled");
                    Launcher.CONSOLE_show = (boolean) jsonObject.get("console-show");
                    Launcher.CONSOLE_log = (boolean) jsonObject.get("console-filelog");
                    
                    Launcher.filehelper.initFiles();
                    
                    Launcher.console.init();
                    
                    Launcher.console.info("Settings loaded!");


            } catch (Exception e) {
                    Launcher.console.error("UTILITY_settings.load() : " + e.toString());
                    e.printStackTrace();
            }
        }
        else {
        }
    }
    
    public void saveSettings(String launcherPath,String javaPath,int javaRAM,boolean cEnabled,boolean cShow,boolean cLog) {
        
        if (!Launcher.firstStart) {
            Launcher.filehelper.emptyFile(Launcher.launcherFile);
            JSONObject obj = new JSONObject();
            obj.put("version", Launcher.launcherVersion);
            obj.put("os", Launcher.OS);
            obj.put("launcher-path", launcherPath);
            obj.put("java-path", javaPath);
            obj.put("max-ram", javaRAM);
            obj.put("console-enabled", cEnabled);
            obj.put("console-show", cShow);
            obj.put("console-filelog", cLog);
            
            Launcher.filehelper.createDir(launcherPath);
            
            Launcher.filehelper.appendFile(Launcher.launcherFile, obj.toJSONString());
            
            Launcher.console.info("Settings saved successfully!");
        }
        else {
            
            JSONObject obj = new JSONObject();
            obj.put("version", Launcher.launcherVersion);
            obj.put("os", Launcher.OS);
            obj.put("launcher-path", launcherPath);
            obj.put("java-path", javaPath);
            obj.put("max-ram", javaRAM);
            obj.put("console-enabled", cEnabled);
            obj.put("console-show", cShow);
            obj.put("console-filelog", cLog);
            
            Launcher.filehelper.appendFile(Launcher.launcherFile, obj.toString());
            
            Launcher.LAUNCHER_path = launcherPath;
            Launcher.JAVA_path = javaPath;
            Launcher.JAVA_ram = javaRAM;
            Launcher.CONSOLE_enabled = (boolean) cEnabled;
            Launcher.CONSOLE_show = (boolean) cShow;
            Launcher.CONSOLE_log = (boolean) cLog;
            Launcher.filehelper.initFiles();
            
            Launcher.console.reload();
            
            Launcher.console.info("Settings saved successfully!");
        }
        
        reLoad();
    }
    
    public void reLoad() {
        if (Launcher.filehelper.fileExists(Launcher.launcherFile)) {
            JSONParser parser = new JSONParser();
            
            try {
                    Object obj = parser.parse(new FileReader(Launcher.launcherFile));

                    JSONObject jsonObject = (JSONObject) obj;

                    //Launcher.launcherVersion = jsonObject.get("version").toString();
                    Launcher.LAUNCHER_path = jsonObject.get("launcher-path").toString();
                    Launcher.JAVA_path = jsonObject.get("java-path").toString();
                    Launcher.JAVA_ram = Integer.parseInt(jsonObject.get("max-ram").toString());
                    Launcher.CONSOLE_enabled = (boolean) jsonObject.get("console-enabled");
                    Launcher.CONSOLE_show = (boolean) jsonObject.get("console-show");
                    Launcher.CONSOLE_log = (boolean) jsonObject.get("console-filelog");
                    
                    Launcher.console.info("Settings reloaded!");
                    Launcher.console.reload();


            } catch (Exception e) {
                    Launcher.console.error("UTILITY_settings.reLoad() : " + e.toString());
                    e.printStackTrace();
            }
        }
    }
    
}
