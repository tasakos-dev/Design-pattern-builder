package dpb.controller;

import java.util.List;

import dpb.model.PatternClass;
import dpb.model.PatternInterface;


public interface IPatternManager {
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public List<PatternClass> getClasses(String pattern);
	public List<PatternInterface> getInterfaces();
}
