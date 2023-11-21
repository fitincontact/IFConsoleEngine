package com.ifce.api.old.main.object;

import com.ifce.api.old.Enter0;
import com.ifce.api.old.Exit0;
import com.ifce.api.old.main.format.Format0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Room0 implements Serializable {

    private static final long serialVersionUID = -8988988578369997739L;

    private final Format0 format0 = Format0.getInstance();
    private boolean isFirst = true;

    private final String name;
    private final String title;
    private final List<Item0> item0s = new ArrayList<>();
    private final List<Way0> way0s = new ArrayList<>();
    private String description;
    private Enter0 enter0;
    private Exit0 exit0;

    protected Room0(
            final String name,
            final String title,
            final String description
    ) {

        this.name = name;
        this.title = title;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Item0> getItems() {
        return item0s;
    }

    public List<Way0> getWays() {
        return way0s;
    }

    public Exit0 getExit() {
        return exit0;
    }

    public void add(final Exit0 exit0) {
        this.exit0 = exit0;
    }

    public Enter0 getEnter() {
        return enter0;
    }

    public void add(final Enter0 use) {
        this.enter0 = use;
    }

    public String getEnterTxt() {
        return format0.getEnterTxt();
    }

    public String getExitTxt() {
        return format0.getExitTxt();
    }

    public boolean exit(
            final Room0 room0To,
            final Inventory0 inventory0
    ) {
        if (exit0 != null) {
            return exit0.apply(room0To, inventory0);
        }
        return true;
    }

    public boolean enter(
            final Room0 room0From,
            final Inventory0 inventory0
    ) {
        if (enter0 != null) {
            enter0.apply(room0From, inventory0);
        }
        return true;
    }

    public List<String> toStrRoom(final Inventory0 inventory0) {
        final var print = new ArrayList<String>();
        if (isFirst) {
            print.add(format0.getRoomTitleHead() + title);
        }
        isFirst = false;
        print.add(format0.getRoomDescriptionHead() + description);
        print.add(riString());
        print.add(rwString());
        print.addAll(inventory0.toStr());
        return print;
    }

    public String riString() {
        final var print = new AtomicReference<String>(Format0.EMPTY);
        item0s.forEach(i -> print.set(print + i.getRoomDescription() + format0.getRoomItemSplit()));
        final String body = print.get().isEmpty() ? Format0.EMPTY : print.get().substring(0, print.get().length() - 1);
        return format0.getRoomItemsHead() + body;
    }

    public String rwString() {
        final var print = new AtomicReference<String>(Format0.EMPTY);
        way0s.forEach(i -> print.set(print + i.getWayTitle() + format0.getRoomWaySplit()));
        final String body = print.get().isEmpty() ? Format0.EMPTY : print.get().substring(0, print.get().length() - 2);
        return format0.getWayHead() + body;
    }

    public String roomAndInventoryString(final Inventory0 inventory0) {
        final String print = format0.getRoomTitleHead() + title +
                format0.getRoomDescriptionHead() + description +
                riString() +
                inventory0 +
                rwString();
        return print;
    }

    public void add(final Item0... item0s) {
        this.item0s.addAll(Arrays.asList(item0s));
    }

    public void add(final Way0... way0s) {
        this.way0s.addAll(Arrays.asList(way0s));
    }

    public boolean remove(final Item0 item0) {
        if (item0s.contains(item0)) {
            item0s.remove(item0);
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((name == null) ? 0 : name.hashCode())
                + ((title == null) ? 0 : title.hashCode())
                + ((description == null) ? 0 : description.hashCode())
        ;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final var other = (Room0) obj;
        return other.name.equals(name) &&
                other.title.equals(title) &&
                other.description.equals(description);
    }
}