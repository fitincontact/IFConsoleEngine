package com.ifce.model.main;

import com.ifce.model.main.enums.PlaceType;
import com.ifce.model.singletons.Objects;
import lombok.Data;
import lombok.val;

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
        val type = objects.defineObjectType(word);
        switch (type) {
            case ITEM -> {
                val item = objects.getItem(word);
                move(item);
            }
            case ROOM -> {
                val room = objects.getRoom(word);
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
}