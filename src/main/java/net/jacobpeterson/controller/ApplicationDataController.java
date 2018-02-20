package net.jacobpeterson.controller;

import net.jacobpeterson.MediaFission;

import java.io.File;

public class ApplicationDataController {

    private MediaFission mediaFission;

    private File applicationDataLocation;

    public ApplicationDataController(MediaFission mediaFission) {
        this.mediaFission = mediaFission;

        this.applicationDataLocation = new File(System.getProperty("user.home") + "/Library/Application Support/MediaFission/"); // Only for Mac
    }

    public void setup() {
        if (!applicationDataLocation.mkdir()) { // Failed to create app support directory

        }
    }
}
