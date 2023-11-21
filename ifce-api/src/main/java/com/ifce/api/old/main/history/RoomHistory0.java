package com.ifce.api.old.main.history;

import com.ifce.api.old.main.object.Room0;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class RoomHistory0 implements Serializable {

    private static final long serialVersionUID = -7868882172095239441L;
    private static RoomHistory0 instance;
    private final Set<Room0> room0s = new HashSet<>();
    private final Deque<Room0> room0History = new ArrayDeque<>();

    private RoomHistory0() {
    }

    public static RoomHistory0 getInstance() {
        if (instance == null) {
            instance = new RoomHistory0();
        }
        return instance;
    }

    public void add(final Room0 room0) throws Exception {
        if (room0s.stream().anyMatch(r -> r.getName().equals(room0.getName()))) {
            throw new Exception("field 'name' for room must be unique!");
        }
        room0s.add(room0);
    }

    public void push(final Room0 room0) {
        room0History.push(room0);
    }

    public void set(final RoomHistory0 r) {
        room0s.removeAll(room0s);
        room0s.addAll(r.room0s);
        room0History.removeAll(room0History);
        room0History.addAll(r.room0History);
    }

}