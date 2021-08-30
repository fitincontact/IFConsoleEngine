package com.ifce.assember.model.singletons;

import com.ifce.assember.model.ItemAsm;
import lombok.Data;
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
}