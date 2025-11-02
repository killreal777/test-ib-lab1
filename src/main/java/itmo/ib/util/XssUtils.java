package itmo.ib.util;

import org.springframework.web.util.HtmlUtils;

public class XssUtils {

    public static String sanitize(String input) {
        return (input != null) ? HtmlUtils.htmlEscape(input) : null;
    }
}
