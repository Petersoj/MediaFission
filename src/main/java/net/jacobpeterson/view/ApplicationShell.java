package net.jacobpeterson.view;

import net.jacobpeterson.view.albumcover.EditAlbumCoverComposite;
import net.jacobpeterson.view.groupcontent.EditSongGroupContent;
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

    private Text urlTextField;
    private Label downloadAsLabel;
    private Combo downloadAsCombo;
    private Label downloadListLabel;
    private List downloadList;
    private EditAlbumCoverComposite editAlbumCoverComposite;
    private Group downloadInfoGroup;

    public ApplicationShell() {
        this.display = new Display();
        this.shell = new Shell(display);

        this.urlTextField = new Text(shell, SWT.SINGLE | SWT.BORDER);
        this.downloadAsLabel = new Label(shell, SWT.NONE);
        this.downloadAsCombo = new Combo(shell, SWT.READ_ONLY);
        this.downloadListLabel = new Label(shell, SWT.NONE);
        this.editAlbumCoverComposite = new EditAlbumCoverComposite(this); // Has to created before downloadList for z-order
        this.downloadList = new List(shell, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
        this.downloadInfoGroup = new Group(shell, SWT.NONE);
    }

    public void setup() {
        this.setupShell();
        this.setupWidgets();
        this.setupLayout();

        // Test code below

//        display.addListener(SWT.Close, event -> {
//            MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.YES | SWT.NO);
//            dialog.setMessage("You have active downloads!\nQuiting will cancel these downloads.\n\nAre you sure you want to quit?");
//
//            if (dialog.open() == SWT.NO) {
//                event.doit = false;
//            }
//        });
//        shell.addListener(SWT.Close, event -> {
//            MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.YES | SWT.NO);
//            dialog.setMessage("You have active downloads!\nQuiting will cancel these downloads.\n\nAre you sure you want to quit?");
//
//            if (dialog.open() == SWT.NO) {
//                event.doit = false;
//            }
//        });


        EditSongGroupContent composite = new EditSongGroupContent(downloadInfoGroup);
        composite.setup();
        this.downloadInfoGroup.layout();


        this.downloadList.add("Post Malone - Go Flex (Clean)");
    }

    public void startUILoop() {
        this.shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        this.display.dispose();
    }

    private void setupShell() {
        this.shell.setText("MediaFission");
        this.shell.setMinimumSize(600, 350);
        this.shell.setSize(900, 500);
    }

    private void setupWidgets() {
        this.urlTextField.setMessage("Enter YouTube, Soundcloud, etc. URL");
        this.downloadAsLabel.setText("Download as:");

        this.downloadListLabel.setText("Downloads");
        FontData fontData = downloadListLabel.getFont().getFontData()[0];
        fontData.setHeight(11);
        this.downloadListLabel.setFont(new Font(shell.getDisplay(), fontData));
        this.downloadListLabel.setForeground(new Color(display, 60, 60, 60));

        this.editAlbumCoverComposite.setup();
//        this.editAlbumCoverComposite.getComposite().setVisible(false); // Hide composite until needed

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
        formData.right = new FormAttachment(downloadAsCombo, 0, SWT.LEFT);
        this.downloadAsLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlTextField, 0, SWT.CENTER);
        formData.right = new FormAttachment(100);
        formData.width = 150;
        this.downloadAsCombo.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(urlTextField, 16, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        this.downloadListLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(downloadListLabel, 2, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(37);
        this.downloadList.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(downloadListLabel, 0, SWT.TOP);
        formData.left = new FormAttachment(downloadList, 10, SWT.RIGHT);
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(100);
        this.downloadInfoGroup.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(downloadList, 0, SWT.TOP);
        formData.left = new FormAttachment(0);
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(downloadInfoGroup, 0, SWT.LEFT);
        this.editAlbumCoverComposite.getComposite().setLayoutData(formData);

        this.shell.layout();

        // Position Shell in center of Display
        Rectangle screenSize = shell.getDisplay().getPrimaryMonitor().getBounds();
        Rectangle shellBounds = shell.getBounds();
        this.shell.setLocation((screenSize.width - shellBounds.width) / 2, (screenSize.height - shellBounds.height) / 2);
    }

//    public void setGroupContentVisible(boolean visible) {
//        for (Control control : downloadInfoGroup.getChildren()) {
//            control.setVisible(visible);
//        }
//    }


    public Display getDisplay() {
        return display;
    }

    public Shell getShell() {
        return shell;
    }

    public Text getUrlTextField() {
        return urlTextField;
    }

    public Label getDownloadAsLabel() {
        return downloadAsLabel;
    }

    public Combo getDownloadAsCombo() {
        return downloadAsCombo;
    }

    public Label getDownloadListLabel() {
        return downloadListLabel;
    }

    public List getDownloadList() {
        return downloadList;
    }

    public EditAlbumCoverComposite getEditAlbumCoverComposite() {
        return editAlbumCoverComposite;
    }

    public Group getDownloadInfoGroup() {
        return downloadInfoGroup;
    }
}