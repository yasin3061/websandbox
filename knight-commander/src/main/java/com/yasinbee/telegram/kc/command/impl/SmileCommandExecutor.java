package com.yasinbee.telegram.kc.command.impl;

import com.vdurmont.emoji.EmojiParser;
import com.yasinbee.telegram.kc.command.CommandExecutor;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class SmileCommandExecutor implements CommandExecutor {
    @Override
    public BotApiMethod executeCommand(Update update) {
        SendMessage response = new SendMessage().setChatId(update.getMessage().getChatId())
                .setText(EmojiParser.parseToUnicode(":smile:"));

        return response;
    }
}
