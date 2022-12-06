package dpb.wizards.setupWizard;

import java.util.List;


import dpb.model.Method;

public class InterfaceMethodsSetup extends MethodsSetup {
	

	public InterfaceMethodsSetup(String className) {
		super(className);
//		this.patternManager = new PatternManager();
	}

	@Override
	protected List<Method> getElements(String name) {
		return patternManager.getInterfaceMethods(name);
	}
	
	@Override
	protected Method getNewMethod() {
		return new Method("method", "Object", "public", true, null);
	}

}
