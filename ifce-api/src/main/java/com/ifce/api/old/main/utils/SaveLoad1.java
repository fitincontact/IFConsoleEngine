package com.ifce.api.old.main.utils;

import com.ifce.api.old.main.core.Monitor;
import com.ifce.api.old.main.format.Format1;
import com.ifce.api.old.main.object.Game1;

import java.io.*;

import static com.ifce.api.old.main.utils.Print1.pl;

public class SaveLoad1 {

    public static void save(final String[] args) throws IOException {
        final Format1 format1 = Format1.getInstance();

        final Game1 savedGame1 = Game1.getInstance();
        final String consoleSuffix = args.length > 1 ? args[1] : Format1.EMPTY;
        final String fileNameFull = format1.getSaveName() + consoleSuffix + format1.getFilenameExtension();
        final var outputStream = new FileOutputStream(fileNameFull);
        final var objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(savedGame1);
        objectOutputStream.close();
        pl(format1.getGameSaveMsg() + Format1.SPACE + fileNameFull);
    }

    public static void load(final String[] args) throws IOException, ClassNotFoundException {
        final Format1 format1 = Format1.getInstance();

        final Game1 game1 = Game1.getInstance();
        final Monitor monitor = Monitor.getInstance();

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(args[1]);
        } catch (final Exception e) {
            pl(e.getMessage());
            fileInputStream = null;
        }

        if (fileInputStream != null) {
            final var objectInputStream = new ObjectInputStream(fileInputStream);

            game1.set((Game1) objectInputStream.readObject());

            pl(format1.getGameLoadMsg());
            pl(monitor.toStrRoomCurrent());
        }
    }
}