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
		// TODO maybe discover interfaces on the other method 
		interfaces.clear();
		List<PatternClass> classes = new ArrayList<>();
		for (String className: fileParser.getClasses(pattern)) {
			String interfaceName = fileParser.getImplementedInterface(className, pattern);
			List<Method> interfaceMethods = getInterfaceMethods(interfaceName, pattern);
			List<Method> classMethods = getClassMethods(className, pattern);
			List<Field> classFields = getClassFields(className, pattern);
			boolean isAbstract = fileParser.isAbstractClass(className, pattern);
			
			PatternClass patternClass;
			if (interfaceName != null && !interfaceName.isBlank()) {
				PatternInterface implementedInterface = new PatternInterface(interfaceName, "public", interfaceMethods);		
				patternClass = new PatternClass(className, "public", isAbstract, classFields, classMethods, implementedInterface);
				patternClass.overrideMethods(implementedInterface.getMethods());
				implementedInterface.addClass(patternClass);
				if (!containsInterface(implementedInterface))
					interfaces.add(implementedInterface);
			} else {
				patternClass = new PatternClass(className, "public", isAbstract, classFields, classMethods, null);
			}
			
			classes.add(patternClass);
			
			
		}
		setExtendedClass(classes, pattern);
		return classes;
	}
	
	private boolean containsInterface(PatternInterface patternInterface) {
		for (PatternInterface pInterface : interfaces) {
			if (pInterface.getName().equals(patternInterface.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private void setExtendedClass(List<PatternClass> classes, String pattern) {
		for (PatternClass patternClass : classes) {
			String classname = patternClass.getName();
			String extendedClassname = fileParser.getExtendedClass(classname, pattern);
			for (PatternClass extendedClass : classes) {
				if (extendedClass.getName().equals(extendedClassname)) {
					patternClass.setExtendedClass(extendedClass);
					for (Method method : extendedClass.getMethods()) {
						if(method.isAbstract()) {
							patternClass.addMethod(method);
						}
					}
					break;
				}			
			}
		}
		
	}
	

	@Override
	public List<PatternInterface> getInterfaces() {
		return interfaces;
	}

	
	private List<Method> getClassMethods(String className, String pattern) {
		String[][] methods = fileParser.getClassMethods(className, pattern);
		List<Method> methodList = new ArrayList<Method>();
		for (int i = 0; i < methods.length; i++) {
			Method method = new Method(methods[i][1], methods[i][0], "public", false,
									fileParser.isAbstractMethod(methods[i][1], className, pattern),
									null,fileParser.isStaticMethod(methods[i][1], className, pattern));
			method.setCode(fileParser.getMethodCode(methods[i][1], className, pattern));
			method.setOwnerName(className);
			String[][] parameters = fileParser.getMethodParameters(methods[0][1], className, pattern);
			for (String[] parameter : parameters) {
				method.addParameter(parameter);
			}
			methodList.add(method);
		}
		return methodList;
	}

	
	private List<Method> getInterfaceMethods(String interfaceName, String pattern) {
		String[][] methods = fileParser.getInterfaceMethods(interfaceName, pattern);
		if (methods == null)
			return null;
		List<Method> methodList = new ArrayList<Method>();
		for (int i = 0; i < methods.length; i++) {
			Method method = new Method(methods[i][1], methods[i][0], "public",true, false, null, fileParser.isStaticMethod(methods[i][1], interfaceName, pattern));
			method.setOwnerName(interfaceName);
			methodList.add(method);
			
			
		}
		return methodList;
	}

	
	private List<Field> getClassFields(String className, String pattern) {
		String[][] fields = fileParser.getClassFields(className, pattern);
		List<Field> fieldsList = new ArrayList<Field>();
		for (int i = 0; i < fields.length; i++) {
			fieldsList.add(new Field(fields[i][1], fields[i][0], "private", fileParser.isStaticField(fields[i][1], className, pattern)));
		}
		return fieldsList;
	}

}
