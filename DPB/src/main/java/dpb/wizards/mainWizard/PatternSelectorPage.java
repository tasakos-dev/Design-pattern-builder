package dpb.wizards.mainWizard;


import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Label;

import dpb.controller.IPatternManager;
import dpb.controller.PatternManager;


public class PatternSelectorPage extends WizardPage {
	private Combo patternCombo;
	private boolean nextPage;
	private IPatternManager patternManager;
	private String pattern;

	
	

	public PatternSelectorPage() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.patternManager = new PatternManager();
		nextPage = false;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		String[] patternsCategory = patternManager.getPatternCategories();
		
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
				String[] patterns = patternManager.getPatternsOfCategory(category);
				patternCombo.setItems(patterns);
			}
		});
		
		
		
		

		
		patternCombo.setBounds(140, 172, 201, 38);
		
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(55, 92, 63, 16);
		lblNewLabel.setText("Category:");
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setBounds(55, 172, 63, 16);
		lblNewLabel_1.setText("Pattern:");
		
		patternCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pattern = patternCombo.getText();
				
			}
		});
		
	
		
	}

	@Override
	public boolean canFlipToNextPage() {
		return nextPage;
	}

	public String getPattern() {
		return pattern;
	}

	
	
//	@Override
//	public IWizardPage getNextPage() {
//		
//		PatternMainSetupPage patternMainSetupPage = (PatternMainSetupPage) getWizard().getNextPage(this);
//		//new PatternMainSetupPage();
//		patternMainSetupPage.setPattern("creational");
//		
//		
//		return patternMainSetupPage;
//		
//	}
//	
	
	
	

	
	
	
}
