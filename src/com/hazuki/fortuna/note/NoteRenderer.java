package com.hazuki.fortuna.note;

import com.hazuki.fortuna.settings.Settings;
import com.hazuki.fortuna.song.Song;

public class NoteRenderer {

    private static Song selectedSong;

    public static void init(Song selectedSong) {
        NoteRenderer.selectedSong = selectedSong;
        NoteManager.generateNotes(NoteRenderer.selectedSong);

    }

    public static void update() {
        NoteManager.getLeftNotes().forEach(note -> note.setPosY(note.getPosY() + Settings.getNoteSpeed())); // Left
        NoteManager.getLeftCenterNotes().forEach(note -> note.setPosY(note.getPosY() + Settings.getNoteSpeed())); // Left center
        NoteManager.getRightCenterNotes().forEach(note -> note.setPosY(note.getPosY() + Settings.getNoteSpeed())); // Right center
        NoteManager.getRightNotes().forEach(note -> note.setPosY(note.getPosY() + Settings.getNoteSpeed())); // Right
    }

    public static void render() {
        NoteManager.getLeftNotes().forEach(Note::render); // Left
        NoteManager.getLeftCenterNotes().forEach(Note::render); // Left center
        NoteManager.getRightCenterNotes().forEach(Note::render); // Right center
        NoteManager.getRightNotes().forEach(Note::render); // Right
    }

}