package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.enums.EffectType;
import com.fitincontact.engine.main.format.Format;
import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Item;
import com.fitincontact.engine.main.object.Room;
import com.fitincontact.engine.main.save.GameDeserialisation;
import com.fitincontact.engine.main.save.GameSerialisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.fitincontact.engine.main.utils.Utils.p;
import static com.fitincontact.engine.main.utils.Utils.pl;

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

    public String start() throws IOException, ClassNotFoundException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        return Format.EMPTY;
    }

    private boolean defineShotWord(final String word) throws IOException, ClassNotFoundException {
        if (word.equals(format.getFlagFinish().getKey())) {
            p(format.getFlagFinish().getValue());
            monitor.setVictory(true);
            return true;
        }
        if (word.equals(format.getFlagItems().getKey())) {
            p(format.getFlagItems().getValue());
            pl(monitor.getRoomCurrent().riString());
            return true;
        }
        if (word.equals(format.getFlagInventory().getKey())) {
            p(format.getFlagInventory().getValue());
            pl(monitor.getInventoryCurrent().toStr());
            return true;
        }
        if (word.equals(format.getFlagRoom().getKey())) {
            pl(monitor.toStrRoomCurrent());
            return true;
        }
        final String[] splited = word.split("\\s+");
        if (splited[0].equals("s.")) {
            GameSerialisation.main(splited);
            return true;
        }
        if (splited[0].equals("l.")) {
            GameDeserialisation.main(splited);
            return true;
        }
        return false;
    }

    private void defineEffect(final String word) {

        final AtomicBoolean isInItems = new AtomicBoolean(false);
        final AtomicBoolean isInInventory = new AtomicBoolean(false);
        final AtomicBoolean isInWay = new AtomicBoolean(false);

        if (word.indexOf(format.getUseSplitSymbol()) > 0) {
            final List<String> splitWords = new ArrayList<>();
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