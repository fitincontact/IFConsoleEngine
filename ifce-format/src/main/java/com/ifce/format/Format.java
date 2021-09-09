package com.ifce.format;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Format {
    //Console
    private String consoleHead = "> ";


    //Command
    private String showInventoryCommand = "i.";
}