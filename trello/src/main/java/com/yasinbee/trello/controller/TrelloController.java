package com.yasinbee.trello.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.yasinbee.trello.dto.TrelloBoard;
import com.yasinbee.trello.dto.TrelloCard;
import com.yasinbee.trello.service.TrelloService;
import com.yasinbee.trello.utils.JsonUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.trello4j.model.Action;

@RestController
public class TrelloController {

    private TrelloService trelloService;

    public TrelloController(TrelloService trelloService) {
        this.trelloService = trelloService;
    }

    @GetMapping("boards/{boardId}")
    public TrelloBoard getTrelloBoard(@PathVariable("boardId") String boardId) {
        return trelloService.getBoardById(boardId);
    }

    @GetMapping("webhooks/board")
    public ResponseEntity sendOkResponse() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("webhooks/board")
    public ResponseEntity webhooksBoard(@RequestBody String request) {
        JsonNode requestJson = JsonUtils.stringToJsonNode(request);

        JsonNode actionNode = requestJson.get("action");

        Action action = JsonUtils.fromJson(actionNode.toString(), Action.class);

        if ("createCard".equalsIgnoreCase(action.getType())) {
            JsonNode cardNode = actionNode.get("display").get("entities").get("card");
            TrelloCard trelloCard = new TrelloCard(action.getData().getBoard().getId(),
                    action.getData().getCard().getName(), action.getData().getBoard().getUrl());
            System.out.println(trelloCard);
        }

        return ResponseEntity.ok().build();
    }
}
