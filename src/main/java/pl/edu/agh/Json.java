package pl.edu.agh;

import java.util.HashMap;
import java.util.Map;

public class Json {

    private Map<String, String> jsonMap = new HashMap<>();
    public String getValue(String key) {
        return jsonMap.get(key);
    }
    public void addValue(String key, String val) {
        jsonMap.put(key, val);
    }
    public String getJson() {
        String result = "{\n";
        for(Map.Entry e : jsonMap.entrySet()) {
            result += "\"" + e.getKey() + "\":\"" + e.getValue()  + "\",\n";
        }
        return result += "}";
    }

    @Override
    public String toString() {
        return getJson();
    }
}
