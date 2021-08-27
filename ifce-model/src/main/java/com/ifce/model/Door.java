package com.ifce.model;

import com.ifce.model.asm.DoorAsm;
import lombok.Data;

@Data
public class Door extends ObjectAbstract {
    private final DoorAsm asm;

    private Room room;
    private boolean isLock;
    private String lockTxt;
}