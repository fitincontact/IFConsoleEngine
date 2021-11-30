package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Add items to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingItemsHandler implements AssemblerHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getItemAsmList().getItemAsms().forEach(itemAsm -> {
            val asmName = itemAsm.getName();
            if (asmList.getObjects().isExistsItem(asmName)) {
                error(String.format("Assembler.addItems: There is duplicate item name [%s]", asmName));
            } else if (asmList.getObjects().isExists(asmName)) {
                error(String.format("Assembler.addItems: There is duplicate object name [%s]", asmName));
            } else {
                asmList.getObjects().add(itemAsm.getItem());
            }
        });
    }
}