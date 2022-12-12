package dpb.model;

import java.util.ArrayList;
import java.util.List;

public class PatternInterface extends PatternElement {

	
	private List<PatternClass> classesThatImplementsMe;

	
	public PatternInterface(String name, String type, List<Method> methods) {
		super(name, type, methods);

		classesThatImplementsMe = new ArrayList<>();
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
	
	@Override
	public void addMethod(Method method) {
		super.addMethod(method);
		for (PatternClass classes: classesThatImplementsMe) {
			classes.addMethod(method);
		}
	}
	
	@Override
	public void removeMethod(Method method) {
		super.removeMethod(method);
		for (PatternClass classes: classesThatImplementsMe) {
			classes.removeMethod(method);
		}
	}

	
	

}
