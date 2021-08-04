package com.ifce.api.main.history;

import com.ifce.api.main.object.Dialog;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DialogHistory implements Serializable {

    private static final long serialVersionUID = 6094504916995085628L;

    private static DialogHistory instance;

    private final Set<Dialog> dialogs = new HashSet<>();

    private DialogHistory() {
    }

    public static DialogHistory getInstance() {
        if (instance == null) {
            instance = new DialogHistory();
        }
        return instance;
    }

    public Set<Dialog> getDialogs() {
        return dialogs;
    }
}