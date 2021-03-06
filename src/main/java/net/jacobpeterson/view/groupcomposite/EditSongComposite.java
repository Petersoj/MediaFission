package net.jacobpeterson.view.groupcomposite;

import net.jacobpeterson.view.groupcomposite.song.AlbumCoverCanvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.internal.cocoa.NSButton;
import org.eclipse.swt.internal.cocoa.NSImage;
import org.eclipse.swt.internal.cocoa.NSSize;
import org.eclipse.swt.internal.cocoa.NSString;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class EditSongComposite {

    private Composite composite;

    private Label titleLabel;
    private Text titleText;
    private Label artistLabel;
    private Text artistText;
    private Label albumLabel;
    private Text albumText;
    private Label genreLabel;
    private Combo genreCombo;
    private Label albumCoverLabel;
    private AlbumCoverCanvas albumCoverCanvas;
    private Label albumCoverStatusLabel;
    private Button editAlbumCoverButton;
    private ProgressBar progressBar;
    private Label progressLabel;
    private Button cancelButton;
    private Button saveButton;

    public EditSongComposite(Group groupContainer) {
        this.composite = new Composite(groupContainer, SWT.NONE);

        this.titleLabel = new Label(composite, SWT.NONE);
        this.titleText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        this.artistLabel = new Label(composite, SWT.NONE);
        this.artistText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        this.albumLabel = new Label(composite, SWT.NONE);
        this.albumText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        this.genreLabel = new Label(composite, SWT.NONE);
        this.genreCombo = new Combo(composite, SWT.SIMPLE);
        this.albumCoverLabel = new Label(composite, SWT.NONE);
        this.albumCoverCanvas = new AlbumCoverCanvas(composite);
        this.albumCoverStatusLabel = new Label(composite, SWT.CENTER | SWT.WRAP);
        this.editAlbumCoverButton = new Button(composite, SWT.FLAT);
        this.progressBar = new ProgressBar(composite, SWT.SMOOTH | SWT.HORIZONTAL);
        this.progressLabel = new Label(composite, SWT.NONE);
        this.cancelButton = new Button(composite, SWT.FLAT);
        this.saveButton = new Button(composite, SWT.PUSH);
    }

    public void setup() {
        this.setupWidgets();
        this.setupLayout();

        // Test code below

//        this.editAlbumCoverButton.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent selectionEvent) {
//                FormData data =(FormData) editAlbumCoverButton.getLayoutData();
//                data.width = SWT.DEFAULT;
//                editAlbumCoverButton.setText("DLKJDDDDDddddddd");
//                editAlbumCoverButton.requestLayout();
//            }
//        });


//        DropTarget dt = new DropTarget(albumCoverCanvas, DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK);
//        dt.setTransfer(FileTransfer.getInstance());
//        dt.addDropListener(new DropTargetAdapter() {
//            public void drop(DropTargetEvent event) {
//                String fileList[] = null;
//                FileTransfer ft = FileTransfer.getInstance();
//                if (event.data == null) { // no data to copy, indicate failure in event.detail
//                    event.detail = DND.DROP_NONE;
//                    return;
//                }
//                if (ft.isSupportedType(event.currentDataType)) {
//                    fileList = (String[]) event.data;
//                }
//                System.out.println(Arrays.toString(fileList));
//            }
//
//            @Override
//            public void dropAccept(DropTargetEvent event) {
//                Object object = FileTransfer.getInstance().nativeToJava(event.currentDataType);
//                if (object instanceof String[]) {
//                    String[] strs = (String[]) object;
//                    System.out.println("dropAccept " + strs[0]);
//                    event.detail = DND.DROP_COPY;
//                }
//                event.detail = DND.DROP_COPY;
//            }
//
//            @Override
//            public void dragEnter(DropTargetEvent event) {
//                event.detail = DND.DROP_COPY;
//            }
//
//            @Override
//            public void dragOver(DropTargetEvent dropTargetEvent) {
//                dropTargetEvent.detail = DND.DROP_COPY;
//                dropTargetEvent.feedback = DND.FEEDBACK_SELECT;
//            }
//        });
//
//        albumCoverCanvas.addMouseWheelListener(new MouseWheelListener() {
//            @Override
//            public void mouseScrolled(MouseEvent mouseEvent) {
//                System.out.println(mouseEvent.count);
//            }
//        });
    }

    private void setupWidgets() {
        this.titleLabel.setText("Title");
        this.artistLabel.setText("Artist");
        this.albumLabel.setText("Album");
        this.genreLabel.setText("Genre");
        this.genreCombo.setItems(
                "Alternative",
                "Blues",
                "Classical",
                "Country",
                "Dance",
                "Electronic",
                "Hip-Hop/Rap",
                "Indie",
                "Inspirational",
                "Instrumental",
                "Jazz",
                "Latino",
                "Opera",
                "Pop",
                "R&B/Soul",
                "Reggae",
                "Rock",
                "Soundtrack",
                "Vocal",
                "World");

        this.albumCoverCanvas.setup();

        this.albumCoverLabel.setText("Album Cover");
        this.albumCoverStatusLabel.setText("\n\n"); // Reserve 2 lines for centering vertically in setLayout()
        this.albumCoverStatusLabel.setForeground(new Color(composite.getDisplay(), 255, 0, 0));
        this.albumCoverStatusLabel.setVisible(false);

        this.editAlbumCoverButton.setText("Edit");
        this.saveButton.setText("Save");

        NSButton nsEditAlbumCoverButton = (NSButton) editAlbumCoverButton.view;
        nsEditAlbumCoverButton.setBezelStyle(15); // Inline button bezel style
        FontData albumFontData = editAlbumCoverButton.getFont().getFontData()[0];
        albumFontData.setHeight(11);
        albumFontData.setStyle(SWT.BOLD);
        editAlbumCoverButton.setForeground(new Color(composite.getDisplay(), 255, 255, 255));
        editAlbumCoverButton.setFont(new Font(composite.getDisplay(), albumFontData));

        this.progressLabel.setText("Status");
        FontData fontData = progressLabel.getFont().getFontData()[0];
        fontData.setHeight(11);
        this.progressLabel.setFont(new Font(composite.getDisplay(), fontData));

        NSButton nsCancelButton = (NSButton) cancelButton.view;
        nsCancelButton.setBezelStyle(15); // Inline button bezel style
        NSImage stopProgressImage = NSImage.imageNamed(NSString.stringWith("NSStopProgressTemplate"));
        NSSize imageSize = new NSSize();
        imageSize.height = 8;
        imageSize.width = 8;
        stopProgressImage.setSize(imageSize);
        nsCancelButton.setImage(stopProgressImage);
    }

    private void setupLayout() {
        // External Layout

        FormData externalFormData = new FormData();
        externalFormData.top = new FormAttachment(0);
        externalFormData.left = new FormAttachment(0);
        externalFormData.bottom = new FormAttachment(100);
        externalFormData.right = new FormAttachment(100);
        this.composite.setLayoutData(externalFormData);

        // Internal Layout

        FormLayout layout = new FormLayout();
        layout.marginTop = 10;
        layout.marginBottom = 10;
        layout.marginLeft = 10;
        layout.marginRight = 10;
        this.composite.setLayout(layout);

        FormData formData = new FormData();
        formData.top = new FormAttachment(titleText, 0, SWT.CENTER);
        formData.right = new FormAttachment(albumCoverLabel, 0, SWT.RIGHT);
        this.titleLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(0);
        formData.left = new FormAttachment(titleLabel, 10, SWT.RIGHT);
        formData.right = new FormAttachment(100);
        this.titleText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(artistText, 0, SWT.CENTER);
        formData.right = new FormAttachment(albumCoverLabel, 0, SWT.RIGHT);
        this.artistLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(titleText, 11, SWT.BOTTOM);
        formData.left = new FormAttachment(titleText, 0, SWT.LEFT);
        formData.right = new FormAttachment(100);
        this.artistText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumText, 0, SWT.CENTER);
        formData.right = new FormAttachment(albumCoverLabel, 0, SWT.RIGHT);
        this.albumLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(artistText, 11, SWT.BOTTOM);
        formData.left = new FormAttachment(artistText, 0, SWT.LEFT);
        formData.right = new FormAttachment(100);
        this.albumText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(genreCombo, 0, SWT.CENTER);
        formData.right = new FormAttachment(albumCoverLabel, 0, SWT.RIGHT);
        this.genreLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumText, 11, SWT.BOTTOM);
        formData.left = new FormAttachment(albumText, 0, SWT.LEFT);
        formData.right = new FormAttachment(100);
        this.genreCombo.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumCoverCanvas.getCanvas(), 0, SWT.CENTER);
        formData.left = new FormAttachment(0);
        this.albumCoverLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(genreCombo, 11, SWT.BOTTOM);
        formData.left = new FormAttachment(genreCombo, 0, SWT.LEFT);
        formData.bottom = new FormAttachment(progressBar, -20, SWT.TOP);
        formData.width = 300;
        this.albumCoverCanvas.getCanvas().setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumCoverCanvas.getCanvas(), 0, SWT.CENTER);
        formData.left = new FormAttachment(albumCoverCanvas.getCanvas(), 0, SWT.LEFT);
        formData.right = new FormAttachment(albumCoverCanvas.getCanvas(), 0, SWT.RIGHT);
        this.albumCoverStatusLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(albumCoverLabel, 3, SWT.BOTTOM);
        formData.left = new FormAttachment(albumCoverLabel, 0, SWT.CENTER);
        formData.width = 40;
        this.editAlbumCoverButton.setLayoutData(formData);

        formData = new FormData();
        formData.left = new FormAttachment(0);
        formData.bottom = new FormAttachment(progressLabel, 8, SWT.TOP);
        formData.width = 100;
        this.progressBar.setLayoutData(formData);

        formData = new FormData();
        formData.bottom = new FormAttachment(progressLabel, 0, SWT.TOP);
        formData.left = new FormAttachment(0);
        formData.width = 150;
        this.progressBar.setLayoutData(formData);

        formData = new FormData();
        formData.bottom = new FormAttachment(100);
        formData.left = new FormAttachment(0);
        this.progressLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(progressBar, 0, SWT.CENTER);
        formData.left = new FormAttachment(progressBar, 5, SWT.RIGHT);
        this.cancelButton.setLayoutData(formData);

        formData = new FormData();
        formData.bottom = new FormAttachment(100);
        formData.right = new FormAttachment(100);
        this.saveButton.setLayoutData(formData);

        this.composite.layout();
    }

    public Composite getComposite() {
        return composite;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Text getTitleText() {
        return titleText;
    }

    public Label getArtistLabel() {
        return artistLabel;
    }

    public Text getArtistText() {
        return artistText;
    }

    public Label getAlbumLabel() {
        return albumLabel;
    }

    public Text getAlbumText() {
        return albumText;
    }

    public Label getGenreLabel() {
        return genreLabel;
    }

    public Combo getGenreCombo() {
        return genreCombo;
    }

    public Label getAlbumCoverLabel() {
        return albumCoverLabel;
    }

    public AlbumCoverCanvas getAlbumCoverCanvas() {
        return albumCoverCanvas;
    }

    public Button getEditAlbumCoverButton() {
        return editAlbumCoverButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Label getProgressLabel() {
        return progressLabel;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
