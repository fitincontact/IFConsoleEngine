package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.assember.model.singletons.GameAsm;
import com.ifce.assember.model.singletons.ItemAsmList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateHandlerTest {
    @InjectMocks
    private ValidateHandler service;
    @Mock
    private AsmList asmList;
    @Mock
    private GameAsm gameAsm;
    @Mock
    private ItemAsmList itemAsmList;

    @Test
    public void validatePlayer() {
        when(asmList.getGameAsm()).thenReturn(gameAsm);
        when(asmList.getGameAsm().getPlayerNames()).thenReturn(null);
        when(asmList.getItemAsmList()).thenReturn(itemAsmList);
        when(asmList.getItemAsmList().getItem(any())).thenReturn(null);

        assertEquals(
                "Assembler RuntimeException: Assembler.ValidateHandler: Player is not created",
                assertThrowsExactly(RuntimeException.class, () -> service.exec()).getMessage()
        );
    }
}