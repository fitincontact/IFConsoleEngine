package com.ifce.api.old.main.history;

import com.ifce.api.old.main.object.Way1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class WayHistory1 implements Serializable {
    private static final long serialVersionUID = 8224917193934168792L;

    private static WayHistory1 instance;

    private final Set<Way1> way1s = new HashSet<>();

    private WayHistory1() {
    }

    public static WayHistory1 getInstance() {
        if (instance == null) {
            instance = new WayHistory1();
        }
        return instance;
    }

    public void add(final Way1 way1) throws Exception {
        if (way1s.stream().anyMatch(w -> w.getWayTitle().equals(way1.getWayTitle()))) {
            throw new Exception("field 'wayTitle' for way must be unique!");
        }
        way1s.add(way1);
    }

    public void set(final WayHistory1 w) {
        way1s.removeAll(way1s);
        way1s.addAll(w.way1s);
    }

}