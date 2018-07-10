package com.yasinbee.telegram.kc.command;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface CommandExecutor {

    BotApiMethod executeCommand(Update update);
}
