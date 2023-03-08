package dpb.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.CoreException;

public class ClassGeneratorFactory implements IPatternGeneratorFactory {
	@Override
	public IPatternGenerator createPatternGenerator() throws CoreException, URISyntaxException, IOException {
	    return new ClassGenerator();
	}
}
