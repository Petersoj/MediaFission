package net.jacobpeterson.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

import java.util.Arrays;

public class ShellContent {

    private Shell shell;

    private Text urlTextField;
    private Label downloadAsLabel;
    private Combo downloadAsCombo;
    private Label downloadListLabel;
    private List downloadList;
    private Group downloadInfoGroup;

    public ShellContent(Shell shell) {
        this.shell = shell;

        this.urlTextField = new Text(shell, SWT.SINGLE | SWT.BORDER);
        this.downloadAsLabel = new Label(shell, SWT.NONE);
        this.downloadAsCombo = new Combo(shell, SWT.READ_ONLY);
        this.downloadListLabel = new Label(shell, SWT.NONE);
        this.downloadList = new List(shell, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
        this.downloadInfoGroup = new Group(shell, SWT.NONE);
    }

    public void setup() {
        this.setupShell();
        this.setupWidgets();
        this.setupLayout();

        // Test code below

        DropTarget dt = new DropTarget(downloadInfoGroup, DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK);
        dt.setTransfer(FileTransfer.getInstance());
        dt.addDropListener(new DropTargetAdapter() {
            public void drop(DropTargetEvent event) {
                String fileList[] = null;
                FileTransfer ft = FileTransfer.getInstance();
                if (ft.isSupportedType(event.currentDataType)) {
                    fileList = (String[]) event.data;
                }
                System.out.println(Arrays.toString(fileList));
            }
        });

        EditAudioGroupContent composite = new EditAudioGroupContent(downloadInfoGroup);
        composite.setup();
        this.downloadInfoGroup.layout();


        this.downloadList.add("saafdsdfasfdasfdasafdsdasfdfsaafdsdfasfdasfdasafdsdasfdf");
        this.downloadList.add("saafdsdfasfdasfdasafdsdasfdfsaafdsdfasfdasfdasafdsdasfdf");
        this.downloadList.add("saafdsdfasfdasfdasafdsdasfdfsaafdsdfasfdasfdasafdsdasfdf");
        this.downloadList.add("saafdsdfasfdasfdasafdsdasfdfsaafdsdfasfdasfdasafdsdasfdf");
        this.downloadList.add("saafdsdfasfdasfdasafdsdasfdfsaafdsdfasfdasfdasafdsdasfdf");
    }

    private void setupShell() {
        this.shell.setText("MediaFission");
        this.shell.setMinimumSize(500, 350);
        this.shell.setSize(800, 500);

        Rectangle screenSize = shell.getDisplay().getPrimaryMonitor().getBounds();
        Rectangle shellBounds = shell.getBounds();
        this.shell.setLocation((screenSize.width - shellBounds.width) / 2, (screenSize.height - shellBounds.height) / 2);
    }

    private void setupWidgets() {
        this.urlTextField.setMessage("Enter YouTube, Vimeo, or Soundcloud URL");
        this.downloadAsLabel.setText("Download as:");

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

    public void setGroupContentVisible(boolean visible) {
        for (Control control : downloadInfoGroup.getChildren()) {
            control.setVisible(visible);
        }
    }

    public void showVideoInfoGroupContent() {

    }

    public void showEditAudioGroupContent() {

    }
}