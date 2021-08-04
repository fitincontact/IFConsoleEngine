package com.ifce.api.main.utils;

import com.ifce.api.main.core.Monitor;
import com.ifce.api.main.format.Format;
import com.ifce.api.main.object.Game;

import java.io.*;

import static com.ifce.api.main.utils.Print.pl;

public class SaveLoad {

    public static void save(final String[] args) throws IOException {
        final Format format = Format.getInstance();

        final Game savedGame = Game.getInstance();
        final String consoleSuffix = args.length > 1 ? args[1] : Format.EMPTY;
        final String fileNameFull = format.getSaveName() + consoleSuffix + format.getFilenameExtension();
        final var outputStream = new FileOutputStream(fileNameFull);
        final var objectOutputStream = new ObjectOutputStream(outputStream);
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
            final var objectInputStream = new ObjectInputStream(fileInputStream);

            game.set((Game) objectInputStream.readObject());

            pl(format.getGameLoadMsg());
            pl(monitor.toStrRoomCurrent());
        }
    }
}