package com.ifce.assember.assemblerHandler.handlers;

/**
 * Handler interface for processing steps for assembling
 */
public interface AssemblerHandler {
    /**
     * Execute step
     */
    void exec();

    /**
     * RuntimeException while assembling
     *
     * @param message error message
     */
    default void error(final String message) {
        throw new RuntimeException(String.format("Assembler RuntimeException: %s", message));
    }
}