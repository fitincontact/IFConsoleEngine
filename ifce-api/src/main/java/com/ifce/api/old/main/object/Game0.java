package com.ifce.api.old.main.object;

import com.ifce.api.old.main.core.Monitor0;
import com.ifce.api.old.main.format.Format0;
import com.ifce.api.old.main.history.ItemHistory0;
import com.ifce.api.old.main.history.RoomHistory0;
import com.ifce.api.old.main.history.WayHistory0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game0 implements Serializable {
    private static final long serialVersionUID = 7975751773571518852L;
    private static Game0 instance;
    private final Format0 format0 = Format0.getInstance();
    private final List<Person0> person0s = new ArrayList<>();
    private final Monitor0 monitor0 = Monitor0.getInstance();
    private final RoomHistory0 roomHistory0 = RoomHistory0.getInstance();
    private final WayHistory0 wayHistory0 = WayHistory0.getInstance();
    private final ItemHistory0 itemHistory0 = ItemHistory0.getInstance();

    private Game0() {
    }

    public static Game0 getInstance() {
        if (instance == null) {
            instance = new Game0();
        }
        return instance;
    }

    public void set(
            final Game0 game0
    ) {
        this.format0.set(game0.getFormat());
        this.person0s.removeAll(this.person0s);
        this.person0s.addAll(game0.getPersons());
        this.monitor0.set(game0.getMonitor());
        this.roomHistory0.set(game0.getRoomHistory());
        this.wayHistory0.set(game0.getWayHistory());
        this.itemHistory0.set(game0.getItemHistory());
    }

    public Format0 getFormat() {
        return format0;
    }

    public List<Person0> getPersons() {
        return person0s;
    }

    public Monitor0 getMonitor() {
        return monitor0;
    }

    public RoomHistory0 getRoomHistory() {
        return roomHistory0;
    }

    public WayHistory0 getWayHistory() {
        return wayHistory0;
    }

    public ItemHistory0 getItemHistory() {
        return itemHistory0;
    }

    public void add(final Person0 person0) {
        this.person0s.add(person0);
    }

    public String go(final Room0 room0) {
        monitor0.setRoomCurrent(room0);
        final String msg = format0.getGoTxt() + room0.getTitle();
        return msg;
    }
}