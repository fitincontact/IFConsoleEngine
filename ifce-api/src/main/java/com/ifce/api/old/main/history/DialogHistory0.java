package com.ifce.api.old.main.history;

import com.ifce.api.old.main.object.Dialog0;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DialogHistory0 implements Serializable {

    private static final long serialVersionUID = 6094504916995085628L;

    private static DialogHistory0 instance;

    private final Set<Dialog0> dialog0s = new HashSet<>();

    private DialogHistory0() {
    }

    public static DialogHistory0 getInstance() {
        if (instance == null) {
            instance = new DialogHistory0();
        }
        return instance;
    }

    public Set<Dialog0> getDialogs() {
        return dialog0s;
    }
}