package com.ifce.assember.model.singletons;

import com.ifce.assember.model.PlayerAsm;
import com.ifce.model.common.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerAsmList {
    private final List<PlayerAsm> playerAsmList = new ArrayList<>();

    public void add(final PlayerAsm playerAsm) {
        playerAsmList.add(playerAsm);
    }

    public Player getItem(final String name) {
        for (var i : playerAsmList) {
            if (i.getPlayer().getName().equals(name)) {
                return i.getPlayer();
            }
        }
        return null;
    }
}
