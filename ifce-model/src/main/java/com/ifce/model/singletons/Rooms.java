package com.ifce.model.singletons;

import com.ifce.model.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Rooms {
    private final List<Room> rooms = new ArrayList<>();
}