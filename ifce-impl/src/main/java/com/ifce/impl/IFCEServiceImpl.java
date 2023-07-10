package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.common.*;
import com.ifce.model.singletons.State;
import com.ifce.service.AssemblerService;
import com.ifce.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
//@ComponentScan(basePackages = "")
@PropertySource("classpath:list.properties")
public class IFCEServiceImpl implements IFCEService {
    @Value("${properties.doc}")
    String t;

    @Autowired
    private AssemblerService assemblerService;
    @Autowired
    private EngineService engineService;

    //todo delete
    public IFCEServiceImpl(AsmList asmList, AssemblerService assemblerService, EngineService engineService, State state) {
    }

    @Override
    public Player player(String name, String room) {
        return assemblerService.getPlayer(name, room);
    }

    @Override
    public Room room(String name) {
        return assemblerService.getRoom(name);
    }

    @Override
    public Door door(String name, String roomFrom, String roomTo) {
        return assemblerService.getDoor(name, roomFrom, roomTo);
    }

    @Override
    public Item item(String name, String place) {
        return assemblerService.getItem(name, place);
    }

    @Override
    public Dialog dialog(String title, Dialog... dialogs) {
        return assemblerService.getDialog(title, dialogs);
    }

    @Override
    public Dialog dialog(String request, String response, Dialog... dialogs) {
        return new Dialog(request, response, dialogs);
    }

    @Override
    public void start(String annotation) {
        assemblerService.start(annotation);
        engineService.start();
    }

    @Override
    public void story(String item, String annotation) {

    }
}