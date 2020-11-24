package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.enums.ActType;
import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Item;
import com.fitincontact.engine.main.object.Person;
import com.fitincontact.engine.main.object.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.fitincontact.engine.Utils.p;
import static com.fitincontact.engine.Utils.pl;

public class Core {

    private final Act currentAct = new Act();
    private final Person person;

    protected Core(
            final Person person,
            final Room room,
            final Inventory inventory
    ) {
        this.currentAct.setRoomCurrent(room);
        this.currentAct.setInventoryCurrent(inventory);
        this.currentAct.setVictory(false);
        this.person = person;
        this.person.setAct(currentAct);
    }

    final List<Room> rooms =new ArrayList<>();

    private void defineAct(String word) {

        AtomicBoolean isInItems = new AtomicBoolean(false);
        AtomicBoolean isInInventory = new AtomicBoolean(false);
        AtomicBoolean isInWay = new AtomicBoolean(false);

        if (word.indexOf("-") > 0) {
            for (String splitWord : word.split("-")) {

                String splitUnSpaceWord = splitWord.replaceAll("\\s", "");
                final List<String> splitWords = new ArrayList<>();
                splitWords.add(splitUnSpaceWord);

                currentAct.getRoomCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(splitUnSpaceWord)) {
                        currentAct.setActType(ActType.USE);
                        currentAct.getItemsUse().add(i);
                    }
                });
                currentAct.getInventoryCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(splitUnSpaceWord)) {
                        currentAct.setActType(ActType.USE);
                        currentAct.getItemsUse().add(i);
                    }
                });

                if (currentAct.getItemsUse()
                        .stream()
                        .anyMatch(i ->
                                splitWords
                                        .stream()
                                        .noneMatch(s -> i.getWord().equals(s)))) {
                    unDefininedWord(splitUnSpaceWord);
                    currentAct.cleanGoActUse();
                }

            }
            //return;
        }
        currentAct.getRoomCurrent().getWays().forEach(w -> {
            if (w.getWayTitle().equals(word)) {
                currentAct.setActType(ActType.GO);
                currentAct.setWayGo(w);
                isInWay.set(true);
            }
        });
        currentAct.getRoomCurrent().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                currentAct.setActType(ActType.ACT_ROOM_ITEM);
                currentAct.setRoomItemAct(i);
                isInItems.set(true);
            }
        });
        currentAct.getInventoryCurrent().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                currentAct.setActType(ActType.ACT_INVENTORY_ITEM);
                currentAct.setInvItemAct(i);
                isInInventory.set(true);
            }
        });



        if (!isInWay.get() &&
                !isInItems.get() &&
                !isInInventory.get()) {
            unDefininedWord(word);
            currentAct.cleanGoActUse();
        }
    }

    private void act(Act act) {
        if (act.getActType() == ActType.USE) {
            act.use(
                    act.getRoomCurrent(),
                    act.getItemsUse());
        }

        if (act.getActType() == ActType.ACT_ROOM_ITEM) {
            act.getRoomItemAct().act(
                    act.getRoomCurrent(),
                    act.getInventoryCurrent());
        }

        if (act.getActType() == ActType.ACT_INVENTORY_ITEM) {
            //pl(act.getInvItemAct().getActInventoryTxt());
            act.getInvItemAct().act(act.getRoomCurrent(),act.getInventoryCurrent());
        }

        if (act.getActType() == ActType.GO) {
            act.setRoomCurrent(act.getWayGo().getRoom());
            currentAct.pa();
        }

        act.cleanGoActUse();
    }

    private void unDefininedWord(String word) {
        pl(word + " не вижу здесь чего-то похожего");
    }

    public String start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word;
        boolean first = true;
        while (!currentAct.isVictory()) {
            if (first) {
                currentAct.pa();
                first = false;
                rooms.addAll(currentAct.getRooms());
            }
            p(">");
            word = reader.readLine();

            if (word.equals("ф.")) {
                pl("финиш");
                return "финиш";
            }
            if (word.equals("о.")) {
                pl("Объкты:");
                currentAct.getRoomCurrent().getItems().forEach(Item::pd);
            }
            if (word.equals("к.")) {
                currentAct.pa();
            }

            defineAct(word);
            act(currentAct);

            int f = 1;

        }
        return "";
    }





}
