package com.ifce.model.common;

import lombok.Data;

@Data
public class Door extends ObjectAbstract {
    private Room room;
    private boolean isLock;
    private String lockTxt;

    public Door(final String name) {
        super.add(name);
    }

    public Object getWayTitle() {
        return null;
    }
}