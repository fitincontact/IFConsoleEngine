package com.ifce.engine;

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
    private final Print print;
    private final Format format;
    private final Game game;

    @Override
    public void start() throws IOException {
        val reader = new BufferedReader(new InputStreamReader(System.in));
        print.pl(game.getAnnotation());
        while (!game.isWin()) {
            print.p(format.getConsoleHead());
            game.setWord(reader.readLine());


        }
    }
}