package com.ifce.api.old.main.object;

import com.ifce.api.old.Phrase1;
import com.ifce.api.old.main.core.Monitor;
import com.ifce.api.old.main.format.Format1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ifce.api.old.main.utils.Print1.p;
import static com.ifce.api.old.main.utils.Print1.pl;

public class Dialog1 implements Serializable {

    private static final long serialVersionUID = -5762702416347307236L;

    final Format1 format1 = Format1.getInstance();
    final Monitor monitor = Monitor.getInstance();

    private final List<Dialog1> continues = new ArrayList<>();
    private Dialog1 parent;
    private Dialog1 root;
    private String title;
    private String number;
    private String requestTxt;
    private String responseTxt;
    private Phrase1 requestPhr;
    private Phrase1 responsePhr;
    private boolean isActive = true;
    private boolean isStop = false;
    private long uniqueLong;
    private long depth;
    private long globalDepth;

    protected Dialog1(final String title) {
        this.title = title;
        root = this;
    }

    public Dialog1(final String requestTxt, final String responseTxt) {
        this.requestTxt = requestTxt;
        this.responseTxt = responseTxt;
    }

    public Dialog1(final Phrase1 requestPhr, final Phrase1 responsePhr) {
        this.requestPhr = requestPhr;
        this.responsePhr = responsePhr;
    }

    public Dialog1(final String requestTxt, final Phrase1 responsePhr) {
        this.requestTxt = requestTxt;
        this.responsePhr = responsePhr;
    }

    public Dialog1(final Phrase1 requestPhr, final String responseTxt) {
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

    public Dialog1 addContinues(final Dialog1 cont) {
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
            final Room1 room1,
            final Inventory1 inventory1
    ) {
        if (requestPhr == null) {
            return requestTxt;
        } else {
            requestPhr.apply(room1, inventory1);
            return Format1.EMPTY;
        }
    }

    private String response(
            final Room1 room1,
            final Inventory1 inventory1
    ) {
        if (responsePhr == null) {
            return responseTxt;
        } else {
            responsePhr.apply(room1, inventory1);
            return Format1.EMPTY;
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

    public Phrase1 getRequestPhr() {
        return requestPhr;
    }

    public void setRequestPhr(final Phrase1 requestPhr) {
        this.requestPhr = requestPhr;
    }

    public Phrase1 getResponsePhr() {
        return responsePhr;
    }

    public void setResponsePhr(final Phrase1 responsePhr) {
        this.responsePhr = responsePhr;
    }

    public List<Dialog1> getContinues() {
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

    public Dialog1 getParent() {
        return parent;
    }

    public void setParent(final Dialog1 parent) {
        this.parent = parent;
    }

    public Dialog1 getRoot() {
        return root;
    }

    public void setRoot(final Dialog1 root) {
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
        if (!(o instanceof Dialog1)) {
            return false;
        }

        final var d = (Dialog1) o;

        return d.uniqueLong == ((Dialog1) o).uniqueLong;
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

        final var currentDialogs = new ArrayList<Dialog1>();
        final var downDialogs = new ArrayList<Dialog1>();
        final var upDialogs = new ArrayList<Dialog1>();
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
            p(format1.getConsoleHead());
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
            } else if (currentDialogs.stream().noneMatch(Dialog1::isActive)) {
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
                        isUpActive = upDialogs.stream().anyMatch(Dialog1::isActive);
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

    private void printChoice(Dialog1 d) {
        pl(d.request(monitor.getRoomCurrent(), monitor.getInventoryCurrent()) + " (" + d.number + ")");
    }
}