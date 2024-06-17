package com.ifce.api.old.main.core;

import com.ifce.format.Format;
import com.service.model.common.Inventory;
import com.service.model.common.Item;
import com.service.model.common.Room;
import com.service.model.common.enums.EffectType;
import com.ifce.saver.SaveLoad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.ifce.util.Print.p;
import static com.ifce.util.Print.pl;

public class Core {
    private final Monitor monitor = Monitor.getInstance();
    private final Format format = Format.getInstance();
    SaveLoad saveLoad;

    protected Core(
            final Room room,
            final Inventory inventory
    ) {
        this.monitor.setRoomCurrent(room);
        this.monitor.setInventoryCurrent(inventory);
        this.monitor.setVictory(false);
    }

    public void start() throws IOException, ClassNotFoundException {
        final var reader = new BufferedReader(new InputStreamReader(System.in));
        String word;
        pl(monitor.toStrRoomCurrent());
        while (!monitor.isVictory()) {
            p(format.getConsoleHead());
            word = reader.readLine();

            if (!word.equals(Format.EMPTY) && !defineShotWord(word)) {
                defineEffect(word);
                produceEffect(monitor);
            }
        }
    }

    private boolean defineShotWord(final String word) throws IOException, ClassNotFoundException {
        if (word.equals(format.getFlagFinish())) {
            p(format.getFlagFinish());
            monitor.setVictory(true);
            return true;
        }
        if (word.equals(format.getFlagItems())) {
            p(format.getFlagItems().toString());
            pl(monitor.getRoomCurrent().toString());
            return true;
        }
        if (word.equals(format.getFlagInventory().toString())) {
            p(format.getFlagInventory().toString());
            pl(monitor.getInventoryCurrent().toString());
            return true;
        }
        if (word.equals(format.getFlagRoom().toString())) {
            pl(monitor.toStrRoomCurrent());
            return true;
        }
        final String[] splitWords = word.split(Format.SPLIT_SPACE);
        if (splitWords[0].equals(format.getFlagSave())) {
            SaveLoad.save(splitWords);
            return true;
        }
        if (splitWords[0].equals(format.getFlagLoad())) {
            SaveLoad.load(splitWords);
            return true;
        }
        return false;
    }

    private void defineEffect(final String word) {

        final var isInItems = new AtomicBoolean(false);
        final var isInInventory = new AtomicBoolean(false);
        final var isInWay = new AtomicBoolean(false);

        if (word.indexOf(format.getUseSplitSymbol()) > 0) {
            final var splitWords = new ArrayList<String>();
            for (final String splitWord : word.split(format.getUseSplitSymbol())) {

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
            final Item rootItemOrRandom = getItemRootOrRandom(monitor.getItemsUse());
            pl(rootItemOrRandom.use(
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
            pl(word + format.getUnDefininedWordIfContains());
            return;
        }
        pl(word + format.getUnDefininedWordIfNotContains());
    }

    //todo naming logic
    private void unDefininedWord2(final String word) {
        pl(word + format.getUnDefininedWordUse());
    }

    private Item getItemRootOrRandom(final List<Item> useItems) {
        final List<Item> useItemsWithNonDefaultUse =
                useItems.stream().filter(i -> i.getUse()).collect(Collectors.toList());
        if (useItemsWithNonDefaultUse.size() != 1) {
            return useItems.get(0);
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