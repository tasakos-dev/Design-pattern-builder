package dpb.wizards.setupWizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import dpb.controller.PatternManager;
import dpb.model.PatternClass;
import dpb.model.PatternElement;
import dpb.model.PatternInterface;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class ModuleSetup extends WizardPage {
	private Text text;
	private PatternElement patternElement;
	private String module;
	private Button btnCheckButton;
	private Combo combo;
	private boolean isAbstract;
	private PatternManager patternManager;
	

	public ModuleSetup(PatternElement patternElement, String module) {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.patternElement = patternElement;
		this.module = module;
		this.patternManager = (PatternManager) PatternManager.getInstance();

	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		container.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Name:");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text.setText(patternElement.getName());
		
		if (module.equals("class")) {
			
			PatternClass patternClass = (PatternClass) patternElement;
			Label lblNewLabel_1 = new Label(container, SWT.NONE);
			lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_1.setText("Is Abstract:");
			
			btnCheckButton = new Button(container, SWT.CHECK);
			btnCheckButton.setSelection(patternClass.isAbstract());
//			isAbstract = btnCheckButton.getSelection();
			
//			btnCheckButton.addSelectionListener(new SelectionAdapter() {
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					Button btn = (Button) e.getSource();
//					isAbstract = btn.getSelection();
//				}
//			});
			
			Label lblNewLabel_2 = new Label(container, SWT.NONE);
			lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_2.setText("Implement Interface:");
			String[] interfaceNames = new String[patternManager.getInterfaces().size()];
			for (int i = 0;i<patternManager.getInterfaces().size();i++) {
				interfaceNames[i] = patternManager.getInterfaces().get(i).getName();
			}
			
			
			
			combo = new Combo(container, SWT.NONE);
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			combo.setItems(interfaceNames);
		}
		
		
	}
	public void finish() {
		patternManager.updateClassName(text.getText(), patternElement);
//		if (module.equals("class")) {
//			PatternClass patternClass = (PatternClass) patternElement;
//			patternClass.setAbstract(btnCheckButton.getSelection());
//			for (PatternInterface patternInterface: patternManager.getInterfaces()) {
//				if (patternInterface.getName().equals(combo.getText())) {
//					patternInterface.addClass(patternClass);
//					patternClass.setImplementedInterface(patternInterface);
//					break;
//				} 
//			}
//			
//		}
	}
}
