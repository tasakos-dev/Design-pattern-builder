package dpb.controller;

import java.util.List;

import dpb.exceptions.NoPropertiesException;
import dpb.model.Field;
import dpb.model.Method;
import dpb.model.PatternClass;
import dpb.model.PatternElement;
import dpb.model.PatternInterface;



public interface IPatternManager {
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	public String getPatternDescription(String pattern);
	
	public List<PatternClass> getClasses(String category, String pattern);
	public List<PatternInterface> getInterfaces();
	public void updateClassName(String newName, PatternElement element);
	public void updateFieldName(String newName, Field field, PatternClass patternClass);
	public void updateMethodName(String newName, Method method);
	public String[] getProperties(String pattern) throws NoPropertiesException;
	
	
}
