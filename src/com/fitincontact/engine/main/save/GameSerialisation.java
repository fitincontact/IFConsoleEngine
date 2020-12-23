package com.fitincontact.engine.main.save;

import com.fitincontact.engine.main.format.Format;
import com.fitincontact.engine.main.object.Game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static com.fitincontact.engine.main.utils.Utils.pl;

public class GameSerialisation {

    public static void main(final String[] args) throws IOException {
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
}