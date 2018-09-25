package hr.tel.fer.dz1.htmlregex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private final static Map<String, RegexBox> NAME_TO_REGEX_BOX = new HashMap<String, RegexBox>() {{
        put("all", new RegexBox("[^\n\r]*", true));
        put("tag", new RegexBox("\\s*<(tag)((?: \\w+=(\"?)\\w+\\3)*)>((?:.|\\n|\\r)+?)<\\/\\1>", false));
        put("email", new RegexBox("(?:\\w+[-.])*\\w+@(?:[-\\w]+\\.)*\\w+", true));
        put("ipv4", new RegexBox("\\b(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\b", true));
        put("date", new RegexBox("\\b(?:[0-2]?[0-9]|30)\\/(?:0?[0-9]|1[0-2])\\/\\d{4}\\b", true));
        put("tel", new RegexBox("\\b(?:385[- ])?(?:\\d+[- ]){2}\\d+\\b", true));
    }};

    public static boolean hasRegexInfo(String name){
        return NAME_TO_REGEX_BOX.get(name) != null;
    }

    public static Optional<Pattern> getPattern(String name){
        return NAME_TO_REGEX_BOX.get(name).pattern;
    }

    public static String getPatternAsString(String name){
        return NAME_TO_REGEX_BOX.get(name).patternAsString;
    }

    public static List<String> find(Matcher m, int count){
        List<String> finds = new ArrayList<>();
        while ( m.find() && count-- != 0 ) {
            finds.add(m.group());
        }
        return finds;
    }


    private static class RegexBox {
        Optional<Pattern> pattern;
        String patternAsString;

        public RegexBox(String patternAsString, boolean compile){
            this.patternAsString = patternAsString;
            if ( compile ) {
                pattern = Optional.of(Pattern.compile(patternAsString));
            } else {
                pattern = Optional.empty();
            }
        }
    }
}
