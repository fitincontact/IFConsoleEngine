package com.ifce.api.old.main.utils;

import com.ifce.api.old.main.format.Format0;
import com.ifce.api.old.main.object.Item0;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Etc0 {

    public static int countSubString(final String subString, final String string) {
        return (string.length() - string.replace(subString, Format0.EMPTY).length()) / subString.length();
    }

    public static boolean equals(final List<Item0> l1, final List<Item0> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        final var isEquals = new AtomicBoolean(true);
        l1.forEach(item1 -> {
            if (!l2.contains(item1)) {
                isEquals.set(false);
            }
        });
        return isEquals.get();
    }
}
