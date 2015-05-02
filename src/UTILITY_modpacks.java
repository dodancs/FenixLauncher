import java.util.ArrayList;
import org.json.simple.JSONArray;
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
 * @author dodan_000
 */
public class UTILITY_modpacks {
    
    private static String offlineModpacks = "";
    private String MODPACK_name = "";
    
    private static String onlineModpacks = "";
    private String JSON_name = "";
    
    public ArrayList<String> getModpacks() {
        ArrayList<String> list = new ArrayList<String>();
        
        if (Launcher.filehelper.fileExists(Launcher.modpacksFile)) {       

            offlineModpacks = Launcher.filehelper.getFirstLine(Launcher.modpacksFile).toString();
            try {
                JSONArray array = Launcher.jsonhelper.getJsonArray(offlineModpacks);
                
                for (int i=0;i<array.size();i++) {
                    JSONObject obj2 = Launcher.jsonhelper.getJsonObjectAt(offlineModpacks, i);
                    
                    /*MODPACK_codename = Launcher.jsonhelper.oGetKeyValueString(obj2,"code_name");*/
                    MODPACK_name = Launcher.jsonhelper.oGetKeyValueString(obj2,"name");
                    /*MODPACK_version = Launcher.jsonhelper.oGetKeyValueString(obj2,"version");
                    MODPACK_mc_version = Launcher.jsonhelper.oGetKeyValueString(obj2,"mc_version");
                    MODPACK_is_forge = Launcher.jsonhelper.oGetKeyValueString(obj2,"is_forge");
                    MODPACK_forge_version = Launcher.jsonhelper.oGetKeyValueString(obj2,"forge_version");
                    MODPACK_folder = Launcher.jsonhelper.oGetKeyValueString(obj2,"folder");
                    MODPACK_args = Launcher.jsonhelper.oGetKeyValueString(obj2,"args");*/

                    list.add(MODPACK_name);
                    
                }
            }
            catch (Exception e) {Launcher.console.error("UTILITY_modpacks.getModpacks() : " + e.toString());e.printStackTrace();}
        }
        else {
            Launcher.filehelper.createFile(Launcher.modpacksFile);
            Launcher.filehelper.appendFile(Launcher.modpacksFile,"[]");
        }
        
        return list;
    }
    
    public JSONObject getModpackInfo(String modpackName) {
        JSONObject modpackInfo = new JSONObject();
        
        if (Launcher.filehelper.fileExists(Launcher.modpacksFile)) {       

            offlineModpacks = Launcher.filehelper.getFirstLine(Launcher.modpacksFile).toString();
            try {
                JSONArray array = Launcher.jsonhelper.getJsonArray(offlineModpacks);
                
                for (int i=0;i<array.size();i++) {
                    JSONObject obj2 = Launcher.jsonhelper.getJsonObjectAt(offlineModpacks, i);
                    
                    MODPACK_name = Launcher.jsonhelper.oGetKeyValueString(obj2,"name");

                    if (MODPACK_name.contains(modpackName)) {
                        modpackInfo = Launcher.jsonhelper.getJsonObjectAt(offlineModpacks, i);
                    }
                    
                }
            }
            catch (Exception e) {Launcher.console.error("UTILITY_modpacks.getModpackInfo() : " + e.toString());e.printStackTrace();}
        }
        else {
            Launcher.filehelper.createFile(Launcher.modpacksFile);
            Launcher.filehelper.appendFile(Launcher.modpacksFile,"[]");
        }
        
        return modpackInfo;
    }
    
    public ArrayList<String> getOnlineModpacks() {
        
        ArrayList<String> list = new ArrayList<String>();
        
        onlineModpacks = Launcher.filehelper.getRemoteFileFirstLine(Launcher.modpacksURL).toString();
        
        try {
            
            JSONArray array = Launcher.jsonhelper.getJsonArray(onlineModpacks);
            
            for (int i=0;i<array.size();i++) {
                JSONObject obj2 = Launcher.jsonhelper.getJsonObjectAt(onlineModpacks, i);
                
                JSON_name = Launcher.jsonhelper.oGetKeyValueString(obj2,"name");
                
                list.add(JSON_name);
            }
        }
        catch (Exception e) {
            Launcher.console.error("UTILITY_modpacks.getOnlineModpacks() : " + e.toString());
            e.printStackTrace();
        }
        
        return list;
        
    }
    
    public JSONObject getOnlineModpackInfo(String modpackName) {
        
        JSONObject modpackInfo = new JSONObject();
        
        onlineModpacks = Launcher.filehelper.getRemoteFileFirstLine(Launcher.modpacksURL).toString();
        
        try {
            
            JSONArray array = Launcher.jsonhelper.getJsonArray(onlineModpacks);
            
            for (int i=0;i<array.size();i++) {
                JSONObject obj2 = Launcher.jsonhelper.getJsonObjectAt(onlineModpacks, i);
                
                JSON_name = Launcher.jsonhelper.oGetKeyValueString(obj2,"name");
                
                if (JSON_name.contains(modpackName)) {
                    modpackInfo = Launcher.jsonhelper.getJsonObjectAt(onlineModpacks, i);
                }
            }
        }
        catch (Exception e) {
            Launcher.console.error("UTILITY_modpacks.getOnlineModpackInfo() : " + e.toString());
            e.printStackTrace();
        }
        
        return modpackInfo;
        
    }
    
    public boolean checkFor(String modpackName) {
        boolean bool = false;
        
        if (Launcher.filehelper.fileExists(Launcher.modpacksFile)) {       

            offlineModpacks = Launcher.filehelper.getFirstLine(Launcher.modpacksFile).toString();
            try {
                JSONArray array = Launcher.jsonhelper.getJsonArray(offlineModpacks);
                
                for (int i=0;i<array.size();i++) {
                    JSONObject obj2 = Launcher.jsonhelper.getJsonObjectAt(offlineModpacks, i);
                    
                    MODPACK_name = Launcher.jsonhelper.oGetKeyValueString(obj2,"name");
                    
                    if (bool == false) {
                        if (MODPACK_name.contains(modpackName)) {bool = true;}
                        else {bool = false;}
                    }
                }
            }
            catch (Exception e) {Launcher.console.error("UTILITY_modpacks.checkFor() : " + e.toString());e.printStackTrace();}
        }
        else {
            Launcher.filehelper.createFile(Launcher.modpacksFile);
            Launcher.filehelper.appendFile(Launcher.modpacksFile,"[]");
        }
        
        return bool;
    }
    
    public void addModpack(String codename, String name, String version, String mc_version, boolean isforge, String forge_version, String folder, String args) {
        if (Launcher.filehelper.fileExists(Launcher.modpacksFile)) {
            
            JSONObject obj = new JSONObject();
            String newJSONString = "";
            
            obj.put("code_name", codename);
            obj.put("name", name);
            obj.put("version", version);
            obj.put("mc_version", mc_version);
            obj.put("is_forge", isforge);
            obj.put("forge_version", forge_version);
            obj.put("folder", folder);
            obj.put("args", args);

            String source = Launcher.filehelper.getFirstLine(Launcher.modpacksFile);
            
            int index = source.lastIndexOf("]");
            
            if(index >= 0&& index<source.length()) {
                newJSONString = source.substring(0, index) + obj.toJSONString() + source.substring(index);
            }
            
            Launcher.filehelper.emptyFile(Launcher.modpacksFile);
            Launcher.filehelper.appendFile(Launcher.modpacksFile, newJSONString);
            
            /*JSONArray company = new JSONArray();
            company.add("Compnay: eBay");
            company.add("Compnay: Paypal");
            company.add("Compnay: Google");
            obj.put("Company List", company);*/
            
        }
    }
    
}
