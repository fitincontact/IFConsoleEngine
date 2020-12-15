package com.fitincontact.engine.main.history;

import com.fitincontact.engine.main.object.Room;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class RoomHistory {

    private static RoomHistory instance;
    private final Set<Room> rooms = new HashSet<Room>();
    private final Stack<Room> roomHistory = new Stack<Room>();

    public static RoomHistory getInstance() {
        if (instance == null) {
            instance = new RoomHistory();
        }
        return instance;
    }

    public void add(final Room room) throws Exception {
        if (rooms.stream().anyMatch(r -> r.getName().equals(room.getName()))) {
            throw new Exception("name for room must be unique!");
        }
        rooms.add(room);
    }

    public void push(final Room room) {
        roomHistory.push(room);
    }

}