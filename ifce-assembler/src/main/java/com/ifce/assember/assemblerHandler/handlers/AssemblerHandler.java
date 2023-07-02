package com.ifce.assember.assemblerHandler.handlers;

/**
 * Handler interface for processing steps for assembling
 */
public class AssemblerHandler {
    /**
     * RuntimeException while assembling
     *
     * @param message error message
     */
    public static void throwError(final String message) {
        throw new RuntimeException(String.format("Assembler RuntimeException: %s", message));
    }
}