package com.fitincontact.engine.main.utils;

import com.fitincontact.engine.main.format.Format;
import com.fitincontact.engine.main.object.Item;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Utils {
    public static void p(final String s) {
        System.out.print(s);
    }

    public static void pl(final String s) {
        System.out.println(s);
    }

    public static int countSubString(final String subString, final String string) {
        return (string.length() - string.replace(subString, Format.EMPTY).length()) / subString.length();
    }

    public static boolean equals(final List<Item> l1, final List<Item> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        final AtomicBoolean isEquals = new AtomicBoolean(true);
        l1.forEach(item1 -> {
            if (!l2.contains(item1)) {
                isEquals.set(false);
            }
        });
        return isEquals.get();
    }
}