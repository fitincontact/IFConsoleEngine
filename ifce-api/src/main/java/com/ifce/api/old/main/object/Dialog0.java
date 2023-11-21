package com.ifce.api.old.main.object;

import com.ifce.api.old.Phrase0;
import com.ifce.api.old.main.core.Monitor0;
import com.ifce.api.old.main.format.Format0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ifce.api.old.main.utils.Print0.p;
import static com.ifce.api.old.main.utils.Print0.pl;

public class Dialog0 implements Serializable {

    private static final long serialVersionUID = -5762702416347307236L;

    final Format0 format0 = Format0.getInstance();
    final Monitor0 monitor0 = Monitor0.getInstance();

    private final List<Dialog0> continues = new ArrayList<>();
    private Dialog0 parent;
    private Dialog0 root;
    private String title;
    private String number;
    private String requestTxt;
    private String responseTxt;
    private Phrase0 requestPhr;
    private Phrase0 responsePhr;
    private boolean isActive = true;
    private boolean isStop = false;
    private long uniqueLong;
    private long depth;
    private long globalDepth;

    protected Dialog0(final String title) {
        this.title = title;
        root = this;
    }

    public Dialog0(final String requestTxt, final String responseTxt) {
        this.requestTxt = requestTxt;
        this.responseTxt = responseTxt;
    }

    public Dialog0(final Phrase0 requestPhr, final Phrase0 responsePhr) {
        this.requestPhr = requestPhr;
        this.responsePhr = responsePhr;
    }

    public Dialog0(final String requestTxt, final Phrase0 responsePhr) {
        this.requestTxt = requestTxt;
        this.responsePhr = responsePhr;
    }

    public Dialog0(final Phrase0 requestPhr, final String responseTxt) {
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

    public Dialog0 addContinues(final Dialog0 cont) {
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
            final Room0 room0,
            final Inventory0 inventory0
    ) {
        if (requestPhr == null) {
            return requestTxt;
        } else {
            requestPhr.apply(room0, inventory0);
            return Format0.EMPTY;
        }
    }

    private String response(
            final Room0 room0,
            final Inventory0 inventory0
    ) {
        if (responsePhr == null) {
            return responseTxt;
        } else {
            responsePhr.apply(room0, inventory0);
            return Format0.EMPTY;
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

    public Phrase0 getRequestPhr() {
        return requestPhr;
    }

    public void setRequestPhr(final Phrase0 requestPhr) {
        this.requestPhr = requestPhr;
    }

    public Phrase0 getResponsePhr() {
        return responsePhr;
    }

    public void setResponsePhr(final Phrase0 responsePhr) {
        this.responsePhr = responsePhr;
    }

    public List<Dialog0> getContinues() {
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

    public Dialog0 getParent() {
        return parent;
    }

    public void setParent(final Dialog0 parent) {
        this.parent = parent;
    }

    public Dialog0 getRoot() {
        return root;
    }

    public void setRoot(final Dialog0 root) {
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
        if (!(o instanceof Dialog0)) {
            return false;
        }

        final var d = (Dialog0) o;

        return d.uniqueLong == ((Dialog0) o).uniqueLong;
    }

    @Override
    public int hashCode() {
        return (int) uniqueLong;
    }

    private void setDepth(final long d) {
        depth = d;
    }

    public void start() throws IOException {
        monitor0.setDialogCurrent(this);

        final var reader = new BufferedReader(new InputStreamReader(System.in));
        String word;

        final var currentDialogs = new ArrayList<Dialog0>();
        final var downDialogs = new ArrayList<Dialog0>();
        final var upDialogs = new ArrayList<Dialog0>();
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
            p(format0.getConsoleHead());
            word = reader.readLine();
            final String finalWord = word;

            currentDialogs.forEach(d -> {
                if (d.number.equals(finalWord) && d.isActive) {
                    p("- ");
                    pl(d.request(monitor0.getRoomCurrent(), monitor0.getInventoryCurrent()));
                    p("- ");
                    pl(d.response(monitor0.getRoomCurrent(), monitor0.getInventoryCurrent()));
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
            } else if (currentDialogs.stream().noneMatch(Dialog0::isActive)) {
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
                        isUpActive = upDialogs.stream().anyMatch(Dialog0::isActive);
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
        pl(monitor0.toStrRoomCurrent());
    }

    private void printChoice(Dialog0 d) {
        pl(d.request(monitor0.getRoomCurrent(), monitor0.getInventoryCurrent()) + " (" + d.number + ")");
    }
}