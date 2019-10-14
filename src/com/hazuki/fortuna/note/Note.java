package com.hazuki.fortuna.note;

import com.hazuki.fortuna.display.Display;
import org.newdawn.slick.Color;

public class Note {

    private int type;

    private int posX, posY;

    private int width, height;

    public Note(int type, int posX, int posY, int width, int height) {
        this.type = type;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void render() {
        this.drawRect(this.posX, this.posY, this.width, this.height, new Color(0, 0, 0, 180));

        if (this.type == 0) {
            this.drawRect(this.posX + 10, this.posY + 10, this.width + 10, this.height + 10, new Color(255, 0, 0, 80));
        }
    }

    private void drawRect(int x, int y, int width, int height, Color color) {
        Display.getGraphics().setColor(color);
        Display.getGraphics().fillRect(x, y, width, height);
    }

    public int getType() {
        return this.type;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}