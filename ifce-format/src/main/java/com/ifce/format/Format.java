package com.ifce.format;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.List;

/**
 * String samples for text decor
 */
@Data
@Component
public class Format {
    public static final String EMPTY = "";
    public static final String SPLIT_SPACE = "";
    private static Format instance;

    public static Format getInstance() {
        if (instance == null) {
            instance = new Format();
        }
        return instance;
    }

    //Console
    private String consoleHead = "> ";


    public List<String> getFlagFinish() {
        return null;
    }

    public PrintStream getFlagItems() {
        return null;
    }

    public PrintStream getFlagInventory() {
        return null;
    }

    public Object getFlagRoom() {
        return null;
    }

    public String getFlagSave() {
        return null;
    }

    public String getFlagLoad() {
        return null;
    }

    public String getUseSplitSymbol() {
        return null;
    }

    public String getUnDefininedWordIfContains() {
        return null;
    }

    public String getUnDefininedWordIfNotContains() {
        return null;
    }

    public String getUnDefininedWordUse() {
        return null;
    }
}