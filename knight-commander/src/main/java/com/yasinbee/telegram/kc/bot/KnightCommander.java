package com.yasinbee.telegram.kc.bot;

import com.yasinbee.telegram.kc.command.CommandExecutor;
import com.yasinbee.telegram.kc.command.CommandExecutorFactory;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Optional;

public class KnightCommander extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();

            Optional<CommandExecutor> executor = CommandExecutorFactory.getCommandExecutor(command);

            executor.ifPresent(c -> {
                BotApiMethod response = c.executeCommand(update);
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public String getBotUsername() {
        return "KnightCommanderBot";
    }

    @Override
    public String getBotToken() {
        return "611447127:AAHBqxv-Zkm04On4X0eurUgAfFN7PkwIdbQ";
    }
}
