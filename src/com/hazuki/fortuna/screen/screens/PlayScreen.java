package com.hazuki.fortuna.screen.screens;

import com.hazuki.fortuna.control.Controller;
import com.hazuki.fortuna.display.Display;
import com.hazuki.fortuna.display.DisplayInfo;
import com.hazuki.fortuna.font.Fonts;
import com.hazuki.fortuna.note.NoteManager;
import com.hazuki.fortuna.note.NoteRenderer;
import com.hazuki.fortuna.screen.Screen;
import com.hazuki.fortuna.settings.Settings;
import com.hazuki.fortuna.song.Song;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

public class PlayScreen extends Screen {

    private Song selectedSong;

    private PlayState state = PlayState.PAUSE;

    private int fade = 180;

    public PlayScreen(Song selectedSong) {
        this.selectedSong = selectedSong;
    }

    @Override
    public void init() {
        this.resetFadeAlpha();
        // Stop the song
        this.selectedSong.getSongMusic().stop();
        // Init controllers
        Controller.INPUT_LEFT = false;
        Controller.INPUT_LEFT_CENTER = false;
        Controller.INPUT_RIGHT_CENTER = false;
        Controller.INPUT_RIGHT = false;
        // Init notes
        NoteRenderer.init(this.selectedSong);
    }

    @Override
    public void actionPerformed(int buttonID) {
        super.actionPerformed(buttonID);
    }

    @Override
    public void update() {
        this.decrementFadeAlpha();
        // Update note position
        if (this.state == PlayState.PLAYING) {
            // Update fade
            if (this.fade > 0) {
                this.fade -= 5;
            }
            // Update notes
            NoteRenderer.update();
            // Update key state
            Controller.INPUT_LEFT = Keyboard.isKeyDown(Settings.getInputLeft());
            Controller.INPUT_LEFT_CENTER = Keyboard.isKeyDown(Settings.getInputLeftCenter());
            Controller.INPUT_RIGHT_CENTER = Keyboard.isKeyDown(Settings.getInputRightCenter());
            Controller.INPUT_RIGHT = Keyboard.isKeyDown(Settings.getInputRight());
        }
    }

    @Override
    public void render() {
        this.drawDefaultBackground();
        this.drawFPS();
        this.drawSongInfo();
        this.drawLane();
        this.drawKey();
        this.drawKeyGuide();

        if (this.state == PlayState.PLAYING) {
            NoteRenderer.render();
        }
        this.drawPauseFadeAnimation();
        this.drawFadeAnimation();
    }

    @Override
    public void keyPressed(int key, char c) {
        var pitch = 1.0F;
        var volume = Settings.getPlayMusicVolume() * Settings.getMasterVolume();

        if (key == Settings.getInputSpace() && this.state == PlayState.PAUSE) {
            this.selectedSong.getSongMusic().play(pitch, volume);
            this.state = PlayState.PLAYING;
        }
        if (key == Input.KEY_ESCAPE) {
            this.selectedSong.getSongMusic().stop();
            this.clearNotes();
            Display.setScreen(new SongListScreen());
        }
    }

    private void drawSongInfo() {
        var font = Fonts.getLightFont(20);
        var fontWidth = font.getWidth(this.selectedSong.getSongName());
        var centerPosX = 0.0F + (250.0F / 2.0F) - (fontWidth / 2.0F);

        this.drawImage(this.selectedSong.getSongImage(), 25.0F, 25.0F);
        this.drawString(font, this.selectedSong.getSongName(), centerPosX, 245.0F, Color.black);
    }

    private void drawLane() {
        this.drawRect(250.0F, 0.0F, 580.0F, 720.0F, new Color(255, 255, 255, 180)); // Background
        this.drawRect(395.0F, 0.0F, 1.0F, 720.0F, new Color(196, 196, 196, 255)); // Left
        this.drawRect(540.0F, 0.0F, 1.0F, 720.0F, new Color(196, 196, 196, 255)); // Center
        this.drawRect(685.0F, 0.0F, 1.0F, 720.0F, new Color(196, 196, 196, 255)); // Right
    }

    private void drawKey() {
        var leftKeyColor = Controller.INPUT_LEFT ? new Color(0, 0, 0, 180) : new Color(255, 255, 255, 180);
        var leftCenterKeyColor = Controller.INPUT_LEFT_CENTER ? new Color(0, 0, 0, 180) : new Color(255, 255, 255, 180);
        var rightCenterKeyColor = Controller.INPUT_RIGHT_CENTER ? new Color(0, 0, 0, 180) : new Color(255, 255, 255, 180);
        var rightKeyColor = Controller.INPUT_RIGHT ? new Color(0, 0, 0, 180) : new Color(255, 255, 255, 180);

        this.drawRect(250.0F, 620.0F, 145.0F, 70.0F, leftKeyColor); // Left
        this.drawRect(395.0F, 620.0F, 145.0F, 70.0F, leftCenterKeyColor); // Left center
        this.drawRect(540.0F, 620.0F, 145.0F, 70.0F, rightCenterKeyColor); // Right center
        this.drawRect(685.0F, 620.0F, 145.0F, 70.0F, rightKeyColor); // Right
    }

    private void drawKeyGuide() {
        if (!Settings.isRenderKeyGuide()) {
            return;
        }
        var keyGuideFont = Fonts.getThinFont(40);
        var leftKeyGuideColor = Controller.INPUT_LEFT ? Color.white : Color.black;
        var leftCenterKeyGuideColor = Controller.INPUT_LEFT_CENTER ? Color.white : Color.black;
        var rightCenterKeyGuideColor = Controller.INPUT_RIGHT_CENTER ? Color.white : Color.black;
        var rightKeyGuideColor = Controller.INPUT_RIGHT ? Color.white : Color.black;
        var left = Keyboard.getKeyName(Settings.getInputLeft());
        var leftCenter = Keyboard.getKeyName(Settings.getInputLeftCenter());
        var right = Keyboard.getKeyName(Settings.getInputRight());
        var rightCenter = Keyboard.getKeyName(Settings.getInputRightCenter());
        var centerPosXLeft = 250.0F + (145.0F / 2.0F) - keyGuideFont.getWidth(left);
        var centerPosXLeftCenter = 395.0F + (145.0F / 2.0F) - keyGuideFont.getWidth(leftCenter);
        var centerPosXRightCenter = 540.0F + (145.0F / 2.0F) - keyGuideFont.getWidth(right);
        var centerPosXRight = 685.0F + (145.0F / 2.0F) - keyGuideFont.getWidth(rightCenter);

        this.drawString(keyGuideFont, left, centerPosXLeft, 635.0F, leftKeyGuideColor); // Left
        this.drawString(keyGuideFont, leftCenter, centerPosXLeftCenter, 635.0F, leftCenterKeyGuideColor); // Left center
        this.drawString(keyGuideFont, rightCenter, centerPosXRightCenter, 635.0F, rightCenterKeyGuideColor); // Right center
        this.drawString(keyGuideFont, right, centerPosXRight, 635.0F, rightKeyGuideColor); // Right
    }

    private void drawPauseFadeAnimation() {
        var font = Fonts.getThinFont(40);
        var keyGuide = "Press SPACE key to start the track";
        var fontWidth = font.getWidth(keyGuide);
        var fontHeight = font.getHeight(keyGuide);
        var centerPosX = 0.0F + (DisplayInfo.DISPLAY_WIDTH / 2.0F) - (fontWidth / 2.0F);
        var centerPosY = 0.0F + (DisplayInfo.DISPLAY_HEIGHT / 2.0F) - (fontHeight / 2.0F);

        this.drawRect(0.0F, 0.0F, DisplayInfo.DISPLAY_WIDTH, DisplayInfo.DISPLAY_HEIGHT, new Color(255, 255, 255, this.fade));
        this.drawString(font, keyGuide, centerPosX, centerPosY, new Color(0, 0, 0, this.fade));
    }

    private void clearNotes() {
        NoteManager.getLeftNotes().clear();
        NoteManager.getLeftCenterNotes().clear();
        NoteManager.getRightCenterNotes().clear();
        NoteManager.getRightNotes().clear();
    }

    enum PlayState {
        PAUSE, PLAYING
    }

}