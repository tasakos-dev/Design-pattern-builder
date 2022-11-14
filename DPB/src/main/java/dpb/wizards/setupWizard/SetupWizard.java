package dpb.wizards.setupWizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class SetupWizard extends Wizard implements INewWizard {
	private String className;
	
	
	private ISelection selection;

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		this.selection = arg1;
		
	}

	public SetupWizard(String className) {
		setWindowTitle("New Wizard");
		this.className = className;
	}

	@Override
	public void addPages() {
//		addPage(new ClassSetup(selection));
		addPage(new FieldsSetup(className));
		addPage(new MethodsSetup(className));
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
