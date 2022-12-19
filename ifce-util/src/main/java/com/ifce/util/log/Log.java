package com.ifce.util.log;

import com.ifce.util.TextFile;

public class Log {
    //todo read from resource?
    private static final String LOG_PATH = "log";

    public static void write(final String text) {
        TextFile.write(LOG_PATH, text);
    }

    public static String read() {
        return TextFile.read(LOG_PATH);
    }
}