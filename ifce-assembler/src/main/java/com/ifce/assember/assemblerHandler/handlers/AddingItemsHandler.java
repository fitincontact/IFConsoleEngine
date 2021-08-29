package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.model.assembler.singletons.ItemAsmList;
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
        itemAsmList.getItems().forEach(item -> {
            val asmName = item.getAsm().getName();
            if (objects.isExistsItem(asmName)) {
                error(String.format("Assembler.addItems: There is duplicate item name [%s]", asmName));
            } else {
                objects.add(item);
            }
        });
    }
}