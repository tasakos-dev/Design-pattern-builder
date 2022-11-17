package dpb.wizards.setupWizard;

import java.util.List;

import dpb.controller.IPatternManager;
import dpb.controller.PatternManager;
import dpb.model.ClassMethod;

public class InterfaceMethodsSetup extends MethodsSetup {
	private IPatternManager patternManager;

	public InterfaceMethodsSetup(String className) {
		super(className);
		// TODO Auto-generated constructor stub
		this.patternManager = new PatternManager();
	}

	@Override
	protected List<ClassMethod> getMethods(String name) {
		return patternManager.getInterfaceMethods(name);
	}

}
