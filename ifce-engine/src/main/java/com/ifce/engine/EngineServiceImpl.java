package com.ifce.engine;

import com.ifce.engine.wordhandler.WordHandlerService;
import com.ifce.format.Format;
import com.ifce.model.etc.Game;
import com.ifce.service.EngineService;
import com.ifce.util.Print;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Service
public class EngineServiceImpl implements EngineService {

    private final Format format;
    private final Game game;
    private final WordHandlerService wordHandlerService;

    @Override
    public void start() {
        val reader = new BufferedReader(new InputStreamReader(System.in));
        Print.pl(game.getAnnotation());
        while (!game.isWin()) {
            Print.p(format.getConsoleHead());
            game.getWord().add(read(reader));
            wordHandlerService.handle();
        }
    }

    private String read(final BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {

            game.end(e.getMessage());
            return null;
        }
    }
}