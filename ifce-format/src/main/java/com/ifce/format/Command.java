package com.ifce.format;

import lombok.Data;
import org.springframework.stereotype.Component;
//todo move

/**
 * Commands
 */
@Data
@Component
public class Command {

    //Command
    private String showInventoryCommand = "i.";
}