package com.hazuki.fortuna.button;

import com.hazuki.fortuna.display.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;

public class Button {

    private int buttonID;

    private String buttonText;

    private float x, y;

    private float width, height;

    private boolean hovered;

    private boolean visible;

    public Button(int buttonID, String buttonText, float x, float y, float width, float height) {
        this.buttonID = buttonID;
        this.buttonText = buttonText;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hovered = false;
        this.visible = true;
    }

    public void update() {
        // Don't write any codes here
    }

    public void render() {
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

    public int getButtonID() {
        return this.buttonID;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public boolean isHovered() {
        return this.hovered;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}