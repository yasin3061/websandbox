package com.yasinbee.telegram.kc.command;

import java.util.HashMap;
import java.util.Map;

public enum Command {

    FUN("/fun"), SMILE("Smile"), EXIT("Exit"), KICK("kick");

    private String command;

    Command(String command) {
        this.command = command;
    }
}
