package com.ifce.engine.wordhandler;

import com.ifce.engine.wordhandler.handlers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WordHandlerService {
    private final InitialWordHandler initialHandler;
    private final ItemActWordHandler itemActHandler;
    private final ItemToItemInteractionWordHandler itemToItemInteractionHandler;
    private final DoorActWordHandler doorActHandler;
    private final ItemToDoorInteractionWordHandler itemToDoorInteractionHandler;
    private final UnknownWordHandler unknownHandler;
    private final FinalizerWordHandler finalizerHandler;

    public void handle(final String word) {
        /*
        item - ItemActWordHandler
        item_item - ItemToItemInteractionWordHandler
        way - DoorActWordHandler
        item_way - ItemToDoorInteractionWordHandler
        unknown - UnknownWordHandler
        FinalizerWordHandler
         */
        new WordExecutor()
                .addHandler(initialHandler.init(word))
                .addHandler(itemActHandler)
                .addHandler(itemToItemInteractionHandler)
                .addHandler(doorActHandler)
                .addHandler(itemToDoorInteractionHandler)
                .addHandler(unknownHandler)
                .addHandler(finalizerHandler)
                .exec();
    }
}