package com.ifce.assember.model;

import com.ifce.model.main.Item;
import lombok.Data;

@Data
public class ItemAsm {
    private final String name;
    private final String place;
    private final Item item;

    public ItemAsm(
            final String name,
            final String place
    ) {
        this.name = name;
        this.place = place;
        item = new Item(name);
    }
}