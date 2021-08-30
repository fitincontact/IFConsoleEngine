package com.ifce.assember.model;

import com.ifce.model.main.Dialog;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class DialogAsm {
    private final String name;
    private final List<Dialog> dialogs = new ArrayList<>();

    public DialogAsm(
            String title,
            Dialog... dialogs
    ) {
        this.name = title;
        this.dialogs.addAll(Arrays.asList(dialogs));
    }
}