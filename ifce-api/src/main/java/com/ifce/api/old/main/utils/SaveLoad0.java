package com.ifce.api.old.main.utils;

import com.ifce.api.old.main.core.Monitor0;
import com.ifce.api.old.main.format.Format0;
import com.ifce.api.old.main.object.Game0;

import java.io.*;

import static com.ifce.api.old.main.utils.Print0.pl;

public class SaveLoad0 {

    public static void save(final String[] args) throws IOException {
        final Format0 format0 = Format0.getInstance();

        final Game0 savedGame0 = Game0.getInstance();
        final String consoleSuffix = args.length > 1 ? args[1] : Format0.EMPTY;
        final String fileNameFull = format0.getSaveName() + consoleSuffix + format0.getFilenameExtension();
        final var outputStream = new FileOutputStream(fileNameFull);
        final var objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(savedGame0);
        objectOutputStream.close();
        pl(format0.getGameSaveMsg() + Format0.SPACE + fileNameFull);
    }

    public static void load(final String[] args) throws IOException, ClassNotFoundException {
        final Format0 format0 = Format0.getInstance();

        final Game0 game0 = Game0.getInstance();
        final Monitor0 monitor0 = Monitor0.getInstance();

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(args[1]);
        } catch (final Exception e) {
            pl(e.getMessage());
            fileInputStream = null;
        }

        if (fileInputStream != null) {
            final var objectInputStream = new ObjectInputStream(fileInputStream);

            game0.set((Game0) objectInputStream.readObject());

            pl(format0.getGameLoadMsg());
            pl(monitor0.toStrRoomCurrent());
        }
    }
}