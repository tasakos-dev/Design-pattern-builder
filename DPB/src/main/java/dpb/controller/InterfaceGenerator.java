package dpb.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import dpb.model.Method;
import dpb.model.PatternElement;

public class InterfaceGenerator extends PatternGenerator {


	public InterfaceGenerator() throws CoreException, URISyntaxException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generateMethods(StringBuffer buffer, PatternElement patternElement) {
		for (Method method: patternElement.getMethods()) {
			buffer.append("\t"+method.getModifier()+" "+method.getType()+" "+method.getName());
			buffer.append("(");
			int numOfparameters = method.getParameters().size();
			List<String[]> parameters = method.getParameters(); 
			for (String[] parameter: parameters) {
				String ending = "";
				if (parameter[1].equals(parameters.get(numOfparameters-1))) {
					ending = ", ";
				}
				buffer.append(parameter[0]+" "+parameter[1]+ending);
			}
			buffer.append(");\n");	
		}
	}

	@Override
	protected void generateFields(StringBuffer buffer, PatternElement patternElement) {
	}

	@Override
	protected void generateHeader(StringBuffer buffer, PatternElement patternElement) {
		buffer.append(patternElement.getType() +  " interface " + patternElement.getName() + "{\n\n");		
	}
	
}
