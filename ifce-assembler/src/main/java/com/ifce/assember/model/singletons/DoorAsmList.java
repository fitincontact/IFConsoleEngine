package com.ifce.assember.model.singletons;

import com.ifce.assember.model.DoorAsm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all DoorAsm for {@AssemblerHandlerService}
 */
@Data
@Component
public class DoorAsmList {
    /**
     * All DoorAsm
     */
    private final List<DoorAsm> doorAsmList = new ArrayList<>();

    public void add(DoorAsm doorAsm) {
        doorAsmList.add(doorAsm);
    }
}