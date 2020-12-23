package com.fitincontact.engine.main.history;

import com.fitincontact.engine.main.object.Room;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class RoomHistory implements Serializable {

    private static final long serialVersionUID = -7868882172095239441L;
    private static RoomHistory instance;
    private final Set<Room> rooms = new HashSet<>();
    private final Deque<Room> roomHistory = new ArrayDeque<>();

    private RoomHistory() {
    }

    public static RoomHistory getInstance() {
        if (instance == null) {
            instance = new RoomHistory();
        }
        return instance;
    }

    public void add(final Room room) throws Exception {
        if (rooms.stream().anyMatch(r -> r.getName().equals(room.getName()))) {
            throw new Exception("field 'name' for room must be unique!");
        }
        rooms.add(room);
    }

    public void push(final Room room) {
        roomHistory.push(room);
    }

    public void set(final RoomHistory r) {
        rooms.removeAll(rooms);
        rooms.addAll(r.rooms);
        roomHistory.removeAll(roomHistory);
        roomHistory.addAll(r.roomHistory);
    }

}