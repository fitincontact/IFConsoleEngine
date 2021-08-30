package com.ifce.assember.model.singletons;

import com.ifce.assember.model.DoorAsm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DoorAsmList {
    private final List<DoorAsm> doorAsms = new ArrayList<>();

    public void add(DoorAsm doorAsm) {
        doorAsms.add(doorAsm);
    }
}