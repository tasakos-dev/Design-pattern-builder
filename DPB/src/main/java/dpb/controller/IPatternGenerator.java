package dpb.controller;


import org.eclipse.jdt.core.JavaModelException;

import dpb.model.PatternClass;
import dpb.model.PatternElement;
import dpb.model.PatternInterface;

public interface IPatternGenerator {
	public void generate(PatternElement patternElement) throws JavaModelException;
	
	

}
