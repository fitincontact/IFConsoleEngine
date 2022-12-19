package com.ifce.service;

/**
 * Main Service (work with console and {@link com.ifce.model.singletons#Game})
 */
public interface EngineService {
    /**
     * Execs loop for console for reads word and process Game
     */
    void start();
}