package dpb.model;

public class ClassField {
	private String name;
	private String type;
	private String modifier;
	
	public ClassField(String name, String type, String modifier) {
		super();
		this.name = name;
		this.type = type;
		this.modifier = modifier;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getModifier() {
		return modifier;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	

}
