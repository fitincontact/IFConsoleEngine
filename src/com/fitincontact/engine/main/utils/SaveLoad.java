package com.fitincontact.engine.main.utils;

import com.fitincontact.engine.main.core.Monitor;
import com.fitincontact.engine.main.format.Format;
import com.fitincontact.engine.main.object.Game;

import java.io.*;

import static com.fitincontact.engine.main.utils.Print.pl;

public class SaveLoad {

    public static void save(final String[] args) throws IOException {
        final Format format = Format.getInstance();

        final Game savedGame = Game.getInstance();
        final String consoleSuffix = args.length > 1 ? args[1] : Format.EMPTY;
        final String fileNameFull = format.getSaveName() + consoleSuffix + format.getFilenameExtension();
        final FileOutputStream outputStream = new FileOutputStream(fileNameFull);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(savedGame);
        objectOutputStream.close();
        pl(format.getGameSaveMsg() + Format.SPACE + fileNameFull);
    }

    public static void load(final String[] args) throws IOException, ClassNotFoundException {
        final Format format = Format.getInstance();

        final Game game = Game.getInstance();
        final Monitor monitor = Monitor.getInstance();

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(args[1]);
        } catch (final Exception e) {
            pl(e.getMessage());
            fileInputStream = null;
        }

        if (fileInputStream != null) {
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            game.set((Game) objectInputStream.readObject());

            pl(format.getGameLoadMsg());
            pl(monitor.toStrRoomCurrent());
        }
    }
}