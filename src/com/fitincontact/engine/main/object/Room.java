package com.fitincontact.engine.main.object;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.fitincontact.engine.Utils.p;
import static com.fitincontact.engine.Utils.pl;

public class Room {
    private final String name;
    private final String title;
    private final String description;
    private List<Item> items = new ArrayList<>();
    private List<Way> ways = new ArrayList<>();

    protected Room(
            String name,
            String title,
            String description
    ) {
        this.name = name;
        this.title = title;
        this.description = description;
    }

    protected Room(String name,
                   String title,
                   String description,
                   List<Item> items,
                   List<Way> ways
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

    public List<Item> getItems() {
        return items;
    }

    public List<Way> getWays() {
        return ways;
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

    public void pr() {
        prw();
        pl(":" + title);
        pl(":" + description);
        pri();
    }

    public void prw() {
        p(":");
        AtomicReference<String> print = new AtomicReference<>("");
        ways.forEach(i -> print.set(print + i.getWayTitle() + " | "));
        pl(print.get().length() == 0 ? "" : print.get().substring(0, print.get().length() - 2));
    }

    public void pri() {
        p(":");
        AtomicReference<String> print = new AtomicReference<>("");
        items.forEach(i -> print.set(print + i.getRoomDescription() + " "));
        pl(print.get().length() == 0 ? "" : print.get().substring(0, print.get().length() - 1));
    }

    public void add(Item item) {
        items.add(item);
    }

    public void add(Way way) {
        ways.add(way);
    }

    public void remove(final Item item) {
        items.remove(item);
    }

}
