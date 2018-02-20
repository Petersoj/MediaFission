package net.jacobpeterson;

import net.jacobpeterson.controller.DownloadsController;
import net.jacobpeterson.view.ApplicationShell;

public class MediaFission {

    private ApplicationShell applicationShell;
    private DownloadsController downloadsController;

    public MediaFission() {
        this.applicationShell = new ApplicationShell(this);
        this.downloadsController = new DownloadsController(this);
    }

    public void setup() {
        this.applicationShell.setup();
    }

    public void start() {
        this.applicationShell.start(); // Blocking method that opens the GUI
    }

    public DownloadsController getDownloadsController() {
        return downloadsController;
    }


    public static void main(String[] args) {
        args = new String[]{"https://www.youtube.com/watch?v=hCBX28p-4JY"}; // Temporary REMOVE LATER

        // Temporary CommandLine version of MediaFission (No GUI)
        MediaFissionCommandLine mediaFissionCommandLine = new MediaFissionCommandLine(args);
        mediaFissionCommandLine.execute();

//        MediaFission mediaFission = new MediaFission(); // Instantiates everything
//        mediaFission.setup(); // Configures everything
//        mediaFission.start(); // Open/shows/starts everything
    }


    // TONS OF TEST CODE BELOW WOW HOLY CRAP!
    // Will be removed later!

    private static void callTempTestCode() {
//              canvas scroll bar listener

//        Display display = new Display();
//        Shell shell = new Shell(display);
//        shell.setLayout(new FillLayout());
//        Image originalImage = null;
//        FileDialog dialog = new FileDialog(shell, SWT.OPEN);
//        dialog.setText("Open an image file or cancel");
//        String string = dialog.open();
//        if (string != null) {
//            originalImage = new Image(display, string);
//        }
//        if (originalImage == null) {
//            int width = 150, height = 200;
//            originalImage = new Image(display, width, height);
//            GC gc = new GC(originalImage);
//            gc.fillRectangle(0, 0, width, height);
//            gc.drawLine(0, 0, width, height);
//            gc.drawLine(0, height, width, 0);
//            gc.drawText("Default Image", 10, 10);
//            gc.dispose();
//        }
//        final Image image = originalImage;
//        final Point origin = new Point(0, 0);
//        final Canvas canvas = new Canvas(shell, SWT.NO_BACKGROUND |
//                SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);
//        final ScrollBar hBar = canvas.getHorizontalBar();
//        hBar.addListener(SWT.Selection, e -> {
//            int hSelection = hBar.getSelection();
//            int destX = -hSelection - origin.x;
//            Rectangle rect = image.getBounds();
//            canvas.scroll(destX, 0, 0, 0, rect.width, rect.height, false);
//            origin.x = -hSelection;
//        });
//        final ScrollBar vBar = canvas.getVerticalBar();
//        vBar.addListener(SWT.Selection, e -> {
//            int vSelection = vBar.getSelection();
//            int destY = -vSelection - origin.y;
//            Rectangle rect = image.getBounds();
//            canvas.scroll(0, destY, 0, 0, rect.width, rect.height, false);
//            origin.y = -vSelection;
//        });
//        canvas.addListener(SWT.Resize, e -> {
//            Rectangle rect = image.getBounds();
//            Rectangle client = canvas.getClientArea();
//            hBar.setMaximum(rect.width);
//            vBar.setMaximum(rect.height);
//            hBar.setThumb(Math.min(rect.width, client.width));
//            vBar.setThumb(Math.min(rect.height, client.height));
//            int hPage = rect.width - client.width;
//            int vPage = rect.height - client.height;
//            int hSelection = hBar.getSelection();
//            int vSelection = vBar.getSelection();
//            if (hSelection >= hPage) {
//                if (hPage <= 0) hSelection = 0;
//                origin.x = -hSelection;
//            }
//            if (vSelection >= vPage) {
//                if (vPage <= 0) vSelection = 0;
//                origin.y = -vSelection;
//            }
//            canvas.redraw();
//        });
//        canvas.addListener(SWT.Paint, e -> {
//            GC gc = e.gc;
//            gc.drawImage(image, origin.x, origin.y);
//            Rectangle rect = image.getBounds();
//            Rectangle client = canvas.getClientArea();
//            int marginWidth = client.width - rect.width;
//            if (marginWidth > 0) {
//                gc.fillRectangle(rect.width, 0, marginWidth, client.height);
//            }
//            int marginHeight = client.height - rect.height;
//            if (marginHeight > 0) {
//                gc.fillRectangle(0, rect.height, client.width, marginHeight);
//            }
//        });
//        Rectangle rect = image.getBounds();
//        shell.setSize(Math.max(200, rect.width - 100), Math.max(150, rect.height - 100));
//        shell.open();
//        while (!shell.isDisposed()) {
//            if (!display.readAndDispatch()) display.sleep();
//        }
//        originalImage.dispose();
//        display.dispose();


//        FileDialog dialog = new FileDialog(shell, SWT.SHEET);
//        dialog.open();

//        Shell other = new Shell(shell, SWT.MODELESS);
//        other.setSize(200, 100);
//        other.setLocation(800, 500);
//
//        TabFolder tab = new TabFolder(other, SWT.TOP);
//        TabItem item = new TabItem(tab, SWT.NONE);
//        item.setText("asdf");
//        TabItem item2 = new TabItem(tab, SWT.NONE);
//        item2.setText("asd2f");
//
//        tab.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent selectionEvent) {
//                System.out.println("asdffff");
//            }
//        });
//
//        other.setLayout(new FillLayout());
//
//        other.open();

//        Shell other = new Shell(shell, SWT.CLOSE | SWT.BORDER | SWT.TITLE | SWT.RESIZE);
//        other.setSize(200, 100);
//        other.setLayout(new FillLayout());
//        Combo combo = new Combo(other, SWT.READ_ONLY);
//        combo.add("Video");
//        combo.add("  mp4");
//        combo.add("  mov");
//        combo.add("Audio");
//        combo.add("  mp3");
//        combo.add("  m4a");
//        combo.add("  ogg");
//
//        other.setLocation(shell.getBounds().x + shellContent.getAudioButton().getBounds().x, shell.getBounds().y + shellContent.getAudioButton().getBounds().y);
//        other.open();


//        shell.setLayout(new FillLayout(SWT.VERTICAL));
//
//        final Text text = new Text(shell, SWT.BORDER);
//        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
//
//        DropTarget dt = new DropTarget(text, DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK);
//        dt.setTransfer(FileTransfer.getInstance());
//        dt.addDropListener(new DropTargetAdapter() {
//            public void drop(DropTargetEvent event) {
//                String fileList[] = null;
//                FileTransfer ft = FileTransfer.getInstance();
//                if (ft.isSupportedType(event.currentDataType)) {
//                    fileList = (String[]) event.data;
//                }
//                System.out.println(Arrays.toString(fileList));
//            }
//        });
//
//        EditSongComposite c = new EditSongComposite(shellContent);
//        c.setLayout(new FillLayout());
//        for (int i = 0; i < 20; i++){
//            Button butto = new Button(shell, SWT.PUSH);
//            NSButton buttons = (NSButton) butto.view;
//            buttons.cell().setControlSize(1); // NSControlSize.small
//            butto.setText("asdf");
//        }
//        NSButton nsRefreshAlbumArtButton = (NSButton) refreshAlbumArtButton.view;
//        nsRefreshAlbumArtButton.cell().setControlSize(2); // NSControlSize.mini
//        this.refreshAlbumArtButton.setText("Refresh");
//
//
//        List list = new List(shell, SWT.SINGLE);
//        list.add("asdfasdfadfs");
//        list.add("asdfasdfadfs");
//        list.add("asdfasdfadfs");
//        list.add("asdfasdfadfs");
//        list.add("asdfasdfadfs");
//
//
//        shell.open();
//
//

//        Menu me = new Menu(button);
//
//        MenuItem item = new MenuItem(me, SWT.NONE);
//        item.setText("asdf");
//
//        button.setMenu(me);
//
//
//        button.add("asdf");
//        button.add("asdf");
//        button.add("asdf");
//        button.add("asdf");
//        button.add("asdf");
//        button.add("asdf");
//        button.add("asdf");
//        button.setListVisible(true);
//        NSButton button1 = (NSButton) button.view;
//        button1.setBezelStyle(9);


//        final Display display = new Display();
//        final Shell shell = new Shell(display);
//        shell.setText("StackOverflow");
//        shell.setLayout(new FillLayout());
//
//        final SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL);
//
//        Text text1 = new Text(sashForm, SWT.CENTER);
//        text1.setText("Text in pane #1");
//        Text text2 = new Text(sashForm, SWT.CENTER);
//        text2.setText("Text in pane #2");
//
//        final SashForm sashForm2 = new SashForm(sashForm, SWT.VERTICAL);
//
//        final Label labelA = new Label(sashForm2, SWT.BORDER | SWT.CENTER);
//        labelA.setText("Label in pane A");
//        final Label labelB = new Label(sashForm2, SWT.BORDER | SWT.CENTER);
//        labelB.setText("Label in pane B");
//
//        sashForm.setWeights(new int[] { 1, 2, 3 });
//
//        new Label(shell, SWT.NONE).setText("Label");
//
//        shell.pack();
//        shell.setSize(400, 300);
//        shell.open();
//
//        while (!shell.isDisposed())
//        {
//            if (!display.readAndDispatch())
//                display.sleep();
//        }
//        display.dispose();

//        final Display display = new Display();
//        Shell shell = new Shell(display);
//        shell.setLayout(new FillLayout());
//
//        Table table = new Table(shell, SWT.VIRTUAL | SWT.BORDER_DASH);
//        table.setLinesVisible(true);
//        table.setHeaderVisible(true);
//        String[] titles = { " ", "C", "!", "Description", "Resource",
//                "In Folder", "Location" };
//        for (int i = 0; i < titles.length; i++) {
//            TableColumn column = new TableColumn(table, SWT.NONE);
//            column.setText(titles[i]);
//        }
//        int count = 50;
//        for (int i = 0; i < count; i++) {
//            TableItem item = new TableItem(table, SWT.NONE);
//            item.setText(0, "x");
//            item.setText(1, "y");
//            item.setText(2, "!");
//            item.setText(3, "this stuff behaves the way I expect");
//            item.setText(4, "almost everywhere");
//            item.setText(5, "some.folder");
//            item.setText(6, "line " + i + " in nowhere");
//        }
//        for (int i = 0; i < titles.length; i++) {
//            table.getColumn(i).pack();
//        }
//        table.setSize(table.computeSize(SWT.DEFAULT, 200));
//        shell.pack();
//        shell.open();
//        while (!shell.isDisposed()) {
//            if (!display.readAndDispatch())
//                display.sleep();
//        }
//        display.dispose();


//        Display display = new Display();
//        Shell shell = new MediaFission().createShell(display);
//        shell.open();
//        while (!shell.isDisposed()) {
//            if (!display.readAndDispatch())
//                display.sleep();
//        }
    }

//
//
//    Image dogImage;
//    Text dogNameText;
//    Combo dogBreedCombo;
//    Canvas dogPhoto;
//    List categories;
//    Text nameText;
//    Text phoneText;
//
//
//    public Shell createShell(final Display display) {
//        final Shell shell = new Shell(display);
//
//        try {
//            BufferedImage image = ImageIO.read(new File("/Users/Jacob/Documents/Websites/jacobpeterson.net NEW/favicon.png"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        FormLayout layout = new FormLayout();
//        layout.marginWidth = 20;
//        layout.marginHeight = 20;
//        shell.setLayout(layout);
//        shell.setText("Dog Show Entry");
//
//        Group ownerInfo = new Group(shell, SWT.NONE);
//        ownerInfo.setText("Owner Info");
//        FormLayout ownerLayout = new FormLayout();
//        ownerLayout.marginWidth = 50;
//        ownerLayout.marginHeight = 5;
//        ownerInfo.setLayout(ownerLayout);
//
//        Label dogName = new Label(shell, SWT.NONE);
//        dogName.setText("Dog's Name:");
//        dogNameText = new Text(shell, SWT.SINGLE | SWT.BORDER);
//
//        Label dogBreed = new Label(shell, SWT.NONE);
//        dogBreed.setText("Breed:");
//
//        dogBreedCombo = new Combo(shell, SWT.NONE);
//        dogBreedCombo.setItems("Collie", "Pitbull", "Poodle",
//                "Scottie", "Black Lab");
//
//        Label photo = new Label(shell, SWT.NONE);
//        photo.setText("Photo:");
//        dogPhoto = new Canvas(shell, SWT.BORDER);
//
//        Button browse = new Button(shell, SWT.PUSH);
//        browse.setText("Browse...");
//
//        Button delete = new Button(shell, SWT.PUSH);
//        delete.setText("Delete");
//
//        Label cats = new Label(shell, SWT.NONE);
//        cats.setText("Categories");
//        categories = new List(shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL
//                | SWT.H_SCROLL);
//        categories.setItems("Best of Breed", "Prettiest Female",
//                "Handsomest Male", "Best Dressed", "Fluffiest Ears",
//                "Most Colors", "Best Performer", "Loudest Bark",
//                "Best Behaved", "Prettiest Eyes", "Most Hair", "Longest Tail",
//                "Cutest Trick");
//
//        Button enter = new Button(shell, SWT.PUSH);
//        enter.setText("Enter");
//        FormData data = new FormData();
//        data.top = new FormAttachment(dogNameText, 0, SWT.CENTER);
//        dogName.setLayoutData(data);
//        data = new FormData();
//        data.left = new FormAttachment(dogName, 5);
//        data.right = new FormAttachment(100, 0);
//        dogNameText.setLayoutData(data);
//
//        data = new FormData();
//        data.top = new FormAttachment(dogBreedCombo, 0, SWT.CENTER);
//        dogBreed.setLayoutData(data);
//        data = new FormData();
//        data.top = new FormAttachment(dogNameText, 5);
//        data.left = new FormAttachment(dogNameText, 0, SWT.LEFT);
//        data.right = new FormAttachment(categories, -5);
//        dogBreedCombo.setLayoutData(data);
//
//        data = new FormData(80, 80);
//        data.top = new FormAttachment(dogBreedCombo, 5);
//        data.left = new FormAttachment(dogNameText, 0, SWT.LEFT);
//        data.right = new FormAttachment(categories, -5);
//        data.bottom = new FormAttachment(ownerInfo, -5);
//        dogPhoto.setLayoutData(data);
//        dogPhoto.addPaintListener(event -> {
//            if (dogImage != null) {
//                event.gc.drawImage(dogImage, 0, 0);
//            }
//        });
//        data = new FormData();
//        data.top = new FormAttachment(dogPhoto, 0, SWT.TOP);
//        photo.setLayoutData(data);
//        data = new FormData();
//        data.top = new FormAttachment(photo, 5);
//        data.right = new FormAttachment(dogPhoto, -5);
//        data.bottom = new FormAttachment(browse, 15);
//        browse.setLayoutData(data);
//        browse.addSelectionListener(new SelectionAdapter() {
//            public void widgetSelected(SelectionEvent event) {
//                String fileName = new FileDialog(shell).open();
//                if (fileName != null) {
//                    dogImage = new Image(display, fileName);
//                    dogPhoto.redraw();
//                }
//            }
//        });
//
//        data = new FormData();
//        data.left = new FormAttachment(browse, 0, SWT.LEFT);
//        data.top = new FormAttachment(browse, 5);
//        data.right = new FormAttachment(dogPhoto, -5);
//        delete.setLayoutData(data);
//        delete.addSelectionListener(new SelectionAdapter() {
//            public void widgetSelected(SelectionEvent event) {
//                if (dogImage != null) {
//                    dogImage.dispose();
//                    dogImage = null;
//                    dogPhoto.redraw();
//                }
//            }
//        });
//
//        data = new FormData(90, 140);
//        data.top = new FormAttachment(dogPhoto, 0, SWT.TOP);
//        data.right = new FormAttachment(100, 0);
//        data.bottom = new FormAttachment(enter, -5);
//        categories.setLayoutData(data);
//
//        data = new FormData();
//        data.bottom = new FormAttachment(categories, -5);
//        data.left = new FormAttachment(categories, 0, SWT.CENTER);
//        cats.setLayoutData(data);
//
//        data = new FormData();
//        data.right = new FormAttachment(100, 0);
//        data.bottom = new FormAttachment(100, 0);
//        enter.setLayoutData(data);
//        enter.addSelectionListener(new SelectionAdapter() {
//            public void widgetSelected(SelectionEvent event) {
//                System.out.println("\nDog Name: " + dogNameText.getText());
//                System.out.println("Dog Breed: " + dogBreedCombo.getText());
//                System.out.println("Owner Name: " + nameText.getText());
//                System.out.println("Owner Phone: " + phoneText.getText());
//                System.out.println("Categories:");
//                String cats[] = categories.getSelection();
//                for (String cat : cats) {
//                    System.out.println("\t" + cat);
//                }
//            }
//        });
//
//        data = new FormData();
//        data.bottom = new FormAttachment(enter, -225);
//        data.left = new FormAttachment(0, 0);
//        data.right = new FormAttachment(categories, -5);
//        ownerInfo.setLayoutData(data);
//
//        Label name = new Label(ownerInfo, SWT.NULL);
//        name.setText("Name:");
//
//        Label phone = new Label(ownerInfo, SWT.PUSH);
//        phone.setText("Phone:");
//
//        nameText = new Text(ownerInfo, SWT.SINGLE | SWT.BORDER);
//        phoneText = new Text(ownerInfo, SWT.SINGLE | SWT.BORDER);
//
//        data = new FormData();
//        data.top = new FormAttachment(nameText, 0, SWT.CENTER);
//        name.setLayoutData(data);
//
//        data = new FormData();
//        data.top = new FormAttachment(phoneText, 0, SWT.CENTER);
//        phone.setLayoutData(data);
//
//        data = new FormData();
//        data.left = new FormAttachment(phone, 5);
//        data.right = new FormAttachment(100, 0);
//        nameText.setLayoutData(data);
//
//        data = new FormData();
//        data.left = new FormAttachment(nameText, 0, SWT.LEFT);
//        data.right = new FormAttachment(100, 0);
//        data.top = new FormAttachment(55, 0);
//        phoneText.setLayoutData(data);
//
//        shell.setMinimumSize(200, 200);
//        shell.pack();
//
//        return shell;
//    }
}
