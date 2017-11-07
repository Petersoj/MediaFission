package net.jacobpeterson.view;

import net.jacobpeterson.download.Download;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.cocoa.NSButton;
import org.eclipse.swt.internal.cocoa.NSImage;
import org.eclipse.swt.internal.cocoa.NSSize;
import org.eclipse.swt.internal.cocoa.NSString;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class EditAudioGroupContent {

    private Group groupContainer;

    private Download shownDownload;

    private Label titleLabel;
    private Text titleText;
    private Label artistLabel;
    private Text artistText;
    private Label albumLabel;
    private Text albumText;
    private Label albumArtLabel;
    private Canvas albumArtCanvas;
    private ProgressBar progressBar;
    private Label progressLabel;
    private Button saveButton;
    private Image albumArtImage;

    public EditAudioGroupContent(Group groupContainer) {
        this.groupContainer = groupContainer;

        this.titleLabel = new Label(groupContainer, SWT.NONE);
        this.titleText = new Text(groupContainer, SWT.SINGLE | SWT.BORDER);
        this.artistLabel = new Label(groupContainer, SWT.NONE);
        this.artistText = new Text(groupContainer, SWT.SINGLE | SWT.BORDER);
        this.albumLabel = new Label(groupContainer, SWT.NONE);
        this.albumText = new Text(groupContainer, SWT.SINGLE | SWT.BORDER);
        this.albumArtLabel = new Label(groupContainer, SWT.NONE);
        this.albumArtCanvas = new Canvas(groupContainer, SWT.BORDER);
        this.progressBar = new ProgressBar(groupContainer, SWT.SMOOTH | SWT.HORIZONTAL);
        this.progressLabel = new Label(groupContainer, SWT.NONE);
        this.saveButton = new Button(groupContainer, SWT.FLAT);
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

        NSButton nsSaveButton = (NSButton) saveButton.view;
        nsSaveButton.setBezelStyle(15);
        NSImage image = NSImage.imageNamed(NSString.stringWith("NSStopProgressTemplate"));
        NSSize size = new NSSize();
        size.height = 8;
        size.width = 8;
        image.setSize(size);
        nsSaveButton.setImage(image);
    }

    private void setupLayout() {
        FormLayout layout = new FormLayout();
        layout.marginTop = 10;
        layout.marginBottom = 10;
        layout.marginLeft = 10;
        layout.marginRight = 10;
        this.groupContainer.setLayout(layout);

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
        formData.top = new FormAttachment(40);
        formData.right = new FormAttachment(100);
        this.saveButton.setLayoutData(formData);

        this.groupContainer.layout();
    }
}
