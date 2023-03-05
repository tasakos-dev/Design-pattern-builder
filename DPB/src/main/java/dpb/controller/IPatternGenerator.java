package dpb.controller;


import org.eclipse.jdt.core.JavaModelException;
import dpb.model.PatternElement;


public interface IPatternGenerator {
	public void generate(PatternElement patternElement) throws JavaModelException;
	
	

}
