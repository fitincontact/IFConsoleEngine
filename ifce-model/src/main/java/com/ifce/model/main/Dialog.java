package com.ifce.model.main;

import lombok.Data;

@Data
public class Dialog extends ObjectAbstract {
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