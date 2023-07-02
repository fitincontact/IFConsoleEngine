package com.ifce.engine;

import com.ifce.engine.wordhandler.WordHandlerService;
import com.ifce.format.Format;
import com.ifce.model.singletons.Game;
import com.ifce.model.singletons.State;
import com.ifce.service.EngineService;
import com.ifce.util.Print;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Service
public class EngineServiceImpl implements EngineService {

    private final Format format;
    private final Game game;
    private final State state;
    private final WordHandlerService wordHandlerService;

    @Override
    public void start() {
        state.init();

        var reader = new BufferedReader(new InputStreamReader(System.in));
        Print.pl(game.getAnnotation());
        while (!state.isEnd()) {
            Print.p(format.getConsoleHead());
            wordHandlerService.handle(read(reader));
        }
    }

    private String read(final BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            state.end(e.getMessage());
            return null;
        }
    }
}