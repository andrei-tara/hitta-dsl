package se.hitta.dsl.util;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecutionUtil {


    public static List<String> extractBracketsPatterns(String str) {
        Pattern pattern = Pattern.compile("\\{(.+?)\\}");
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }

    public static <K, V, E> E readVariable(Map<K, V> memory, String variable) {
        String[] tokens = variable.split("\\.", 2);
        String json = (String) memory.get(tokens[0]);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        try {
            return (E) JsonPath.read(document, "$." + tokens[1]);
        } catch (PathNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String replaceVars(Map<String, Object> memory, String string, boolean encode) throws UnsupportedEncodingException {
        List<String> urlVariables = ExecutionUtil.extractBracketsPatterns(string);
        for (String variable : urlVariables) {

            String value = ExecutionUtil.readVariable(memory, variable) + "";
            string = string.replace("{" + variable + "}", (encode) ? URLEncoder.encode(value, "UTF-8") : value);
        }
        return string;
    }


}
