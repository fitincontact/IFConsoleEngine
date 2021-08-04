package com.ifce.api.main.object;

import com.ifce.api.api.Phrase;
import com.ifce.api.main.core.Monitor;
import com.ifce.api.main.format.Format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ifce.api.main.utils.Print.p;
import static com.ifce.api.main.utils.Print.pl;

public class Dialog implements Serializable {

    private static final long serialVersionUID = -5762702416347307236L;

    final Format format = Format.getInstance();
    final Monitor monitor = Monitor.getInstance();

    private final List<Dialog> continues = new ArrayList<>();
    private Dialog parent;
    private Dialog root;
    private String title;
    private String number;
    private String requestTxt;
    private String responseTxt;
    private Phrase requestPhr;
    private Phrase responsePhr;
    private boolean isActive = true;
    private boolean isStop = false;
    private long uniqueLong;
    private long depth;
    private long globalDepth;

    protected Dialog(final String title) {
        this.title = title;
        root = this;
    }

    public Dialog(final String requestTxt, final String responseTxt) {
        this.requestTxt = requestTxt;
        this.responseTxt = responseTxt;
    }

    public Dialog(final Phrase requestPhr, final Phrase responsePhr) {
        this.requestPhr = requestPhr;
        this.responsePhr = responsePhr;
    }

    public Dialog(final String requestTxt, final Phrase responsePhr) {
        this.requestTxt = requestTxt;
        this.responsePhr = responsePhr;
    }

    public Dialog(final Phrase requestPhr, final String responseTxt) {
        this.requestPhr = requestPhr;
        this.responseTxt = responseTxt;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(final boolean stop) {
        isStop = stop;
    }

    public void stop() {
        isStop = true;
    }

    public Dialog addContinues(final Dialog cont) {
        cont.setParent(this);
        cont.setRoot(this.root);
        final long contDepth = depth + 1;
        cont.setDepth(contDepth);
        root.setGlobalDepth(contDepth);
        final long num = continues.size() + 1;
        cont.setNumber(String.valueOf(num));
        continues.add(cont);
        return cont;
    }

    private String request(
            final Room room,
            final Inventory inventory
    ) {
        if (requestPhr == null) {
            return requestTxt;
        } else {
            requestPhr.apply(room, inventory);
            return Format.EMPTY;
        }
    }

    private String response(
            final Room room,
            final Inventory inventory
    ) {
        if (responsePhr == null) {
            return responseTxt;
        } else {
            responsePhr.apply(room, inventory);
            return Format.EMPTY;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getRequestTxt() {
        return requestTxt;
    }

    public void setRequestTxt(final String requestTxt) {
        this.requestTxt = requestTxt;
    }

    public String getResponseTxt() {
        return responseTxt;
    }

    public void setResponseTxt(final String responseTxt) {
        this.responseTxt = responseTxt;
    }

    public Phrase getRequestPhr() {
        return requestPhr;
    }

    public void setRequestPhr(final Phrase requestPhr) {
        this.requestPhr = requestPhr;
    }

    public Phrase getResponsePhr() {
        return responsePhr;
    }

    public void setResponsePhr(final Phrase responsePhr) {
        this.responsePhr = responsePhr;
    }

    public List<Dialog> getContinues() {
        return continues;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(final boolean active) {
        isActive = active;
    }

    public long getUniqueLong() {
        return uniqueLong;
    }

    public void setUniqueLong(final long uniqueLong) {
        this.uniqueLong = uniqueLong;
    }

    public Dialog getParent() {
        return parent;
    }

    public void setParent(final Dialog parent) {
        this.parent = parent;
    }

    public Dialog getRoot() {
        return root;
    }

    public void setRoot(final Dialog root) {
        this.root = root;
    }

    public long getGlobalDepth() {
        return globalDepth;
    }

    public void setGlobalDepth(final long globalDepth) {
        if (this.globalDepth < globalDepth) {
            this.globalDepth = globalDepth;
        }
    }

    @Override
    public boolean equals(final Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof Dialog)) {
            return false;
        }

        final var d = (Dialog) o;

        return d.uniqueLong == ((Dialog) o).uniqueLong;
    }

    @Override
    public int hashCode() {
        return (int) uniqueLong;
    }

    private void setDepth(final long d) {
        depth = d;
    }

    public void start() throws IOException {
        monitor.setDialogCurrent(this);

        final var reader = new BufferedReader(new InputStreamReader(System.in));
        String word;

        final var currentDialogs = new ArrayList<Dialog>();
        final var downDialogs = new ArrayList<Dialog>();
        final var upDialogs = new ArrayList<Dialog>();
        if (!continues.isEmpty()) {
            currentDialogs.addAll(continues);
            pl(title);
            currentDialogs.forEach(d -> {
                if (d.isActive) {
                    printChoice(d);
                }
            });
        }

        final var isToDown = new AtomicBoolean(false);
        while (this.isActive && !currentDialogs.isEmpty() && !this.isStop) {
            p(format.getConsoleHead());
            word = reader.readLine();
            final String finalWord = word;

            currentDialogs.forEach(d -> {
                if (d.number.equals(finalWord) && d.isActive) {
                    p("- ");
                    pl(d.request(monitor.getRoomCurrent(), monitor.getInventoryCurrent()));
                    p("- ");
                    pl(d.response(monitor.getRoomCurrent(), monitor.getInventoryCurrent()));
                    d.isActive = false;

                    if (!d.continues.isEmpty()) {
                        downDialogs.removeAll(downDialogs);
                        downDialogs.addAll(d.continues);
                        isToDown.set(true);
                    }

                }
            });

            if (isToDown.get()) {
                currentDialogs.removeAll(currentDialogs);
                currentDialogs.addAll(downDialogs);
                isToDown.set(false);
            } else if (currentDialogs.stream().noneMatch(Dialog::isActive)) {
                boolean isUpActive = false;
                while (!isUpActive && this.isActive) {
                    if (currentDialogs.get(0).parent == null || currentDialogs.get(0).parent.parent == null) {
                        this.isActive = false;
                    }
                    if (this.isActive) {
                        upDialogs.removeAll(upDialogs);
                        upDialogs.addAll(currentDialogs.get(0).parent.parent.continues);
                        currentDialogs.removeAll(currentDialogs);
                        currentDialogs.addAll(upDialogs);
                        isUpActive = upDialogs.stream().anyMatch(Dialog::isActive);
                    }
                }
                if (isUpActive && !this.isActive) {
                    currentDialogs.removeAll(currentDialogs);
                    currentDialogs.addAll(upDialogs);
                }
            }

            currentDialogs.forEach(d -> {
                if (d.isActive && !this.isStop) {
                    printChoice(d);
                }
            });
        }
        pl("разговор завершен");
        pl(monitor.toStrRoomCurrent());
    }

    private void printChoice(Dialog d) {
        pl(d.request(monitor.getRoomCurrent(), monitor.getInventoryCurrent()) + " (" + d.number + ")");
    }
}