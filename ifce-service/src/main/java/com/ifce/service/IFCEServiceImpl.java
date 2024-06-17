package com.ifce.service;

import com.api.model.common.*;
import com.ifce.service.api.IFCEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:list.properties")
public class IFCEServiceImpl implements IFCEService {
    @Autowired
    private AssemblerService assemblerService;
    @Autowired
    private EngineService engineService;

    public Player player(String name, String room) {
        return assemblerService.getPlayer(name, room);
    }

    public Room room(String name) {
        return assemblerService.getRoom(name);
    }

    public Door door(String name, String roomFrom, String roomTo) {
        return assemblerService.getDoor(name, roomFrom, roomTo);
    }

    public Item item(String name, String place) {
        return assemblerService.getItem(name, place);
    }

    public Dialog dialog(String title, Dialog... dialogs) {
        return assemblerService.getDialog(title, dialogs);
    }

    public Dialog dialog(String request, String response, Dialog... dialogs) {
        return new Dialog(request, response, dialogs);
    }

    public void story(Player player, String annotation) {

    }

    public void start(String annotation) {
        assemblerService.start(annotation);
        engineService.start();
    }
}