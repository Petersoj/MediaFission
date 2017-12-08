package net.jacobpeterson.controller.viewcontroller.groupcontent;

import net.jacobpeterson.controller.viewcontroller.ApplicationShellController;
import net.jacobpeterson.view.groupcontent.EditSongComposite;

public class EditSongCompositeController {

    private ApplicationShellController applicationShellController;
    private EditSongComposite editSongGroupComposite;

    public EditSongCompositeController(ApplicationShellController applicationShellController) {
        this.applicationShellController = applicationShellController;

        this.editSongGroupComposite = new EditSongComposite(applicationShellController.getApplicationShell().getDownloadGroup());
    }

}
