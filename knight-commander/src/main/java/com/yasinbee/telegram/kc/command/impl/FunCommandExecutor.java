package com.yasinbee.telegram.kc.command.impl;

import com.yasinbee.telegram.kc.command.CommandExecutor;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class FunCommandExecutor implements CommandExecutor {
    @Override
    public BotApiMethod executeCommand(Update update) {
        long chatId = update.getMessage().getChatId();
        SendMessage response = new SendMessage().setChatId(chatId).setText("Click on the button");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Smile");
        row.add("Alien");
        row.add("Wink");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Clap");
        row.add("Thumbs Up");
        row.add("Exit");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        response.setReplyMarkup(keyboardMarkup);

        return response;
    }
}
