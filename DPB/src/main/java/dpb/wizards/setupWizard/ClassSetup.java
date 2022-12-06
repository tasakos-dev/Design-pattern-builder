package dpb.wizards.setupWizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class ClassSetup extends WizardPage {

	private Text text;

	public ClassSetup() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");

	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		System.err.println("OK");
		setControl(container);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setBounds(406, 115, 83, 32);
		btnNewButton.setText("New Button");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(118, 115, 83, 27);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(28, 115, 63, 16);
		lblNewLabel.setText("New Label");
	}
}
