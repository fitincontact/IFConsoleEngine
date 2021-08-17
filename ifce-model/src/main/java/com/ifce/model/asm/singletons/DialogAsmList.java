package com.ifce.model.asm.singletons;

import com.ifce.dialog.Dialog;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DialogAsmList {
    private List<Dialog> dialogs = new ArrayList<>();

    public void add(Dialog dialog) {
        dialogs.add(dialog);
    }
}