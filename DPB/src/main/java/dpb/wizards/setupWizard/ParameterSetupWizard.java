package dpb.wizards.setupWizard;

import org.eclipse.jface.wizard.Wizard;

import dpb.model.Method;

public class ParameterSetupWizard extends Wizard {
	private MethodParameterSetupPage methodParameterSetupPage;

	public ParameterSetupWizard(Method method) {
		setWindowTitle("New Wizard");
		methodParameterSetupPage = new MethodParameterSetupPage(method);
		
	}

	@Override
	public void addPages() {
		addPage(methodParameterSetupPage);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
