package com.service.model.common.enums;

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

    PlaceType(String description) {
    }
}