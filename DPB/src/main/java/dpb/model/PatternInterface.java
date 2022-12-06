package dpb.model;

import java.util.ArrayList;
import java.util.List;

public class PatternInterface {
	private String name;
	private String type;
	private List<Method> methods;
	
	private List<PatternClass> classesThatImplementsMe;

	
	public PatternInterface(String name, String type, List<Method> methods) {
		super();
		this.name = name;
		this.type = type;
		this.methods = methods;
		classesThatImplementsMe = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void addMethods(List<Method> methods) {
		for (Method method: methods) {
			if (!this.methods.contains(method)) {
				this.methods.add(method);
			}
		}
		for (PatternClass patternClass : classesThatImplementsMe) {
			patternClass.addMethods(methods);
		}
	}

	public List<PatternClass> getClassesThatImplementsMe() {
		return classesThatImplementsMe;
	}

	public void setClassesThatImplementsMe(List<PatternClass> classesThatImplementsMe) {
		this.classesThatImplementsMe = classesThatImplementsMe;
	}
	
	public void addClass(PatternClass patternClass) {
		classesThatImplementsMe.add(patternClass);
	}

	
	

}
