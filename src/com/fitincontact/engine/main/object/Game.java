package com.fitincontact.engine.main.object;

import com.fitincontact.engine.main.core.Monitor;
import com.fitincontact.engine.main.format.Format;
import com.fitincontact.engine.main.history.ItemHistory;
import com.fitincontact.engine.main.history.RoomHistory;
import com.fitincontact.engine.main.history.WayHistory;
import com.fitincontact.engine.main.variable.Variable;

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

    protected Game(
            final Format format,
            final List<Person> persons,
            final Monitor monitor,
            final RoomHistory roomHistory,
            final WayHistory wayHistory,
            final ItemHistory itemHistory,
            final Variable variable
    ) {
        this.format.set(format);
        this.persons.removeAll(this.persons);
        this.persons.addAll(persons);
        this.monitor.set(monitor);
        this.roomHistory.set(roomHistory);
        this.wayHistory.set(wayHistory);
        this.itemHistory.set(itemHistory);
        this.variable.set(variable);
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
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