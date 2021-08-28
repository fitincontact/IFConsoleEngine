package com.ifce.model.assembler.singletons;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GameAsm {
    private String playerName;
    private String annotation;
}