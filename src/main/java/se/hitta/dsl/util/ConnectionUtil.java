package se.hitta.dsl.util;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionUtil {

    /**
     * Download file to local file
     *
     * @param remoteURL
     * @param targetFilePath
     * @throws IOException
     */
    public static void download(String remoteURL, String targetFilePath)
            throws IOException {

        System.out.println("Download " + remoteURL);

        File file = new File(targetFilePath);
        file.getParentFile().mkdirs();

        URL downloadableFile = new URL(remoteURL);
        ReadableByteChannel readableByteChannel = Channels.newChannel(downloadableFile.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath);
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }

    /**
     * Fetch resource to text
     *
     * @param remoteURL
     * @return
     * @throws IOException
     */
    public static String fetch(String remoteURL)
            throws IOException {

        System.out.println("Fetch " + remoteURL);

        try (Scanner scanner = new Scanner(new URL(remoteURL).openStream(),
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";

        }

    }


}
