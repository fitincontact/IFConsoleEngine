package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.enums.ActType;
import com.fitincontact.engine.main.object.Game;
import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Item;
import com.fitincontact.engine.main.object.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.fitincontact.engine.Utils.p;
import static com.fitincontact.engine.Utils.pl;

public class Core {

    private final Monitor monitor = new Monitor();
    private final Game game;

    protected Core(
            final Game game,
            final Room room,
            final Inventory inventory
    ) {
        this.monitor.setRoomCurrent(room);
        this.monitor.setInventoryCurrent(inventory);
        this.monitor.setVictory(false);
        this.game = game;
        this.game.setAct(monitor);
    }

    private void defineAct(final String word) {

        //if(monitor.getInventoryCurrent().isHave()){}

        final AtomicBoolean isInItems = new AtomicBoolean(false);
        final AtomicBoolean isInInventory = new AtomicBoolean(false);
        final AtomicBoolean isInWay = new AtomicBoolean(false);

        if (word.indexOf("-") > 0) {
            final List<String> splitWords = new ArrayList<>();
            for (final String splitWord : word.split("-")) {

                final String splitUnSpaceWord = splitWord.trim();
                splitWords.add(splitUnSpaceWord);

                monitor.getRoomCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(splitUnSpaceWord)) {
                        monitor.setActType(ActType.USE_ITEM);
                        monitor.getItemsUse().add(i);
                    }
                });
                monitor.getInventoryCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(splitUnSpaceWord)) {
                        monitor.setActType(ActType.USE_ITEM);
                        monitor.getItemsUse().add(i);
                    }
                });
                monitor.getRoomCurrent().getWays().forEach(w -> {
                    if (w.getWayTitle().equals(splitUnSpaceWord)) {
                        monitor.setActType(ActType.USE_WAY);
                        monitor.setWayUse(w);
                    }
                });

                if (monitor.getItemsUse()
                           .stream()
                           .anyMatch(i ->
                                   splitWords
                                           .stream()
                                           .noneMatch(s -> i.getWord().equals(s))) &&
                    monitor.getWayUse() == null
                ) {
                    unDefininedWord(splitUnSpaceWord);
                    monitor.cleanGoActUse();
                }
            }
            if (monitor.getActType() == ActType.USE_ITEM &&
                monitor.getItemsUse().size() < 2) {
                unDefininedWord2(word);
                monitor.cleanGoActUse();
            }
            return;
        }
        monitor.getRoomCurrent().getWays().forEach(w -> {
            if (w.getWayTitle().equals(word)) {
                monitor.setActType(ActType.GO);
                monitor.setWayGo(w);
                isInWay.set(true);
            }
        });
        monitor.getRoomCurrent().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                monitor.setActType(ActType.ACT_ROOM_ITEM);
                monitor.setRoomItemAct(i);
                isInItems.set(true);
            }
        });
        monitor.getInventoryCurrent().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                monitor.setActType(ActType.ACT_INVENTORY_ITEM);
                monitor.setInvItemAct(i);
                isInInventory.set(true);
            }
        });

        if (!isInWay.get() &&
            !isInItems.get() &&
            !isInInventory.get()
        ) {
            unDefininedWord(word);
            monitor.cleanGoActUse();
        }
        isInWay.set(false);
        isInItems.set(false);
        isInInventory.set(false);
    }

    private void act(final Monitor monitor) {
        if (monitor.getActType() == ActType.USE_WAY) {
            monitor.getWayUse().use(monitor.getRoomCurrent(), monitor.getItemsUse());
        }

        if (monitor.getActType() == ActType.USE_ITEM) {
            final Item rootItemOrRandom = getItemRootOrRandom(monitor.getItemsUse());
            rootItemOrRandom.use(
                    monitor.getRoomCurrent(),
                    monitor.getItemsUse()
            );
        }

        if (monitor.getActType() == ActType.ACT_ROOM_ITEM) {
            monitor.getRoomItemAct().act(
                    monitor.getRoomCurrent(),
                    monitor.getInventoryCurrent()
            );
        }

        if (monitor.getActType() == ActType.ACT_INVENTORY_ITEM) {
            monitor.getInvItemAct().act(monitor.getRoomCurrent(), monitor.getInventoryCurrent());
        }

        if (monitor.getActType() == ActType.GO) {
            if (monitor.getRoomCurrent().exit(
                    monitor.getWayGo().getRoom(),
                    monitor.getInventoryCurrent()
            )) {
                if (!monitor.getRoomCurrent().exit(monitor.getWayGo().getRoom(), monitor.getInventoryCurrent())) {
                    pl(monitor.getRoomCurrent().getExitTxt());
                    return;
                }
                if (monitor.getWayGo().isLock()) {
                    pl(monitor.getWayGo().getLockTxt());
                    return;
                }
                if (!monitor.getWayGo().getRoom().enter(monitor.getRoomCurrent(), monitor.getInventoryCurrent())) {
                    pl(monitor.getWayGo().getRoom().getEnterTxt());
                    return;
                }
                //game.go()
                monitor.setRoomCurrent(monitor.getWayGo().getRoom());
            }
            this.monitor.pm();
        }

        monitor.cleanGoActUse();
    }

    private void unDefininedWord(final String word) {
        if (monitor.toString().contains(word)) {
            pl(word + " - это невозможно использовать, мой друг");
            return;
        }
        pl(word + " - не вижу здесь чего-то похожего");
    }
//todo naming logic
    private void unDefininedWord2(final String word) {
        pl(word + " - нужно попробовать что-то другое");
    }

    private Item getItemRootOrRandom(final List<Item> useItems) {
        final List<Item> useItemsWithNonDefaultUse =
                useItems.stream().filter(i -> i.getUse() != null).collect(Collectors.toList());
        if (useItemsWithNonDefaultUse.size() != 1) {
            return useItems.get(0);
        }
        return useItemsWithNonDefaultUse.get(0);
    }

    public String start() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word;
        boolean first = true;
        while (!monitor.isVictory()) {
            if (first) {
                monitor.pm();
                first = false;

            }
            p(">");
            word = reader.readLine();

            defineShotWord(word);

            defineAct(word);
            act(monitor);
        }
        return "";
    }

    private String defineShotWord(final String word) {
        if (word.equals("ф.")) {
            p("финиш");
            return "финиш";
        }
        if (word.equals("о.")) {
            p("Объкты:");
            monitor.getRoomCurrent().pri();
        }
        if (word.equals("и.")) {
            p("Инвентарь:");
            monitor.getInventoryCurrent().pi();
        }
        if (word.equals("к.")) {
            monitor.pm();
        }
        return "";
    }
}