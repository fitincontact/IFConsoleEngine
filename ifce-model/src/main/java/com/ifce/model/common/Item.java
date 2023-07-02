package com.ifce.model.common;

import com.ifce.model.common.enums.PlaceType;
import com.ifce.model.singletons.Objects;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Item extends ObjectAbstract {
    private final Objects objects;

    private List<Item> inventory = new ArrayList<>();
    //todo
    private String place;
    //todo
    private PlaceType placeType;

    public Item(
            final String name,
            final Objects objects
    ) {
        super.add(name);
        this.objects = objects;
    }

    public void add(final Item item) {
        inventory.add(item);
    }

    public void move(final String word) {
        var type = objects.defineObjectType(word);
        switch (type) {
            case ITEM -> {
                var item = objects.getItem(word);
                move(item);
            }
            case ROOM -> {
                var room = objects.getRoom(word);
                move(room);
            }
        }
    }

    private void move(Room room) {
        //todo
    }

    public void move(final Item item) {
        //todo
        //this.inventory.
        item.add(this);
    }

    public Object getWord() {
        return null;
    }

    public List<String> use(Room roomCurrent, List<Item> itemsUse) {
        return null;
    }

    public List<String> act(Room roomCurrent, Inventory inventoryCurrent) {
        return null;
    }

    public boolean getUse() {
        return false;
    }
}