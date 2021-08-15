package com.ifce.model.singleton;

import com.ifce.model.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Rooms {
    private final List<Room> rooms;
}