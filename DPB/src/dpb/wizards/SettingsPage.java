package dpb.wizards;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.wb.swt.SWTResourceManager;

import dpb.wizards.mapWizard.MapWizard;
import dpb.wizards.setupWizards.SetupWizard;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SettingsPage extends WizardPage {
	private ISelection selection;

	public SettingsPage(ISelection selection) {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 56, 432, 247);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Tree tree = new Tree(scrolledComposite, SWT.BORDER);
		
		
		TreeItem classesTreeItem = new TreeItem(tree, SWT.NONE);
		classesTreeItem.setText("Classes");
		
		TreeItem interfacesTreeItem = new TreeItem(tree, SWT.NONE);
		interfacesTreeItem.setText("Interfaces");
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(classesTreeItem, SWT.NONE);
		trtmNewTreeitem_1.setText("Singleton");
		classesTreeItem.setExpanded(true);
		scrolledComposite.setContent(tree);
		scrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button editClassBtn = new Button(container, SWT.NONE);
		
		editClassBtn.setBounds(448, 94, 99, 32);
		editClassBtn.setText("Edit class");
		editClassBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.err.println("click");
				WizardDialog wizardDialog = new WizardDialog(getShell(), new SetupWizard());
				wizardDialog.open();
		
			}
		});
		
		Button editInterfaceBtn = new Button(container, SWT.NONE);
		editInterfaceBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		editInterfaceBtn.setBounds(448, 132, 99, 32);
		editInterfaceBtn.setText("Edit interface");
		
		Button editMethodsBtn = new Button(container, SWT.NONE);
		editMethodsBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				WizardDialog mapDialog = new WizardDialog(getShell(), new MapWizard());
				mapDialog.open();
				
			}
		});
		
		editMethodsBtn.setBounds(448, 170, 99, 32);
		editMethodsBtn.setText("Map methods");
		
		Button editFieldsBtn = new Button(container, SWT.NONE);
		editFieldsBtn.setText("Map fields");
		editFieldsBtn.setBounds(448, 208, 99, 32);
		
		Button browseBtn = new Button(container, SWT.NONE);
		browseBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		browseBtn.setBounds(448, 56, 99, 32);
		browseBtn.setText("Browse...");
//		editMethodsBtn.setVisible(false);
//		editFieldsBtn.setVisible(false);
//		
		
	
		
		
		
	
		
	}
	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select package");
		
	}
}
