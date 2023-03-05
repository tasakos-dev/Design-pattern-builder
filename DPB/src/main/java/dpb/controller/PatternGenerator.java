package dpb.controller;



import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import dpb.model.PatternElement;

public abstract class PatternGenerator  implements IPatternGenerator {
	private IPackageFragment selectedPackage;
	
	
	

	public PatternGenerator() throws CoreException {
		super();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
//		Object element = selection.getFirstElement();
		
		if (selection != null && !selection.isEmpty()) {
		    Object selectedElement = selection.getFirstElement();
		    if (selectedElement instanceof IPackageFragment) {
		        selectedPackage = (IPackageFragment) selectedElement;
		    } else if (selectedElement instanceof IJavaElement) {
		        selectedPackage = (IPackageFragment) ((IJavaElement) selectedElement).getAncestor(IJavaElement.PACKAGE_FRAGMENT);
		    }
		}
		if (selectedPackage == null) {
		    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		    IProject[] projects = root.getProjects();
		    for (IProject project : projects) {
				if (project.isOpen() && project.hasNature(JavaCore.NATURE_ID)) {
				    IJavaProject javaProject = JavaCore.create(project);
				    selectedPackage = javaProject.getPackageFragmentRoot(project.getFolder("src")).getPackageFragment("");
				    if (selectedPackage != null) {
				        break;
				    }
				}
		    }
		}
		
	}
	
	@Override
	public void generate(PatternElement patternElement) throws JavaModelException {
		StringBuffer buffer = new StringBuffer();
		generatePackageHeader(buffer, patternElement);
		generateFields(buffer, patternElement);
		generateMethods(buffer, patternElement);
		buffer.append("}");
		selectedPackage.createCompilationUnit(patternElement.getName()+".java", buffer.toString(), false, null);
	}
	
	private void generatePackageHeader(StringBuffer buffer, PatternElement patternElement) {
		if (!selectedPackage.getElementName().isBlank()) buffer.append("package "+selectedPackage.getElementName()+";\n\n");
		generateHeader(buffer, patternElement);
	}
	
	protected abstract void generateMethods(StringBuffer buffer, PatternElement patternElement);
	protected abstract void generateFields(StringBuffer buffer, PatternElement patternElement);
	protected abstract void generateHeader(StringBuffer buffer, PatternElement patternElement);

}
