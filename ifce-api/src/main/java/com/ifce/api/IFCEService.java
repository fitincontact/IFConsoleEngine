package com.ifce.api;

import com.ifce.model.main.Dialog;
import com.ifce.model.main.Door;
import com.ifce.model.main.Item;
import com.ifce.model.main.Room;
import org.jetbrains.annotations.NotNull;

/**
 * Main Service for game
 */
public interface IFCEService {
    //Model block

    /**
     * Main Dialog constructor
     *
     * @param title   title
     * @param dialogs nested dialogs
     * @return Dialog
     */
    Dialog dialog(
            @NotNull String title,
            @NotNull Dialog... dialogs);

    /**
     * Nested dialog constructor
     * !Use only as parameter into {@link IFCEService#dialog(String, Dialog...)}!
     *
     * @param request  request
     * @param response response
     * @param dialogs  nested dialogs
     * @return Dialog
     */
    Dialog dialog(
            @NotNull String request,
            @NotNull String response,
            @NotNull Dialog... dialogs
    );

    /**
     * Main Door constructor
     *
     * @param name     name
     * @param roomFrom roomFrom (room where this door will be placed)
     * @param roomTo   roomTo
     * @return Door
     */
    Door door(
            @NotNull String name,
            @NotNull String roomFrom,
            String roomTo
    );

    /**
     * Main Item constructor
     *
     * @param name  name
     * @param place room/item here this item must place
     * @return Item
     */
    Item item(
            @NotNull String name,
            @NotNull String place);

    /**
     * Main Room constructor
     *
     * @param name name
     * @return Room
     */
    Room room(@NotNull String name);

    //Game block

    /**
     * Init Game singleton
     * Required info for assembler
     *
     * @param playerName playerName
     * @param annotation annotation
     */
    void story(
            @NotNull String playerName,
            @NotNull String annotation
    );

    /**
     * Start game
     */
    void start();
}