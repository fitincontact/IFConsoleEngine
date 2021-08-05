package com.ifce.api.old.main.utils;

import java.util.List;

public class Print1 {
    public static void p(final String s) {
        System.out.print(s);
    }

    public static void p(final List<String> list) {
        list.forEach(i -> System.out.print(i));
    }

    public static void pl(final String s) {
        System.out.println(s);
    }

    public static void pl() {
        System.out.println("");
    }

    public static void pl(final List<String> list) {
        list.forEach(i -> System.out.println(i));
    }
}