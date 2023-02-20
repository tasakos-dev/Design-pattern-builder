package dpb.wizards.mainWizard;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import dpb.controller.IPatternGenerator;
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
		IPatternGenerator patternGenerator = new PatternGenerator();

		for (PatternClass patternClass: patternMainSetupPage.getClasses()) {
			try {
				patternGenerator.generateClass(patternClass);
			} catch (JavaModelException e) {
				MessageDialog.openError(getShell(), "Error", e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		for (PatternInterface patternInterface: patternMainSetupPage.getInterfaces()) {
			try {
				patternGenerator.generateInterface(patternInterface);
			} catch (JavaModelException e) {
				MessageDialog.openError(getShell(), "Error", e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		return true;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page instanceof PatternSelectorPage) {
			
			PatternSelectorPage patternSelectorPage = (PatternSelectorPage) page;
			patternMainSetupPage.setPattern(patternSelectorPage.getPattern());
			patternMainSetupPage.setTitle("Setup "+patternSelectorPage.getPattern()+" Design Pattern");
			patternMainSetupPage.setDescription(patternSelectorPage.getDescription());
			patternMainSetupPage.addElements();
		}
		return super.getNextPage(page);
	}

}
