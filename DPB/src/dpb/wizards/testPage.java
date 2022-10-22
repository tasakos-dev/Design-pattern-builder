package dpb.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;


public class testPage extends WizardPage {
	private ISelection selection;
	
	

	public testPage(ISelection selection) {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		String[] strings = new String[] {"hello", "hi"};

		setControl(container);
		
		ComboViewer comboViewer = new ComboViewer(container, SWT.BORDER | SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		
		combo.setBounds(26, 77, 201, 38);
		combo.setItems(strings);
		
		ComboViewer comboViewer_1 = new ComboViewer(container, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.err.println("hhoeoeoeo");
				if (combo.getSelection().equals("hello"))
					combo_1.setItems(new String[] {"hello", "hi1"});
				else
					combo_1.setItems(new String[] {"hello", "hi2"});
			}
		});
		

		
		combo_1.setBounds(301, 77, 201, 38);
		
	
		
	}
}
