package com.ifce.model;

import com.ifce.model.asm.DialogAsm;
import lombok.Data;

@Data
public class Dialog {
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