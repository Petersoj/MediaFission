package net.jacobpeterson.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class ShellContent {

    private Shell shell;

    private Text urlTextField;
    private Label downloadAsLabel;
    private Button audioButton;
    private Button videoButton;
    private Label downloadListLabel;
    private List downloadList;
    private Group downloadInfoGroup;

    public ShellContent(Shell shell) {
        this.shell = shell;

        this.urlTextField = new Text(shell, SWT.SINGLE | SWT.BORDER);
        this.downloadAsLabel = new Label(shell, SWT.NONE);
        this.audioButton = new Button(shell, SWT.PUSH);
        this.videoButton = new Button(shell, SWT.PUSH);
        this.downloadListLabel = new Label(shell, SWT.NONE);
        this.downloadList = new List(shell, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
        this.downloadInfoGroup = new Group(shell, SWT.NONE);
    }

    public void setup() {
        this.setupShell();
        this.setupWidgets();
        this.setupLayout();

        EditAudioComposite composite = new EditAudioComposite(downloadInfoGroup);
        composite.setup();
        this.downloadInfoGroup.layout();
    }

    private void setupShell() {
        this.shell.setText("YouDown");
        this.shell.setMinimumSize(550, 300);
        this.shell.setSize(640, 400);

        Rectangle screenSize = shell.getDisplay().getPrimaryMonitor().getBounds();
        Rectangle shellBounds = shell.getBounds();
        this.shell.setLocation((screenSize.width - shellBounds.width) / 2, (screenSize.height - shellBounds.height) / 2);
    }

    private void setupWidgets() {
        this.urlTextField.setMessage("Enter YouTube or Vimeo URL");
        this.downloadAsLabel.setText("Download as:");
        this.audioButton.setText("Audio");
        this.videoButton.setText("Video");

        this.downloadListLabel.setText("Downloads");
        FontData[] fontData = downloadListLabel.getFont().getFontData();
        fontData[0].setHeight(11);
        this.downloadListLabel.setFont(new Font(shell.getDisplay(), fontData[0]));

        this.downloadInfoGroup.setText("Download Info/Edit");
        this.downloadInfoGroup.setLayout(new FillLayout());
    }

    private void setupLayout() {
        FormLayout layout = new FormLayout();
        layout.marginTop = 20;
        layout.marginBottom = 20;
        layout.marginLeft = 20;
        layout.marginRight = 20;
        this.shell.setLayout(layout);

        FormData formData = new FormData();
        formData.top = new FormAttachment(0);
        formData.left = new FormAttachment(0);
        formData.right = new FormAttachment(downloadAsLabel, -8, SWT.LEFT);
        this.urlTextField.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlTextField, 0, SWT.CENTER);
        formData.right = new FormAttachment(audioButton, 0, SWT.LEFT);
        this.downloadAsLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlTextField, 0, SWT.CENTER);
        formData.right = new FormAttachment(videoButton, 0, SWT.LEFT);
        this.audioButton.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlTextField, 0, SWT.CENTER);
        formData.right = new FormAttachment(100);
        this.videoButton.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlTextField, 16, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        this.downloadListLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(downloadListLabel, 2, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(35);
        this.downloadList.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(downloadListLabel, 0, SWT.TOP);
        formData.left = new FormAttachment(downloadList, 10, SWT.RIGHT);
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(100);
        this.downloadInfoGroup.setLayoutData(formData);

        this.shell.layout();
    }

}
