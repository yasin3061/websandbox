package com.yasinbee.telegram.echobot.bot.com.yasinbee.telegram.echobot.main;

import com.yasinbee.telegram.echobot.bot.Echobot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new Echobot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
