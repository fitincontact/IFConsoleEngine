package com.ifce.model.assembler.singletons;

import com.ifce.model.main.Dialog;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DialogAsmList {
    private final List<Dialog> dialogs = new ArrayList<>();

    public void add(Dialog dialog) {
        dialogs.add(dialog);
    }
}