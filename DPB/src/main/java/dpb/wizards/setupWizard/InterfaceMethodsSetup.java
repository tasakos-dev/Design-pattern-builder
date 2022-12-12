package dpb.wizards.setupWizard;

import dpb.model.Method;
import dpb.model.PatternElement;

public class InterfaceMethodsSetup extends MethodsSetup {
	

	public InterfaceMethodsSetup(PatternElement patternElement) {
		super(patternElement);
//		this.patternManager = new PatternManager();
	}

	
	@Override
	protected Method getNewMethod() {
		return new Method("method", "Object", "public", true, false, null, false);
	}

}
