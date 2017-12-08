package net.jacobpeterson.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class UpdateShellComposite {

    private Shell shell;

    private Label updatingLabel;

    public UpdateShellComposite(Shell shell) {
        this.shell = shell;

        this.updatingLabel = new Label(shell, SWT.WRAP | SWT.CENTER);
    }

    public void setup() {
        this.setupShell();
        this.setupWidgets();
        this.setupLayout();
    }

    private void setupShell() {

    }

    private void setupWidgets() {

    }

    private void setupLayout() {

    }

}
