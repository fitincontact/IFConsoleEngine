package com.ifce.api.old.main.history;

import com.ifce.api.old.main.object.Item0;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ItemHistory0 implements Serializable {

    private static final long serialVersionUID = -7761809927129811200L;

    private static ItemHistory0 instance;

    private final Set<Item0> item0s = new HashSet<>();
    private final Deque<Item0> item0History = new ArrayDeque<>();

    private ItemHistory0() {
    }

    public static ItemHistory0 getInstance() {
        if (instance == null) {
            instance = new ItemHistory0();
        }
        return instance;
    }

    public void add(final Item0 item0) throws Exception {
        if (item0s.stream().anyMatch(i -> i.getWord().equals(item0.getWord()))) {
            throw new Exception("field 'word' for item must be unique!");
        }
        item0s.add(item0);
    }

    public void push(final Item0 item0) {
        item0History.push(item0);
    }

    public void set(final ItemHistory0 i) {
        item0s.removeAll(item0s);
        item0s.addAll(i.item0s);
        item0History.removeAll(item0History);
        item0History.addAll(i.item0History);
    }
}