package net.jacobpeterson.controller.viewcontroller;

import net.jacobpeterson.MediaFission;
import net.jacobpeterson.controller.viewcontroller.groupcontent.EditSongCompositeController;
import net.jacobpeterson.view.ApplicationShell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.Event;

public class ApplicationShellController {

    private MediaFission mediaFission;

    private ApplicationShell applicationShell;
    private EditSongCompositeController editSongCompositeController;

    public ApplicationShellController(MediaFission mediaFission) {
        this.mediaFission = mediaFission;

        this.applicationShell = new ApplicationShell();
        this.editSongCompositeController = new EditSongCompositeController(this);
    }

    public void setup() {
        this.applicationShell.setup();
        this.addListeners();
    }

    public void start() {
        this.applicationShell.start(); // Blocks until main shell is disposed
    }

    private void addListeners() {
        this.applicationShell.getShell().addListener(SWT.Close, this::handleClose);
        this.applicationShell.getDisplay().addListener(SWT.Close, this::handleClose);
        this.applicationShell.getURLText().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                handleURLTextKeyRelease(keyEvent);
            }
        });
        this.applicationShell.getURLText().addModifyListener(this::handleURLTextModification);
        this.applicationShell.getDownloadAsCombo().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                handleDownloadAsComboSelection(selectionEvent);
            }
        });
        this.applicationShell.getDownloadList().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                handleDownloadListSelect(selectionEvent);
            }
        });
    }

    private void handleURLTextKeyRelease(KeyEvent keyEvent) {
        System.out.println(keyEvent.character == 0x0D); // Return key char code
    }

    private void handleURLTextModification(ModifyEvent modifyEvent) {
        System.out.println(applicationShell.getURLText().getText());
    }

    private void handleDownloadAsComboSelection(SelectionEvent selectionEvent) {
        System.out.println("asdf");
    }

    private void handleDownloadListSelect(SelectionEvent selectionEvent) {
        System.out.println("asdf");
    }

    private void handleClose(Event event) {
        // event.doit = false if there is a download and stuff
//        MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.YES | SWT.NO);
//            dialog.setMessage("You have active downloads!\nQuiting will cancel these downloads.\n\nAre you sure you want to quit?");
//
//            if (dialog.open() == SWT.NO) {
//                event.doit = false;
//            }
    }

    public ApplicationShell getApplicationShell() {
        return applicationShell;
    }
}
