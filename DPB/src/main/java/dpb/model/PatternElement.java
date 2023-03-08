package dpb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatternElement {
	private String pattern;
	private String categoryOfPattern;
	private String role;
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

	

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getPattern() {
		return pattern;
	}


	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	public String getCategoryOfPattern() {
		return categoryOfPattern;
	}


	public void setCategoryOfPattern(String categoryOfPattern) {
		this.categoryOfPattern = categoryOfPattern;
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



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatternElement other = (PatternElement) obj;
		return Objects.equals(name, other.name);
	}
	
	

	
}
