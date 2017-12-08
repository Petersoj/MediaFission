package net.jacobpeterson.view;

import net.jacobpeterson.view.albumcovertab.AlbumCoverTabItem;
import net.jacobpeterson.view.albumcovertab.CustomTabItem;
import net.jacobpeterson.view.albumcovertab.ThumbnailTabItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

public class EditAlbumCoverComposite {

    private ApplicationShell applicationShell;
    private Composite composite;

    private TabFolder tabFolder;
    private AlbumCoverTabItem albumCoverTabItem;
    private ThumbnailTabItem thumbnailTabItem;
    private CustomTabItem customTabItem;
    private Button removeButton;
    private Button doneButton;

    public EditAlbumCoverComposite(ApplicationShell applicationShell) {
        this.applicationShell = applicationShell;
        this.composite = new Composite(applicationShell.getShell(), SWT.NONE);

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
        // External Layout

        FormData externalFormData = new FormData();
        externalFormData.top = new FormAttachment(applicationShell.getDownloadList(), 0, SWT.TOP);
        externalFormData.left = new FormAttachment(0);
        externalFormData.bottom = new FormAttachment(100);
        externalFormData.right = new FormAttachment(applicationShell.getDownloadGroup(), 0, SWT.LEFT);
        this.composite.setLayoutData(externalFormData);

        // Internal Layout

        FormLayout layout = new FormLayout();
        this.composite.setLayout(layout);

        FormData internalFormData = new FormData();
        internalFormData.top = new FormAttachment(0);
        internalFormData.left = new FormAttachment(0);
        internalFormData.bottom = new FormAttachment(doneButton, 5, SWT.TOP);
        internalFormData.right = new FormAttachment(100);
        this.tabFolder.setLayoutData(internalFormData);

        internalFormData = new FormData();
        internalFormData.bottom = new FormAttachment(100);
        internalFormData.right = new FormAttachment(doneButton, 5, SWT.LEFT);
        this.removeButton.setLayoutData(internalFormData);

        internalFormData = new FormData();
        internalFormData.bottom = new FormAttachment(100);
        internalFormData.right = new FormAttachment(100);
        this.doneButton.setLayoutData(internalFormData);

        this.composite.layout();
    }

    public Composite getComposite() {
        return composite;
    }
}
