package dpb.controller;

import java.util.ArrayList;
import java.util.List;

import dpb.io.FileParser;
import dpb.io.IFileParser;
import dpb.model.Field;
import dpb.model.Method;


public class PatternManager implements IPatternManager {
	private IFileParser fileParser;
	
	

	public PatternManager() {
		super();
		fileParser = new FileParser();
	}

	@Override
	public String[] getPatternCategories() {
		return fileParser.getPatternCategories();
	}

	@Override
	public String[] getPatternsOfCategory(String category) {
		return fileParser.getPatternsOfCategory(category);
	}

	@Override
	public String[] getClasses(String pattern) {
		return fileParser.getClasses(pattern);
	}

	@Override
	public String[] getInterfaces(String pattern) {
		return fileParser.getInterfaces(pattern);
	}

	@Override
	public List<Method> getClassMethods(String className) {
		String[][] methods = fileParser.getClassMethods(className);
		List<Method> methodList = new ArrayList<Method>();
		for (int i = 0; i < methods.length; i++) {
			methodList.add(new Method(methods[i][1], methods[i][0], "public", null));
		}
		return methodList;
	}

	@Override
	public List<Method> getInterfaceMethods(String interfaceName) {
		String[][] methods = fileParser.getInterfaceMethods(interfaceName);
		List<Method> methodList = new ArrayList<Method>();
		for (int i = 0; i < methods.length; i++) {
			methodList.add(new Method(methods[i][1], methods[i][0], "public", null));
		}
		return methodList;
	}

	@Override
	public List<Field> getClassFields(String className) {
		String[][] fields = fileParser.getClassFields(className);
		List<Field> fieldsList = new ArrayList<Field>();
		for (int i = 0; i < fields.length; i++) {
			fieldsList.add(new Field(fields[i][1], fields[i][0], "private"));
		}
		return fieldsList;
	}

}
