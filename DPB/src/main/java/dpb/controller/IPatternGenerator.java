package dpb.controller;


import org.eclipse.jdt.core.JavaModelException;

import dpb.model.PatternClass;
import dpb.model.PatternInterface;

public interface IPatternGenerator {
	public void generateClass(PatternClass patternClass) throws JavaModelException;
	public void generateInterface(PatternInterface patternInterface) throws JavaModelException;
	

}
