package com.gameshow.api.activity;

public class ActivityNotFoundException extends Exception {

    ActivityNotFoundException(Long activityId) {
        super("Activity " + activityId + " not found");
    }

}
