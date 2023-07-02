package com.ifce.model.common.enums;

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