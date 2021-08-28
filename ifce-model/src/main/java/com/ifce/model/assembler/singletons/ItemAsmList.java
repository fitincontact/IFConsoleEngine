package com.ifce.model.assembler.singletons;

import com.ifce.model.main.Item;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ItemAsmList {
    private final List<Item> items = new ArrayList<>();

    public void add(final Item item) {
        items.add(item);
    }
}