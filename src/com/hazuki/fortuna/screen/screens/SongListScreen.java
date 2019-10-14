package com.hazuki.fortuna.screen.screens;

import com.hazuki.fortuna.button.Button;
import com.hazuki.fortuna.button.buttons.BasicButton;
import com.hazuki.fortuna.button.buttons.SongButton;
import com.hazuki.fortuna.display.Display;
import com.hazuki.fortuna.font.Fonts;
import com.hazuki.fortuna.screen.Screen;
import com.hazuki.fortuna.settings.Settings;
import com.hazuki.fortuna.song.Song;
import com.hazuki.fortuna.song.SongManager;
import org.newdawn.slick.Color;

public class SongListScreen extends Screen {

    private Song selectedSong = null;

    private Button playButton = null;

    @Override
    public void init() {
        var x = 50.0F;
        var y = 100.0F;

        for (int i = 0; i < SongManager.getSongs().size(); i++) {
            Song song = SongManager.getSongs().get(i);

            if (i != 0 && i % 2 == 0) {
                x = 50.0F;
                y += 280.0F;
            }
            this.getButtons().add(new SongButton(i, song.getSongImage(), song.getSongName(), x, y));
            x += 270.0F;
        }
        this.playButton = new BasicButton(9998, "PLAY", 819.0F, 490.0F);
        this.playButton.setVisible(false);
        this.getButtons().add(this.playButton);
        this.getButtons().add(new BasicButton(9999, "BACK", 8.0F, 673.0F));
        this.resetFadeAlpha();
    }

    @Override
    public void actionPerformed(int buttonID) {
        super.actionPerformed(buttonID);

        for (int i = 0; i < SongManager.getSongs().size(); i++) {
            if (buttonID == i) {
                var pitch = 1.0F;
                var volume = Settings.getMenuMusicVolume() * Settings.getMasterVolume();

                if (this.selectedSong != null) {
                    this.selectedSong.getSongMusic().stop();
                }
                this.selectedSong = SongManager.getSongs().get(i);
                this.stopMenuMusic();
                this.selectedSong.getSongMusic().loop(pitch, volume);
            }
        }
        if (buttonID == 9998) {
            Display.setScreen(new PlayScreen(this.selectedSong));
        }
        if (buttonID == 9999) {
            if (this.selectedSong != null) {
                this.selectedSong.getSongMusic().stop();
                this.playMenuMusic();
            }
            Display.setScreen(new MainMenuScreen());
        }
    }

    @Override
    public void update() {
        if (this.selectedSong != null) {
            this.playButton.setVisible(true);
        }
        this.decrementFadeAlpha();
        this.updateButtons();
    }

    @Override
    public void render() {
        var font = Fonts.getThinFont(40);

        this.drawDefaultBackground();
        this.drawString(font, "SELECT SONG", 50.0F, 30.0F, Color.black);
        this.drawRect(650.0F, 0.0F, 430.0F, 720.0F, new Color(255, 255, 255, 180));

        if (this.selectedSong != null) {
            var fontWidth = font.getWidth(this.selectedSong.getSongName());
            var centerPosX = 650.0F + (430.0F / 2.0F) - (fontWidth / 2.0F);

            this.drawImage(this.selectedSong.getSongImage(), 765.0F, 100.0F);
            this.drawString(font, this.selectedSong.getSongName(), centerPosX, 350.0F, Color.black);
        }
        this.renderButtons();
        this.drawFadeAnimation(); // Write it at last line to use fade animation
    }

}