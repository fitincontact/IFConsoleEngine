package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.ItemAsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Add object to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingItemsHandler implements AssemblerHandler {
    private final Objects objects;
    private final ItemAsmList itemAsmList;

    @Override
    public void exec() {
        itemAsmList.getItemAsms().forEach(itemAsm -> {
            val asmName = itemAsm.getName();
            if (objects.isExistsItem(asmName)) {
                error(String.format("Assembler.addItems: There is duplicate item name [%s]", asmName));
            } else {
                objects.add(itemAsm.getItem());
            }
        });
    }
}