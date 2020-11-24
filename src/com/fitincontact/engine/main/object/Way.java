package com.fitincontact.engine.main.object;

import static com.fitincontact.engine.Utils.pl;

public class Way {
    private final Room room;
    private final String wayTitle;

    protected Way(
            Room room,
            String wayTitle
    ) {
        this.room = room;
        this.wayTitle = wayTitle;
    }

    public Room getRoom() {
        return room;
    }

    public String getWayTitle() {
        return wayTitle;
    }

    public void pw() {
        pl(wayTitle + "|");
    }
}
