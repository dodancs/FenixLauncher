import java.util.ArrayList;

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
    public static String slash = ""; //slash - windows \ ; linux /
    public static String launcherVersion = "1.1"; //launcher version
    public static String startDir = System.getProperty("user.dir"); //app start dir
    public static String OS = System.getProperty("os.name").toLowerCase(); //current OS
    public static String launcherFile = ""; //launcher file
    public static String accountsFile = ""; //accounts file
    public static String modpacksFile = ""; //modpacks file
    public static String modpacksDir = ""; //modpacks directory
    public static String downloadsDir = ""; //downloads directory
    public static String CONSOLE_logsdir = ""; //console logs directory
    public static String CONSOLE_logFile = ""; //console log file
    
    public static boolean loggedIn = false;
    public static String currentUser = "";
    
    public static boolean firstStart = false;
    
    public static String LAUNCHER_path = ""; //launcher files path
    public static String JAVA_path = ""; //java path
    public static int JAVA_ram = 0; //java allocate RAM
    public static boolean CONSOLE_enabled = true; //console enabled status
    public static boolean CONSOLE_show = false; //console visible status
    public static boolean CONSOLE_log = true; //console file log status
    
    public static String modpacksURL = "http://update.fenixportal.eu/modpacks";
    public static String modpackImgUrl = "http://img.fenixportal.eu/";
    public static ArrayList<String> modpacksList = new ArrayList<String>(); //avaliable modpacks
    public static String currentModpack = ""; //current modpack
    
    
    //HELPERS
    public static UTILITY_filehelper filehelper = new UTILITY_filehelper(); //create new filehelper
    public static UTILITY_console console = new UTILITY_console(); //create new console
    public static UTILITY_settings settings = new UTILITY_settings(); //create new settings
    public static UTILITY_modpacks modpacks = new UTILITY_modpacks(); //create new modpacks
    public static UTILITY_jsonhelper jsonhelper = new UTILITY_jsonhelper(); //create new jsonhelper
    public static UTILITY_downloader downloader = new UTILITY_downloader(); //create new downloader
    
    //GUIs
    public static GUI_Launcher gui_launcher = new GUI_Launcher(); //create new Launcher GUI
    public static GUI_Setup gui_setup = new GUI_Setup(); //create new Setup GUI
    public static GUI_Settings gui_settings = new GUI_Settings(); //create new Settings GUI
    public static GUI_Console gui_console = new GUI_Console(); //create new Console GUI
    public static GUI_ModpackEdit gui_modpackEdit = new GUI_ModpackEdit(); //create new ModpackEdit GUI
    public static GUI_Downloader gui_downloader = new GUI_Downloader(); //create new Downloader GUI
    
    public static void main(String args[]) {
        
        if (OS.contains("windows")) {slash = "\\";} else {slash = "/";} //define the slash
        launcherFile = startDir + slash +  "launcher.json"; //define launcher file path based on startDir and slash
        
        modpacksList.add("Načítavam...");
        
        if (!filehelper.fileExists(launcherFile)) {firstStart = true; console.disable();}
        else {firstStart = false; console.enable();}
           
        if (firstStart) { //launch first time setup
            gui_setup.init(); //init Setup GUI
            gui_setup.show(); //show Setup GUI
        }
        else { //load Launcher GUI
            settings.load(); //load settings from launcher file
                
            gui_launcher.init(); //init Launcher GUI
            gui_launcher.show(); //show Launcher GUI
            gui_settings.init(); //init Settings GUI
            gui_modpackEdit.init(); //init ModpackEdit GUI
            gui_downloader.init(); //init Downloader GUI
            
            if (filehelper.onlineFileExists(modpacksURL)) {loadModpacks(modpacks.getOnlineModpacks());}
            else {loadModpacks(modpacks.getModpacks());}
        }
    }    
    
    public static void loadModpacks(ArrayList<String> m) {
        
        boolean first = true;
        modpacksList.clear();
                
        for (int i=0;i<m.size();i++) {
            Launcher.console.info("Found modpack: " + m.get(i));
            modpacksList.add(m.get(i));
            if (first) {currentModpack = m.get(i); first=false;}  
        }
                
        console.info("Current modpack: " + Launcher.currentModpack);
        
        gui_launcher.updateModpacks(modpacksList.toArray(new String[modpacksList.size()]));
        
    }
    
    public static void close() { //launcher closing operations; close all GUIs
        gui_launcher.dispose(); //dispose Launcher GUI
        gui_setup.dispose(); //dispose Setup GUI
        gui_settings.dispose(); //dispose Settings GUI
        gui_console.dispose(); //dispose Console GUI
        gui_modpackEdit.dispose(); //dispose ModpackEdit GUI
        gui_downloader.dispose(); //dispose Downloader GUI
        System.exit(0); //exit application
    }
    
}
