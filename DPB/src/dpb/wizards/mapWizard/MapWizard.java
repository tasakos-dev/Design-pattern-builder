package dpb.wizards.mapWizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class MapWizard extends Wizard implements INewWizard {
	
	
	private ISelection selection;

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		this.selection = arg1;
		
	}

	public MapWizard() {
		setWindowTitle("New Wizard");
	}

	@Override
	public void addPages() {
		List<String> items = new ArrayList<>();
		items.add("getId");
		items.add("getName");
		ArrayList<String> patternItems = new ArrayList<>();
		patternItems.add("getInstance");
		patternItems.add("getSingletonData");

		addPage(new SelectItems(true, patternItems));
		addPage(new MapItems(items, patternItems));
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
