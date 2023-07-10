package com.ifce.model.common.enums;

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
    private final String description;
}