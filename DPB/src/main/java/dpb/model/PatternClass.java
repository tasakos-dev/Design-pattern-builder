package dpb.model;

import java.util.List;

public class PatternClass {
	private String name;
	private String type;
	private List<ClassField> fields;
	private List<ClassMethod> methods;
	
	public PatternClass(String name, String type, List<ClassField> fields, List<ClassMethod> methods) {
		super();
		this.name = name;
		this.type = type;
		this.fields = fields;
		this.methods = methods;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}

	public List<ClassField> getFields() {
		return fields;
	}

	public List<ClassMethod> getMethods() {
		return methods;
	}
	

}
