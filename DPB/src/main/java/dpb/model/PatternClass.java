package dpb.model;

import java.util.List;

public class PatternClass {
	private String name;
	private String type;
	private PatternInterface implementedInterface;
	private boolean isAbstract;
	private List<Field> fields;
	private List<Method> methods;
	
	
	public PatternClass(String name, String type, boolean isAbstract, List<Field> fields, List<Method> methods, PatternInterface implementedInterface) {
		super();
		this.implementedInterface = implementedInterface;
		this.name = name;
		this.type = type;
		this.fields = fields;
		this.methods = methods;
		this.isAbstract = isAbstract;

	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<Method> getMethods() {
		
		return methods;
	}

	public PatternInterface getImplementedInterface() {
		return implementedInterface;
	}

//	public void setImplementedInterface(PatternInterface implementedInterface) {
//		this.implementedInterface = implementedInterface;
//	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void addMethods(List<Method> methods) {
		for (Method method: methods) {
			if (!this.methods.contains(method)) {
				this.methods.add(method);
			}
		}
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	
	

}
