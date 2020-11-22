package com.fitincontact.engine;

import java.util.ArrayList;
import java.util.List;

import static com.fitincontact.engine.Utils.*;

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

    public void pr() {
        p(":");
        ways.forEach(Way::pw);
        p("\n:");
        p(name);
        p("\n:");
        p(description);
        p("\n:");
        items.forEach(Item::pd);
    }

    public void add(Item item) {
        items.add(item);
    }
    public void add(Way way) {
        ways.add(way);
    }

    public void remove(final Item item){
        items.remove(item);
    }

}
