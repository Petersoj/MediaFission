package net.jacobpeterson.view.groupcomposite.song;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class AlbumCoverCanvas implements PaintListener, MouseMoveListener, MouseWheelListener {

    private Composite composite;

    private Canvas canvas;
    private Image albumCover;

    public AlbumCoverCanvas(Composite composite) {
        this.composite = composite;
        this.canvas = new Canvas(composite, SWT.BORDER);
    }

    public void setup() {
        // All added listeners are for UI updating and interacting
        this.canvas.addPaintListener(this);
        this.canvas.addMouseWheelListener(this);
        this.canvas.addMouseMoveListener(this);
        this.canvas.addListener(SWT.Resize, e -> {
            // Used to maintain a 1:1 aspect ration for this canvas.
            // Will overflow on the right side, but eh.
            FormData formData = (FormData) canvas.getLayoutData();
            formData.width = canvas.getSize().y;
            canvas.setLayoutData(formData);
            canvas.getShell().layout(new Control[]{canvas}, SWT.CHANGED);
        });
    }

    @Override
    public void paintControl(PaintEvent paintEvent) {
        GC gc = paintEvent.gc;
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent) {
        if (mouseEvent.stateMask == SWT.BUTTON1) {

        }
    }

    @Override
    public void mouseScrolled(MouseEvent mouseEvent) {

    }

    public void setAlbumCoverImage(Image albumCover) {
        if (albumCover != null) {
            this.canvas.setCursor(composite.getDisplay().getSystemCursor(SWT.CURSOR_HAND)); // Lets user know they can drag the image around.
        } else {
            this.canvas.setCursor(null); // Reset cursor to normal
        }
        this.albumCover = albumCover;
    }


    public Canvas getCanvas() {
        return canvas;
    }
}
