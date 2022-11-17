package dpb.model;

import java.util.List;

public class PatternClass {
	private String name;
	private String type;
	private PatternInterface implementedInterface;
	private List<Field> fields;
	private List<Method> methods;
	
	public PatternClass(String name, String type, List<Field> fields, List<Method> methods, PatternInterface implementedInterface) {
		super();
		this.implementedInterface = implementedInterface;
		this.name = name;
		this.type = type;
		this.fields = fields;
		this.methods = methods;
		
		if (implementedInterface != null) {
			for (Method imethod: implementedInterface.getMethods())
			this.methods.add(imethod);
		}
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

	public void setImplementedInterface(PatternInterface implementedInterface) {
		this.implementedInterface = implementedInterface;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	

}
