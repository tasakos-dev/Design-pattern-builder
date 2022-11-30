package dpb.wizards.setupWizard;

import java.util.List;

import dpb.controller.IPatternManager;
import dpb.controller.PatternManager;
import dpb.model.Method;

public class ClassMethodsSetup extends MethodsSetup {
	private IPatternManager patternManager;

	public ClassMethodsSetup(String className) {
		super(className);
		this.patternManager = new PatternManager();
	}

	@Override
	protected List<Method> getElements(String name) {
		return patternManager.getClassMethods(name);
	}

}
