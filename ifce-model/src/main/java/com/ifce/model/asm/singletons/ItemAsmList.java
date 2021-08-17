package com.ifce.model.asm.singletons;

import com.ifce.model.Item;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ItemAsmList {
    private List<Item> items = new ArrayList<>();

    public void add(final Item item) {
        items.add(item);
    }
}