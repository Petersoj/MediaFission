package net.jacobpeterson.view;

import net.jacobpeterson.view.groupcomposite.EditSongComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class ApplicationShellContents {

    private ApplicationShell applicationShell;
    private Shell shell;

    private Text urlText;
    private Label downloadAsLabel;
    private Combo downloadAsCombo;
    private Label leftColumnLabel;
    private List downloadList;
    private Group downloadGroup;
    private EditAlbumCoverComposite editAlbumCoverComposite;

    public final String DOWNLOADS_COLUMN_LABEL = "Downloads";
    public final String EDIT_ALBUM_COVER_COLUMN_LABEL = "Edit Album Cover";
    public final String[] DOWNLOAD_AS_LIST = {"Video", "    mp4", "Audio", "    mp3", "Song  (Adds album art, etc.)", "    mp3"};

    public ApplicationShellContents(ApplicationShell applicationShell) {
        this.applicationShell = applicationShell;
        this.shell = applicationShell.getShell();

        this.urlText = new Text(shell, SWT.SINGLE | SWT.BORDER);
        this.downloadAsLabel = new Label(shell, SWT.NONE);
        this.downloadAsCombo = new Combo(shell, SWT.READ_ONLY);
        this.leftColumnLabel = new Label(shell, SWT.NONE);
        this.downloadList = new List(shell, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        this.downloadGroup = new Group(shell, SWT.NONE);
        this.editAlbumCoverComposite = new EditAlbumCoverComposite(applicationShell);
    }

    public void setup() {
        this.setupWidgets();
        this.setupLayout();
        this.setupListeners();
    }

    private void setupWidgets() {
        this.urlText.setMessage("Enter YouTube, Soundcloud, etc. URL");
        this.downloadAsLabel.setText("Download as:");
        this.downloadAsCombo.setItems(DOWNLOAD_AS_LIST);
        this.downloadAsCombo.select(2);

        this.leftColumnLabel.setText(DOWNLOADS_COLUMN_LABEL);
        FontData fontData = leftColumnLabel.getFont().getFontData()[0];
        fontData.setHeight(11);
        this.leftColumnLabel.setFont(new Font(shell.getDisplay(), fontData));
        this.leftColumnLabel.setForeground(new Color(shell.getDisplay(), 60, 60, 60)); // gray-black like the downloadGroup font

        this.downloadGroup.setText("Download Info/Edit");
        EditSongComposite composite = new EditSongComposite(downloadGroup);
        composite.setup();
        this.downloadGroup.layout();
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
        this.downloadGroup.setLayout(new FormLayout());
    }

    private void setupListeners() {

    }

    public Text getUrlText() {
        return urlText;
    }

    public Label getDownloadAsLabel() {
        return downloadAsLabel;
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

    public EditAlbumCoverComposite getEditAlbumCoverComposite() {
        return editAlbumCoverComposite;
    }
}
