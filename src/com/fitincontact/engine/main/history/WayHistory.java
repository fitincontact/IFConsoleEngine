package com.fitincontact.engine.main.history;

import com.fitincontact.engine.main.object.Way;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class WayHistory implements Serializable {
    private static final long serialVersionUID = 8224917193934168792L;
    private static WayHistory instance;
    private final Set<Way> ways = new HashSet<>();

    public static WayHistory getInstance() {
        if (instance == null) {
            instance = new WayHistory();
        }
        return instance;
    }

    private WayHistory() {
    }

    public void add(final Way way) throws Exception {
        if (ways.stream().anyMatch(w -> w.getWayTitle().equals(way.getWayTitle()))) {
            throw new Exception("field 'wayTitle' for way must be unique!");
        }
        ways.add(way);
    }

    public void set(final WayHistory wayHistory){

    }

}
