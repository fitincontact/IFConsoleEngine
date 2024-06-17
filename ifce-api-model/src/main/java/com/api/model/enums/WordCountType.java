package com.api.model.enums;

import lombok.RequiredArgsConstructor;

/**
 * Words count type for console word
 */
@RequiredArgsConstructor
public enum WordCountType {
    ONE("One word"),
    TWO("Two words"),
    MULTI("Multiple words"),
    EMPTY("Initial type (before word defining)"),

    ;

    WordCountType(String description) {
    }
}