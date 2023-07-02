package com.ifce.assember.model.singletons;

import com.ifce.assember.model.ItemAsm;
import com.ifce.model.common.Item;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all ItemAsm for {@AssemblerHandlerService}
 */
@Data
@Component
public class ItemAsmList {
    /**
     * All ItemAsm
     */
    private final List<ItemAsm> itemAsms = new ArrayList<>();

    public void add(final ItemAsm itemAsm) {
        itemAsms.add(itemAsm);
    }

    public Item getItem(final String name) {
        for (var i : itemAsms) {
            if (i.getItem().getName().equals(name)) {
                return i.getItem();
            }
        }
        return null;
    }
}