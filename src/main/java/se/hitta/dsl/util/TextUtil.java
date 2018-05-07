package se.hitta.dsl.util;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {


    public static Integer getIdentSpacing(String input) {

        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '\t' && input.charAt(i) != ' ') {
                break;
            }
            count++;
        }
        return count;
    }


    public static <T> T last(List<T> list) {
        return list.get(list.size() - 1);
    }


}
