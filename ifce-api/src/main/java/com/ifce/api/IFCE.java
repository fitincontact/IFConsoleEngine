package com.ifce.api;

import com.api.model.common.*;
import com.ifce.service.api.IFCEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Main Service for game
 */
@Service
public class IFCE {
 @Autowired
 private IFCEService service;
    /**
     * @param name player name
     * @param room player room
     * @return player
     */
    public Player player(String name,
                         String room) {
     return service.player(name, room);
    }

    /**
     * Main Room constructor
     *
     * @param name name
     * @return Room
     */
    public Room room(@NonNull String name) {
     return service.room(name);
    }

    /**
     * Main Item constructor
     *
     * @param name  name
     * @param place room/item here this item must place
     * @return Item
     */
    public Item item(
            @NonNull String name,
            @NonNull String place) {
     return service.item(name, place);
    }

    /**
     * Main Door constructor
     *
     * @param name     name
     * @param roomFrom roomFrom (room where this door will be placed)
     * @param roomTo   roomTo
     * @return Door
     */
    public Door door(
            @NonNull String name,
            @NonNull String roomFrom,
            String roomTo) {
     return service.door(name, roomFrom, roomTo);
    }

    /**
     * Main Dialog constructor
     *
     * @param title   title
     * @param dialogs nested dialogs
     * @return Dialog
     */
    public Dialog dialog(
            @NonNull String title,
            @NonNull Dialog... dialogs) {
     return service.dialog(title, dialogs);
    }

    /**
     * Nested dialog constructor
     * !Use only as parameter into {@link IFCE#dialog(String, Dialog...)}!
     *
     * @param request  request
     * @param response response
     * @param dialogs  nested dialogs
     * @return Dialog
     */
    public Dialog dialog(
            @NonNull String request,
            @NonNull String response,
            @NonNull Dialog... dialogs
    ) {
     return service.dialog(request, response, dialogs);
    }

 public void story(
         Player player,
         String annotation) {
  service.story(player, annotation);
 }

    /**
     * Start game
     */
    public void start(String annotation) {
     service.start(annotation);
    }
}