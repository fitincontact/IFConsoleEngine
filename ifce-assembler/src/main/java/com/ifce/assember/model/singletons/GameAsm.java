package com.ifce.assember.model.singletons;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * General date for assembling
 */
@Data
@Component
public class GameAsm {
    /**
     * Player name
     */
    private List<String> playerNames;
    /**
     * Game annotation
     */
    private String annotation;
}