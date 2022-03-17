package dev.aapy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 7qv_ on 14/3/2022.
 * @project SnakeHub
 */
public class SnakeUtil {
    public static String getDate() {
        return (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
    }

    public static String getHour() {
        return (new SimpleDateFormat("HH:mm")).format(new Date());
    }
}
