
import java.io.IOException;
import java.text.ParseException;
import org.json.simple.parser.ContentHandler;

class KeyFinder implements ContentHandler{
  private Object value;
  private boolean found = false;
  private boolean end = false;
  private String key;
  private String matchKey;
        
  public void setMatchKey(String matchKey){
    this.matchKey = matchKey;
  }
        
  public Object getValue(){
    return value;
  }
        
  public boolean isEnd(){
    return end;
  }
        
  public void setFound(boolean found){
    this.found = found;
  }
        
  public boolean isFound(){
    return found;
  }
        
  public void startJSON() {
    found = false;
    end = false;
  }

  public void endJSON() {
    end = true;
  }

  public boolean primitive(Object value) {
    if(key != null){
      if(key.equals(matchKey)){
        found = true;
        this.value = value;
        key = null;
        return false;
      }
    }
    return true;
  }

  public boolean startArray() {
    return true;
  }

        
  public boolean startObject() {
    return true;
  }

  public boolean startObjectEntry(String key) {
    this.key = key;
    return true;
  }
        
  public boolean endArray() {
    return false;
  }

  public boolean endObject() {
    return true;
  }

  public boolean endObjectEntry() {
    return true;
  }
}