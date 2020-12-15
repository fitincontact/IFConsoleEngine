package com.fitincontact.engine.main.object;

import com.fitincontact.engine.api.Use;

import java.util.List;

import static com.fitincontact.engine.main.utils.Utils.pl;

public class Way {
    private final Room room;
    private final String wayTitle;
    private boolean isLock;
    private String lockTxt;
    private Use use;
    private String useTxt;

    protected Way(
            final Room room,
            final String wayTitle
    ) {
        this.room = room;
        this.wayTitle = wayTitle;
    }

    public Room getRoom() {
        return room;
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

    public void add(final Use use) {
        this.use = use;
    }

    public String getUseTxt() {
        return useTxt;
    }

    public void setUseTxt(final String useTxt) {
        this.useTxt = useTxt;
    }

    public void use(
            final Room room,
            final List<Item> items
    ) {
        if (use == null) {
            pl(useTxt);
        } else {
            use.apply(room, items);
        }
    }

    @Override
    public String toString() {
        return wayTitle;
    }
}