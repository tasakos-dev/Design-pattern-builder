package dpb.patternGeneratorTests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;

import dpb.controller.ClassGenerator;

public class MockClassGenerator extends ClassGenerator {

	public MockClassGenerator() throws CoreException, URISyntaxException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void addAnnotationsToClassPath() throws JavaModelException, URISyntaxException, IOException {
		// TODO Auto-generated method stub
	}

}
