package com.ifce.service;

/**
 * Main Service (work with console and {@link com.ifce.model.singletons#Game})
 */
@SuppressWarnings("JavadocReference")
public interface EngineService {
    /**
     * Starts loop for console for word reading and the game processing
     */
    void start();
}