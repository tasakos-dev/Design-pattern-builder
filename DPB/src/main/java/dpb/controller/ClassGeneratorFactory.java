package dpb.controller;

import org.eclipse.core.runtime.CoreException;

public class ClassGeneratorFactory implements IPatternGeneratorFactory {
	@Override
	public IPatternGenerator createPatternGenerator() throws CoreException {
	    return new ClassGenerator();
	}
}
