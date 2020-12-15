package com.fitincontact.engine.main.object;

import com.fitincontact.engine.main.core.Monitor;
import com.fitincontact.engine.main.format.Format;

import java.util.ArrayList;
import java.util.List;

import static com.fitincontact.engine.main.utils.Utils.pl;

public class Game {
    private final Format format = Format.getInstance();

    private Monitor monitor;
    private final List<Person> persons = new ArrayList<>();

    protected Game() {
    }

    protected Game(final Person person) {
        this.persons.add(person);
    }

    public void add(final Monitor monitor) {
        this.monitor = monitor;
    }

    public void add(final Person person) {
        this.persons.add(person);
    }

    public String go(final Room room) {
        monitor.setRoomCurrent(room);
        final String msg = format.getGoTxt() + room.getTitle();
        pl(msg);
        return msg;
    }
}