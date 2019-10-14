package com.hazuki.fortuna.song;

import com.hazuki.fortuna.settings.Settings;
import com.hazuki.fortuna.utils.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SongManager {

    private static ArrayList<Song> songs = new ArrayList<>();

    public static void loadSongs() throws SlickException {
        File songListFolder = new File(Settings.getSongListFolderPath());
        File[] songFolders = songListFolder.listFiles();
        String songName = null;
        Music songMusic = null;
        Image songImage = null;
        File songSheetFile = null;

        if (songFolders == null) {
            return;
        }
        for (File songFolder : songFolders) {
            if (songFolder.exists() && songFolder.isDirectory()) {
                File[] songFiles = songFolder.listFiles();

                if (songFiles == null) {
                    return;
                }
                for (File songFile : songFiles) {
                    if (songFile.exists() && songFile.isFile()) {
                        var fileName = songFile.getName();
                        var filePath = songFile.getPath();

                        switch (fileName) {
                            case "Sheet.fss": {
                                songSheetFile = songFile;

                                try {
                                    FileReader fr = new FileReader(songFile);
                                    BufferedReader br = new BufferedReader(fr);
                                    String line;

                                    while ((line = br.readLine()) != null) {
                                        var lineText = line.split(" ", 2);

                                        if (lineText[0].equalsIgnoreCase("[SongName]")) {
                                            songName = lineText[1];
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            case "Song.ogg": {
                                songMusic = new Music(filePath);
                                break;
                            }
                            case "Image.png": {
                                songImage = new Image(filePath);
                                break;
                            }
                        }
                    }
                }
                SongManager.songs.add(new Song(songName, songMusic, songImage, songSheetFile));
            }
        }
        Logger.info("Loaded" + " " + SongManager.songs.size() + " " + "Songs");
    }

    public static ArrayList<Song> getSongs() {
        return SongManager.songs;
    }

}