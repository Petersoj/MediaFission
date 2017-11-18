package net.jacobpeterson.view.albumcover;

import net.jacobpeterson.view.ApplicationShell;
import net.jacobpeterson.view.albumcover.albumcovertab.AlbumCoverTabItem;
import net.jacobpeterson.view.albumcover.albumcovertab.CustomTabItem;
import net.jacobpeterson.view.albumcover.albumcovertab.ThumbnailTabItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

public class EditAlbumCoverComposite {

    private Composite composite;

    private TabFolder tabFolder;
    private AlbumCoverTabItem albumCoverTabItem;
    private ThumbnailTabItem thumbnailTabItem;
    private CustomTabItem customTabItem;
    private Button removeButton;
    private Button doneButton;

    public EditAlbumCoverComposite(ApplicationShell shell) {
        this.composite = new Composite(shell.getShell(), SWT.NONE);

        this.tabFolder = new TabFolder(composite, SWT.TOP);
        this.albumCoverTabItem = new AlbumCoverTabItem(tabFolder);
        this.thumbnailTabItem = new ThumbnailTabItem(tabFolder);
        this.customTabItem = new CustomTabItem(tabFolder);
        this.removeButton = new Button(composite, SWT.PUSH);
        this.doneButton = new Button(composite, SWT.PUSH);
    }

    public void setup() {
        this.setupWidgets();
        this.setupLayout();
    }

    private void setupWidgets() {
        this.albumCoverTabItem.setup();
        this.thumbnailTabItem.setup();
        this.customTabItem.setup();

        this.removeButton.setText("Remove");
        this.doneButton.setText("Done");
    }

    private void setupLayout() {
        FormLayout layout = new FormLayout();
        this.composite.setLayout(layout);

        FormData formData = new FormData();
        formData.top = new FormAttachment(0);
        formData.left = new FormAttachment(0);
        formData.bottom = new FormAttachment(doneButton, 5, SWT.TOP);
        formData.right = new FormAttachment(100);
        this.tabFolder.setLayoutData(formData);

        formData = new FormData();
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(doneButton, 5, SWT.LEFT);
        this.removeButton.setLayoutData(formData);

        formData = new FormData();
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(100);
        this.doneButton.setLayoutData(formData);

        this.composite.layout();
    }

    public Composite getComposite() {
        return composite;
    }
}
