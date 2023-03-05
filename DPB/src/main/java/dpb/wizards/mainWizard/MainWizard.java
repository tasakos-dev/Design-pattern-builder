package dpb.wizards.mainWizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import dpb.controller.ClassGeneratorFactory;
import dpb.controller.IPatternGenerator;
import dpb.controller.IPatternGeneratorFactory;
import dpb.controller.InterfaceGeneratorFactory;
import dpb.controller.PatternGenerator;
import dpb.model.PatternClass;
import dpb.model.PatternInterface;


public class MainWizard extends Wizard implements INewWizard {
	private PatternMainSetupPage patternMainSetupPage;


	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
	
	}

	public MainWizard() {
		setWindowTitle("Add new Design Pattern");
	}

	@Override
	public void addPages() {
		PatternSelectorPage patternSelectorPage = new PatternSelectorPage();
		patternMainSetupPage = new PatternMainSetupPage();
		addPage(patternSelectorPage);
		addPage(patternMainSetupPage);
		
			

	}
	
	@Override
	public boolean canFinish() {
		return patternMainSetupPage.getClasses().size() > 0;
		
	}

	@Override
	public boolean performFinish() {
		IPatternGeneratorFactory classGeneratorFactory;
		classGeneratorFactory = new ClassGeneratorFactory();
		IPatternGenerator classGenerator = null;
		try {
			classGenerator = classGeneratorFactory.createPatternGenerator();
		} catch (CoreException e1) {
			MessageDialog.openError(getShell(), "Error", e1.getMessage());
		}
		
		IPatternGeneratorFactory interfaceGeneratorFactory = new InterfaceGeneratorFactory();
		IPatternGenerator interfaceGenerator = null;
		try {
			interfaceGenerator = interfaceGeneratorFactory.createPatternGenerator();
		} catch (CoreException e1) {
			MessageDialog.openError(getShell(), "Error", e1.getMessage());
		}
		

		for (PatternClass patternClass: patternMainSetupPage.getClasses()) {
			try {
				classGenerator.generate(patternClass);
			} catch (JavaModelException e) {
				MessageDialog.openError(getShell(), "Error", e.getMessage());
			}
			
		}
		
		for (PatternInterface patternInterface: patternMainSetupPage.getInterfaces()) {
			try {
				interfaceGenerator.generate(patternInterface);
			} catch (JavaModelException e) {
				MessageDialog.openError(getShell(), "Error", e.getMessage());
			}
			
		}
		
		return true;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page instanceof PatternSelectorPage) {
			PatternSelectorPage patternSelectorPage = (PatternSelectorPage) page;
			patternMainSetupPage.setTitle("Setup "+patternSelectorPage.getPattern()+" Design Pattern");
			patternMainSetupPage.addElements(patternSelectorPage.getPattern());
		}
		return super.getNextPage(page);
	}

}
