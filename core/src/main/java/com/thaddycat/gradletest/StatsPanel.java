package com.thaddycat.gradletest;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.thaddycat.gradletest.backend.Character;

public class StatsPanel extends Table {
    private Label nameLabel;
    private Label hpLabel;
    private Label commandLabel;


    public StatsPanel(Skin skin) {
        super();
        nameLabel = new Label("Name: N/A", skin);
        hpLabel = new Label("HP: N/A", skin);
        commandLabel = new Label("Queued: N/A", skin);

        this.top().left();
        this.add(commandLabel).left().row();
        this.add(nameLabel).left().row();
        this.add(hpLabel).left().row();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        // add logic to hide/show actual UI elements if needed
    }

    public void updateCommandText(Character character) {
        if (character != null) {
            nameLabel.setText("Name: " + character.getName());
            hpLabel.setText("HP: " + character.getHp() + "/" + character.getMaxHp());
        } else {
            nameLabel.setText("Name: N/A");
            hpLabel.setText("HP: N/A");
        }
    }
    public void update(Character character, String queued) {
        if (character != null) {
            nameLabel.setText("Name: " + character.getName());
            hpLabel.setText("HP: " + character.getHp() + "/" + character.getMaxHp());
            commandLabel.setText("Queued: " + (queued != null ? queued : "N/A"));
        } else {
            nameLabel.setText("Name: N/A");
            hpLabel.setText("HP: N/A");
            commandLabel.setText("Queued: N/A");
        }
    }

}
