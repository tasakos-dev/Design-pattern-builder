package dpb.controller;

import java.util.ArrayList;
import java.util.List;

import dpb.io.FileParser;
import dpb.io.IFileParser;
import dpb.model.ClassField;
import dpb.model.ClassMethod;


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
	public List<ClassMethod> getClassMethods(String className) {
		String[][] methods = fileParser.getClassMethods(className);
		List<ClassMethod> methodList = new ArrayList<ClassMethod>();
		for (int i = 0; i < methods.length; i++) {
			methodList.add(new ClassMethod(methods[i][1], methods[i][0], "private", null));
		}
		return methodList;
	}

	@Override
	public List<ClassMethod> getInterfaceMethods(String interfaceName) {
		String[][] methods = fileParser.getInterfaceMethods(interfaceName);
		List<ClassMethod> methodList = new ArrayList<ClassMethod>();
		for (int i = 0; i < methods.length; i++) {
			methodList.add(new ClassMethod(methods[i][1], methods[i][0], "private", null));
		}
		return methodList;
	}

	@Override
	public List<ClassField> getClassFields(String className) {
		String[][] fields = fileParser.getClassFields(className);
		List<ClassField> fieldsList = new ArrayList<ClassField>();
		for (int i = 0; i < fields.length; i++) {
			fieldsList.add(new ClassField(fields[i][1], fields[i][0], "private"));
		}
		return fieldsList;
	}

}
