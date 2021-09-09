package com.ifce.assember.model.singletons;

import com.ifce.assember.model.ItemAsm;
import com.ifce.model.main.Item;
import lombok.Data;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ItemAsmList {
    private final List<ItemAsm> itemAsms = new ArrayList<>();

    public void add(final ItemAsm itemAsm) {
        itemAsms.add(itemAsm);
    }

    public Item getItem(final String name) {
        for (val i : itemAsms) {
            if (i.getItem().getName().equals(name)) {
                return i.getItem();
            }
        }
        return null;
    }
}