package com.ifce.model.main;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Item extends ObjectAbstract {
    private List<Item> inventory = new ArrayList<>();

    public Item(final String name) {
        super.add(name);
    }

    public void add(final Item item) {
        inventory.add(item);
    }
}