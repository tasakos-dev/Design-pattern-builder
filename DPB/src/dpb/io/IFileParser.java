package dpb.io;

public interface IFileParser {
	
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory();
	
	public String[] getClasses();
	public String[] getInterfaces();
	
	public String[] getClassMethods(String className);
	public String[] getInterfaceMethods(String interfaceName);
	
	public String[] getClassFields(String className);
	
	
}
