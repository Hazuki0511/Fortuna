package com.hazuki.fortuna.screen.screens;

import com.hazuki.fortuna.button.buttons.BasicButton;
import com.hazuki.fortuna.display.Display;
import com.hazuki.fortuna.screen.Screen;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MainMenuScreen extends Screen {

    private Image logoImage;

    @Override
    public void init() throws SlickException {
        this.logoImage = new Image("assets/textures/Logo.png");
        this.resetFadeAlpha();
        this.clearButtons();
        this.getButtons().add(new BasicButton(0, "PLAY", 494.0F, 450.0F));
        this.getButtons().add(new BasicButton(1, "OPTIONS", 200.0F, 450.0F));
        this.getButtons().add(new BasicButton(2, "BACK", 716.0F, 450.0F));
    }

    @Override
    public void actionPerformed(int buttonID) {
        super.actionPerformed(buttonID);

        if (buttonID == 0) {
            Display.setScreen(new SongListScreen());
        }
        if (buttonID == 1) {

        }
        if (buttonID == 2) {
            Display.setScreen(new TitleScreen());
        }
    }

    @Override
    public void update() {
        this.decrementFadeAlpha();
        this.updateButtons();
    }

    @Override
    public void render() {
        this.drawDefaultBackground();
        this.drawCredit();
        this.drawImage(this.logoImage, 318.0F, 200.0F);
        this.renderButtons();
        this.drawFadeAnimation(); // Write it at last line to use fade animation
    }

}