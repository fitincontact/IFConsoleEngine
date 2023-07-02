package com.ifce.model.common.enums;

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