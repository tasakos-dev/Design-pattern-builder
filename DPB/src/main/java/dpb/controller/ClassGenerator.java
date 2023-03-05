package dpb.controller;

import java.util.List;

import org.eclipse.core.runtime.CoreException;

import dpb.model.Field;
import dpb.model.Method;
import dpb.model.PatternClass;
import dpb.model.PatternElement;

public class ClassGenerator extends PatternGenerator {
	
	public ClassGenerator() throws CoreException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generateHeader(StringBuffer buffer, PatternElement patternElement) {
		PatternClass patternClass = (PatternClass) patternElement;
		String abstractClass = " ";
		if (patternClass.isAbstract()) {
			abstractClass = " abstract ";
		}
		
		String implementString = "";
		String extendString = "";
		if (patternClass.getImplementedInterface() != null)
			implementString = " implements "+ patternClass.getImplementedInterface().getName();
		if (patternClass.getExtendedClass() != null)
			extendString = " extends "+ patternClass.getExtendedClass().getName() + " ";
		
		buffer.append(patternElement.getType() + abstractClass + "class " +patternClass.getName()+extendString+implementString+"{\n\n");	
	}
	
	@Override
	protected void generateFields(StringBuffer buffer, PatternElement patternElement) {
		for (Field field:((PatternClass) patternElement).getFields()) {
			String staticString = "";
			if (field.isStatic()) {
				staticString = "static ";
			}
					
			buffer.append("\t"+field.getModifier()+" "+staticString+field.getType()+" "+field.getName()+";\n");
		}
		buffer.append("\n\n");
	}
	
	@Override
	protected void generateMethods(StringBuffer buffer, PatternElement patternElement) {
		for (Method method: patternElement.getMethods()) {
			if (method.isOverride() || (method.isAbstract() && !method.getOwnerName().equals(patternElement.getName()))) {
				buffer.append("\t@Override\n");
			}
			String staticString = "";
			if (method.isStatic()) {
				staticString = " static ";
			}
			String abstractString = " ";
			if (method.isAbstract() && method.getOwnerName().equals(patternElement.getName())) {
				abstractString = " abstract ";
			}
				
			buffer.append("\t"+method.getModifier()+abstractString+staticString+method.getType()+" "+method.getName());
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
			if (method.isAbstract() && method.getOwnerName().equals(patternElement.getName())) {
				buffer.append(");\n");
			}
			else {
				buffer.append("){\n");
				buffer.append("\t\t// TODO Auto-generated block\n");
				String code = codeFormat(method.getCode());
				if (code.isBlank() && (!method.getType().isEmpty() && !method.getType().equals("void"))) {
					code = "\t\treturn null;";				
				}
	
				buffer.append(code);
				buffer.append("\n\t}\n");
			}
		

	}

		
			
	}
	
	private String codeFormat(String code) {
		String formattedCode = "";
		if (code == null) return formattedCode;
		if (code.isBlank()) return formattedCode;
		formattedCode = code.replace("\t", "");
		formattedCode = formattedCode.replace("{\n", "{\n\t");
		formattedCode = formattedCode.replace("\n", "\n\t\t");
		formattedCode = "\t\t" + formattedCode;
		
		return formattedCode;
	}



}
