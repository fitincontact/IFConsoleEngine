package com.fitincontact.engine.main.object;

import com.fitincontact.engine.main.format.Format;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.fitincontact.engine.Utils.pl;

public class Inventory {
    private final Format format = Format.getInstance();
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
        final String inventoryHead = format.getInventoryHead();
        if (items.isEmpty()) {
            return inventoryHead;
        }
        final AtomicReference<String> print = new AtomicReference<>(Format.EMPTY);
        items.forEach(i -> print.set(print + i.getInvName() + format.getInventoryItem()));
        return inventoryHead + print.get().substring(0, print.get().length() - 2);
    }

    public void remove(final Item item) {
        items.remove(item);
    }

}