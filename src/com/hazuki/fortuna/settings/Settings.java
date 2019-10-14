package com.hazuki.fortuna.settings;

import org.lwjgl.input.Keyboard;

public class Settings {

    private static String songListFolderPath = "C:/Users/Hazuki/Documents/Fortuna/songs";

    private static int INPUT_LEFT = Keyboard.KEY_D;

    private static int INPUT_LEFT_CENTER = Keyboard.KEY_F;

    private static int INPUT_RIGHT_CENTER = Keyboard.KEY_J;

    private static int INPUT_RIGHT = Keyboard.KEY_K;

    private static int INPUT_SPACE = Keyboard.KEY_SPACE;

    private static int noteSpeed = 12; // 720.0F / 60FPS

    private static float masterVolume = 1.0F;

    private static float playMusicVolume = 0.2F;

    private static float menuMusicVolume = 0.2F;

    private static float effectVolume = 0.5F;

    private static boolean renderKeyGuide = true;

    public static int getInputLeft() {
        return Settings.INPUT_LEFT;
    }

    public static int getInputLeftCenter() {
        return Settings.INPUT_LEFT_CENTER;
    }

    public static int getInputRightCenter() {
        return Settings.INPUT_RIGHT_CENTER;
    }

    public static int getInputRight() {
        return Settings.INPUT_RIGHT;
    }

    public static int getInputSpace() {
        return Settings.INPUT_SPACE;
    }

    public static String getSongListFolderPath() {
        return Settings.songListFolderPath;
    }

    public static int getNoteSpeed() {
        return Settings.noteSpeed;
    }

    public static float getMasterVolume() {
        return Settings.masterVolume;
    }

    public static float getPlayMusicVolume() {
        return Settings.playMusicVolume;
    }

    public static float getMenuMusicVolume() {
        return Settings.menuMusicVolume;
    }

    public static float getEffectVolume() {
        return Settings.effectVolume;
    }

    public static boolean isRenderKeyGuide() {
        return Settings.renderKeyGuide;
    }

}