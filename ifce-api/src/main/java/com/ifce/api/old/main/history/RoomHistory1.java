package com.ifce.api.old.main.history;

import com.ifce.api.old.main.object.Room1;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class RoomHistory1 implements Serializable {

    private static final long serialVersionUID = -7868882172095239441L;
    private static RoomHistory1 instance;
    private final Set<Room1> room1s = new HashSet<>();
    private final Deque<Room1> room1History = new ArrayDeque<>();

    private RoomHistory1() {
    }

    public static RoomHistory1 getInstance() {
        if (instance == null) {
            instance = new RoomHistory1();
        }
        return instance;
    }

    public void add(final Room1 room1) throws Exception {
        if (room1s.stream().anyMatch(r -> r.getName().equals(room1.getName()))) {
            throw new Exception("field 'name' for room must be unique!");
        }
        room1s.add(room1);
    }

    public void push(final Room1 room1) {
        room1History.push(room1);
    }

    public void set(final RoomHistory1 r) {
        room1s.removeAll(room1s);
        room1s.addAll(r.room1s);
        room1History.removeAll(room1History);
        room1History.addAll(r.room1History);
    }

}