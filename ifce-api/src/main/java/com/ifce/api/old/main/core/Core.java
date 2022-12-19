package com.ifce.api.old.main.core;

import com.ifce.api.old.main.enums.EffectType;
import com.ifce.api.old.main.format.Format1;
import com.ifce.api.old.main.object.Inventory1;
import com.ifce.api.old.main.object.Item1;
import com.ifce.api.old.main.object.Room1;
import com.ifce.api.old.main.utils.Print1;
import com.ifce.api.old.main.utils.SaveLoad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.ifce.api.old.main.utils.Print1.p;
import static com.ifce.api.old.main.utils.Print1.pl;

public class Core {

    private final Monitor monitor = Monitor.getInstance();
    private final Format1 format1 = Format1.getInstance();

    protected Core(
            final Room1 room1,
            final Inventory1 inventory1
    ) {
        this.monitor.setRoomCurrent(room1);
        this.monitor.setInventoryCurrent(inventory1);
        this.monitor.setVictory(false);
    }

    public void start() throws IOException, ClassNotFoundException {
        final var reader = new BufferedReader(new InputStreamReader(System.in));
        String word;
        pl(monitor.toStrRoomCurrent());
        while (!monitor.isVictory()) {
            p(format1.getConsoleHead());
            word = reader.readLine();

            if (!word.equals(Format1.EMPTY) && !defineShotWord(word)) {
                defineEffect(word);
                produceEffect(monitor);
            }
        }
    }

    private boolean defineShotWord(final String word) throws IOException, ClassNotFoundException {
        if (word.equals(format1.getFlagFinish().cmd())) {
            Print1.p(format1.getFlagFinish().print());
            monitor.setVictory(true);
            return true;
        }
        if (word.equals(format1.getFlagItems().cmd())) {
            Print1.p(format1.getFlagItems().print());
            pl(monitor.getRoomCurrent().riString());
            return true;
        }
        if (word.equals(format1.getFlagInventory().cmd())) {
            Print1.p(format1.getFlagInventory().print());
            pl(monitor.getInventoryCurrent().toStr());
            return true;
        }
        if (word.equals(format1.getFlagRoom().cmd())) {
            pl(monitor.toStrRoomCurrent());
            return true;
        }
        final String[] splitWords = word.split(Format1.SPLIT_SPACE);
        if (splitWords[0].equals(format1.getFlagSave())) {
            SaveLoad1.save(splitWords);
            return true;
        }
        if (splitWords[0].equals(format1.getFlagLoad())) {
            SaveLoad1.load(splitWords);
            return true;
        }
        return false;
    }

    private void defineEffect(final String word) {

        final var isInItems = new AtomicBoolean(false);
        final var isInInventory = new AtomicBoolean(false);
        final var isInWay = new AtomicBoolean(false);

        if (word.indexOf(format1.getUseSplitSymbol()) > 0) {
            final var splitWords = new ArrayList<String>();
            for (final String splitWord : word.split(format1.getUseSplitSymbol())) {

                final String splitUnSpaceWord = splitWord.trim();
                splitWords.add(splitUnSpaceWord);

                monitor.getRoomCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(splitUnSpaceWord)) {
                        monitor.setActType(EffectType.USE_ITEM);
                        monitor.add(i);
                    }
                });
                monitor.getRoomCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(word)) {
                        monitor.setActType(EffectType.ACT_ROOM_ITEM);
                        monitor.setRoomItemAct(i);
                        isInItems.set(true);
                        return;
                    }
                });
                monitor.getInventoryCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(splitUnSpaceWord)) {
                        monitor.setActType(EffectType.USE_ITEM);
                        monitor.getItemsUse().add(i);
                    }
                });
                monitor.getRoomCurrent().getWays().forEach(w -> {
                    if (w.getWayTitle().equals(splitUnSpaceWord)) {
                        monitor.setActType(EffectType.USE_WAY);
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
            if (monitor.getActType() == EffectType.USE_ITEM &&
                    monitor.getItemsUse().size() < 2) {
                unDefininedWord2(word);
                monitor.cleanGoActUse();
            }
            return;
        }
        monitor.getRoomCurrent().getWays().forEach(w -> {
            if (w.getWayTitle().equals(word)) {
                monitor.setActType(EffectType.GO);
                monitor.setWayGo(w);
                isInWay.set(true);
                return;
            }
        });
        monitor.getRoomCurrent().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                monitor.setActType(EffectType.ACT_ROOM_ITEM);
                monitor.setRoomItemAct(i);
                isInItems.set(true);
                return;
            }
        });
        monitor.getInventoryCurrent().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                monitor.setActType(EffectType.ACT_INVENTORY_ITEM);
                monitor.setInvItemAct(i);
                isInInventory.set(true);
                return;
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

    private void produceEffect(final Monitor monitor) throws IOException, ClassNotFoundException {
        if (monitor.getActType() == EffectType.USE_WAY) {
            pl(monitor.getWayUse().use(monitor.getRoomCurrent(), monitor.getItemsUse()));
        }

        if (monitor.getActType() == EffectType.USE_ITEM) {
            final Item1 rootItem1OrRandom = getItemRootOrRandom(monitor.getItemsUse());
            pl(rootItem1OrRandom.use(
                    monitor.getRoomCurrent(),
                    monitor.getItemsUse()
            ));
        }

        if (monitor.getActType() == EffectType.ACT_ROOM_ITEM) {
            pl(monitor.getRoomItemAct().act(
                    monitor.getRoomCurrent(),
                    monitor.getInventoryCurrent()
            ));
        }

        if (monitor.getActType() == EffectType.ACT_INVENTORY_ITEM) {
            pl(monitor.getInvItemAct().act(monitor.getRoomCurrent(), monitor.getInventoryCurrent()));
        }

        if (monitor.getActType() == EffectType.GO) {
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
                monitor.setRoomCurrent(monitor.getWayGo().getRoom());
            }
            pl(this.monitor.toStrRoomCurrent());
        }

        monitor.cleanGoActUse();
    }

    private void unDefininedWord(final String word) {
        if (monitor.toString().contains(word)) {
            pl(word + format1.getUnDefininedWordIfContains());
            return;
        }
        pl(word + format1.getUnDefininedWordIfNotContains());
    }

    //todo naming logic
    private void unDefininedWord2(final String word) {
        pl(word + format1.getUnDefininedWordUse());
    }

    private Item1 getItemRootOrRandom(final List<Item1> useItem1s) {
        final List<Item1> useItemsWithNonDefaultUse =
                useItem1s.stream().filter(i -> i.getUse() != null).collect(Collectors.toList());
        if (useItemsWithNonDefaultUse.size() != 1) {
            return useItem1s.get(0);
        }
        return useItemsWithNonDefaultUse.get(0);
    }

    public static void main(String[] args) {
        var l = new ArrayList<List<Double>>();
        var f = List.of(1.);
        l.add(f);

        for (int i = 0; i < 1000; i++) {

        }


    }
}