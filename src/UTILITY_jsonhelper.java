
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

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
public class UTILITY_jsonhelper {
    
    public JSONArray getJsonArray(String j) {
        Object obj = JSONValue.parse(j);
        JSONArray array=(JSONArray)obj;
        return array;
    }
    
    public JSONObject getJsonObjectAt(String j, int k) {
        Object obj = JSONValue.parse(j);
        JSONArray array=(JSONArray)obj;
        JSONObject obj2=(JSONObject)array.get(k);
        return obj2;
    }
    
    public String oGetKeyValueString(JSONObject o, String k) {
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        String val = "";
        finder.setMatchKey(k);
        try{
            while(!finder.isEnd()) {
                parser.parse(o.toJSONString(), finder, true);
                if(finder.isFound()){
                    finder.setFound(false);
                    val = finder.getValue().toString();
                }     
            }
        }
        catch(Exception pe){
            Launcher.console.error(pe.toString());
            pe.printStackTrace();
        }
        
        return val;

    }
    public String aGetKeyValueString(JSONArray a, String k) {
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        String val = "";
        finder.setMatchKey(k);
        try{
            while(!finder.isEnd()) {
                parser.parse(a.toJSONString(), finder, true);
                if(finder.isFound()){
                    finder.setFound(false);
                    val = finder.getValue().toString();
                }      
            }
        }
        catch(Exception pe){
            Launcher.console.error(pe.toString());
            pe.printStackTrace();
        }
        
        return val;

    }
    
    public ArrayList<String> oGetKeyValuesArray(JSONObject o, String k) {
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        ArrayList<String> vals = new ArrayList<>();
        finder.setMatchKey(k);
        try{
            while(!finder.isEnd()){
                parser.parse(o.toJSONString(), finder, true);
                if(finder.isFound()){
                    finder.setFound(false);
                    vals.add(finder.getValue().toString());
                }     
            }
        }
        catch(Exception pe){
            Launcher.console.error(pe.toString());
            pe.printStackTrace();
        }
        
        return vals;
    }
    public ArrayList<String> aGetKeyValuesArray(JSONArray a, String k) {
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        ArrayList<String> vals = new ArrayList<>();
        finder.setMatchKey(k);
        try{
            while(!finder.isEnd()){
                parser.parse(a.toJSONString(), finder, true);
                if(finder.isFound()){
                    finder.setFound(false);
                    vals.add(finder.getValue().toString());
                }     
            }
        }
        catch(Exception pe){
            Launcher.console.error(pe.toString());
            pe.printStackTrace();
        }
        
        return vals;
    }
    
}
