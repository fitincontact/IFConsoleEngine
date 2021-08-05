package com.ifce.api.old.main.object;

import com.ifce.api.old.Use1;
import com.ifce.api.old.main.format.Format1;

import java.io.Serializable;
import java.util.List;

public class Way1 implements Serializable {

    private static final long serialVersionUID = -3295874691010061271L;

    private final Room1 room1;
    private final String wayTitle;
    private boolean isLock;
    private String lockTxt;
    private Use1 use1;
    private String useTxt;

    protected Way1(
            final Room1 room1,
            final String wayTitle
    ) {
        this.room1 = room1;
        this.wayTitle = wayTitle;
    }

    public Room1 getRoom() {
        return room1;
    }

    public String getWayTitle() {
        return wayTitle;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(final boolean lock) {
        isLock = lock;
    }

    public String getLockTxt() {
        return lockTxt;
    }

    public void setLockTxt(final String lockTxt) {
        this.lockTxt = lockTxt;
    }

    public void add(final Use1 use1) {
        this.use1 = use1;
    }

    public String getUseTxt() {
        return useTxt;
    }

    public void setUseTxt(final String useTxt) {
        this.useTxt = useTxt;
    }

    public String use(
            final Room1 room1,
            final List<Item1> item1s
    ) {
        if (use1 == null) {
            return useTxt;
        } else {
            use1.apply(room1, item1s);
            return Format1.EMPTY;
        }
    }

    @Override
    public String toString() {
        return wayTitle;
    }
}