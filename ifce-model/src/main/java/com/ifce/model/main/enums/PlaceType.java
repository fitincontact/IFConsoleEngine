package com.ifce.model.main.enums;

import lombok.RequiredArgsConstructor;

/**
 * Place type
 */
@RequiredArgsConstructor
public enum PlaceType {
    ITEM("Not player inventory"),
    INVENTORY("Player inventory"),
    ROOM("Room"),

    ;
    private final String description;
}