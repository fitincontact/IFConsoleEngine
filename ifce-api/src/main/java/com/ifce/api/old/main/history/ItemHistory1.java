package com.ifce.api.old.main.history;

import com.ifce.api.old.main.object.Item1;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ItemHistory1 implements Serializable {

    private static final long serialVersionUID = -7761809927129811200L;

    private static ItemHistory1 instance;

    private final Set<Item1> item1s = new HashSet<>();
    private final Deque<Item1> item1History = new ArrayDeque<>();

    private ItemHistory1() {
    }

    public static ItemHistory1 getInstance() {
        if (instance == null) {
            instance = new ItemHistory1();
        }
        return instance;
    }

    public void add(final Item1 item1) throws Exception {
        if (item1s.stream().anyMatch(i -> i.getWord().equals(item1.getWord()))) {
            throw new Exception("field 'word' for item must be unique!");
        }
        item1s.add(item1);
    }

    public void push(final Item1 item1) {
        item1History.push(item1);
    }

    public void set(final ItemHistory1 i) {
        item1s.removeAll(item1s);
        item1s.addAll(i.item1s);
        item1History.removeAll(item1History);
        item1History.addAll(i.item1History);
    }
}