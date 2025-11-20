package com.ai.agent.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSlicer {

    // Pattern for ACTION NAME
    private static final Pattern ACTION_NAME_PATTERN =
            Pattern.compile("<action\\s+name=\\\"(.*?)\\\">", Pattern.MULTILINE);

    // Pattern for ACTION JSON
    private static final Pattern ACTION_JSON_PATTERN =
            Pattern.compile("<action\\s+name=\\\"(.*?)\\\">([\\s\\S]*?)</action>",
                    Pattern.MULTILINE | Pattern.DOTALL);

    private TextSlicer() {}

    private static String extractUsingRegex(String text, Pattern pattern, int grpIndex) {
        if (text == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            // defensive: ensure group exists
            if (matcher.groupCount() >= grpIndex) {
                return matcher.group(grpIndex).trim();
            }
            return null;
        }
        return null;
    }

    public static String sliceActionNameFromText(String text) {
        return extractUsingRegex(text, ACTION_NAME_PATTERN, 1);
    }

    public static String sliceActionJSONFromText(String text) {
        return extractUsingRegex(text, ACTION_JSON_PATTERN, 2);
    }

    public static String sliceTextBetweenTags(String text, String tag) {

        if (text == null || tag == null) {
            return null;
        }

        String open = "<" + tag + ">";
        String close = "</" + tag + ">";
        int start = text.indexOf(open);
        int end = text.indexOf(close, start + open.length()); // ensure close after open

        if (start == -1 || end == -1 || end < start) {
            return null;
        }

        return text.substring(start + open.length(), end).trim();
    }
}
