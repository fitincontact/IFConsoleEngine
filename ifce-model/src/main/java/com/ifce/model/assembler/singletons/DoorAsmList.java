package com.ifce.model.assembler.singletons;

import com.ifce.model.main.Door;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DoorAsmList {
    private final List<Door> doors = new ArrayList<>();

    public void add(Door door) {
        doors.add(door);
    }
}