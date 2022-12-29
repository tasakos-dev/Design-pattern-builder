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
	private boolean isAbstract;
	private boolean isStatic;
	private String ownerName;
	
	
	public Method(String name, String type, String modifier, boolean isOverride, boolean isAbstract , List<String[]> parameters, boolean isStatic) {
		super();
		this.name = name;
		this.type = type;
		this.isOverride = isOverride;
		this.modifier = modifier;
		this.parameters = parameters;
		this.isAbstract = isAbstract;
		this.isStatic = isStatic;
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
	
	public void addParameter(String[] parameter) {
		parameters.add(parameter);
	}
	
	public void removeParameter(String[] parameter) {
		parameters.remove(parameter);
	}
	
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public boolean isAbstract() {
		return isAbstract;
	}
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
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
	
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
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
