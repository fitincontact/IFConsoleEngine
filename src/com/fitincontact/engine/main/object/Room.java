package com.fitincontact.engine.main.object;

import com.fitincontact.engine.api.Enter;
import com.fitincontact.engine.api.Exit;
import com.fitincontact.engine.main.format.Format;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Room implements Serializable {

    private static final long serialVersionUID = -8988988578369997739L;

    private final Format format = Format.getInstance();
    private boolean isFirst = true;

    private final String name;
    private final String title;
    private final List<Item> items = new ArrayList<>();
    private final List<Way> ways = new ArrayList<>();
    private String description;
    private Enter enter;
    private Exit exit;

    protected Room(
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

    public List<Item> getItems() {
        return items;
    }

    public List<Way> getWays() {
        return ways;
    }

    public Exit getExit() {
        return exit;
    }

    public void add(final Exit exit) {
        this.exit = exit;
    }

    public Enter getEnter() {
        return enter;
    }

    public void add(final Enter use) {
        this.enter = use;
    }

    public String getEnterTxt() {
        return format.getEnterTxt();
    }

    public String getExitTxt() {
        return format.getExitTxt();
    }

    public boolean exit(
            final Room roomTo,
            final Inventory inventory
    ) {
        if (exit != null) {
            return exit.apply(roomTo, inventory);
        }
        return true;
    }

    public boolean enter(
            final Room roomFrom,
            final Inventory inventory
    ) {
        if (enter != null) {
            enter.apply(roomFrom, inventory);
        }
        return true;
    }

    public List<String> toStrRoom(final Inventory inventory) {
        final var print = new ArrayList<String>();
        if (isFirst) {
            print.add(format.getRoomTitleHead() + title);
        }
        isFirst = false;
        print.add(format.getRoomDescriptionHead() + description);
        print.add(riString());
        print.add(rwString());
        print.addAll(inventory.toStr());
        return print;
    }

    public String riString() {
        final var print = new AtomicReference<String>(Format.EMPTY);
        items.forEach(i -> print.set(print + i.getRoomDescription() + format.getRoomItemSplit()));
        final String body = print.get().isEmpty() ? Format.EMPTY : print.get().substring(0, print.get().length() - 1);
        return format.getRoomItemsHead() + body;
    }

    public String rwString() {
        final var print = new AtomicReference<String>(Format.EMPTY);
        ways.forEach(i -> print.set(print + i.getWayTitle() + format.getRoomWaySplit()));
        final String body = print.get().isEmpty() ? Format.EMPTY : print.get().substring(0, print.get().length() - 2);
        return format.getWayHead() + body;
    }

    public String roomAndInventoryString(final Inventory inventory) {
        final String print = format.getRoomTitleHead() + title +
                format.getRoomDescriptionHead() + description +
                riString() +
                inventory +
                rwString();
        return print;
    }

    public void add(final Item... items) {
        this.items.addAll(Arrays.asList(items));
    }

    public void add(final Way... ways) {
        this.ways.addAll(Arrays.asList(ways));
    }

    public boolean remove(final Item item) {
        if (items.contains(item)) {
            items.remove(item);
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
        final var other = (Room) obj;
        return other.name.equals(name) &&
                other.title.equals(title) &&
                other.description.equals(description);
    }
}