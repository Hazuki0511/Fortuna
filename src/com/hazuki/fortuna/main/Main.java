package com.hazuki.fortuna.main;

import com.hazuki.fortuna.Fortuna;
import com.hazuki.fortuna.display.DisplayInfo;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Fortuna(DisplayInfo.DISPLAY_TITLE));

            app.setDisplayMode(DisplayInfo.DISPLAY_WIDTH, DisplayInfo.DISPLAY_HEIGHT, DisplayInfo.FULLSCREEN);
            app.setTargetFrameRate(DisplayInfo.TARGET_FPS);
            app.setShowFPS(DisplayInfo.SHOW_FPS);
            app.setAlwaysRender(DisplayInfo.ALWAYS_RENDER);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}