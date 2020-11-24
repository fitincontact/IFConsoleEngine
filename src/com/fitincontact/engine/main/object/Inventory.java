package com.fitincontact.engine.main.object;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.fitincontact.engine.Utils.*;

public class Inventory {
    private List<Item> items = new ArrayList<>();

    protected Inventory() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void add(Item item) {
        items.add(item);
    }

    public void pi() {
        p(":");
        AtomicReference<String> print = new AtomicReference<>("");
        items.forEach(i -> print.set(print + i.getInvName() + ", "));
        pl(print.get().substring(0, print.get().length() - 2));
    }

    public void remove(Item item) {
        items.remove(item);
    }

}