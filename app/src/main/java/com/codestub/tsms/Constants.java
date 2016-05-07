package com.codestub.tsms;

/**
 * This class holds the constants used across the app
 * Created by ganesh on 7/5/16.
 */
public enum  Constants {
    CONTACT_PHOTO_BYTES("contactPhotoBytes"),
    TRANSITION_SENDER("transitionSender"),
    TRANSITION_PHOTO("transitionPhoto");
    private String value;

    Constants(String value){
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
