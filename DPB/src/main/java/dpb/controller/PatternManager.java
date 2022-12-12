package dpb.controller;

import java.util.ArrayList;
import java.util.List;

import dpb.io.FileParser;
import dpb.io.IFileParser;
import dpb.model.Field;
import dpb.model.Method;
import dpb.model.PatternClass;
import dpb.model.PatternInterface;


public class PatternManager implements IPatternManager {
	private IFileParser fileParser;
	private List<PatternInterface> interfaces;
	
	

	public PatternManager() {
		super();
		fileParser = new FileParser();
		interfaces = new ArrayList<>();
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
	public List<PatternClass> getClasses(String pattern) {
		List<PatternClass> classes = new ArrayList<>();
		for (String className: fileParser.getClasses(pattern)) {
			String interfaceName = fileParser.getImplementedInterface(className);
			List<Method> interfaceMethods = getInterfaceMethods(interfaceName);
			List<Method> classMethods = getClassMethods(className);
			List<Field> classFields = getClassFields(className);
			boolean isAbstract = fileParser.isAbstractClass(className);
			PatternClass patternClass;
			if (!interfaceName.isBlank()) {
				PatternInterface implementedInterface = new PatternInterface(interfaceName, "public", interfaceMethods);		
				patternClass = new PatternClass(className, "public", isAbstract, classFields, classMethods, implementedInterface);
				patternClass.addMethods(implementedInterface.getMethods());
				implementedInterface.addClass(patternClass);
				interfaces.add(implementedInterface);
			} else {
				patternClass = new PatternClass(className, "public", isAbstract, classFields, classMethods, null);
			}
			classes.add(patternClass);
			
			
		}
		return classes;
	}

	@Override
	public List<PatternInterface> getInterfaces() {
		return interfaces;
	}

	@Override
	public List<Method> getClassMethods(String className) {
		String[][] methods = fileParser.getClassMethods(className);
		List<Method> methodList = new ArrayList<Method>();
		for (int i = 0; i < methods.length; i++) {
			Method method = new Method(methods[i][1], methods[i][0], "public", false, fileParser.isAbstractMethod(methods[i][1]), null,fileParser.isStaticMethod(methods[i][1]));
			method.setCode(fileParser.getMethodCode(methods[i][1]));
			methodList.add(method);
		}
		return methodList;
	}

	@Override
	public List<Method> getInterfaceMethods(String interfaceName) {
		String[][] methods = fileParser.getInterfaceMethods(interfaceName);
		if (methods == null)
			return null;
		List<Method> methodList = new ArrayList<Method>();
		for (int i = 0; i < methods.length; i++) {
			methodList.add(new Method(methods[i][1], methods[i][0], "public",true, false, null, fileParser.isStaticMethod(methods[i][1])));
		}
		return methodList;
	}

	@Override
	public List<Field> getClassFields(String className) {
		String[][] fields = fileParser.getClassFields(className);
		List<Field> fieldsList = new ArrayList<Field>();
		for (int i = 0; i < fields.length; i++) {
			fieldsList.add(new Field(fields[i][1], fields[i][0], "private", fileParser.isStaticField(fields[i][1])));
		}
		return fieldsList;
	}

}
