package com.ifce.api.old.main.format;

public class GeneratorFormat {

    public GeneratorFormat() {
    }

    public PreFormat1 newPreFormat() {
        return new PreFormat1();
    }

    public int setInstance(final PreFormat1 preFormat1) {
        return Format1.setInstance(preFormat1);
    }

    public Format1 getInstance() {
        return Format1.getInstance();
    }

}