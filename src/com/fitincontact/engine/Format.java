package com.fitincontact.engine;

public class Format {
    private static Format format;
    private String wayHead = ":";
    private String roomTitleHead = "\n:";
    private String roomDescriptionHead = "\n:";
    private String roomItemsHead = "\n:";
    private String inventoryHead = "\n:";
    private String consoleHead = ">";
    private String useSplitSymbol = "-";

    private Format() {
    }

    public Format(
            final String wayHead,
            final String roomTitleHead,
            final String roomDescriptionHead,
            final String roomItemsHead,
            final String inventoryHead,
            final String consoleHead,
            final String useSplitSymbol
    ) {
        if (wayHead != null) {
            this.wayHead = wayHead;
        }
        if (roomTitleHead != null) {
            this.roomTitleHead = roomTitleHead;
        }
        if (roomDescriptionHead != null) {
            this.roomDescriptionHead = roomDescriptionHead;
        }
        if (roomItemsHead != null) {
            this.roomItemsHead = roomItemsHead;
        }
        if (inventoryHead != null) {
            this.inventoryHead = inventoryHead;
        }
        if (consoleHead != null) {
            this.consoleHead = consoleHead;
        }
        if (useSplitSymbol != null) {
            this.useSplitSymbol = useSplitSymbol;
        }
    }

    private static Format getFormat() {
        if (format == null) {
            return new Format();
        }
        return format;
    }

    public String getWayHead() {
        return wayHead;
    }

    public String getRoomTitleHead() {
        return roomTitleHead;
    }

    public String getRoomDescriptionHead() {
        return roomDescriptionHead;
    }

    public String getRoomItemsHead() {
        return roomItemsHead;
    }

    public String getInventoryHead() {
        return inventoryHead;
    }

    public String getConsoleHead() {
        return consoleHead;
    }

    public String getUseSplitSymbol() {
        return useSplitSymbol;
    }
}
