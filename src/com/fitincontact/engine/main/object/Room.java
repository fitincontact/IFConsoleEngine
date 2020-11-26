package com.fitincontact.engine.main.object;

import com.fitincontact.engine.api.Enter;
import com.fitincontact.engine.api.Exit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.fitincontact.engine.Utils.pl;

public class Room {
    private final String name;
    private final String title;
    private String description;
    private List<Item> items = new ArrayList<>();
    private List<Way> ways = new ArrayList<>();
    private Enter enter;
    private Exit exit;
    private final String enterTxt = "я не могу войти сюда";
    private final String exitTxt = "я не могу выйти отсюда";


    protected Room(
            final String name,
            final String title,
            final String description
    ) {
        this.name = name;
        this.title = title;
        this.description = description;
    }

    protected Room(
            final String name,
            final String title,
            final String description,
            final List<Item> items,
            final List<Way> ways
    ) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.items = items;
        this.ways = ways;
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
        return enterTxt;
    }

    public String getExitTxt() {
        return exitTxt;
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

    public void pr() {
        pl(":" + title);
        pl(":" + description);
        pri();
        prw();
    }

    public void pr(final Inventory inventory) {
        pl(":" + title);
        pl(":" + description);
        pri();
        inventory.pi();
        prw();
    }

    public void pri() {
        pl(riString());
    }

    public void prw() {
        pl(rwString());
    }

    public String riString() {
        final String formatHead = ":";
        final AtomicReference<String> print = new AtomicReference<>("");
        items.forEach(i -> print.set(print + i.getRoomDescription() + " "));
        final String body = print.get().isEmpty() ? "" : print.get().substring(0, print.get().length() - 1);
        return formatHead + body;
    }

    public String rwString() {
        final String formatHead = ":";
        final AtomicReference<String> print = new AtomicReference<>("");
        ways.forEach(i -> print.set(print + i.getWayTitle() + " | "));
        final String body = print.get().isEmpty() ? "" : print.get().substring(0, print.get().length() - 2);
        return formatHead + body;
    }

    public String roomAndInventoryString(final Inventory inventory) {
        final String print = ":" + title +
                             ":" + description +
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

    public void remove(final Item item) {
        items.remove(item);
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
        final Room other = (Room) obj;
        return other.name.equals(name) &&
               other.title.equals(title) &&
               other.description.equals(description);
    }

}
