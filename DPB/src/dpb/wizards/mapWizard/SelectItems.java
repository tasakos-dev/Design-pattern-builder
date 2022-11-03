package dpb.wizards.mapWizard;

import java.awt.Button;
import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;

public class SelectItems extends WizardPage {
	boolean isFields;
	ArrayList<String> patternItems;
	
	public SelectItems(boolean isFields, ArrayList<String> patternItems) {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.isFields = isFields;
		this.patternItems = patternItems;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		List list = new List(container, SWT.BORDER);
		list.setBounds(10, 33, 235, 293);
		
		List list_1 = new List(container, SWT.BORDER);
		list_1.setBounds(339, 33, 235, 293);
		
		org.eclipse.swt.widgets.Button btnNewButton = new org.eclipse.swt.widgets.Button(container, SWT.NONE);
		btnNewButton.setBounds(251, 108, 83, 32);
		btnNewButton.setText(">");
		
		org.eclipse.swt.widgets.Button btnNewButton_1 = new org.eclipse.swt.widgets.Button(container, SWT.NONE);
		btnNewButton_1.setBounds(251, 146, 83, 32);
		btnNewButton_1.setText("<");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(95, 11, 63, 16);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setBounds(433, 11, 63, 16);
		for (String item: patternItems) {
			list.add(item);
		}
		

		
	}

}
