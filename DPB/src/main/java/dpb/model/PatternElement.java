package dpb.model;

import java.util.ArrayList;
import java.util.List;

public class PatternElement {
	private String name;
	private String type;
	private List<Method> methods;
	
	public PatternElement(String name, String type, List<Method> methods) {
		super();
		this.name = name;
		this.type = type;
		this.methods = methods;
	}
	

	public PatternElement(String name, String type) {
		super();
		this.name = name;
		this.type = type;
		methods = new ArrayList<>();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
	public void overrideMethods(List<Method> methods) {
		for (int i = 0;i<methods.size();i++) {
			Method method = methods.get(i);
			if (!this.methods.contains(method)) {
				this.methods.add(method);
			} else {
				searchMethod(method).setOverride(true);
			}
		}
	}
	private Method searchMethod(Method matchMethod) {
		for (Method method: methods) {
			if (method.equals(matchMethod)) {
				return method;
			}
		}
		return null;
	}

	
	public void addMethod(Method method) {
		this.methods.add(method);
	}
	
	public void removeMethod(Method method) {
		this.methods.remove(method);
	}
	

	
}
