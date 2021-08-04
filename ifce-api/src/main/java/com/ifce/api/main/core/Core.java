package com.ifce.api.main.core;

import com.ifce.api.main.enums.EffectType;
import com.ifce.api.main.format.Format;
import com.ifce.api.main.object.Inventory;
import com.ifce.api.main.object.Item;
import com.ifce.api.main.object.Room;
import com.ifce.api.main.utils.Print;
import com.ifce.api.main.utils.SaveLoad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.ifce.api.main.utils.Print.p;
import static com.ifce.api.main.utils.Print.pl;

public class Core {

    private final Monitor monitor = Monitor.getInstance();
    private final Format format = Format.getInstance();

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
        if (word.equals(format.getFlagFinish().cmd())) {
            Print.p(format.getFlagFinish().print());
            monitor.setVictory(true);
            return true;
        }
        if (word.equals(format.getFlagItems().cmd())) {
            Print.p(format.getFlagItems().print());
            pl(monitor.getRoomCurrent().riString());
            return true;
        }
        if (word.equals(format.getFlagInventory().cmd())) {
            Print.p(format.getFlagInventory().print());
            pl(monitor.getInventoryCurrent().toStr());
            return true;
        }
        if (word.equals(format.getFlagRoom().cmd())) {
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
                        monitor.getItemsUse().add(i);
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
                useItems.stream().filter(i -> i.getUse() != null).collect(Collectors.toList());
        if (useItemsWithNonDefaultUse.size() != 1) {
            return useItems.get(0);
        }
        return useItemsWithNonDefaultUse.get(0);
    }
}