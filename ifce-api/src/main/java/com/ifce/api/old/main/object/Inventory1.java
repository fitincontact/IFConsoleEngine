package com.ifce.api.old.main.object;

import com.ifce.api.old.main.format.Format1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory1 implements Serializable {
    private static final long serialVersionUID = 6145303040368813210L;
    private final Format1 format1 = Format1.getInstance();
    private final List<Item1> item1s = new ArrayList<>();

    protected Inventory1() {
    }

    public List<Item1> getItems() {
        return item1s;
    }

    public void add(final Item1 item1) {
        item1s.add(item1);
    }

    public boolean isHave(final Item1 item1) {
        return item1s.contains(item1);
    }

    public List<String> toStr() {
        final var printList = new ArrayList<String>();
        printList.add(format1.getInventoryHead());
        if (item1s.isEmpty()) {
            return printList;
        }
        item1s.forEach(i -> printList.add(i.getInvName() + format1.getInventoryItemSplit()));
        String lastItem = printList.get(printList.size() - 1);
        lastItem = lastItem.substring(0, lastItem.length() - format1.getInventoryItemSplit().length());
        printList.set(printList.size() - 1, lastItem);
        return printList;
    }

    public void remove(final Item1 item1) {
        item1s.remove(item1);
    }
}