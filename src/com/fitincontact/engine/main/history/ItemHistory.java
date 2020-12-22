package com.fitincontact.engine.main.history;

import com.fitincontact.engine.main.object.Item;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ItemHistory {

    private static ItemHistory instance;
    private final Set<Item> items = new HashSet<Item>();
    private final Stack<Item> itemHistory = new Stack<Item>();

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

}