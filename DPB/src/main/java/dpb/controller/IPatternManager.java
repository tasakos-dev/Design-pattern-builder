package dpb.controller;

import java.util.List;

import dpb.model.ClassField;
import dpb.model.ClassMethod;


public interface IPatternManager {
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public String[] getClasses(String pattern);
	public String[] getInterfaces(String pattern);
	
	public List<ClassMethod> getClassMethods(String className);
	public List<ClassMethod> getInterfaceMethods(String interfaceName);
	
	public List<ClassField> getClassFields(String className);
	
	
}
