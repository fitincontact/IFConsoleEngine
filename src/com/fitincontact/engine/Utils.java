package com.fitincontact.engine;

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

    public static int countSubString(String subString, String string) {
        return (string.length() - string.replace(subString, "").length()) / subString.length();
    }

    public static boolean equals(List<Item> l1, List<Item> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        AtomicBoolean isEquals = new AtomicBoolean(true);
        l1.forEach(item1 -> {
            if (!l2.contains(item1)) {
                isEquals.set(false);
            }
        });
        return isEquals.get();
    }
}
