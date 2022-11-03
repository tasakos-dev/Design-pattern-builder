package dpb.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;


public class testPage extends WizardPage {
	private ISelection selection;
	private Combo patternCombo;
	private boolean nextPage;
	
	

	public testPage(ISelection selection) {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.selection = selection;
		nextPage = false;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		String[] patternsCategory = new String[] {"Creational patterns", "Structural patterns", "Behavioral patterns"};

		setControl(container);
		
		ComboViewer comboViewer = new ComboViewer(container, SWT.BORDER | SWT.READ_ONLY);
		Combo categoryCombo = comboViewer.getCombo();
		
		categoryCombo.setBounds(140, 91, 201, 38);
		categoryCombo.setItems(patternsCategory);
		
		ComboViewer comboViewer_1 = new ComboViewer(container, SWT.READ_ONLY);
		patternCombo = comboViewer_1.getCombo();
		patternCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (patternCombo.getItemCount() > 0) {
					nextPage = true;
					canFlipToNextPage();
					getWizard().getContainer().updateButtons();
				}
			}
		});
		
		categoryCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String category = categoryCombo.getText();
				if (category.contains("Creational")) {
					patternCombo.setItems(new String[] {"Abstract Factory", "Factory method", "Singleton"});
					
				} else if (category.contains("Structural")) {
					patternCombo.setItems(new String[] {"Adapter", "Bridge", "Composite"});
				} else if (category.contains("Behavioral")){
					patternCombo.setItems(new String[] {"Command", "Interpeter", "Observer"});					
				}
			}
		});
		

		
		patternCombo.setBounds(140, 172, 201, 38);
		
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(55, 92, 63, 16);
		lblNewLabel.setText("Category:");
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setBounds(55, 172, 63, 16);
		lblNewLabel_1.setText("Pattern:");
		
	
		
	}

	@Override
	public boolean canFlipToNextPage() {
		// TODO Auto-generated method stub
		return nextPage;
	}

	
	
	
}
