package com.ifce.api.old.main.object;

import com.ifce.api.old.Enter1;
import com.ifce.api.old.Exit1;
import com.ifce.api.old.main.format.Format1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Room1 implements Serializable {

    private static final long serialVersionUID = -8988988578369997739L;

    private final Format1 format1 = Format1.getInstance();
    private boolean isFirst = true;

    private final String name;
    private final String title;
    private final List<Item1> item1s = new ArrayList<>();
    private final List<Way1> way1s = new ArrayList<>();
    private String description;
    private Enter1 enter1;
    private Exit1 exit1;

    protected Room1(
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

    public List<Item1> getItems() {
        return item1s;
    }

    public List<Way1> getWays() {
        return way1s;
    }

    public Exit1 getExit() {
        return exit1;
    }

    public void add(final Exit1 exit1) {
        this.exit1 = exit1;
    }

    public Enter1 getEnter() {
        return enter1;
    }

    public void add(final Enter1 use) {
        this.enter1 = use;
    }

    public String getEnterTxt() {
        return format1.getEnterTxt();
    }

    public String getExitTxt() {
        return format1.getExitTxt();
    }

    public boolean exit(
            final Room1 room1To,
            final Inventory1 inventory1
    ) {
        if (exit1 != null) {
            return exit1.apply(room1To, inventory1);
        }
        return true;
    }

    public boolean enter(
            final Room1 room1From,
            final Inventory1 inventory1
    ) {
        if (enter1 != null) {
            enter1.apply(room1From, inventory1);
        }
        return true;
    }

    public List<String> toStrRoom(final Inventory1 inventory1) {
        final var print = new ArrayList<String>();
        if (isFirst) {
            print.add(format1.getRoomTitleHead() + title);
        }
        isFirst = false;
        print.add(format1.getRoomDescriptionHead() + description);
        print.add(riString());
        print.add(rwString());
        print.addAll(inventory1.toStr());
        return print;
    }

    public String riString() {
        final var print = new AtomicReference<String>(Format1.EMPTY);
        item1s.forEach(i -> print.set(print + i.getRoomDescription() + format1.getRoomItemSplit()));
        final String body = print.get().isEmpty() ? Format1.EMPTY : print.get().substring(0, print.get().length() - 1);
        return format1.getRoomItemsHead() + body;
    }

    public String rwString() {
        final var print = new AtomicReference<String>(Format1.EMPTY);
        way1s.forEach(i -> print.set(print + i.getWayTitle() + format1.getRoomWaySplit()));
        final String body = print.get().isEmpty() ? Format1.EMPTY : print.get().substring(0, print.get().length() - 2);
        return format1.getWayHead() + body;
    }

    public String roomAndInventoryString(final Inventory1 inventory1) {
        final String print = format1.getRoomTitleHead() + title +
                format1.getRoomDescriptionHead() + description +
                riString() +
                inventory1 +
                rwString();
        return print;
    }

    public void add(final Item1... item1s) {
        this.item1s.addAll(Arrays.asList(item1s));
    }

    public void add(final Way1... way1s) {
        this.way1s.addAll(Arrays.asList(way1s));
    }

    public boolean remove(final Item1 item1) {
        if (item1s.contains(item1)) {
            item1s.remove(item1);
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
        final var other = (Room1) obj;
        return other.name.equals(name) &&
                other.title.equals(title) &&
                other.description.equals(description);
    }
}