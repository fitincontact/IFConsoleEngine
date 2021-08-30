package com.ifce.assember.model.singletons;

import com.ifce.assember.model.RoomAsm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class RoomAsmList {
    private final List<RoomAsm> roomAsms = new ArrayList<>();

    public void add(final RoomAsm roomAsm) {
        roomAsms.add(roomAsm);
    }
}