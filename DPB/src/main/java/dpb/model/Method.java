package dpb.model;

import java.util.List;


public class Method {
	private String name;
	private String type;
	private String modifier;
	private List<String[]> parameters;	
	
	
	public Method(String name, String type, String modifier, List<String[]> parameters) {
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
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public void setParameters(List<String[]> parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			return false;
			}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Method other = (Method) obj;
		if (name == null) {
			if (other.name != null) {
				System.err.println("edww3");
				return false;
			}
		} else if (!name.equals(other.name)) {
			System.err.println("reles?");
			return false;
		}
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
	
	
	


}
