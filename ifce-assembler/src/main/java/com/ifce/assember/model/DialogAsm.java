package com.ifce.assember.model;

import com.ifce.assember.assemblerHandler.AssemblerHandlerService;
import com.api.model.common.Dialog;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Assembling date for dialogs
 * Use in {@link AssemblerHandlerService} for assembling
 */
@Data
public class DialogAsm {
    /**
     * Map to {@link Objects#dialogs}
     */
    @SuppressWarnings("JavadocReference")
    private final String name;
    /**
     * General game object for game engine
     */
    private final List<Dialog> dialogs = new ArrayList<>();

    public DialogAsm(
            String title,
            Dialog... dialogs
    ) {
        this.name = title;
        this.dialogs.addAll(Arrays.asList(dialogs));
    }
}