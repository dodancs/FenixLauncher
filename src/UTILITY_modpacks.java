
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
    
    private static String onlineModpacks = "";
    private String JSON_code_name = "";
    private String JSON_current_version = "";
    private String JSON_mc_version = "";
    private String JSON_is_forge = "";
    private String JSON_forge_version = "";
    private String JSON_client_only = "";
    private String JSON_name = "";
    private String JSON_versions = "";
    
    public void getModpacks() {
        Launcher.modpacksList = null;
        
        onlineModpacks = Launcher.filehelper.getRemoteFileFirstLine(Launcher.modpacksURL).toString();
        
        try {
            
            JSONArray array = Launcher.jsonhelper.getJsonArray(onlineModpacks);
            
            for (int i=0;i<array.size();i++) {
                JSONObject obj2 = Launcher.jsonhelper.getJsonObjectAt(onlineModpacks, i);
                
                JSON_code_name = Launcher.jsonhelper.oGetKeyValueString(obj2,"code_name");
                JSON_current_version = Launcher.jsonhelper.oGetKeyValueString(obj2,"current_version");
                JSON_mc_version = Launcher.jsonhelper.oGetKeyValueString(obj2,"mc_version");
                JSON_is_forge = Launcher.jsonhelper.oGetKeyValueString(obj2,"is_forge");
                JSON_forge_version = Launcher.jsonhelper.oGetKeyValueString(obj2,"forge_version");
                JSON_client_only = Launcher.jsonhelper.oGetKeyValueString(obj2,"client_only");
                JSON_name = Launcher.jsonhelper.oGetKeyValueString(obj2,"name");
                JSON_versions = Launcher.jsonhelper.oGetKeyValueString(obj2,"versions");
                
                if (JSON_is_forge.contains("true")) {
                    if (JSON_client_only.contains("false")) {
                        Launcher.console.info("Found modpack: "+JSON_name+" v"+JSON_current_version+" (MC "+JSON_mc_version+") on forge "+JSON_forge_version+" \\w server compatibility");
                    }
                    else {
                        Launcher.console.info("Found modpack: "+JSON_name+" v"+JSON_current_version+" (MC "+JSON_mc_version+") on forge "+JSON_forge_version+", client only");
                    }
                }
                else {
                    if (JSON_client_only.contains("false")) {
                        Launcher.console.info("Found modpack: "+JSON_name+" v"+JSON_current_version+" (MC "+JSON_mc_version+") \\w server compatibility");
                    }
                    else {
                        Launcher.console.info("Found modpack: "+JSON_name+" v"+JSON_current_version+" (MC "+JSON_mc_version+"), client only");
                    }
                }
            }
        }
        catch (Exception e) {
            Launcher.console.error(e.toString());
            e.printStackTrace();
        }
        
    }
    
}
