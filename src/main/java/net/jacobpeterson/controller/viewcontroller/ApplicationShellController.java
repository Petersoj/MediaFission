package net.jacobpeterson.controller.viewcontroller;

import net.jacobpeterson.MediaFission;
import net.jacobpeterson.view.ApplicationShell;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;

public class ApplicationShellController {

    private MediaFission mediaFission;

    private ApplicationShell applicationShell;

    public ApplicationShellController(MediaFission mediaFission) {
        this.mediaFission = mediaFission;

        this.applicationShell = new ApplicationShell(mediaFission);
    }

    public void setup() {
        this.applicationShell.setup();


        this.addListeners();
    }

    public void start() {
        this.applicationShell.start(); // Blocks until main shell is disposed
    }

    private void addListeners() {
//        this.applicationShell.getShell().addListener(SWT.Close, this::handleClose);
//        this.applicationShell.getDisplay().addListener(SWT.Close, this::handleClose);
//        this.applicationShell.getURLText().addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent keyEvent) {
//                handleURLTextKeyRelease(keyEvent);
//            }
//        });
//        //this.applicationShell.getURLText().addModifyListener(this::handleURLTextModification); maybe implement later for soundcloud links (remove video option)
//        this.applicationShell.getDownloadAsCombo().addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent selectionEvent) {
//                handleDownloadAsComboSelection(selectionEvent);
//            }
//        });
//        this.applicationShell.getDownloadList().addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent selectionEvent) {
//                handleDownloadListSelect(selectionEvent);
//            }
//        });
    }

    private void handleURLTextKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.character == 0x0D) { // ASCII hex code for the Return key
//            this.mediaFission.getDownloadsController().download(applicationShell.getURLText().getText());
        }
    }

    private void handleDownloadAsComboSelection(SelectionEvent selectionEvent) {
//        this.mediaFission.getDownloadsController().download(applicationShell.getURLText().getText());
    }

    private void handleDownloadListSelect(SelectionEvent selectionEvent) {

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
