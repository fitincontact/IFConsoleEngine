package com.ifce.model;

import com.ifce.model.etc.Word;
import lombok.Data;

@Data
public class Door {
    /**
     * Fields for assembler {@link com.ifce.assember}
     */
    private final String roomStrFirst;
    private final String doorStrFirst;
    private final String roomStrSecond;
    private final String doorStrSecond;
    /**
     * Fields for engine {@link com.ifce.engine}
     */
    private final Room roomFirst;
    private final Word doorFirst;
    private boolean isLockFirst;
    private String lockTxtFirst;

    private final Room roomSecond;
    private final Word doorSecond;
    private boolean isLockSecond;
    private String lockTxtSecond;
}