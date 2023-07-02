package com.ifce.service;

import com.ifce.model.common.*;

/**
 * Server assembles all model ({@link com.ifce.model.common.Item}, {@link com.ifce.model.common.Room}, {@link com.ifce.model.common.Door},
 * {@link com.ifce.model.common.Dialog})
 * <p>
 * Server uses string names.
 * <p>
 * There are two ways for assembly game (IFCE uses 2.):
 * 1. using class names (order is strong important)
 * var yard = new Room("Big yard");
 * var bottle = new Item("bottle", yard);//item bottle places in yard
 * <p>
 * But in this way order of model is strong. We don`t write like this:
 * var bottle = new Item("bottle", yard);
 * var yard = new Room("Big yard");
 * because object 'var yard' created after object bottle
 * <p>
 * AND
 * <p>
 * 2. using string name (order is not important)
 * var bottle = new Item("bottle","yard");
 * var yard = new Room("yard");
 * Service Assembler builds Game state (move bottle into yard)
 * <p>
 * Developer must keep requirement:
 * !string name and string place name must be equal!:
 * var bottle = new Item("bottle","yard");//place name = "yard"
 * var yard = new Room("yard");//string name = "yard"
 * place name = string name = "yard"
 */
public interface AssemblerService {
    /**
     * Assembling developer code and building {@link com.ifce.model.singletons.Game} state
     */
    void start(String annotation);

    Player getPlayer(String name, String place);

    Room getRoom(String name);

    Door getDoor(String name, String roomFrom, String roomTo);

    Item getItem(String name, String place);

    Dialog getDialog(String title, Dialog... dialogs);

}