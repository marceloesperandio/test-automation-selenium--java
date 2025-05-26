package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String ESCAPE_OUTPUT_PROPERTY = "org.uncommons.reportng.escape-output";

    static {
        System.setProperty(ESCAPE_OUTPUT_PROPERTY, "false");
    }

    public static void INFO(String message) {
        log("INFO", message);
    }

    public static void ERROR(String message) {
        log("ERROR", message);
    }

    private static void log(String level, String message) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
        String methodName = caller.getMethodName();
        int lineNumber = caller.getLineNumber();
        String className = caller.getClassName();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.printf("%s [%s] [%s.%s:%d] - %s%n",
                timestamp, level, className, methodName, lineNumber, message);
    }
}
