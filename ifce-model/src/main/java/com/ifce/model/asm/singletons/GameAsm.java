package com.ifce.model.asm.singletons;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GameAsm {
    private String playerName;
    private String annotation;
}