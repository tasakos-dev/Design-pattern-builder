package dpb.wizards.setupWizard;


import dpb.model.Method;
import dpb.model.PatternElement;

public class ClassMethodsSetup extends MethodsSetup {


	public ClassMethodsSetup(PatternElement patternElement) {
		super(patternElement);
	}

	@Override
	protected Method getNewMethod() {
		return new Method("method", "Object", "public", false, false,  null, false);
	}
}
