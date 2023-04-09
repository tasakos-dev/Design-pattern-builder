package dpb.wizards.setupWizard;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.xml.sax.SAXException;

import dpb.controller.PatternManager;
import dpb.exceptions.NoPropertiesException;
import dpb.model.PatternClass;
import dpb.model.PatternElement;
import dpb.model.PatternInterface;
import dpb.model.Property;

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
	private PatternManager patternManager;
	private String[] availableInterfaces;
	

	public ModuleSetup(PatternElement patternElement, String module, String[] availableInterfaces) {
		super("wizardPage");
		setTitle("Setup "+module);
		setDescription("Setup "+module+" properties");
		this.patternElement = patternElement;
		this.availableInterfaces = availableInterfaces;
		this.module = module;
		try {
			this.patternManager = (PatternManager) PatternManager.getInstance();
		} catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
			MessageDialog.openError(getShell(), "Error", e.getMessage());
			e.printStackTrace();
		}

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
			btnCheckButton.setEnabled(!patternClass.isAbstract());
			
			
			Label lblNewLabel_2 = new Label(container, SWT.NONE);
			lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_2.setText("Implement Interface:");		 
			
			combo = new Combo(container, SWT.NONE);
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			combo.setItems(availableInterfaces);
			combo.select(0);
		}
		
		
	}
	public void finish() {
		if (module.equals("class")) {
			PatternClass patternClass = (PatternClass) patternElement;
			PatternInterface patternInterface = null;
			int selectionIndex = combo.getSelectionIndex();
			String selectedText = "";
			if (selectionIndex != -1) {
				selectedText = combo.getItem(selectionIndex);
			    
			} 
			for (PatternInterface anInterface : patternManager.getInterfaces()) {
				if (anInterface.getName().equals(selectedText)) {
					patternInterface = anInterface;
					break;
					
				}
			}
			
			patternClass.setImplementedInterface(patternInterface);
			patternClass.overrideMethods(patternInterface.getMethods());
			
			String role = "";
			System.err.println("elare"+patternClass.getPattern());
			if (patternElement.getRole() == null || patternElement.getRole().isEmpty()) {
				try {
					for (Property property : patternManager.getProperties(patternClass.getPattern())) {
						if (property.getImplement().equals(patternInterface.getName())) {
							role = property.getAnnotation();
							break;
						}					
					}
				} catch (NoPropertiesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				patternClass.setCategoryOfPattern(patternClass.getCategoryOfPattern());
				patternClass.setPattern(patternClass.getPattern());
				patternClass.setRole(role);
			}
		}
		
		patternManager.updatePatternElementName(text.getText(), patternElement);
	}
}
