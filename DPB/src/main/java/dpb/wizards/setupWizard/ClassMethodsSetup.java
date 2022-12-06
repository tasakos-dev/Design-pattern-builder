package dpb.wizards.setupWizard;

import java.util.List;

import dpb.model.Method;

public class ClassMethodsSetup extends MethodsSetup {


	public ClassMethodsSetup(String className) {
		super(className);
	}

	@Override
	protected List<Method> getElements(String name) {
		return patternManager.getClassMethods(name);
	}

	@Override
	protected Method getNewMethod() {
		return new Method("method", "Object", "public", false, null);
	}
}
