package dpb.model;

import java.util.ArrayList;
import java.util.List;


public class Method {
	private String name;
	private String type;
	private String modifier;
	private List<String[]> parameters;	
	private String code;
	private boolean isOverride;
	
	
	public Method(String name, String type, String modifier, boolean isOverride, List<String[]> parameters) {
		super();
		this.name = name;
		this.type = type;
		this.isOverride = isOverride;
		this.modifier = modifier;
		this.parameters = parameters;
		if (parameters == null) {
			this.parameters = new ArrayList<String[]>();
		}
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
	
	
	
	public boolean isOverride() {
		return isOverride;
	}
	public void setOverride(boolean isOverride) {
		this.isOverride = isOverride;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
				return false;
			}
		} else if (!name.equals(other.name)) {
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Method [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", modifier=");
		builder.append(modifier);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	


}
