package com.hazuki.fortuna.song;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;

import java.io.File;

public class Song {

    private String songName;

    private Music songMusic;

    private Image songImage;

    private File songSheetFile;

    public Song(String songName, Music songMusic, Image songImage, File songSheetFile) {
        this.songName = songName;
        this.songMusic = songMusic;
        this.songImage = songImage;
        this.songSheetFile = songSheetFile;
    }

    public String getSongName() {
        return this.songName;
    }

    public Music getSongMusic() {
        return this.songMusic;
    }

    public Image getSongImage() {
        return this.songImage;
    }

    public File getSongSheetFile() {
        return this.songSheetFile;
    }

}