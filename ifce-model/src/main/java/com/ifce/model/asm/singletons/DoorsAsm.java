package com.ifce.model.asm.singletons;

import com.ifce.model.Door;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DoorsAsm {
    private List<Door> doors = new ArrayList<>();

    public void add(Door door) {
        doors.add(door);
    }
}