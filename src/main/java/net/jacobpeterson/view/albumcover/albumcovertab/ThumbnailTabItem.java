package net.jacobpeterson.view.albumcover.albumcovertab;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class ThumbnailTabItem {

    private TabFolder tabFolder;

    private TabItem tabItem;
    private Composite composite;
    private Label explanationLabel;

    public ThumbnailTabItem(TabFolder tabFolder) {
        this.tabFolder = tabFolder;

        this.tabItem = new TabItem(tabFolder, SWT.NONE);
        this.composite = new Composite(tabFolder, SWT.NONE);
        this.explanationLabel = new Label(composite, SWT.WRAP | SWT.CENTER);
    }

    public void setup() {
        this.setupWidgets();
        this.setupLayout();
    }

    private void setupWidgets() {
        this.tabItem.setText("Thumbnail");
        this.tabItem.setControl(composite);
        this.explanationLabel.setText("This will attempt to use the thumbnail from the given Soundcloud or Youtube link.");
    }

    private void setupLayout() {
        FormLayout layout = new FormLayout();
        layout.marginTop = 10;
        layout.marginLeft = 10;
        layout.marginBottom = 10;
        layout.marginRight = 10;
        this.composite.setLayout(layout);

        FormData formData = new FormData();
        formData.top = new FormAttachment(40);
        formData.left = new FormAttachment(0);
        formData.right = new FormAttachment(100);
        this.explanationLabel.setLayoutData(formData);

        this.composite.layout();
    }

}
