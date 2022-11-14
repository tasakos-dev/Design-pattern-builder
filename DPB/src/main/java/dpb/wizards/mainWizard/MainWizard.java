package dpb.wizards.mainWizard;


import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;


public class MainWizard extends Wizard implements INewWizard {


	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
	
	}

	public MainWizard() {
		setWindowTitle("New Wizard");
	}

	@Override
	public void addPages() {
		PatternSelectorPage patternSelectorPage = new PatternSelectorPage();
		


		addPage(patternSelectorPage);
			

	}

	@Override
	public boolean performFinish() {
		return false;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page instanceof PatternSelectorPage) {
			PatternMainSetupPage patternMainSetupPage = new PatternMainSetupPage();
			PatternSelectorPage patternSelectorPage = (PatternSelectorPage) page;
			
			patternMainSetupPage.setPattern(patternSelectorPage.getPattern());
			addPage(patternMainSetupPage);
		}
		return super.getNextPage(page);
	}

}
