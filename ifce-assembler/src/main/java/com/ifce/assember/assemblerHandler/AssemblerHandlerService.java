package com.ifce.assember.assemblerHandler;

import com.ifce.assember.assemblerHandler.handlers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for all assembling steps
 */
@RequiredArgsConstructor
@Service
public class AssemblerHandlerService {
    private final AddingRoomsHandler addingRoomsHandler;
    private final AddingItemsHandler addingItemsHandler;
    private final AddingDoorsHandler addingDoorsHandler;
    private final AddingDialogsHandler addingDialogsHandler;
    private final BindingItemsHandler bindingItemsHandler;
    private final BindingDoorsHandler bindingDoorsHandler;
    private final BindingDialogsHandler bindingDialogsHandler;
    private final GameProcessHandler gameProcessHandler;

    /**
     * Method includes all steps for assembling objects for game
     */
    public void handler() {
        new AssemblerExecutor()
                .addHandler(addingRoomsHandler)
                .addHandler(addingItemsHandler)
                .addHandler(addingDoorsHandler)
                .addHandler(addingDialogsHandler)
                .addHandler(bindingItemsHandler)
                .addHandler(bindingDoorsHandler)
                .addHandler(bindingDialogsHandler)
                .addHandler(gameProcessHandler)
                .exec();
    }
}