package com.fitincontact.engine.main.object;

import com.fitincontact.engine.main.format.Format;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isHave(final Item item) {
        return items.contains(item);
    }

    public List<String> toStr() {
        final List<String> printList = new ArrayList<>();
        printList.add(format.getInventoryHead());
        if (items.isEmpty()) {
            return printList;
        }
        items.forEach(i -> printList.add(i.getInvName() + format.getInventoryItemSplit()));
        return printList;
    }

    public void remove(final Item item) {
        items.remove(item);
    }
}