package com.ifce.model.main.enums;

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
    private final String description;
}