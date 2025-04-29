package com.thaddycat.gradletest.backend;

import com.badlogic.gdx.Game;
import com.thaddycat.gradletest.CommandManager;
import com.thaddycat.gradletest.GameScreen;

import java.util.List;

public class TurnBasedGame extends Game {

    public TurnBasedGame() {
        MapGenerator.generateCellArray(5, 5);
    }


    public void executeStep() {
        System.out.println("Executing Game Step...");
        for (GameCharacter c : GameCharacter.getCharacterArrayList()) {
            System.out.println("Executing CommandManager for " + c.getName());
            c.getCommandManager().execute();
        }
        System.out.println("Game Step Successfully Executed. \n");

    }

    public void enqueueCommand(Command command) {
        if (command == null || command.getCharacter() == null) {
            System.out.println("Cannot enqueue command: command or character is null.");
            return;
        }

        GameCharacter character = command.getCharacter();
        CommandManager cm = character.getCommandManager();

        if (cm == null) {
            System.out.println("CommandManager is null for " + character.getName());
            return;
        }

        cm.setQueuedCommand(command); // 🔁 replaces any previous one
        System.out.println("Command enqueued for: " + character.getName());
    }

    public void undoStep() {
        System.out.println("[DEBUG] TurnBasedGame.undoStep() called.");
        for (GameCharacter c : GameCharacter.getCharacterArrayList()) {
            CommandManager cm = c.getCommandManager();
            if (cm == null) {
                System.err.println("CommandManager is null for " + c.getName());
                continue;
            }
            System.out.println("[DEBUG] " + c.getName() + " undoing CommandManager");
            cm.undo();
        }

        System.out.println("[DEBUG] Game Step Successfully Undone.\n");
    }
    public List<Command> getCommandQueue() {
        List<Command> allQueued = new java.util.ArrayList<>();
        for (GameCharacter c : GameCharacter.getCharacterArrayList()) {
            CommandManager cm = c.getCommandManager();
            if (cm != null) {
                allQueued.addAll(cm.getQueuedCommands()); // ← call to per-character queue
            }
        }
        return allQueued;
    }


    @Override
    public void create() {
        this.setScreen(new GameScreen());
    }
}
