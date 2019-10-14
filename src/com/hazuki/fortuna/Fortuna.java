package com.hazuki.fortuna;

import com.hazuki.fortuna.display.Display;
import com.hazuki.fortuna.font.Fonts;
import com.hazuki.fortuna.song.SongManager;
import com.hazuki.fortuna.utils.Logger;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Fortuna extends BasicGame {

    public Fortuna(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        // Initialize graphics variable
        Display.setGraphics(gameContainer.getGraphics());
        // Load fonts
        Fonts.loadFonts();
        // Initialize current screen
        Display.getScreen().init();
        // Load songs
        SongManager.loadSongs();
        // Log
        Logger.info("Finished initializing");
    }

    @Override
    public void update(GameContainer gameContainer, int i) {
        // Update debugFPS value to FPS
        Display.setDebugFPS(gameContainer.getFPS());
        // Update current screen
        Display.getScreen().update();
        // mouse pos variables
        var mouseX = gameContainer.getInput().getMouseX();
        var mouseY = gameContainer.getInput().getMouseY();
        // Update button hovered variable
        Display.getScreen().getButtons().forEach(button -> button.setHovered(mouseX >= button.getX() && mouseX <= button.getX() + button.getWidth() && mouseY >= button.getY() && mouseY <= button.getY() + button.getHeight()));
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        if (Display.getGraphics() == null) {
            return;
        }
        // Render current screen
        Display.getScreen().render();
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        // Call actionPerformed function in Screen class
        if (button == 0) {
            Display.getScreen().getButtons().forEach(screenButton -> {
                if (screenButton.isVisible() && screenButton.isHovered()) {
                    Display.getScreen().actionPerformed(screenButton.getButtonID());
                }
            });
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        // Call keyPressed function
        Display.getScreen().keyPressed(key, c);
    }

}