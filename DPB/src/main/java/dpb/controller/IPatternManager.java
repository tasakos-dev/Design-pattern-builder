package dpb.controller;

import java.util.List;

import dpb.model.Field;
import dpb.model.Method;


public interface IPatternManager {
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public String[] getClasses(String pattern);
	public String[] getInterfaces(String pattern);
	
	public List<Method> getClassMethods(String className);
	public List<Method> getInterfaceMethods(String interfaceName);
	
	public List<Field> getClassFields(String className);
	
	
}
