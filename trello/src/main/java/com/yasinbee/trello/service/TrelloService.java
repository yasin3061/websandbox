package com.yasinbee.trello.service;

import com.yasinbee.trello.dto.TrelloBoard;
import org.trello4j.model.Board;

public interface TrelloService {

    TrelloBoard getBoardById(String boardId);
}
