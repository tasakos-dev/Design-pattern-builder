package dpb.controller;

import java.util.List;

import dpb.model.Field;
import dpb.model.Method;
import dpb.model.PatternClass;
import dpb.model.PatternInterface;


public interface IPatternManager {
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public List<PatternClass> getClasses(String pattern);
	public List<PatternInterface> getInterfaces();
	
	public List<Method> getClassMethods(String className);
	public List<Method> getInterfaceMethods(String interfaceName);
	
	public List<Field> getClassFields(String className);
	
	
}
