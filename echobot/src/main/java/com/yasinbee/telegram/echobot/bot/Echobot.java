package com.yasinbee.telegram.echobot.bot;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Echobot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageStr = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String response = messageStr + EmojiParser.parseToUnicode(":smile:");

            SendMessage message = new SendMessage().setChatId(chatId).setText(response);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "bot-name";
    }

    @Override
    public String getBotToken() {
        return "token";
    }
}
