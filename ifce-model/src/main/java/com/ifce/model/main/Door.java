package com.ifce.model.main;

import com.ifce.model.assembler.DoorAsm;
import lombok.Data;

@Data
public class Door extends ObjectAbstract {
    private final DoorAsm asm;

    private Room room;
    private boolean isLock;
    private String lockTxt;
}