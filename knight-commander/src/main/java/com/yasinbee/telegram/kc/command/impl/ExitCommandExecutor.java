package com.yasinbee.telegram.kc.command.impl;

import com.yasinbee.telegram.kc.command.CommandExecutor;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;

public class ExitCommandExecutor implements CommandExecutor {

    @Override
    public BotApiMethod executeCommand(Update update) {
        SendMessage response = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Keyboard hidden");
        ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
        response.setReplyMarkup(keyboardMarkup);
        return response;
    }
}
