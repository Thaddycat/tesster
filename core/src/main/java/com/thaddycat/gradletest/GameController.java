package com.thaddycat.gradletest;

import com.thaddycat.gradletest.backend.Command;
import com.thaddycat.gradletest.backend.TurnBasedGame;

import java.util.List;

public enum GameController {
    INSTANCE;
    private TurnBasedGame game;
    private final TurnBasedGame backend = new TurnBasedGame();

    public void step() {
        backend.executeStep();
    }

    public void undo() {
        backend.undoStep();
    }

    public void enqueueCommand(Command c) {backend.enqueueCommand(c);}

    public List<Command> getQueue() {
        return backend.getCommandQueue();
    }
}
