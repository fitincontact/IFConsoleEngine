package com.ifce.assember.model.singletons;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * General date for assembling
 */
@Data
@Component
public class GameAsm {
    /**
     * Player name
     */
    private String playerName;
    /**
     * Game annotation
     */
    private String annotation;
}