package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.*;
import com.ifce.format.Format;
import com.ifce.model.singletons.Game;
import com.ifce.model.singletons.Objects;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
public class AddingDoorsHandlerTest {
    private AddingDoorsHandler service;
    private AsmList asmList;

    @BeforeEach
    void initUseCase() {
        val dialogAsmList = new DialogAsmList();
        val itemAsmList = new ItemAsmList();
        val roomAsmList = new RoomAsmList();
        val doorAsmList = new DoorAsmList();
        val objects = new Objects();
        val gameAsm = new GameAsm();

        val  format = new Format();
        val game = new Game(format,objects);

        asmList = new AsmList(
                dialogAsmList,
                doorAsmList,
                itemAsmList,
                roomAsmList,
                gameAsm,
                objects,
                game
        );

        service = new AddingDoorsHandler(asmList);
    }

    @Test
    public void validatePlayer() {
        assertEquals(
                "Assembler RuntimeException: Assembler.ValidateHandler: Player is not created",
                assertThrowsExactly(RuntimeException.class, () -> service.exec()).getMessage()
        );
    }

    @Test
    public void validateObject() {
        assertEquals(
                "Assembler RuntimeException: Assembler.ValidateHandler: Player is not created",
                assertThrowsExactly(RuntimeException.class, () -> service.exec()).getMessage()
        );
    }
}
