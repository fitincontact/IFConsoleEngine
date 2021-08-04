package com.ifce.api.main.object;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = -2132288474284088632L;
    private final String name;

    protected Person(final String name) {
        this.name = name;
    }
}