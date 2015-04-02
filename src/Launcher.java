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
 * @author dodan_000
 */
public class Launcher {
   
    //VARIABLES
    public static String slash = "/";
    public static String launcherVersion = "";
    public static String startDir = System.getProperty("user.dir");
    public static String OS = System.getProperty("os.name").toLowerCase();
    public static String launcherFile = "";
    public static String accountsFile = "";
    public static String CONSOLE_logFile = "";
    
    public static String LAUNCHER_path = "";
    public static String JAVA_path = "";
    public static int JAVA_ram = 0;
    public static boolean CONSOLE_enabled = true;
    public static boolean CONSOLE_show = false;
    public static boolean CONSOLE_log = true;
    
    public static String currentModpack = "";
    
    
    //HELPERS
    public static UTILITY_filehelper filehelper = new UTILITY_filehelper();
    public static UTILITY_console console = new UTILITY_console();
    public static UTILITY_settings settings = new UTILITY_settings();
    
    //GUI
    public static GUI_Launcher gui_launcher = new GUI_Launcher();
    public static GUI_Setup gui_setup = new GUI_Setup();
    public static GUI_Settings gui_settings = new GUI_Settings();
    public static GUI_Console gui_console = new GUI_Console();
    
    public static void main(String args[]) {
        
        if (OS.contains("windows")) {slash = "\\";}
        
        launcherFile = startDir + slash +  "launcher.json";
        accountsFile = startDir + slash +  "accounts.json";
        CONSOLE_logFile = startDir + slash + "launcher.log";
        
        settings.load();
        
        try {            
            
            if (!filehelper.fileExists(launcherFile)) {
                console.message("Launcher is running First time setup...");
                gui_setup.init();
                gui_setup.show();
            }
            else {
                gui_launcher.init();
                gui_launcher.show();
                gui_settings.init();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }    
    
    public static void close() {
        gui_launcher.dispose();
        gui_setup.dispose();
        gui_settings.dispose();
        gui_console.dispose();
        System.exit(0);
    }
    
}
