package com.ifce.api.old.main.core;

import com.ifce.api.old.main.enums.EffectType0;
import com.ifce.api.old.main.format.Format0;
import com.ifce.api.old.main.object.Inventory0;
import com.ifce.api.old.main.object.Item0;
import com.ifce.api.old.main.object.Room0;
import com.ifce.api.old.main.utils.Print0;
import com.ifce.api.old.main.utils.SaveLoad0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.ifce.api.old.main.utils.Print0.p;
import static com.ifce.api.old.main.utils.Print0.pl;

public class Core0 {

    private final Monitor0 monitor0 = Monitor0.getInstance();
    private final Format0 format0 = Format0.getInstance();

    protected Core0(
            final Room0 room0,
            final Inventory0 inventory0
    ) {
        this.monitor0.setRoomCurrent(room0);
        this.monitor0.setInventoryCurrent(inventory0);
        this.monitor0.setVictory(false);
    }

    public void start() throws IOException, ClassNotFoundException {
        final var reader = new BufferedReader(new InputStreamReader(System.in));
        String word;
        pl(monitor0.toStrRoomCurrent());
        while (!monitor0.isVictory()) {
            p(format0.getConsoleHead());
            word = reader.readLine();

            if (!word.equals(Format0.EMPTY) && !defineShotWord(word)) {
                defineEffect(word);
                produceEffect(monitor0);
            }
        }
    }

    private boolean defineShotWord(final String word) throws IOException, ClassNotFoundException {
        if (word.equals(format0.getFlagFinish().cmd())) {
            Print0.p(format0.getFlagFinish().print());
            monitor0.setVictory(true);
            return true;
        }
        if (word.equals(format0.getFlagItems().cmd())) {
            Print0.p(format0.getFlagItems().print());
            pl(monitor0.getRoomCurrent().riString());
            return true;
        }
        if (word.equals(format0.getFlagInventory().cmd())) {
            Print0.p(format0.getFlagInventory().print());
            pl(monitor0.getInventoryCurrent().toStr());
            return true;
        }
        if (word.equals(format0.getFlagRoom().cmd())) {
            pl(monitor0.toStrRoomCurrent());
            return true;
        }
        final String[] splitWords = word.split(Format0.SPLIT_SPACE);
        if (splitWords[0].equals(format0.getFlagSave())) {
            SaveLoad0.save(splitWords);
            return true;
        }
        if (splitWords[0].equals(format0.getFlagLoad())) {
            SaveLoad0.load(splitWords);
            return true;
        }
        return false;
    }

    private void defineEffect(final String word) {

        final var isInItems = new AtomicBoolean(false);
        final var isInInventory = new AtomicBoolean(false);
        final var isInWay = new AtomicBoolean(false);

        if (word.indexOf(format0.getUseSplitSymbol()) > 0) {
            final var splitWords = new ArrayList<String>();
            for (final String splitWord : word.split(format0.getUseSplitSymbol())) {

                final String splitUnSpaceWord = splitWord.trim();
                splitWords.add(splitUnSpaceWord);

                monitor0.getRoomCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(splitUnSpaceWord)) {
                        monitor0.setActType(EffectType0.USE_ITEM);
                        monitor0.getItemsUse().add(i);
                    }
                });
                monitor0.getInventoryCurrent().getItems().forEach(i -> {
                    if (i.getWord().equals(splitUnSpaceWord)) {
                        monitor0.setActType(EffectType0.USE_ITEM);
                        monitor0.getItemsUse().add(i);
                    }
                });
                monitor0.getRoomCurrent().getWays().forEach(w -> {
                    if (w.getWayTitle().equals(splitUnSpaceWord)) {
                        monitor0.setActType(EffectType0.USE_WAY);
                        monitor0.setWayUse(w);
                    }
                });

                if (monitor0.getItemsUse()
                        .stream()
                        .anyMatch(i ->
                                splitWords
                                        .stream()
                                        .noneMatch(s -> i.getWord().equals(s))) &&
                        monitor0.getWayUse() == null
                ) {
                    unDefininedWord(splitUnSpaceWord);
                    monitor0.cleanGoActUse();
                }
            }
            if (monitor0.getActType() == EffectType0.USE_ITEM &&
                    monitor0.getItemsUse().size() < 2) {
                unDefininedWord2(word);
                monitor0.cleanGoActUse();
            }
            return;
        }
        monitor0.getRoomCurrent().getWays().forEach(w -> {
            if (w.getWayTitle().equals(word)) {
                monitor0.setActType(EffectType0.GO);
                monitor0.setWayGo(w);
                isInWay.set(true);
                return;
            }
        });
        monitor0.getRoomCurrent().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                monitor0.setActType(EffectType0.ACT_ROOM_ITEM);
                monitor0.setRoomItemAct(i);
                isInItems.set(true);
                return;
            }
        });
        monitor0.getInventoryCurrent().getItems().forEach(i -> {
            if (i.getWord().equals(word)) {
                monitor0.setActType(EffectType0.ACT_INVENTORY_ITEM);
                monitor0.setInvItemAct(i);
                isInInventory.set(true);
                return;
            }
        });

        if (!isInWay.get() &&
                !isInItems.get() &&
                !isInInventory.get()
        ) {
            unDefininedWord(word);
            monitor0.cleanGoActUse();
        }
        isInWay.set(false);
        isInItems.set(false);
        isInInventory.set(false);
    }

    private void produceEffect(final Monitor0 monitor0) throws IOException, ClassNotFoundException {
        if (monitor0.getActType() == EffectType0.USE_WAY) {
            pl(monitor0.getWayUse().use(monitor0.getRoomCurrent(), monitor0.getItemsUse()));
        }

        if (monitor0.getActType() == EffectType0.USE_ITEM) {
            final Item0 rootItem0OrRandom = getItemRootOrRandom(monitor0.getItemsUse());
            pl(rootItem0OrRandom.use(
                    monitor0.getRoomCurrent(),
                    monitor0.getItemsUse()
            ));
        }

        if (monitor0.getActType() == EffectType0.ACT_ROOM_ITEM) {
            pl(monitor0.getRoomItemAct().act(
                    monitor0.getRoomCurrent(),
                    monitor0.getInventoryCurrent()
            ));
        }

        if (monitor0.getActType() == EffectType0.ACT_INVENTORY_ITEM) {
            pl(monitor0.getInvItemAct().act(monitor0.getRoomCurrent(), monitor0.getInventoryCurrent()));
        }

        if (monitor0.getActType() == EffectType0.GO) {
            if (monitor0.getRoomCurrent().exit(
                    monitor0.getWayGo().getRoom(),
                    monitor0.getInventoryCurrent()
            )) {
                if (!monitor0.getRoomCurrent().exit(monitor0.getWayGo().getRoom(), monitor0.getInventoryCurrent())) {
                    pl(monitor0.getRoomCurrent().getExitTxt());
                    return;
                }
                if (monitor0.getWayGo().isLock()) {
                    pl(monitor0.getWayGo().getLockTxt());
                    return;
                }
                if (!monitor0.getWayGo().getRoom().enter(monitor0.getRoomCurrent(), monitor0.getInventoryCurrent())) {
                    pl(monitor0.getWayGo().getRoom().getEnterTxt());
                    return;
                }
                monitor0.setRoomCurrent(monitor0.getWayGo().getRoom());
            }
            pl(this.monitor0.toStrRoomCurrent());
        }

        monitor0.cleanGoActUse();
    }

    private void unDefininedWord(final String word) {
        if (monitor0.toString().contains(word)) {
            pl(word + format0.getUnDefininedWordIfContains());
            return;
        }
        pl(word + format0.getUnDefininedWordIfNotContains());
    }

    //todo naming logic
    private void unDefininedWord2(final String word) {
        pl(word + format0.getUnDefininedWordUse());
    }

    private Item0 getItemRootOrRandom(final List<Item0> useItem0s) {
        final List<Item0> useItemsWithNonDefaultUse =
                useItem0s.stream().filter(i -> i.getUse() != null).collect(Collectors.toList());
        if (useItemsWithNonDefaultUse.size() != 1) {
            return useItem0s.get(0);
        }
        return useItemsWithNonDefaultUse.get(0);
    }
}