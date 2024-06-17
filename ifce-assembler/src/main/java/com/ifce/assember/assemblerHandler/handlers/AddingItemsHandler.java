package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ifce.assember.assemblerHandler.handlers.AssemblerHandler.throwError;

/**
 * Add items to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingItemsHandler implements CoRHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getItemAsmList().getItemAsmList().forEach(itemAsm -> {
            var asmName = itemAsm.getName();
            if (asmList.getObjects().isExistsItem(asmName)) {
                throwError(String.format("Assembler.addItems: There is duplicate item name [%s]", asmName));
            } else if (asmList.getObjects().isExists(asmName)) {
                throwError(String.format("Assembler.addItems: There is duplicate object name [%s]", asmName));
            } else {
                asmList.getObjects().add(itemAsm.getItem());
            }
        });
    }
}