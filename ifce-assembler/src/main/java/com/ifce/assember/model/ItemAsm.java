package com.ifce.assember.model;

import com.ifce.model.main.Item;
import com.ifce.model.singletons.Objects;
import lombok.Data;

@Data
public class ItemAsm {
    private final String name;
    private final String place;
    private final Item item;
    private final Objects objects;

    public ItemAsm(
            final String name,
            final String place,
            final Objects objects
    ) {
        this.objects = objects;
        this.name = name;
        this.place = place;
        item = new Item(name, this.objects);
    }
}