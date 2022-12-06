package dpb.io;

public interface IFileParser {
	
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public String[] getClasses(String pattern);
	public String[] getInterfaces(String pattern);
	
	public String getImplementedInterface(String className);
	public boolean isAbstract(String className);
	
	public String[][] getClassMethods(String className);
	public String[][] getInterfaceMethods(String interfaceName);
	
	public String[][] getClassFields(String className);
	
	
}
