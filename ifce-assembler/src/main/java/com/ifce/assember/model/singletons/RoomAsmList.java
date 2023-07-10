package com.ifce.assember.model.singletons;

import com.ifce.assember.model.RoomAsm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all RoomAsm for {@AssemblerHandlerService}
 */
@Data
@Component
public class RoomAsmList {
    /**
     * All RoomAsm
     */
    private final List<RoomAsm> roomAsmList = new ArrayList<>();

    public void add(final RoomAsm roomAsm) {
        roomAsmList.add(roomAsm);
    }
}