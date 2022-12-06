package dpb.controller;


import dpb.model.PatternClass;
import dpb.model.PatternInterface;

public interface IPatternGenerator {
	public void generateClass(PatternClass patternClass);
	public void generateInterface(PatternInterface patternInterface);
	

}
