package com.hazuki.fortuna.font;

import com.hazuki.fortuna.utils.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Fonts {

    private static Map<Integer, UnicodeFont> thinFonts = new HashMap<>();

    private static Map<Integer, UnicodeFont> lightFonts = new HashMap<>();

    private static final int[] thinFontSizes = {40, 60};

    private static final int[] lightFontSizes = {20, 25};

    private static final String thinTTFLocationPath = "assets/fonts/Roboto-Thin.ttf";

    private static final String lightTTFLocationPath = "assets/fonts/Roboto-Light.ttf";

    @SuppressWarnings("unchecked")
    public static void loadFonts() throws SlickException {
        /* Thin fonts */
        for (int fontSize : Fonts.thinFontSizes) {
            UnicodeFont thinFont = new UnicodeFont(Fonts.thinTTFLocationPath, fontSize, false, false);

            thinFont.getEffects().add(new ColorEffect(Color.white));
            thinFont.addAsciiGlyphs();
            thinFont.loadGlyphs();
            Fonts.thinFonts.put(fontSize, thinFont);
        }
        /* Light fonts */
        for (int fontSize : Fonts.lightFontSizes) {
            UnicodeFont lightFont = new UnicodeFont(Fonts.lightTTFLocationPath, fontSize, false, false);

            lightFont.getEffects().add(new ColorEffect(Color.white));
            lightFont.addAsciiGlyphs();
            lightFont.loadGlyphs();
            Fonts.lightFonts.put(fontSize, lightFont);
        }
        // Log
        Logger.info("Loaded" + " " + Fonts.getThinFontCount() + " " + "Thin Fonts");
        Logger.info("Loaded" + " " + Fonts.getLightFontCount() + " " + "Light Fonts");
    }

    public static UnicodeFont getThinFont(int size) {
        return Fonts.thinFonts.get(size);
    }

    private static int getThinFontCount() {
        return Fonts.thinFonts.size();
    }

    public static UnicodeFont getLightFont(int size) {
        return Fonts.lightFonts.get(size);
    }

    private static int getLightFontCount() {
        return Fonts.lightFonts.size();
    }

}