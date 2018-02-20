package net.jacobpeterson.view;

import net.jacobpeterson.MediaFission;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ApplicationShell {

    private MediaFission mediaFission;

    private Display display;
    private Shell shell;

    private ApplicationShellContents applicationShellContents;

    public ApplicationShell(MediaFission mediaFission) {
        this.mediaFission = mediaFission;
        this.display = new Display();
        this.shell = new Shell(display);
    }

    public void setup() {
        this.setupShell();
        this.setupLayout();
    }

    private void setupShell() {
        this.shell.setText("MediaFission");
        this.shell.setMinimumSize(600, 350);
        this.shell.setSize(900, 500);
    }

    private void setupLayout() {
        // Position Shell in center of Display
        Rectangle screenSize = shell.getDisplay().getPrimaryMonitor().getBounds();
        Rectangle shellBounds = shell.getBounds();
        this.shell.setLocation((screenSize.width - shellBounds.width) / 2, (screenSize.height - shellBounds.height) / 2);
    }

    public void createContents() {
        this.applicationShellContents = new ApplicationShellContents(this);
        this.applicationShellContents.setup();
    }


    // Blocking method
    public void start() {
        this.shell.layout(); // Layout everything
        this.shell.open(); // Shows the Window

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        this.display.dispose();
    }


    public MediaFission getMediaFission() {
        return mediaFission;
    }

    public Display getDisplay() {
        return display;
    }

    public Shell getShell() {
        return shell;
    }

    public ApplicationShellContents getContents() {
        return applicationShellContents;
    }
}