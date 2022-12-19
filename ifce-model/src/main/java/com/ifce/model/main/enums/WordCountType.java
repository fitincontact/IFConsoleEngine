package com.ifce.model.main.enums;

import lombok.RequiredArgsConstructor;

/**
 * Word count type for console word
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