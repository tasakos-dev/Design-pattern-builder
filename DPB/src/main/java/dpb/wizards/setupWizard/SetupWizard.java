package dpb.wizards.setupWizard;


import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import dpb.model.Field;
import dpb.model.Method;

public class SetupWizard extends Wizard implements INewWizard {
	private String module;
	private FieldsSetup fieldsSetup;
	private MethodsSetup methodsSetup;
//	private MethodsSetup interfaceMethodsSetup;
	
	private List<Field> fields;
	private List<Method> methods;
//	private List<Method> interfaceMethods;
	
	

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {

		
	}

	public SetupWizard(String className, String module) {
		setWindowTitle("New Wizard");
		this.module = module;
		
		
		if (module.equals("class")) {
			fieldsSetup = new FieldsSetup(className);
			methodsSetup = new ClassMethodsSetup(className);
			fields = fieldsSetup.getFieldsList();
		}
		else {
			methodsSetup = new InterfaceMethodsSetup(className);
		}
		
		
		methods = methodsSetup.getMethods();
//		interfaceMethods = interfaceMethodsSetup.getMethods();
	}

	@Override
	public void addPages() {
		
		if (module.equals("class")) {
			
			
			addPage(fieldsSetup);
			addPage(methodsSetup);
		}else {
			
			addPage(methodsSetup);
		}
		
		
	}

	@Override
	public boolean performFinish() {
		if (module.equals("class"))
			fields = fieldsSetup.getFieldsList();
		methods = methodsSetup.getMethods();
		return true;
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	

	
	
	
	

}
