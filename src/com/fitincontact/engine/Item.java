package com.fitincontact.engine;

import static com.fitincontact.engine.Utils.*;

public class Item {
    private final String word;
    private final boolean isForInventory;
    //if "" then invisible
    private final String invName;
    private final String roomDescription;
    private final String actText;
    private final String takeText;
    private final String useText;

    protected Item(
            String word,
            boolean isForInventory,
            String invName,
            String roomDescription,
            String actText,
            String takeText,
            String useText
    ) {
        this.word = word;
        this.isForInventory = isForInventory;
        this.invName = invName;
        this.roomDescription = roomDescription;
        this.actText = actText;
        this.takeText = takeText;
        this.useText = useText;
    }

    public String getWord() {
        return word;
    }

    public String getInvName() {
        return invName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public String getActText() {
        return actText;
    }

    public String getTakeText() {
        return takeText;
    }

    public String getUseText() {
        return useText;
    }

    public boolean isForInventory() {
        return isForInventory;
    }

    public void pd() {
        p(roomDescription + " ");
    }

    public void pn() {
        pl(invName + ", ");
    }

    public String act(
            final Room room,
            final Inventory inventory
    ) {
        if(isForInventory){
            move(room, inventory);
        }
        return actText;
    }

     public void move(
             final Room room,
             final Inventory inventory
     ){
         room.remove(this);
         inventory.add(this);
     }

}
