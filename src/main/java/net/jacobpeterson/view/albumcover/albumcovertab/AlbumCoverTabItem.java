package net.jacobpeterson.view.albumcover.albumcovertab;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class AlbumCoverTabItem {

    private TabFolder tabFolder;

    private TabItem tabItem;
    private Composite composite;
    private Label explanationLabel;
    private Label actualTitleLabel;
    private Text actualTitleText;
    private Label actualArtistLabel;
    private Text actualArtistText;
    private Label actualExplanationLabel;
    private Button refreshButton;

    public AlbumCoverTabItem(TabFolder tabFolder) {
        this.tabFolder = tabFolder;

        this.tabItem = new TabItem(tabFolder, SWT.NONE);
        this.composite = new Composite(tabFolder, SWT.NONE);
        this.explanationLabel = new Label(composite, SWT.WRAP | SWT.CENTER);
        this.actualTitleLabel = new Label(composite, SWT.NONE);
        this.actualTitleText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        this.actualArtistLabel = new Label(composite, SWT.NONE);
        this.actualArtistText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        this.actualExplanationLabel = new Label(composite, SWT.WRAP | SWT.CENTER);
        this.refreshButton = new Button(composite, SWT.PUSH);
    }

    public void setup() {
        this.setupWidgets();
        this.setupLayout();
    }

    private void setupWidgets() {
        this.tabItem.setText("Album Cover");
        this.tabItem.setControl(composite);

        this.explanationLabel.setText("This program uses an external web service to load the album covers of various songs. " +
                "Sometimes, depending on the title and artist name format, the album cover is not loaded due to varying titles/artists of the same song/artist. " +
                "If the album cover fails to load, try changing the below words to something the web service database may have indexed.");
        this.actualTitleLabel.setText("Actual Title");
        this.actualArtistLabel.setText("Actual Artist");
        this.actualExplanationLabel.setText("Generally, removing words like \'remix\' helps. Click the button below to re-attempt fetching the album cover.");
        this.refreshButton.setText("Refresh");

        Color explanationColor = new Color(tabFolder.getDisplay(), 127, 127, 127);
        this.explanationLabel.setForeground(explanationColor);
        this.actualExplanationLabel.setForeground(explanationColor);
    }

    private void setupLayout() {
        FormLayout layout = new FormLayout();
        layout.marginTop = 10;
        layout.marginLeft = 10;
        layout.marginBottom = 10;
        layout.marginRight = 10;
        this.composite.setLayout(layout);

        FormData formData = new FormData();
        formData.top = new FormAttachment(0);
        formData.left = new FormAttachment(0);
        formData.right = new FormAttachment(100);
        this.explanationLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(actualTitleText, 0, SWT.CENTER);
        formData.right = new FormAttachment(actualArtistLabel, 0, SWT.RIGHT);
        this.actualTitleLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(explanationLabel, 10, SWT.BOTTOM);
        formData.left = new FormAttachment(actualTitleLabel, 10, SWT.RIGHT);
        formData.right = new FormAttachment(100);
        this.actualTitleText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(actualArtistText, 0, SWT.CENTER);
        formData.left = new FormAttachment(0);
        this.actualArtistLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(actualTitleText, 10, SWT.BOTTOM);
        formData.left = new FormAttachment(actualArtistLabel, 10, SWT.RIGHT);
        formData.right = new FormAttachment(100);
        this.actualArtistText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(actualArtistText, 10, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        formData.right = new FormAttachment(100);
        this.actualExplanationLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(actualExplanationLabel, 10, SWT.BOTTOM);
        formData.right = new FormAttachment(100);
        this.refreshButton.setLayoutData(formData);

        this.composite.layout();
    }

}
