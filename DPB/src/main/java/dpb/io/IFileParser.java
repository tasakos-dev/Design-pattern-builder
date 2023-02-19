package dpb.io;

public interface IFileParser {
	
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public String[] getClasses(String pattern);
//	public String[] getInterfaces(String pattern);
	
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
	
	
	
	
}
