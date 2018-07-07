package com.yasinbee.trello.service.impl;

import com.yasinbee.trello.dto.TrelloBoard;
import com.yasinbee.trello.service.TrelloService;
import org.springframework.stereotype.Service;
import org.trello4j.Trello;
import org.trello4j.model.Board;

@Service("trello4JBased")
public class Trello4jBasedImpl implements TrelloService {

    private Trello trello;

    public Trello4jBasedImpl(Trello trello) {
        this.trello = trello;
    }

    @Override
    public TrelloBoard getBoardById(String boardId) {
        Board trello4jBoard = trello.getBoard(boardId);

        TrelloBoard dto = new TrelloBoard(trello4jBoard.getName(), trello4jBoard.getUrl());
        return dto;
    }
}
