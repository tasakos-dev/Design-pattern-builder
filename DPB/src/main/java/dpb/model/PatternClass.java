package dpb.model;

import java.util.ArrayList;
import java.util.List;

public class PatternClass extends PatternElement{
	
	private PatternInterface implementedInterface;
	private boolean isAbstract;
	private List<Field> fields;
	
	
	
	public PatternClass(String name, String type, boolean isAbstract, List<Field> fields, List<Method> methods, PatternInterface implementedInterface) {
		super(name, type, methods);
		this.implementedInterface = implementedInterface;
		this.fields = fields;
		this.isAbstract = isAbstract;
	
	}
	
	


	public PatternClass(String name, String type) {
		super(name, type);
		fields = new ArrayList<>();
	}




	public List<Field> getFields() {
		return fields;
	}

	public PatternInterface getImplementedInterface() {
		return implementedInterface;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}


	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}



	public void setImplementedInterface(PatternInterface implementedInterface) {
		this.implementedInterface = implementedInterface;
	}
	
	
	

}
