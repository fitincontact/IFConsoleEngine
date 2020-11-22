package com.fitincontact.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.fitincontact.engine.Utils.*;

public class Core {

    private final Act currentAct = new Act();

    protected Core(
            Room room,
            Inventory inventory
    ) {
        currentAct.setRoom(room);
        currentAct.setInventory(inventory);
        currentAct.setVictory(false);
    }

    private void defineAct(String word) {
        if (word.indexOf("-") > 0) {
            //TODO
        }
        currentAct.getRoom().getWays().forEach(w -> {
            if (w.getWayTitle().equals(word)) {
                currentAct.setActType(ActType.WAY);
                currentAct.setWay(w);
            }
        });
        currentAct.getRoom().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                currentAct.setActType(ActType.ROOM_ITEM);
                currentAct.setRoomItem(i);
                return;
            }
        });
        currentAct.getInventory().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                currentAct.setActType(ActType.INVENTORY_ITEM);
                currentAct.setInvItem(i);
                return;
            }
        });
    }

    private void act(Act act) {
        if (act.getActType() == ActType.ROOM_ITEM) {
            p(act.getRoomItem().act(
                    act.getRoom(),
                    act.getInventory())
            );
            p("\n");
            act.setActType(ActType.UNKNOW);
        }
        if (act.getActType() == ActType.INVENTORY_ITEM) {
            act.getInvItem().pn();
            act.setActType(ActType.UNKNOW);
        }
    }

    public String start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word;
        boolean first = true;
        while (!currentAct.isVictory()) {
            if (first) {
                currentAct.pc();
                first = false;
            }
            p(">");
            word = reader.readLine();

            if (word.equals("ф.")) {
                pl("финиш");
                return "финиш";
            }
            if (word.equals("о.")) {
                pl("Объкты:");
                currentAct.getRoom().getItems().forEach(Item::pd);
            }
            if (word.equals("к.")) {
                currentAct.pc();
            }

            defineAct(word);
            act(currentAct);

            int f = 1;

        }
        return "";
    }


}
