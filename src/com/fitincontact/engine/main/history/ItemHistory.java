package com.fitincontact.engine.main.history;

import com.fitincontact.engine.main.object.Item;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ItemHistory implements Serializable {

    private static final long serialVersionUID = -7761809927129811200L;

    private static ItemHistory instance;

    private final Set<Item> items = new HashSet<>();
    private final Deque<Item> itemHistory = new ArrayDeque<>();

    private ItemHistory() {
    }

    public static ItemHistory getInstance() {
        if (instance == null) {
            instance = new ItemHistory();
        }
        return instance;
    }

    public void add(final Item item) throws Exception {
        if (items.stream().anyMatch(i -> i.getWord().equals(item.getWord()))) {
            throw new Exception("field 'word' for item must be unique!");
        }
        items.add(item);
    }

    public void push(final Item item) {
        itemHistory.push(item);
    }

    public void set(final ItemHistory i) {
        items.removeAll(items);
        items.addAll(i.items);
        itemHistory.removeAll(itemHistory);
        itemHistory.addAll(i.itemHistory);
    }
}