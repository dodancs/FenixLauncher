/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author dodan_000
 */
public class UTILITY_console {
    
    public void enable() {Launcher.CONSOLE_enabled = true; if (canShow()) {Launcher.gui_console.show();}}
    public void disable() {Launcher.CONSOLE_enabled = false; if (canShow()) {Launcher.gui_console.hide();}}
    public boolean isEnabled() {return Launcher.CONSOLE_enabled;}
    
    public void show() {Launcher.CONSOLE_show = true; if (isEnabled()) {Launcher.gui_console.show();}}
    public void hide() {Launcher.CONSOLE_show = false; if (isEnabled()) {Launcher.gui_console.hide();}}
    public boolean canShow() {return Launcher.CONSOLE_show;}
    
    public void logToFile() {Launcher.CONSOLE_log = true;}
    public void notLogToFile() {Launcher.CONSOLE_log = false;}
    public boolean canLogToFile() {if (Launcher.filehelper.fileExists(Launcher.CONSOLE_logFile)) {return Launcher.CONSOLE_log;} else{return false;}}
    
    public void init() {        
        Launcher.gui_console.init();
        
        if (isEnabled()) {
            if (canShow()) {
                Launcher.gui_console.show();
            }
            message("Starting log...");
            message("Log path: " + Launcher.CONSOLE_logFile);
        }
        
        if (!Launcher.filehelper.fileExists(Launcher.CONSOLE_logFile)) {
            if (Launcher.firstStart) {}
            else {Launcher.filehelper.createDir(Launcher.CONSOLE_logsdir);Launcher.filehelper.createFile(Launcher.CONSOLE_logFile);}
        }
    }
    
    public void reload() {
        if (isEnabled()) {
            if (canShow()) {
                Launcher.gui_console.show();
            }
            else {
                Launcher.gui_console.hide();
            }
        }
        else {
            Launcher.gui_console.hide();
        }
        
        if (!Launcher.filehelper.fileExists(Launcher.CONSOLE_logFile)) {
            Launcher.filehelper.createFile(Launcher.CONSOLE_logFile);
        }
    }
    
    public String currentTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String timestamp = dateFormat.format(cal.getTime()).toString();
        return timestamp;
    }

    public void message(String text) {
        if (isEnabled()) {
            if (canLogToFile()) {
                Launcher.filehelper.appendFile(Launcher.CONSOLE_logFile, currentTimestamp() + ": " + text);
            }
            
            if (canShow()) {Launcher.gui_console.appendln(text);}
        }
    }
    public void error(String text) {
        if (isEnabled()) {
            if (canLogToFile()) {
                Launcher.filehelper.appendFile(Launcher.CONSOLE_logFile, currentTimestamp() + ": [ERROR]: " + text);
            }
            
            if (canShow()) {Launcher.gui_console.appendln("[ERROR]: " + text);}
        }
    }
    public void critical(String text) {
        if (isEnabled()) {
            if (canLogToFile()) {
                Launcher.filehelper.appendFile(Launcher.CONSOLE_logFile, currentTimestamp() + ": [CRITICAL]: " + text);
            }
            
            if (canShow()) {Launcher.gui_console.appendln("[CRITICAL]: " + text);}
        }
    }
    public void info(String text) {
        if (isEnabled()) {
            if (canLogToFile()) {
                Launcher.filehelper.appendFile(Launcher.CONSOLE_logFile, currentTimestamp() + ": [INFO]: " + text);
            }
            
            if (canShow()) {Launcher.gui_console.appendln("[INFO]: " + text);}
        }
    }
    
}
