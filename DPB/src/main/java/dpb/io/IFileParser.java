package dpb.io;

import dpb.exceptions.NoPropertiesException;

public interface IFileParser {
	
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public String[] getProperties(String pattern) throws NoPropertiesException;
	
	public String[] getClasses(String pattern);
	public String getPatternDescription(String pattern);
	
	public String getImplementedInterface(String className, String pattern);
	public boolean isAbstractClass(String className, String pattern);
	public boolean isAbstractMethod(String method, String classname, String pattern);
	
	public String getExtendedClass(String className, String pattern);
	
	public String[][] getClassMethods(String className, String pattern);
	public String[][] getInterfaceMethods(String interfaceName, String pattern);
	
	public String getMethodCode(String methodName, String name, String pattern);
	public String[][] getMethodParameters(String method, String classname, String pattern);
	
	public String[][] getClassFields(String className, String pattern);
	public boolean isStaticField(String name, String classname, String pattern);
	public boolean isStaticMethod(String name, String classname, String pattern);
	public String getAnnotation(String name, String category, String pattern);
	
	
	
}
