package com.hazuki.fortuna.screen;

import com.hazuki.fortuna.button.Button;
import com.hazuki.fortuna.display.Display;
import com.hazuki.fortuna.display.DisplayInfo;
import com.hazuki.fortuna.font.Fonts;
import com.hazuki.fortuna.settings.Settings;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class Screen {

    private ArrayList<Button> buttons = new ArrayList<>();

    private Image defaultBackground;

    private Music defaultMenuMusic;

    private Sound defaultClickSound;

    private int alpha = 200;

    public Screen() {
        try {
            // Load background image
            this.defaultBackground = new Image("assets/textures/Background.png");
            // Load menu music
            this.defaultMenuMusic = new Music("assets/ogg/MenuMusic.ogg");
            // Load click sound
            this.defaultClickSound = new Sound("assets/ogg/ButtonClick.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void init() throws SlickException {
        // Don't write any codes here
    }

    public void actionPerformed(int buttonID) {
        this.playClickSound();
    }

    public void update() {
        // Don't write any codes here
    }

    public void render() {
        // Don't write any codes here
    }

    public void keyPressed(int key, char c) {
        // Don't write any codes here
    }

    protected void drawString(UnicodeFont font, String text, float x, float y, Color color) {
        font.drawString(x, y, text, color);
    }

    protected void drawRect(float x, float y, float width, float height, Color color) {
        Display.getGraphics().setColor(color);
        Display.getGraphics().fillRect(x, y, width, height);
    }

    protected void drawImage(Image image, float x, float y) {
        Display.getGraphics().drawImage(image, x, y);
    }

    protected void drawDefaultBackground() {
        this.drawImage(this.defaultBackground, 0.0F, 0.0F);
    }

    protected void drawFPS() {
        var font = Fonts.getLightFont(20);
        var fpsText = "FPS:" + " " + Display.getDebugFPS();
        var fontWidth = font.getWidth(fpsText);
        var fontHeight = font.getHeight(fpsText);

        this.drawString(font, fpsText, DisplayInfo.DISPLAY_WIDTH - fontWidth - 3.0F, DisplayInfo.DISPLAY_HEIGHT - fontHeight - 3.0F, Color.black);
    }

    protected void drawCredit() {
        var font = Fonts.getLightFont(20);

        this.drawString(font, "@Hazuki - 2019", 0.0F, 697.0F, Color.black);
    }

    protected void drawFadeAnimation() {
        this.drawRect(0.0F, 0.0F, DisplayInfo.DISPLAY_WIDTH, DisplayInfo.DISPLAY_HEIGHT, new Color(0, 0, 0, this.alpha));
    }

    protected void resetFadeAlpha() {
        // This is the best value
        this.alpha = 200;
    }

    protected void decrementFadeAlpha() {
        if (this.alpha > 0) {
            this.alpha -= 5;
        }
    }

    protected void playMenuMusic() {
        var pitch = 1.0F;
        var volume = Settings.getMenuMusicVolume() * Settings.getMasterVolume();

        this.defaultMenuMusic.loop(pitch, volume);
    }

    protected void stopMenuMusic() {
        this.defaultMenuMusic.stop();
    }

    protected void playClickSound() {
        var pitch = 1.0F;
        var volume = Settings.getEffectVolume() * Settings.getMasterVolume();

        this.defaultClickSound.play(pitch, volume);
    }

    protected void clearButtons() {
        this.buttons.clear();
    }

    protected void updateButtons() {
        this.buttons.forEach(Button::update);
    }

    protected void renderButtons() {
        this.buttons.forEach(Button::render);
    }

    public ArrayList<Button> getButtons() {
        return this.buttons;
    }

}