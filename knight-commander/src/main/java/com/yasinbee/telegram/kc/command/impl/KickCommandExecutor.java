package com.yasinbee.telegram.kc.command.impl;

import com.yasinbee.telegram.kc.command.CommandExecutor;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.groupadministration.KickChatMember;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.MessageEntity;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

import java.util.Optional;

public class KickCommandExecutor implements CommandExecutor {

    @Override
    public BotApiMethod executeCommand(Update update) {

        Optional<MessageEntity> mentions = update.getMessage().getEntities().stream().filter(e -> e.getType().equals("text_mention")).findFirst();

        if (mentions.isPresent()) {
            User toBeKicked = mentions.get().getUser();
            KickChatMember kick = new KickChatMember()
                    .setChatId(update.getMessage().getChatId())
                    .setUserId(toBeKicked.getId());
            return kick;
        }

        SendMessage sendMessage = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("You must mention a user to be kicked. Please use @mention to mention the user.");

        return sendMessage;
    }
}
