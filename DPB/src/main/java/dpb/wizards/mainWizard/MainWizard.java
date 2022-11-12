package dpb.wizards.mainWizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import dpb.io.FileParser;
import dpb.io.IFileParser;

public class MainWizard extends Wizard implements INewWizard {
	private ISelection selection;

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		this.selection = arg1;
		
	}

	public MainWizard() {
		setWindowTitle("New Wizard");
	}

	@Override
	public void addPages() {
		IFileParser fileParser = new FileParser();
		
		System.err.println(fileParser.getPatternCategories()[0]);
		System.err.println(fileParser.getPatternCategories()[1]);
		System.err.println(fileParser.getPatternsOfCategory("creational")[0]);
		System.err.println(fileParser.getPatternsOfCategory("creational")[1]);
		System.err.println(fileParser.getPatternsOfCategory("creational1")[0]);
		System.exit(0);
		addPage(new PatternSelectorPage(selection));
		addPage(new PatternMainSetupPage(selection));
	}

	@Override
	public boolean performFinish() {
		return false;
	}
	
	

}
