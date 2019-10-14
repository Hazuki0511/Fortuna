package com.hazuki.fortuna.button.buttons;

import com.hazuki.fortuna.button.Button;
import com.hazuki.fortuna.font.Fonts;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class SongButton extends Button {

    private Image songImage;

    private int fade;

    public SongButton(int buttonID, Image songImage, String buttonText, float x, float y) {
        super(buttonID, buttonText, x, y, songImage.getWidth(), songImage.getHeight());
        this.songImage = songImage;
    }

    @Override
    public void update() {
        if (!this.isVisible()) {
            return;
        }
        if (this.isHovered()) {
            if (this.fade < 180) {
                this.fade += 5;
            }
        } else {
            if (this.fade > 0) {
                this.fade -= 5;
            }
        }
    }

    @Override
    public void render() {
        if (!this.isVisible()) {
            return;
        }
        var font = Fonts.getLightFont(20);
        var fontWidth = font.getWidth(this.getButtonText());
        var fontHeight = font.getHeight(this.getButtonText());
        var centerPosX = this.getX() + (this.getWidth() / 2.0F) - (fontWidth / 2.0F);
        var posY = this.getY() + this.getHeight() + 20.0F;

        this.drawRect(this.getX() - 20.0F, this.getY() - 20.0F, this.getWidth() + 20.0F + 20.0F, this.getHeight() + 20.0F + fontHeight + 20.0F + 20.0F, new Color(255, 255, 255, this.fade));
        this.drawImage(this.songImage, this.getX(), this.getY());
        this.drawString(font, this.getButtonText(), centerPosX, posY, Color.black);
    }

}