package dpb.model;

public class Property {
	private String annotation;
	private String implement;
	
	public Property(String annotation, String implement) {
		super();
		this.annotation = annotation;
		this.implement = implement;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getImplement() {
		return implement;
	}

	public void setImplement(String implement) {
		this.implement = implement;
	}
	
	
	
	

}
