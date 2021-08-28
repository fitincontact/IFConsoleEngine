package com.ifce.model.main;

import com.ifce.model.assembler.DialogAsm;
import lombok.Data;

@Data
public class Dialog extends ObjectAbstract {
    private DialogAsm asm;

    public Dialog(
            String title,
            Dialog[]... dialogs
    ) {
    }

    public Dialog(
            String request,
            String response,
            Dialog[]... dialogs
    ) {

    }
}