package controllers;

import services.GameService;

import java.io.IOException;

public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public void play() throws IOException {
        gameService.play();
    }
}
