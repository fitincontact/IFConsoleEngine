package com.ifce.model;

import com.ifce.model.asm.ItemAsm;
import com.ifce.model.etc.Word;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Item {
    private final ItemAsm asm;

    private Word name;
    private List<Item> inventory = new ArrayList<>();
}