package com.my.musicplayer.utils;

public class Utils {



    public static String convertToMintsSecs(int milliseconds) {

        int secondsValue = milliseconds / 1000 % 60;
        int minutesValue = (milliseconds / (1000 * 60)) % 60;
        int hoursValue = (milliseconds / (1000 * 60 * 60)) % 24;

        String seconds = "";
        String minutes = "";
        String hours = "";

        if (secondsValue < 10) {
            seconds = "0" + secondsValue;
        } else {
            seconds = "" + secondsValue;
        }

        if (minutesValue < 10) {
            minutes = "0" + minutesValue;
        } else {
            minutes = "" + minutesValue;
        }

        if (hoursValue < 10) {
            hours = "0" + hoursValue;
        } else {
            hours = "" + hoursValue;
        }

        String output = "";
        if (hoursValue != 0) {
            output = hours + ":" + minutes + ":" + seconds;
        } else {
            output = minutes + ":" + seconds;
        }

        return output;
    }
}
