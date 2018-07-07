package com.yasinbee.trello.trello4j;

import com.yasinbee.trello.config.TrelloApiConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.trello4j.Trello;
import org.trello4j.TrelloImpl;

@Configuration
public class Trello4jConfiguration {

    private TrelloApiConfiguration trelloApiConfiguration;

    public Trello4jConfiguration(TrelloApiConfiguration trelloApiConfiguration) {
        this.trelloApiConfiguration = trelloApiConfiguration;
    }

    @Bean
    public Trello createTrello4jImpl() {
        return new TrelloImpl(trelloApiConfiguration.getKey(), trelloApiConfiguration.getToken());
    }
}
