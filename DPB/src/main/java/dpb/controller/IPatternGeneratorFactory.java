package dpb.controller;

import org.eclipse.core.runtime.CoreException;

public interface IPatternGeneratorFactory {
	public IPatternGenerator createPatternGenerator() throws CoreException;

}
