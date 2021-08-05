package com.ifce.api.old.main.object;

import com.ifce.api.old.main.core.Monitor;
import com.ifce.api.old.main.format.Format1;
import com.ifce.api.old.main.history.ItemHistory1;
import com.ifce.api.old.main.history.RoomHistory1;
import com.ifce.api.old.main.history.WayHistory1;
import com.ifce.api.old.main.variable.Variable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game1 implements Serializable {
    private static final long serialVersionUID = 7975751773571518852L;
    private static Game1 instance;
    private final Format1 format1 = Format1.getInstance();
    private final List<Person1> person1s = new ArrayList<>();
    private final Monitor monitor = Monitor.getInstance();
    private final RoomHistory1 roomHistory1 = RoomHistory1.getInstance();
    private final WayHistory1 wayHistory1 = WayHistory1.getInstance();
    private final ItemHistory1 itemHistory1 = ItemHistory1.getInstance();
    private final Variable variable = Variable.getInstance();

    private Game1() {
    }

    public static Game1 getInstance() {
        if (instance == null) {
            instance = new Game1();
        }
        return instance;
    }

    public void set(
            final Game1 game1
    ) {
        this.format1.set(game1.getFormat());
        this.person1s.removeAll(this.person1s);
        this.person1s.addAll(game1.getPersons());
        this.monitor.set(game1.getMonitor());
        this.roomHistory1.set(game1.getRoomHistory());
        this.wayHistory1.set(game1.getWayHistory());
        this.itemHistory1.set(game1.getItemHistory());
        this.variable.set(game1.getVariable());
    }

    public Format1 getFormat() {
        return format1;
    }

    public List<Person1> getPersons() {
        return person1s;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public RoomHistory1 getRoomHistory() {
        return roomHistory1;
    }

    public WayHistory1 getWayHistory() {
        return wayHistory1;
    }

    public ItemHistory1 getItemHistory() {
        return itemHistory1;
    }

    public Variable getVariable() {
        return variable;
    }

    public void add(final Person1 person1) {
        this.person1s.add(person1);
    }

    public String go(final Room1 room1) {
        monitor.setRoomCurrent(room1);
        final String msg = format1.getGoTxt() + room1.getTitle();
        return msg;
    }
}