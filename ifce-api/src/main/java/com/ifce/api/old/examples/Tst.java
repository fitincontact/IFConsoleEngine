package com.ifce.api.old.examples;

public class Tst {


    public static void main(String[] args) {
        final var t = new Tclass();
        final var tt = t.getEn();
        System.out.println(tt);
        t.setEn(Tenum.v);
        System.out.println(t.getEn());

        System.out.println(tt);
    }

}
