package com.ifce.api.old.main.variable;

import java.util.ArrayList;

public class FormatRec {

    public static void main(String[] arg) {
        var l = 1L;
        var list = new ArrayList<String>();

        var f = new Fmt("", "");
        System.out.println(f);
    }

    public record Fmt(String s1, String s2) {
    }
}