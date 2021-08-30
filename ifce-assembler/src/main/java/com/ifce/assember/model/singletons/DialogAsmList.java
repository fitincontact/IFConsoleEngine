package com.ifce.assember.model.singletons;

import com.ifce.assember.model.DialogAsm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DialogAsmList {
    private final List<DialogAsm> dialogAsms = new ArrayList<>();

    public void add(DialogAsm dialogAsm) {
        dialogAsms.add(dialogAsm);
    }
}