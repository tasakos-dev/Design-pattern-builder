package dpb.controller;

import org.eclipse.core.runtime.CoreException;

public class InterfaceGeneratorFactory implements IPatternGeneratorFactory {

	@Override
	public IPatternGenerator createPatternGenerator() throws CoreException {
		return new InterfaceGenerator();
	}

}
