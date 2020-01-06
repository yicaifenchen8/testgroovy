import org.apache.bsf.util.Bean;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        exec("src/test.groovy");
    }

    public static String format(String str, Map obj) {
        Matcher matcher = Pattern.compile("\\{(\\w+)\\}").matcher(str);
        while (matcher.find()){
            String key  = matcher.group(1);
            str = str.replace("{"+key+"}", String.valueOf(obj.get(key)));
        }
        return str;
    }

    //支持JavaScript、JRuby、Jython和Groovy
    public static ScriptEngine getScriptEngine(){
        return new ScriptEngineManager().getEngineByName("Groovy");//java 9支持
    }

    public static Object evalStr(String str){
        try {
            // 执行js代码
            Object ret = getScriptEngine().eval(str);
            return (String) ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Object evalFile(String file){
        try {
            // 执行js代码
            Object ret = getScriptEngine().eval(new FileReader(file));
            return (String) ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String str(String str) {
        return (String) evalStr(str);
    }

    public static String json(String json) {
        return (String) evalStr("JSON.stringify(" + json + ")");
    }

    public static String exec(String file) {
        return (String) evalFile(file);
    }
}
