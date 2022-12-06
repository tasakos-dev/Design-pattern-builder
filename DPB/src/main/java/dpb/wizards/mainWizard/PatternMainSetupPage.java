package dpb.wizards.mainWizard;


import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import dpb.controller.IPatternManager;
import dpb.controller.PatternManager;
import dpb.model.PatternClass;
import dpb.model.PatternInterface;
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
	
	private IPatternManager patternManager;
	
	
	private List<PatternClass> classes;
	private List<PatternInterface> interfaces;
	
	private SetupWizard interfaceSetupWizard;
	private SetupWizard classSetupWizard;


	

	public PatternMainSetupPage(String pattern) {
		super("wizardPage");
		this.pattern = pattern;
		patternManager = new PatternManager();
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		
		classes = patternManager.getClasses(pattern);
		interfaces = patternManager.getInterfaces();
		
		
		
		
		
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
			for (PatternClass patternClass: classes) {
				TreeItem trtmNewTreeitem_1 = new TreeItem(classesTreeItem, SWT.NONE);
				trtmNewTreeitem_1.setText(patternClass.getName());
			}
			
			for (PatternInterface patternInterface: interfaces) {
				TreeItem trtmNewTreeitem_1 = new TreeItem(interfacesTreeItem, SWT.NONE);
				trtmNewTreeitem_1.setText(patternInterface.getName());
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
				classSetupWizard = new SetupWizard(className, "class");
				WizardDialog wizardDialog = new WizardDialog(getShell(), classSetupWizard);
				wizardDialog.open();
				for (PatternClass patternClass : classes) {
					if (patternClass.getName().equals(className)) {
						patternClass.addMethods(classSetupWizard.getMethods());
						patternClass.setFields(classSetupWizard.getFields());
						break;
					}
				}
		
			}
		});
		
		
		
		
		
		Button editInterfaceBtn = new Button(container, SWT.NONE);
		editInterfaceBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				String interfaceName = tree.getSelection()[0].getText();
				interfaceSetupWizard = new SetupWizard(interfaceName, "interface");
				
				WizardDialog wizardDialog = new WizardDialog(getShell(), interfaceSetupWizard);
				wizardDialog.open();
				for (PatternInterface patternInterface : interfaces) {
					if (patternInterface.getName().equals(interfaceName)) {
						patternInterface.addMethods(interfaceSetupWizard.getMethods());
						break;
					}
				}
				
		
			}
		});
		editClassBtn.setEnabled(false);
		editInterfaceBtn.setEnabled(false);
		
		editInterfaceBtn.setBounds(448, 94, 99, 32);
		editInterfaceBtn.setText("Edit interface");
		
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				// TODO null pointer!
				
				if (tree.getSelection()[0].getParentItem().getText().equals("Classes")) {
					editInterfaceBtn.setEnabled(false);
					editClassBtn.setEnabled(true);
				} else if(tree.getSelection()[0].getParentItem().getText().equals("Interfaces")) {
					editClassBtn.setEnabled(false);
					editInterfaceBtn.setEnabled(true);
				}
			}
		});

	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public List<PatternClass> getClasses() {
		return classes;
	}

	public List<PatternInterface> getInterfaces() {
		return interfaces;
	}

	
	
	

	
	
}
