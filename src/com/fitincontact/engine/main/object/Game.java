package com.fitincontact.engine.main.object;

import com.fitincontact.engine.main.core.Monitor;

import static com.fitincontact.engine.Utils.*;

public class Game {
    private Monitor monitor;

    protected Game() {
    }

    public Monitor getAct() {
        return monitor;
    }

    public void setAct(final Monitor monitor) {
        this.monitor = monitor;
    }

    public String go(final Room room){
        monitor.setRoomCurrent(room);
        final String msg = "совершен переход в " + room.getTitle();
        pl(msg);
        return msg;
    }
}