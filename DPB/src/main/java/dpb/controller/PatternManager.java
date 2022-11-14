package dpb.controller;

import dpb.io.FileParser;
import dpb.io.IFileParser;

public class PatternManager implements IPatternManager {
	private IFileParser fileParser;
	
	

	public PatternManager() {
		super();
		fileParser = new FileParser();
	}

	@Override
	public String[] getPatternCategories() {
		// TODO Auto-generated method stub
		return fileParser.getPatternCategories();
	}

	@Override
	public String[] getPatternsOfCategory(String category) {
		// TODO Auto-generated method stub
		return fileParser.getPatternsOfCategory(category);
	}

	@Override
	public String[] getClasses(String pattern) {
		// TODO Auto-generated method stub
		return fileParser.getClasses(pattern);
	}

	@Override
	public String[] getInterfaces(String pattern) {
		// TODO Auto-generated method stub
		return fileParser.getInterfaces(pattern);
	}

	@Override
	public String[][] getClassMethods(String className) {
		// TODO Auto-generated method stub
		return fileParser.getClassMethods(className);
	}

	@Override
	public String[][] getInterfaceMethods(String interfaceName) {
		// TODO Auto-generated method stub
		return fileParser.getInterfaceMethods(interfaceName);
	}

	@Override
	public String[][] getClassFields(String className) {
		// TODO Auto-generated method stub
		return fileParser.getClassFields(className);
	}

}
