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
    public static String slash = "/"; //slash - windows \ ; linux /
    public static String launcherVersion = "1.0"; //launcher version
    public static String startDir = System.getProperty("user.dir"); //app start dir
    public static String OS = System.getProperty("os.name").toLowerCase(); //current OS
    public static String launcherFile = ""; //launcher file
    public static String accountsFile = ""; //accounts file
    public static String CONSOLE_logFile = ""; //console log file
    
    public static String LAUNCHER_path = ""; //launcher files path
    public static String JAVA_path = ""; //java path
    public static int JAVA_ram = 0; //java allocate RAM
    public static boolean CONSOLE_enabled = true; //console enabled status
    public static boolean CONSOLE_show = false; //console visible status
    public static boolean CONSOLE_log = true; //console file log status
    
    public static String[] modpacksList = {"Načítavam..."}; //avaliable modpacks
    public static String currentModpack = ""; //current modpack
    
    
    //HELPERS
    public static UTILITY_filehelper filehelper = new UTILITY_filehelper(); //create new filehelper
    public static UTILITY_console console = new UTILITY_console(); //create new console
    public static UTILITY_settings settings = new UTILITY_settings(); //create new settings
    public static UTILITY_modpacks modpacks = new UTILITY_modpacks(); //create new modpacks
    
    //GUIs
    public static GUI_Launcher gui_launcher = new GUI_Launcher(); //create new Launcher GUI
    public static GUI_Setup gui_setup = new GUI_Setup(); //create new Setup GUI
    public static GUI_Settings gui_settings = new GUI_Settings(); //create new Settings GUI
    public static GUI_Console gui_console = new GUI_Console(); //create new Console GUI
    
    public static void main(String args[]) {
        
        if (OS.contains("windows")) {slash = "\\";} //define the slash
        
        launcherFile = startDir + slash +  "launcher.json"; //define launcher file path based on startDir and slash
        accountsFile = startDir + slash +  "accounts.json"; //define accounts file path based on startDir and slash
        CONSOLE_logFile = startDir + slash + "launcher.log"; //define console log file path based on startDir and slash
        
        settings.load(); //load settings from launcher file
        
        try {            
            if (!filehelper.fileExists(launcherFile)) { //launcher file does not exist; launch first time setup
                console.message("Launcher is running First time setup...");
                gui_setup.init(); //init Setup GUI
                gui_setup.show(); //show Setup GUI
            }
            else { //launcher file already exists; open Launcher GUI
                gui_launcher.init(); //init Launcher GUI
                gui_launcher.show(); //show Launcher GUI
                gui_settings.init(); //init Settings GUI
            }
        }
        catch(Exception e) {
            console.error(e.toString());
            e.printStackTrace();
        }
    }    
    
    public static void close() { //launcher closing operations; close all GUIs
        gui_launcher.dispose(); //dispose Launcher GUI
        gui_setup.dispose(); //dispose Setup GUI
        gui_settings.dispose(); //dispose Settings GUI
        gui_console.dispose(); //dispose Console GUI
        System.exit(0); //exit application
    }
    
}
