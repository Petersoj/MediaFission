package net.jacobpeterson.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.cocoa.NSButton;
import org.eclipse.swt.internal.cocoa.NSString;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class EditAudioComposite extends Composite {

    private Label titleLabel;
    private Text titleText;
    private Label artistLabel;
    private Text artistText;
    private Label albumLabel;
    private Text albumText;
    private Label albumArtLabel;
    private Canvas albumArtCanvas;
    private Button refreshAlbumArtButton;
    private ProgressBar progressBar;
    private Label progressLabel;
    private Button saveButton;
    private Image albumArtImage;

    public EditAudioComposite(Composite composite) {
        super(composite, SWT.NONE);

        this.titleLabel = new Label(this, SWT.NONE);
        this.titleText = new Text(this, SWT.SINGLE | SWT.BORDER);
        this.artistLabel = new Label(this, SWT.NONE);
        this.artistText = new Text(this, SWT.SINGLE | SWT.BORDER);
        this.albumLabel = new Label(this, SWT.NONE);
        this.albumText = new Text(this, SWT.SINGLE | SWT.BORDER);
        this.albumArtLabel = new Label(this, SWT.NONE);
        this.albumArtCanvas = new Canvas(this, SWT.BORDER);
        this.refreshAlbumArtButton = new Button(this, SWT.PUSH);
        this.progressBar = new ProgressBar(this, SWT.SMOOTH | SWT.HORIZONTAL);
        this.progressLabel = new Label(this, SWT.NONE);
        this.saveButton = new Button(this, SWT.PUSH);
    }

    public void setup() {
        this.setupWidgets();
        this.setupLayout();
    }

    private void setupWidgets() {
        this.titleLabel.setText("Title");
        this.artistLabel.setText("Artist");
        this.albumLabel.setText("Album");
        this.albumArtLabel.setText("Album Art");

        NSButton nsRefreshAlbumArtButton = (NSButton) refreshAlbumArtButton.view;
        nsRefreshAlbumArtButton.cell().setControlSize(2); // NSControlSize.mini
        this.refreshAlbumArtButton.setText("Refresh");

        NSButton nsSaveButton = (NSButton) saveButton.view;
        nsSaveButton.setKeyEquivalent(NSString.stringWith("\r"));
        this.saveButton.setText("Save");
    }

    private void setupLayout() {
        FormLayout layout = new FormLayout();
        layout.marginTop = 10;
        layout.marginBottom = 10;
        layout.marginLeft = 10;
        layout.marginRight = 10;
        this.setLayout(layout);

        FormData formData = new FormData();
        formData.top = new FormAttachment(titleText, 0, SWT.CENTER);
        formData.right = new FormAttachment(albumArtLabel, 0, SWT.RIGHT);
        this.titleLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(0);
        formData.left = new FormAttachment(titleLabel, 10, SWT.RIGHT);
        formData.right = new FormAttachment(100);
        this.titleText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(artistText, 0, SWT.CENTER);
        formData.right = new FormAttachment(albumArtLabel, 0, SWT.RIGHT);
        this.artistLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(titleText, 11, SWT.BOTTOM);
        formData.left = new FormAttachment(titleText, 0, SWT.LEFT);
        formData.right = new FormAttachment(100);
        this.artistText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumText, 0, SWT.CENTER);
        formData.right = new FormAttachment(albumArtLabel, 0, SWT.RIGHT);
        this.albumLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(artistText, 11, SWT.BOTTOM);
        formData.left = new FormAttachment(artistText, 0, SWT.LEFT);
        formData.right = new FormAttachment(100);
        this.albumText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumArtCanvas, 0, SWT.CENTER);
        formData.left = new FormAttachment(0);
        this.albumArtLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumText, 11, SWT.BOTTOM);
        formData.left = new FormAttachment(albumText, 0, SWT.LEFT);
        this.albumArtCanvas.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumArtCanvas, 2, SWT.BOTTOM);
        formData.left = new FormAttachment(albumArtCanvas, 0, SWT.CENTER);
        this.refreshAlbumArtButton.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(refreshAlbumArtButton, 0, SWT.CENTER);
        formData.right = new FormAttachment(100);
        this.saveButton.setLayoutData(formData);

        this.layout();
    }

}
