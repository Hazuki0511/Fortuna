package com.hazuki.fortuna.note;

import com.hazuki.fortuna.display.DisplayInfo;
import com.hazuki.fortuna.song.Song;
import com.hazuki.fortuna.utils.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NoteManager {

    private static ArrayList<Note> leftNotes = new ArrayList<>();

    private static ArrayList<Note> leftCenterNotes = new ArrayList<>();

    private static ArrayList<Note> rightCenterNotes = new ArrayList<>();

    private static ArrayList<Note> rightNotes = new ArrayList<>();

    static void generateNotes(Song selectedSong) {
        int posY = DisplayInfo.DISPLAY_HEIGHT - NoteInfo.NOTE_HEIGHT;

        try {
            FileReader fr = new FileReader(selectedSong.getSongSheetFile());
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                var texts = line.split(" ");
                var left = texts[0];
                var leftCenter = texts[1];
                var rightCenter = texts[2];
                var right = texts[3];

                if (texts[0].equalsIgnoreCase("[SongName]")) {
                    continue;
                }
                NoteManager.leftNotes.add(new Note(Integer.parseInt(left), NoteInfo.NOTE_POS_X[0], posY, NoteInfo.NOTE_WIDTH, NoteInfo.NOTE_HEIGHT));
                NoteManager.leftCenterNotes.add(new Note(Integer.parseInt(leftCenter), NoteInfo.NOTE_POS_X[1], posY, NoteInfo.NOTE_WIDTH, NoteInfo.NOTE_HEIGHT));
                NoteManager.rightCenterNotes.add(new Note(Integer.parseInt(rightCenter), NoteInfo.NOTE_POS_X[2], posY, NoteInfo.NOTE_WIDTH, NoteInfo.NOTE_HEIGHT));
                NoteManager.rightNotes.add(new Note(Integer.parseInt(right), NoteInfo.NOTE_POS_X[3], posY, NoteInfo.NOTE_WIDTH, NoteInfo.NOTE_HEIGHT));
                posY -= NoteInfo.NOTE_HEIGHT;
            }
            // Log
            Logger.info("Loading notes is finished");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Note> getLeftNotes() {
        return NoteManager.leftNotes;
    }

    public static ArrayList<Note> getLeftCenterNotes() {
        return NoteManager.leftCenterNotes;
    }

    public static ArrayList<Note> getRightCenterNotes() {
        return NoteManager.rightCenterNotes;
    }

    public static ArrayList<Note> getRightNotes() {
        return NoteManager.rightNotes;
    }

}