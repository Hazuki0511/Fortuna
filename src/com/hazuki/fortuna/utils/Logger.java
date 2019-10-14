package com.hazuki.fortuna.utils;

import org.newdawn.slick.util.Log;

public class Logger {

    public static void info(String msg) {
        Log.info(" " + "[Fortuna] [INFO]:" + " " + msg);
    }

    public static void debug(String msg) {
        Log.debug(" " + "[Fortuna] [DEBUG]:" + " " + msg);
    }

    public static void warn(String msg) {
        Log.warn(" " + "[Fortuna] [WARNING]:" + " " + msg);
    }

    public static void error(String msg) {
        Log.error(" " + "[Fortuna] [ERROR]:" + " " + msg);
    }

}