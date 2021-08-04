package com.ifce.api.main.format;

public class GeneratorFormat {

    public GeneratorFormat() {
    }

    public PreFormat newPreFormat() {
        return new PreFormat();
    }

    public int setInstance(final PreFormat preFormat) {
        return Format.setInstance(preFormat);
    }

    public Format getInstance() {
        return Format.getInstance();
    }

}