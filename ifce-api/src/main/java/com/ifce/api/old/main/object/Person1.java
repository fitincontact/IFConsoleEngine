package com.ifce.api.old.main.object;

import java.io.Serializable;

public class Person1 implements Serializable {
    private static final long serialVersionUID = -2132288474284088632L;
    private final String name;

    protected Person1(final String name) {
        this.name = name;
    }
}