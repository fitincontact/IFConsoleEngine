package com.fitincontact.engine;

import java.util.ArrayList;
import java.util.List;

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

    public void pi(){
        p("\n:");
        items.forEach(Item::pn);
    }

}