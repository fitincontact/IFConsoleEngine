package com.fitincontact.engine.main.object;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.fitincontact.engine.Utils.*;

public class Inventory {
    private final List<Item> items = new ArrayList<>();

    protected Inventory() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void add(final Item item) {
        items.add(item);
    }

    public void pi() {
        pl(toString());
    }

    public boolean isHave(final Item item){
        return items.contains(item);
    }

    @Override
    public String toString() {
        final String printFinal = ":";
        if (items.isEmpty()) {
            return printFinal;
        }
        final AtomicReference<String> print = new AtomicReference<>("");
        items.forEach(i -> print.set(print + i.getInvName() + ", "));
        return printFinal + print.get().substring(0, print.get().length() - 2);
    }

    public void remove(final Item item) {
        items.remove(item);
    }

}