package net.jacobpeterson.util;

import net.jacobpeterson.MediaFission;

public class ExceptionUtils {

    private static MediaFission mediaFissionInstance;

    public static void setMediaFissionInstance(MediaFission mediaFission) {
        mediaFissionInstance = mediaFission;
    }

    public static void handleException(Exception e, boolean fatal) {
        e.printStackTrace(); // If ever debugging
        if (fatal) {
            System.exit(0);
        }
    }

    public static void handleError(String error, boolean openDialog, boolean fatal) {
        try {
            if (openDialog) {
//                MessageBox dialog = new MessageBox(mediaFissionInstance.getShellController().getApplicationShell().getShell(), SWT.ICON_ERROR | SWT.YES | SWT.NO);
//                dialog.setMessage("ERROR: " + error);
            }
        } catch (NullPointerException e) { // In case one of the above instances is null
            fatal = true;
        }
        if (fatal) {
            System.exit(0);
        }
    }

}
