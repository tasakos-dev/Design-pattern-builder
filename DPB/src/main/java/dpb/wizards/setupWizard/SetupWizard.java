package dpb.wizards.setupWizard;




import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import dpb.model.PatternClass;
import dpb.model.PatternElement;


public class SetupWizard extends Wizard implements INewWizard {
	private String module;
	private FieldsSetup fieldsSetup;
	private MethodsSetup methodsSetup;
	private ModuleSetup moduleSetup;

	
	

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {

		
	}

	public SetupWizard(PatternElement patternElement, String module) {
		setWindowTitle("New Wizard");
		this.module = module;
	

		moduleSetup = new ModuleSetup(patternElement, module);
		if (module.equals("interface")) {
			methodsSetup = new InterfaceMethodsSetup(patternElement);
		} else {
			fieldsSetup = new FieldsSetup((PatternClass) patternElement);
			methodsSetup =  new ClassMethodsSetup(patternElement);
		}
		
	}

	@Override
	public void addPages() {
		
		
		addPage(moduleSetup);
		if (module.equals("class")) {

			addPage(fieldsSetup);
			addPage(methodsSetup);
		}else {
			
			addPage(methodsSetup);
		}
		
		
	}

	@Override
	public boolean performFinish() {
		return true;
	}
	

}
