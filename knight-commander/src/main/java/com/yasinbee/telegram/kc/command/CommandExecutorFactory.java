package com.yasinbee.telegram.kc.command;

import com.yasinbee.telegram.kc.command.impl.ExitCommandExecutor;
import com.yasinbee.telegram.kc.command.impl.FunCommandExecutor;
import com.yasinbee.telegram.kc.command.impl.KickCommandExecutor;
import com.yasinbee.telegram.kc.command.impl.SmileCommandExecutor;

import java.util.Optional;

public class CommandExecutorFactory {

    public static Optional<CommandExecutor> getCommandExecutor(String stringCommand) {
        stringCommand = stringCommand.startsWith("/") ? stringCommand.substring(1) : stringCommand;
        stringCommand = stringCommand.split(" ")[0];
        Command command = Command.valueOf(stringCommand.toUpperCase());

        switch (command) {
            case FUN:
                return Optional.of(new FunCommandExecutor());
            case SMILE:
                return Optional.of(new SmileCommandExecutor());
            case EXIT:
                return Optional.of(new ExitCommandExecutor());
            case KICK:
                return Optional.of(new KickCommandExecutor());
            default:
                return Optional.empty();
        }
    }
}
