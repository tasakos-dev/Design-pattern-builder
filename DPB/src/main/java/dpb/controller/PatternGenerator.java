package dpb.controller;



import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import dpb.model.PatternElement;

public abstract class PatternGenerator  implements IPatternGenerator {
	private IPackageFragment selectedPackage;
	
	
	

	public PatternGenerator() throws CoreException {
		super();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
		
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
		addAnnotationsToClassPath();
	}
	
	private void addAnnotationsToClassPath() throws JavaModelException {
		
		IJavaProject project = selectedPackage.getJavaProject();
		IClasspathEntry[] oldEntries = project.getRawClasspath();
		
		System.err.println(project.getElementName());	
		Bundle bundle = Platform.getBundle("DPB");
		URL filUrl = bundle.getEntry("src/main/resources/Annotations.jar");
		File patternsFIle = null;
		try {
			patternsFIle = new File(FileLocator.resolve(filUrl).toURI());
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPath customJarPath = new Path(patternsFIle.getAbsolutePath());
		IClasspathEntry customJarEntry = JavaCore.newLibraryEntry(customJarPath, null, null);
		
		for (IClasspathEntry entry : oldEntries) {
		    if (entry.getPath().equals(customJarEntry)) {
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
		generateHeader(buffer, patternElement);
	}
	
	protected abstract void generateMethods(StringBuffer buffer, PatternElement patternElement);
	protected abstract void generateFields(StringBuffer buffer, PatternElement patternElement);
	protected abstract void generateHeader(StringBuffer buffer, PatternElement patternElement);

}
