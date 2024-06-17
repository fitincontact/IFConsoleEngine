package com.ifce.service.api;

import com.api.model.common.*;

public interface IFCEService {
    Player player(String name, String room);

    Room room(String name);

    Item item(String name, String place);

    Door door(String name, String roomFrom, String roomTo);

    Dialog dialog(String title, Dialog[] dialogs);

    Dialog dialog(
             String request,
             String response,
             Dialog... dialogs
    );

    void story(Player player, String annotation);

    void start(String annotation);
}
