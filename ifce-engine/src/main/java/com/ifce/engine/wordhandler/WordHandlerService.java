package com.ifce.engine.wordhandler;

import com.ifce.engine.wordhandler.handlers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for console word handling
 */
@RequiredArgsConstructor
@Service
public class WordHandlerService {
    private final InitialWordHandler initialHandler;
    private final ItemActWordHandler itemActHandler;
    private final ItemToItemInteractionWordHandler itemToItemInteractionHandler;
    private final ItemToDoorInteractionWordHandler itemToDoorInteractionHandler;
    private final DoorActWordHandler doorActHandler;
    private final CommandWordHandler commandHandler;
    private final UnknownWordHandler unknownHandler;
    private final FinalizerWordHandler finalizerHandler;

    public void handle(final String word) {
        new WordExecutor()
                .addHandler(initialHandler.init(word))
                .addHandler(itemActHandler)
                .addHandler(itemToItemInteractionHandler)
                .addHandler(itemToDoorInteractionHandler)
                .addHandler(doorActHandler)
                .addHandler(commandHandler)
                .addHandler(unknownHandler)
                .addHandler(finalizerHandler)
                .exec();
    }
}