package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.dialog.Dialog;
import com.ifce.model.Door;
import com.ifce.model.Item;
import com.ifce.model.Room;
import com.ifce.service.Assembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IFCEServiceImpl implements IFCEService {
    private final Assembler assembler;

    @Override
    public Dialog dialog(
            String title,
            Dialog[]... dialogs
    ) {
        return null;
    }

    @Override
    public Dialog dialog(
            String request,
            String response,
            Dialog[]... dialogs
    ) {
        return null;
    }

    @Override
    public Door door(
            String roomStrFirst,
            String doorStrFirst,
            String roomStrSecond,
            String doorStrSecond
    ) {
        return null;
    }

    @Override
    public Item item(
            String name,
            String place
    ) {
        return null;
    }

    @Override
    public Room room(String name) {
        return null;
    }

    @Override
    public void setPlayer(Item player) {

    }

    @Override
    public void setAnnotation(String annotation) {

    }

    @Override
    public void start() {
        assembler.assemble();
    }
}