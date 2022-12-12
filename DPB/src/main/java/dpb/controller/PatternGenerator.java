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

public class PatternGenerator implements IPatternGenerator {
	private IPackageFragment selectedPackage;
	
	
	

	public PatternGenerator() {
		super();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
		Object element = selection.getFirstElement();
		selectedPackage = null;
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
		if (patternClass.getImplementedInterface() != null)
			implementString = " implements "+ patternClass.getImplementedInterface().getName();
		buffer.append(patternClass.getType() + abstractClass + "class " +patternClass.getName()+implementString+"{\n\n");	
		
		
		for (Field field: patternClass.getFields()) {
			String staticString = "";
			if (field.isStatic()) {
				staticString = "static ";
			}
					
			buffer.append("\t"+field.getModifier()+" "+staticString+field.getType()+" "+field.getName()+";\n");
		}
		buffer.append("\n\n");
		for (Method method: patternClass.getMethods()) {
			if (method.isOverride()) {
				buffer.append("\t@Override\n");
			}
			String staticString = "";
			if (method.isStatic()) {
				staticString = "static ";
			}
			buffer.append("\t"+method.getModifier()+" "+staticString+method.getType()+" "+method.getName());
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
			buffer.append("){\n\t");
			String code = method.getCode();
			if (code == null && !method.getType().equals("void")) {
				code = "\treturn null;";				
			}
			buffer.append(code);
			buffer.append("\n\t}\n");
			

		}
		buffer.append("}");
		
		try {
			selectedPackage.createCompilationUnit(patternClass.getName()+".java", buffer.toString(), false, null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

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
