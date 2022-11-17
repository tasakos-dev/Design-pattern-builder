package dpb.model;

import java.util.List;

public class PatternInterface {
	private String name;
	private String type;
	private List<Method> methods;

	
	public PatternInterface(String name, String type, List<Method> methods) {
		super();
		this.name = name;
		this.type = type;
		this.methods = methods;
	}
	
	String getName() {
		return name;
	}
	public String getType() {
		return type;
	}

	public List<Method> getMethods() {
		return methods;
	}
	

}
