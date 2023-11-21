package com.ifce.api.old.main.object;

import com.ifce.api.old.main.format.Format0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory0 implements Serializable {
    private static final long serialVersionUID = 6145303040368813210L;
    private final Format0 format0 = Format0.getInstance();
    private final List<Item0> item0s = new ArrayList<>();

    protected Inventory0() {
    }

    public List<Item0> getItems() {
        return item0s;
    }

    public void add(final Item0 item0) {
        item0s.add(item0);
    }

    public boolean isHave(final Item0 item0) {
        return item0s.contains(item0);
    }

    public List<String> toStr() {
        final var printList = new ArrayList<String>();
        printList.add(format0.getInventoryHead());
        if (item0s.isEmpty()) {
            return printList;
        }
        item0s.forEach(i -> printList.add(i.getInvName() + format0.getInventoryItemSplit()));
        String lastItem = printList.get(printList.size() - 1);
        lastItem = lastItem.substring(0, lastItem.length() - format0.getInventoryItemSplit().length());
        printList.set(printList.size() - 1, lastItem);
        return printList;
    }

    public void remove(final Item0 item0) {
        item0s.remove(item0);
    }
}