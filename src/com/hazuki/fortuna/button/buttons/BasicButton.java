package com.hazuki.fortuna.button.buttons;

import com.hazuki.fortuna.button.Button;
import com.hazuki.fortuna.font.Fonts;
import org.newdawn.slick.Color;

public class BasicButton extends Button {

    private int saturation;

    public BasicButton(int buttonID, String buttonText, float x, float y) {
        super(buttonID, buttonText, x, y, Fonts.getThinFont(40).getWidth(buttonText), Fonts.getThinFont(40).getHeight(buttonText));
    }

    @Override
    public void update() {
        if (!this.isVisible()) {
            return;
        }
        if (this.isHovered()) {
            if (this.saturation < 160) {
                this.saturation += 5;
            }
        } else {
            if (this.saturation > 0) {
                this.saturation -= 5;
            }
        }
    }

    @Override
    public void render() {
        if (!this.isVisible()) {
            return;
        }
        var font = Fonts.getThinFont(40);

        this.drawString(font, this.getButtonText(), this.getX(), this.getY(), new Color(this.saturation, this.saturation, this.saturation, 255));
    }

}