package com.ifce.assember.model.singletons;

import com.ifce.assember.model.DialogAsm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all DialogAsm for {@AssemblerHandlerService}
 */
@Data
@Component
public class DialogAsmList {
    /**
     * All DialogAsm
     */
    private final List<DialogAsm> dialogAsmList = new ArrayList<>();

    public void add(DialogAsm dialogAsm) {
        dialogAsmList.add(dialogAsm);
    }
}