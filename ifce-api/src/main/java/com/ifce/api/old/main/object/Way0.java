package com.ifce.api.old.main.object;

import com.ifce.api.old.Use0;
import com.ifce.api.old.main.format.Format0;

import java.io.Serializable;
import java.util.List;

public class Way0 implements Serializable {

    private static final long serialVersionUID = -3295874691010061271L;

    private final Room0 room0;
    private final String wayTitle;
    private boolean isLock;
    private String lockTxt;
    private Use0 use0;
    private String useTxt;

    protected Way0(
            final Room0 room0,
            final String wayTitle
    ) {
        this.room0 = room0;
        this.wayTitle = wayTitle;
    }

    public Room0 getRoom() {
        return room0;
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

    public void add(final Use0 use0) {
        this.use0 = use0;
    }

    public String getUseTxt() {
        return useTxt;
    }

    public void setUseTxt(final String useTxt) {
        this.useTxt = useTxt;
    }

    public String use(
            final Room0 room0,
            final List<Item0> item0s
    ) {
        if (use0 == null) {
            return useTxt;
        } else {
            use0.apply(room0, item0s);
            return Format0.EMPTY;
        }
    }

    @Override
    public String toString() {
        return wayTitle;
    }
}