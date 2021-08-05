package com.ifce.api.old.main.history;

import com.ifce.api.old.main.object.Dialog1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DialogHistory1 implements Serializable {

    private static final long serialVersionUID = 6094504916995085628L;

    private static DialogHistory1 instance;

    private final Set<Dialog1> dialog1s = new HashSet<>();

    private DialogHistory1() {
    }

    public static DialogHistory1 getInstance() {
        if (instance == null) {
            instance = new DialogHistory1();
        }
        return instance;
    }

    public Set<Dialog1> getDialogs() {
        return dialog1s;
    }
}