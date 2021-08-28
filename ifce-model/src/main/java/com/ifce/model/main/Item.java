package com.ifce.model.main;

import com.ifce.model.assembler.ItemAsm;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Item extends ObjectAbstract {
    private final ItemAsm asm;

    private List<Item> inventory = new ArrayList<>();

    public void add(final Item item) {
        inventory.add(item);
    }
}