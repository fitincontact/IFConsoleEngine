package com.ifce.model.asm.singletons;

import com.ifce.model.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class RoomAsmList {
    private final List<Room> rooms = new ArrayList<>();

    public void add(final Room room) {
        rooms.add(room);
    }
}