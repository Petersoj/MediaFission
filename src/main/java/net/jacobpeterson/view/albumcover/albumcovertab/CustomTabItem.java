package net.jacobpeterson.view.albumcover.albumcovertab;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class CustomTabItem {

    private TabFolder tabFolder;

    private TabItem tabItem;
    private Composite composite;
    private Button chooseFileButton;
    private Label fileExplanationLabel;
    private Label orLabel;
    private Label orSeparatorLabel;
    private Label imageURLLabel;
    private Text imageURLText;
    private Label imageExplanationLabel;

    public CustomTabItem(TabFolder tabFolder) {
        this.tabFolder = tabFolder;

        this.tabItem = new TabItem(tabFolder, SWT.NONE);
        this.composite = new Composite(tabFolder, SWT.NONE);
        this.chooseFileButton = new Button(composite, SWT.PUSH);
        this.fileExplanationLabel = new Label(composite, SWT.WRAP | SWT.CENTER);
        this.orLabel = new Label(composite, SWT.CENTER);
        this.orSeparatorLabel = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
        this.imageURLLabel = new Label(composite, SWT.NONE);
        this.imageURLText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        this.imageExplanationLabel = new Label(composite, SWT.WRAP | SWT.CENTER);
    }

    public void setup() {
        this.setupWidgets();
        this.setupLayout();
    }

    private void setupWidgets() {
        this.tabItem.setText("Custom");
        this.tabItem.setControl(composite);

        this.chooseFileButton.setText("Choose File");
        this.fileExplanationLabel.setText("Choose and image from your computer using the button above or " +
                "drag n' drop an image onto the button to set the custom album cover.");
        this.orLabel.setText("OR");

        Color grayColor = new Color(tabFolder.getDisplay(), 127, 127, 127);
        this.fileExplanationLabel.setForeground(grayColor);
        this.orLabel.setBackground(new Color(tabFolder.getDisplay(), 223, 222, 223)); // Color of group container background.

        this.imageURLLabel.setText("Image URL");
        this.imageExplanationLabel.setText("Paste an image URL above to set the custom album cover as an image from the internet. " +
                "This URL usually ends with a \'.jpg\' or \'.png\'");

        this.imageExplanationLabel.setForeground(grayColor);
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
        formData.left = new FormAttachment(fileExplanationLabel, 0, SWT.CENTER);
        this.chooseFileButton.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(chooseFileButton, 0, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        formData.right = new FormAttachment(100);
        this.fileExplanationLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(fileExplanationLabel, 15, SWT.BOTTOM);
        formData.left = new FormAttachment(fileExplanationLabel, 0, SWT.CENTER);
        this.orLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(orLabel, 0, SWT.CENTER);
        formData.left = new FormAttachment(2);
        formData.right = new FormAttachment(98);
        this.orSeparatorLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(imageURLText, 0, SWT.CENTER);
        formData.left = new FormAttachment(0);
        this.imageURLLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(orLabel, 15, SWT.BOTTOM);
        formData.left = new FormAttachment(imageURLLabel, 10, SWT.RIGHT);
        formData.right = new FormAttachment(100);
        this.imageURLText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(imageURLText, 10, SWT.BOTTOM);
        formData.left = new FormAttachment(0);
        formData.right = new FormAttachment(100);
        this.imageExplanationLabel.setLayoutData(formData);

        this.composite.layout();
    }

}
