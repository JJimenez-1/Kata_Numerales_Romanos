package edu.elsmancs.numeralRoman;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RegexNumerosRomanos {

    private Map<String, String> regexMapa = new HashMap<String, String>();

    public Map<String, String> getRegexMapa() {
        return this.regexMapa;
    }

    public void addRegex(String key, String value){
        this.getRegexMapa().put(key, value);
    }

    public String getRegexValue(String key) {
        return this.regexMapa.get(key);
    }

    public Map<String, String> getRegex() {
        return this.regexMapa;
    }
    public Collection<String> getValues() {
        return this.regexMapa.values();
    }
    public long longitud() {
        return this.regexMapa.size();
    }

}
