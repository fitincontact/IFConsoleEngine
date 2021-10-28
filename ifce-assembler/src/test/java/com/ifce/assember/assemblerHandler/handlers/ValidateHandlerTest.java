package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.assember.model.singletons.GameAsm;
import com.ifce.assember.model.singletons.ItemAsmList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

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
        Mockito.when(asmList.getGameAsm()).thenReturn(gameAsm);
        Mockito.when(asmList.getGameAsm().getPlayerName()).thenReturn(null);
        Mockito.when(asmList.getItemAsmList()).thenReturn(itemAsmList);
        Mockito.when(asmList.getItemAsmList().getItem(any())).thenReturn(null);

        Assertions.assertEquals(
                "Assembler RuntimeException: Assembler.ValidateHandler: Player is not created",
                Assertions.assertThrowsExactly(RuntimeException.class, () -> service.exec()).getMessage()
        );
    }
}