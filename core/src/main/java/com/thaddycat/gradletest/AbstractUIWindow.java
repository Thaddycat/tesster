package com.thaddycat.gradletest;

// Basic abstract class for UI windows

import com.badlogic.gdx.scenes.scene2d.Group;
import com.thaddycat.gradletest.backend.Command;
import com.thaddycat.gradletest.backend.CommandHistory;
import com.thaddycat.gradletest.backend.CommandMacro;
import com.thaddycat.gradletest.backend.CommandPool;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUIWindow extends Group {
    public abstract void setVisible(boolean visible);

    public static class CommandManager {
        private final CommandHistory commandHistory;
        private final CommandPool<CommandMacro> commandPool;
        private final List<Command> currentCommands = new ArrayList<>();
        private final int maxHistorySize;

        public CommandManager() {
            this.maxHistorySize = 5; // Default value
            this.commandHistory = new CommandHistory();
            this.commandPool = new CommandPool<>(
                    CommandMacro::new,
                    0, // Initial pool size
                    this.maxHistorySize // Maximum pool/history size
            );
        }

        public void addCommand(Command command) {
            this.currentCommands.add(command);
        }

        // Only allows ONE command at a time per character
        public void setQueuedCommand(Command command) {
            // Remove any existing command from the same character
            currentCommands.removeIf(c -> c.getCharacter() == command.getCharacter());

            currentCommands.add(command);
            System.out.println("Queued command for: " + command.getCharacter().getName());
        }

        public void execute() {
            if (this.currentCommands.isEmpty()) return;

            // Create a new macro for this step
            CommandMacro stepMacro = new CommandMacro();
            stepMacro.addAll(this.currentCommands);

            System.out.println("Executing commands: " + currentCommands.size());
            for (Command c : currentCommands) {
                System.out.println("Executing: " + c);

            }
            stepMacro.execute();

            // Add to history and enforce size limit
            this.commandHistory.push(stepMacro);
            if (this.commandHistory.size() > this.maxHistorySize) {
                this.commandHistory.removeOldest();
            }

            // Reset for the next step
            this.currentCommands.clear();
        }

        public void undo() {
            if (this.commandHistory.isEmpty()) {
                System.out.println("Undo History Empty: Nothing to undo!");
                return;
            }

            CommandMacro lastStep = this.commandHistory.pop();
            lastStep.undo();

            // Return to pool for reuse
            this.commandPool.release(lastStep);
        }
        public List<Command> getQueuedCommands() {
            return currentCommands;
        }
        public void clearQueuedCommands() {
            currentCommands.clear();
        }

    }
}
