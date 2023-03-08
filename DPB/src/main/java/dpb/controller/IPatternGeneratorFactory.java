package dpb.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.CoreException;

public interface IPatternGeneratorFactory {
	public IPatternGenerator createPatternGenerator() throws CoreException, URISyntaxException, IOException;

}
