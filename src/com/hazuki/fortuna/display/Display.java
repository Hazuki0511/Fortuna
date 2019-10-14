package com.hazuki.fortuna.display;

import com.hazuki.fortuna.screen.Screen;
import com.hazuki.fortuna.screen.screens.TitleScreen;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Display {

    private static Graphics graphics;

    private static int debugFPS;

    private static Screen currentScreen = new TitleScreen();

    public static Graphics getGraphics() {
        return Display.graphics;
    }

    public static void setGraphics(Graphics graphics) {
        Display.graphics = graphics;
    }

    public static int getDebugFPS() {
        return Display.debugFPS;
    }

    public static void setDebugFPS(int debugFPS) {
        Display.debugFPS = debugFPS;
    }

    public static Screen getScreen() {
        return Display.currentScreen;
    }

    public static void setScreen(Screen currentScreen) {
        try {
            Display.currentScreen = currentScreen;
            Display.currentScreen.init();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}