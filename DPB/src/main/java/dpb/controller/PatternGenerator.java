package dpb.controller;



import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import dpb.model.PatternElement;

public abstract class PatternGenerator  implements IPatternGenerator {
	private IPackageFragment selectedPackage;
	
	
	

	public PatternGenerator() throws CoreException, URISyntaxException, IOException {
		super();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
		
		if (selection != null && !selection.isEmpty()) {
		    Object selectedElement = selection.getFirstElement();
		    if (selectedElement instanceof IPackageFragment) {
		        selectedPackage = (IPackageFragment) selectedElement;
		    } else {
		    	IPackageFragmentRoot srcFolderRoot = null;
		    	if (selectedElement instanceof IJavaProject) {
		    		IJavaProject project = (IJavaProject) selectedElement;
		    		for (IPackageFragmentRoot root : project.getPackageFragmentRoots()) {
		    		    if (root.getKind() == IPackageFragmentRoot.K_SOURCE) {
		    		        srcFolderRoot = root;
		    		        break;
		    		    }
		    		}
				} else if (selectedElement instanceof IPackageFragmentRoot) {
					srcFolderRoot = (IPackageFragmentRoot) selectedElement;
				}
		    	
		    	selectedPackage = srcFolderRoot.createPackageFragment("", false, null);
		    }
		}

		addAnnotationsToClassPath();
	}
	
	protected void addAnnotationsToClassPath() throws JavaModelException, URISyntaxException, IOException {
		IJavaProject project = selectedPackage.getJavaProject();
		IClasspathEntry[] oldEntries = project.getRawClasspath();
		
//		System.err.println(project.getElementName());	
		Bundle bundle = Platform.getBundle("DPB");
		URL filUrl = bundle.getEntry("src/main/resources/Annotations.jar");
		File patternsFIle = null;

		patternsFIle = new File(FileLocator.resolve(filUrl).toURI());
		
		IPath customJarPath = new Path(patternsFIle.getAbsolutePath());
		IClasspathEntry customJarEntry = JavaCore.newLibraryEntry(customJarPath, null, null);
		
		for (IClasspathEntry entry : oldEntries) {
		    if (entry.getPath().equals(customJarPath)) {
		        // JAR entry already exists in classpath, do not add it again
		        return;
		    }
		}

		// add the custom jar entry to the classpath
		IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
		newEntries[oldEntries.length] = customJarEntry;

		// set the new classpath entries for the project
		project.setRawClasspath(newEntries, null);
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
		String importString = "import " + lowerCaseFirstLetter(patternElement.getCategoryOfPattern().replaceAll("\\s+", "")) + "." + lowerCaseFirstLetter(patternElement.getPattern().replaceAll("\\s+", "")) + "." + patternElement.getRole()+ ";";
		buffer.append(importString + "\n\n");
		buffer.append("@"+patternElement.getRole()+"\n");
		generateHeader(buffer, patternElement);
	}
	
	private String lowerCaseFirstLetter(String word) {
		return word.substring(0, 1).toLowerCase() + word.substring(1);
	}
	
	protected abstract void generateMethods(StringBuffer buffer, PatternElement patternElement);
	protected abstract void generateFields(StringBuffer buffer, PatternElement patternElement);
	protected abstract void generateHeader(StringBuffer buffer, PatternElement patternElement);

	public void setSelectedPackage(IPackageFragment selectedPackage) {
		this.selectedPackage = selectedPackage;
	}
	
	

}
