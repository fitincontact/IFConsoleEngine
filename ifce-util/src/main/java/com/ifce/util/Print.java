package com.ifce.util;

import com.ifce.util.log.Log;

import java.util.List;

public class Print {
    public static void p(final String s) {
        Log.write(s);
        System.out.print(s);
    }

    public static void p(final List<String> list) {
        list.forEach(Log::write);
        list.forEach(System.out::print);
    }

    public static void pl(final String s) {
        Log.write(s);
        System.out.println(s);
    }

    public static void pl() {
        System.out.println();
    }

    public static void pl(final List<String> list) {
        list.forEach(Log::write);
        list.forEach(System.out::println);
    }
}