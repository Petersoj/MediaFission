package net.jacobpeterson.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class ApplicationShell {

    private Display display;
    private Shell shell;

    private Text urlText;
    private Label downloadAsLabel;
    private Combo downloadAsCombo;
    private Label leftColumnLabel;
    private List downloadList;
    private Group downloadGroup;

    public final String DOWNLOADS_COLUMN_LABEL = "Downloads";
    public final String EDIT_ALBUM_COVER_COLUMN_LABEL = "Edit Album Cover";

    public ApplicationShell() {
        this.display = new Display();
        this.shell = new Shell(display);

        this.urlText = new Text(shell, SWT.SINGLE | SWT.BORDER);
        this.downloadAsLabel = new Label(shell, SWT.NONE);
        this.downloadAsCombo = new Combo(shell, SWT.READ_ONLY);
        this.leftColumnLabel = new Label(shell, SWT.NONE);
        this.downloadList = new List(shell, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
        this.downloadGroup = new Group(shell, SWT.NONE);
    }

    public void setup() {
        this.setupShell();
        this.setupWidgets();
        this.setupLayout();
    }

    private void setupShell() {
        this.shell.setText("MediaFission");
        this.shell.setMinimumSize(600, 350);
        this.shell.setSize(900, 500);
    }

    private void setupWidgets() {
        this.urlText.setMessage("Enter YouTube, Soundcloud, etc. URL");
        this.downloadAsLabel.setText("Download as:");

        this.leftColumnLabel.setText(DOWNLOADS_COLUMN_LABEL);
        FontData fontData = leftColumnLabel.getFont().getFontData()[0];
        fontData.setHeight(11);
        this.leftColumnLabel.setFont(new Font(shell.getDisplay(), fontData));
        this.leftColumnLabel.setForeground(new Color(display, 60, 60, 60)); // Faded black like the downloadGroup font

        this.downloadGroup.setText("Download Info/Edit");
    }

    private void setupLayout() {
        // External Layout

        // Position Shell in center of Display
        Rectangle screenSize = shell.getDisplay().getPrimaryMonitor().getBounds();
        Rectangle shellBounds = shell.getBounds();
        this.shell.setLocation((screenSize.width - shellBounds.width) / 2, (screenSize.height - shellBounds.height) / 2);

        // Internal Layout

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
        this.urlText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlText, 0, SWT.CENTER);
        formData.right = new FormAttachment(downloadAsCombo, 0, SWT.LEFT);
        this.downloadAsLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlText, 0, SWT.CENTER);
        formData.right = new FormAttachment(100);
        formData.width = 150;
        this.downloadAsCombo.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlText, 16, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        this.leftColumnLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(leftColumnLabel, 2, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(37);
        this.downloadList.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(leftColumnLabel, 0, SWT.TOP);
        formData.left = new FormAttachment(downloadList, 10, SWT.RIGHT);
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(100);
        this.downloadGroup.setLayoutData(formData);

        // DownloadGroup layout
        this.downloadGroup.setLayout(new FillLayout());
    }

    public void start() {
        this.shell.layout();
        this.downloadGroup.layout();

        this.shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        this.display.dispose();
    }


    public Display getDisplay() {
        return display;
    }

    public Shell getShell() {
        return shell;
    }

    public Text getURLText() {
        return urlText;
    }

    public Combo getDownloadAsCombo() {
        return downloadAsCombo;
    }

    public Label getLeftColumnLabel() {
        return leftColumnLabel;
    }

    public List getDownloadList() {
        return downloadList;
    }

    public Group getDownloadGroup() {
        return downloadGroup;
    }
}