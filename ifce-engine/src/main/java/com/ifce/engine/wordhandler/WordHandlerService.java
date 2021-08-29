package com.ifce.engine.wordhandler;

import com.ifce.engine.wordhandler.handlers.InitialWordHandler;
import com.ifce.engine.wordhandler.handlers.ItemActWordHandler;
import com.ifce.engine.wordhandler.handlers.ItemToItemInteractionWordHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WordHandlerService {
    private final InitialWordHandler initialHandler;
    private final ItemActWordHandler itemActHandler;
    private final ItemToItemInteractionWordHandler itemToItemInteractionHandler;

    public void handle(final String word) {
        new WordHandler()
                .addHandler(initialHandler.init(word))
                .addHandler(itemActHandler)
                .addHandler(itemToItemInteractionHandler)
                .exec();
    }
}