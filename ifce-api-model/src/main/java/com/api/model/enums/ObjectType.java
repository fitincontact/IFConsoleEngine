package com.api.model.enums;

import lombok.RequiredArgsConstructor;

/**
 * ObjectType
 */
@RequiredArgsConstructor
public enum ObjectType {
    ITEM("Item"),
    ROOM("Room"),
    DOOR("Door"),
    DIALOG("Dialog"),

    ;

    ObjectType(String description) {
    }
}