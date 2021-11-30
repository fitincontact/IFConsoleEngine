package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.DialogAsm;
import com.ifce.assember.model.singletons.*;
import com.ifce.format.Format;
import com.ifce.model.main.Dialog;
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
public class AddingDialogsHandlerTest {
    private AddingDialogsHandler service;
    private AsmList asmList;

    @BeforeEach
    void initUseCase() {
        val dialogAsmList = new DialogAsmList();
        val itemAsmList = new ItemAsmList();
        val roomAsmList = new RoomAsmList();
        val doorAsmList = new DoorAsmList();
        val objects = new Objects();
        val gameAsm = new GameAsm();

        val  format = new Format ();
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

        service = new AddingDialogsHandler(asmList);
    }

    @Test
    public void duplicate_dialog_name_Test() {
        val dialogAsm = new DialogAsm(DataAsm.DIALOG_NAME);
        asmList.getDialogAsmList().add(dialogAsm);
        val dialog = new Dialog(DataAsm.DIALOG_NAME);
        asmList.getObjects().add(dialog);

        assertEquals(
                String.format(
                        "Assembler RuntimeException: Assembler.addDialogs: There is duplicate dialog name [%s]",
                        DataAsm.DIALOG_NAME
                ),
                assertThrowsExactly(RuntimeException.class, () -> service.exec()).getMessage()
        );
    }

    @Test
    public void duplicate_object_name_Test(){
        val dialogAsm = new DialogAsm(DataAsm.DIALOG_NAME);
        asmList.getDialogAsmList().add(dialogAsm);
        val dialog = new Dialog(DataAsm.DIALOG_NAME);
        asmList.getObjects().add(dialog);

        assertEquals(
                String.format(
                        "Assembler.addDialogs: There is duplicate object name [%s]",
                        DataAsm.DIALOG_NAME
                ),
                assertThrowsExactly(RuntimeException.class, () -> service.exec()).getMessage()
        );
    }
}