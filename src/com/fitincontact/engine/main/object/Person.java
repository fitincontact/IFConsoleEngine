package com.fitincontact.engine.main.object;

import com.fitincontact.engine.main.core.Act;

import static com.fitincontact.engine.Utils.*;

public class Person {
    private  Act act;

    protected Person() {
    }

    public Act getAct() {
        return act;
    }

    public void setAct(final Act act) {
        this.act = act;
    }

    public String go(final Room room){
        act.setRoomCurrent(room);
        final String msg = "совершен переход в " + room.getTitle();
        pl(msg);
        return msg;
    }
}
