package dpb.io;

public interface IFileParser {
	
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public String[] getClasses(String pattern);
	public String[] getInterfaces(String pattern);
	
	public String getImplementedInterface(String className, String pattern);
	public boolean isAbstractClass(String className);
	public boolean isAbstractMethod(String method);
	
	public String getExtendedClass(String className, String pattern);
	
	public String[][] getClassMethods(String className);
	public String[][] getInterfaceMethods(String interfaceName);
	
	public String getMethodCode(String method);
	
	public String[][] getClassFields(String className);
	public boolean isStaticField(String name);
	public boolean isStaticMethod(String name);
	
	
	
	
}
