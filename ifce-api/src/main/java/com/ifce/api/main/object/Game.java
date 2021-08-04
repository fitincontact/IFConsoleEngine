package com.ifce.api.main.object;

import com.ifce.api.main.core.Monitor;
import com.ifce.api.main.format.Format;
import com.ifce.api.main.history.ItemHistory;
import com.ifce.api.main.history.RoomHistory;
import com.ifce.api.main.history.WayHistory;
import com.ifce.api.main.variable.Variable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    private static final long serialVersionUID = 7975751773571518852L;
    private static Game instance;
    private final Format format = Format.getInstance();
    private final List<Person> persons = new ArrayList<>();
    private final Monitor monitor = Monitor.getInstance();
    private final RoomHistory roomHistory = RoomHistory.getInstance();
    private final WayHistory wayHistory = WayHistory.getInstance();
    private final ItemHistory itemHistory = ItemHistory.getInstance();
    private final Variable variable = Variable.getInstance();

    private Game() {
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void set(
            final Game game
    ) {
        this.format.set(game.getFormat());
        this.persons.removeAll(this.persons);
        this.persons.addAll(game.getPersons());
        this.monitor.set(game.getMonitor());
        this.roomHistory.set(game.getRoomHistory());
        this.wayHistory.set(game.getWayHistory());
        this.itemHistory.set(game.getItemHistory());
        this.variable.set(game.getVariable());
    }

    public Format getFormat() {
        return format;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public RoomHistory getRoomHistory() {
        return roomHistory;
    }

    public WayHistory getWayHistory() {
        return wayHistory;
    }

    public ItemHistory getItemHistory() {
        return itemHistory;
    }

    public Variable getVariable() {
        return variable;
    }

    public void add(final Person person) {
        this.persons.add(person);
    }

    public String go(final Room room) {
        monitor.setRoomCurrent(room);
        final String msg = format.getGoTxt() + room.getTitle();
        return msg;
    }
}