package dpb.controller;


import java.util.List;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import dpb.model.Field;
import dpb.model.Method;
import dpb.model.PatternClass;
import dpb.model.PatternInterface;

public class PatternGenerator  implements IPatternGenerator {
	private IPackageFragment selectedPackage;
	
	
	

	public PatternGenerator() {
		super();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
		Object element = selection.getFirstElement();
		selectedPackage = null;
		System.err.println(element.getClass());
		if (element instanceof IPackageFragment) {
			selectedPackage = (IPackageFragment) element;
		}
		
	}

	@Override
	public void generateClass(PatternClass patternClass) {
			
		String abstractClass = " ";
		StringBuffer buffer = new StringBuffer();
		buffer.append("package "+selectedPackage.getElementName()+";\n\n");
		if (patternClass.isAbstract()) {
			abstractClass = " abstract ";
			
		}
		String implementString = "";
		String extendString = "";
		if (patternClass.getImplementedInterface() != null)
			implementString = " implements "+ patternClass.getImplementedInterface().getName();
		if (patternClass.getExtendedClass() != null)
			extendString = " extends "+ patternClass.getExtendedClass().getName() + " ";
		
		buffer.append(patternClass.getType() + abstractClass + "class " +patternClass.getName()+extendString+implementString+"{\n\n");	
		
		
		for (Field field: patternClass.getFields()) {
			String staticString = "";
			if (field.isStatic()) {
				staticString = "static ";
			}
					
			buffer.append("\t"+field.getModifier()+" "+staticString+field.getType()+" "+field.getName()+";\n");
		}
		buffer.append("\n\n");
		for (Method method: patternClass.getMethods()) {
			if (method.isOverride() || (method.isAbstract() && !method.getOwnerName().equals(patternClass.getName()))) {
				buffer.append("\t@Override\n");
			}
			String staticString = "";
			if (method.isStatic()) {
				staticString = " static ";
			}
			String abstractString = " ";
			if (method.isAbstract() && method.getOwnerName().equals(patternClass.getName())) {
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
			if (method.isAbstract() && method.getOwnerName().equals(patternClass.getName())) {
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
		buffer.append("}");
		
		try {
			selectedPackage.createCompilationUnit(patternClass.getName()+".java", buffer.toString(), false, null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@Override
	public void generateInterface(PatternInterface patternInterface) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("package "+selectedPackage.getElementName()+";\n\n");
		
		buffer.append(patternInterface.getType()+ " interface " +patternInterface.getName() + "{\n\n");	
		
		for (Method method: patternInterface.getMethods()) {
			
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
		buffer.append("}");
		
		try {
			selectedPackage.createCompilationUnit(patternInterface.getName()+".java", buffer.toString(), false, null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
