package dpb.model;

import java.util.List;

public class InterfaceMethod {
	private String name;
	private String type;
	private String modifier;
	private List<String[]> parameters;

	
	public InterfaceMethod(String name, String type, String modifier, List<String[]> parameters) {
		super();
		this.name = name;
		this.type = type;
		this.modifier = modifier;
		this.parameters = parameters;
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
	public List<String[]> getParameters() {
		return parameters;
	}
	


}
