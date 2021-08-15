package com.ifce.api;

import com.ifce.dialog.Dialog;
import com.ifce.model.Door;
import com.ifce.model.Item;
import com.ifce.model.Room;

/**
 * Main Service for game
 */
public interface IFCEService {

    /**
     * Main Dialog constructor
     *
     * @param title   title
     * @param dialogs nested dialogs
     * @return Dialog
     */
    Dialog dialog(String title, Dialog[]... dialogs);

    /**
     * Nested dialog
     *
     * @param request  request
     * @param response response
     * @param dialogs  nested dialogs
     * @return Dialog
     */
    Dialog dialog(String request, String response, Dialog[]... dialogs);

    /**
     * Main Door constructor
     *
     * @param roomStrFirst
     * @param doorStrFirst
     * @param roomStrSecond
     * @param doorStrSecond
     * @return Door
     */
    Door door(
            String roomStrFirst,
            String doorStrFirst,
            String roomStrSecond,
            String doorStrSecond
    );

    Item item(String name, String place);

    Room room(String name);

    void setPlayer(Item player);

    void setAnnotation(String annotation);

    /**
     * Start game
     */
    void start();
}