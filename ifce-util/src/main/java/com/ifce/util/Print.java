package com.ifce.util;

import java.util.List;

public class Print {
    public static void p(final String s) {
        System.out.print(s);
    }

    public static void p(final List<String> list) {
        list.forEach(System.out::print);
    }

    public static void pl(final String s) {
        System.out.println(s);
    }

    public static void pl() {
        System.out.println();
    }

    public static void pl(final List<String> list) {
        list.forEach(System.out::println);
    }
}