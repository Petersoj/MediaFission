package net.jacobpeterson.controller.viewcontroller;

import net.jacobpeterson.view.EditAlbumCoverComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EditAlbumCoverCompositeController {

    private ApplicationShellController applicationShellController;
    private EditAlbumCoverComposite editAlbumCoverComposite;

    public EditAlbumCoverCompositeController(ApplicationShellController applicationShellController) {
        this.applicationShellController = applicationShellController;
        this.editAlbumCoverComposite = new EditAlbumCoverComposite(applicationShellController.getApplicationShell());
    }

    public void setup() {
        this.editAlbumCoverComposite.setup();
        this.editAlbumCoverComposite.getComposite().setVisible(false); // Hide until needed

        this.addListeners();
    }

    private void addListeners() {
        this.editAlbumCoverComposite.getTabFolder().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                handleTabFolderSelection(selectionEvent);
            }
        });
        // May want to use MouseAdapter for press listener!!!
        this.editAlbumCoverComposite.getAlbumCoverTabItem().getRefreshButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                handleAlbumArtRefreshButtonPress(selectionEvent);
            }
        });
    }

    private void handleTabFolderSelection(SelectionEvent selectionEvent) {
        System.out.println(selectionEvent.item == editAlbumCoverComposite.getAlbumCoverTabItem().getTabItem());
    }

    private void handleAlbumArtRefreshButtonPress(SelectionEvent selectionEvent) {

    }

    private void handleCustomChooseFileButtonPress(SelectionEvent selectionEvent) {

    }

    private void handleCustomImageURLButtonPress() {

    }

    private void handleRemoveButtonPress() {

    }

    private void handleDoneButtonPress() {

    }

}
