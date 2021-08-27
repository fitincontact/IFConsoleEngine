package com.ifce.api;

import com.ifce.model.Dialog;
import com.ifce.model.Door;
import com.ifce.model.Item;
import com.ifce.model.Room;
import org.springframework.lang.NonNull;

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
            @NonNull String title,
            @NonNull Dialog[]... dialogs);

    /**
     * Nested dialog
     * !Use only as parameter into {@link IFCEService#dialog(String, Dialog[]...)}!
     *
     * @param request  request
     * @param response response
     * @param dialogs  nested dialogs
     * @return Dialog
     */
    Dialog dialog(
            @NonNull String request,
            @NonNull String response,
            @NonNull Dialog[]... dialogs
    );

    /**
     * Main Door constructor
     *
     * @param name
     * @param roomStr
     * @return Door
     */
    Door door(
            @NonNull String name,
            @NonNull String roomStr
    );

    Item item(
            @NonNull String name,
            @NonNull String place);

    Room room(@NonNull String name);

    //Game block

    /**
     * Init Game singleton
     * Required info for assembler
     *
     * @param playerName playerName
     * @param annotation annotation
     */
    void story(
            @NonNull String playerName,
            @NonNull String annotation
    );

    /**
     * Start game
     */
    void start();
}