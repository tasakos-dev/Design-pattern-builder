package dpb.wizards.mainWizard;


import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import dpb.controller.IPatternManager;
import dpb.controller.PatternManager;
import dpb.wizards.setupWizard.SetupWizard;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PatternMainSetupPage extends WizardPage implements IWizardPage {
	private String pattern;
	private TreeItem classesTreeItem;
	private TreeItem interfacesTreeItem;
	private Tree tree;
	private ScrolledComposite scrolledComposite;


	

	public PatternMainSetupPage() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
//		this.patternClasses = patternClasses;
//		this.patternInterfaces = patternInterfaces;		
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		
		scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 56, 432, 247);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		tree = new Tree(scrolledComposite, SWT.BORDER);
		
		
		classesTreeItem = new TreeItem(tree, SWT.NONE);
		classesTreeItem.setText("Classes");
		classesTreeItem.setExpanded(true);
		
		interfacesTreeItem = new TreeItem(tree, SWT.NONE);
		interfacesTreeItem.setText("Interfaces");
		interfacesTreeItem.setExpanded(true);
		
		
		if (pattern != null) {
			IPatternManager patternManager = new PatternManager();
			String[] patternClasses = patternManager.getClasses(pattern);
			String[] patternInterfaces = patternManager.getInterfaces(pattern);
			for (String patternClass: patternClasses) {
				TreeItem trtmNewTreeitem_1 = new TreeItem(classesTreeItem, SWT.NONE);
				trtmNewTreeitem_1.setText(patternClass);
			}
			
			for (String patternInterface: patternInterfaces) {
				TreeItem trtmNewTreeitem_1 = new TreeItem(interfacesTreeItem, SWT.NONE);
				trtmNewTreeitem_1.setText(patternInterface);
			}
		}
		
		

		
		scrolledComposite.setContent(tree);
		scrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button editClassBtn = new Button(container, SWT.NONE);
		
		editClassBtn.setBounds(448, 56, 99, 32);
		editClassBtn.setText("Edit class");
		editClassBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String className = tree.getSelection()[0].getText();
				System.err.println(className);
				WizardDialog wizardDialog = new WizardDialog(getShell(), new SetupWizard(className));
				wizardDialog.open();
		
			}
		});
		
		Button editInterfaceBtn = new Button(container, SWT.NONE);
		editInterfaceBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		editInterfaceBtn.setBounds(448, 94, 99, 32);
		editInterfaceBtn.setText("Edit interface");

	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	
	
}
