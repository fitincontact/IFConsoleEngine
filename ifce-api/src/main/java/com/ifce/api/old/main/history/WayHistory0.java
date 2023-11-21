package com.ifce.api.old.main.history;

import com.ifce.api.old.main.object.Way0;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class WayHistory0 implements Serializable {
    private static final long serialVersionUID = 8224917193934168792L;

    private static WayHistory0 instance;

    private final Set<Way0> way0s = new HashSet<>();

    private WayHistory0() {
    }

    public static WayHistory0 getInstance() {
        if (instance == null) {
            instance = new WayHistory0();
        }
        return instance;
    }

    public void add(final Way0 way0) throws Exception {
        if (way0s.stream().anyMatch(w -> w.getWayTitle().equals(way0.getWayTitle()))) {
            throw new Exception("field 'wayTitle' for way must be unique!");
        }
        way0s.add(way0);
    }

    public void set(final WayHistory0 w) {
        way0s.removeAll(way0s);
        way0s.addAll(w.way0s);
    }

}