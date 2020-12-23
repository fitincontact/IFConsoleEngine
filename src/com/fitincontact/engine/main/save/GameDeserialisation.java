package com.fitincontact.engine.main.save;

import com.fitincontact.engine.main.core.Monitor;
import com.fitincontact.engine.main.format.Format;
import com.fitincontact.engine.main.object.Game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static com.fitincontact.engine.main.utils.Utils.pl;

public class GameDeserialisation {
    public static void main(final String[] args) throws IOException, ClassNotFoundException {
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