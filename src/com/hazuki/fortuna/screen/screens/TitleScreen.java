package com.hazuki.fortuna.screen.screens;

import com.hazuki.fortuna.button.buttons.BasicButton;
import com.hazuki.fortuna.display.Display;
import com.hazuki.fortuna.font.Fonts;
import com.hazuki.fortuna.screen.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TitleScreen extends Screen {

    private Image logoImage;

    private int fade = 255;

    private boolean shouldIncrement = false;

    @Override
    public void init() throws SlickException {
        this.logoImage = new Image("assets/textures/Logo.png");
        this.playMenuMusic();
        this.clearButtons();
        this.getButtons().add(new BasicButton(0, "EXIT", 993.0F, 673.0F));
    }

    @Override
    public void actionPerformed(int buttonID) {
        super.actionPerformed(buttonID);

        if (buttonID == 0) {
            System.exit(0);
        }
    }

    @Override
    public void update() {
        if (this.fade <= 50 && !this.shouldIncrement) {
            this.shouldIncrement = true;
        }
        if (this.fade >= 255 && this.shouldIncrement) {
            this.shouldIncrement = false;
        }
        if (this.shouldIncrement) {
            this.fade += 5;
        } else {
            this.fade -= 5;
        }
        this.updateButtons();
    }

    @Override
    public void render() {
        var font = Fonts.getLightFont(20);

        this.drawDefaultBackground();
        this.drawCredit();
        this.drawImage(this.logoImage, 318.0F, 200.0F);
        this.drawString(font, "Press any key to continue", 429.0F, 400.0F, new Color(0, 0, 0, this.fade));
        this.renderButtons();
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key != -1) {
            // Change the screen to MainMenu
            Display.setScreen(new MainMenuScreen());
        }
    }
}