package com.ifce.model;

import com.ifce.model.asm.DoorAsm;
import com.ifce.model.etc.Word;
import lombok.Data;

@Data
public class Door {
    private final DoorAsm asm;

    private Word name;

    private Room roomFirst;
    private Word doorFirst;
    private boolean isLockFirst;
    private String lockTxtFirst;

    private Room roomSecond;
    private Word doorSecond;
    private boolean isLockSecond;
    private String lockTxtSecond;
}