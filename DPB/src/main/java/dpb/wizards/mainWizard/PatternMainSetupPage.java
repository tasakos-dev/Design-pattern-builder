package dpb.wizards.mainWizard;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.xml.sax.SAXException;

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
	private TreeItem classesTreeItem;
	private TreeItem interfacesTreeItem;
	private Tree tree;
	private ScrolledComposite scrolledComposite;
	
	private IPatternManager patternManager;
	
	
	private List<PatternClass> classes;
	private List<PatternInterface> interfaces;
	
	private SetupWizard interfaceSetupWizard;
	private SetupWizard classSetupWizard;
	private Button btnNewButton;


	

	public PatternMainSetupPage() {
		super("wizardPage");
		try {
			patternManager = (PatternManager) PatternManager.getInstance();
		} catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
			MessageDialog.openError(getShell(), "Error", e.getMessage());
			e.printStackTrace();
		}
		classes = new ArrayList<>();
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
		
		scrolledComposite.setContent(tree);
		scrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button editClassBtn = new Button(container, SWT.NONE);
		
		editClassBtn.setBounds(448, 56, 99, 32);
		editClassBtn.setText("Edit class");
		editClassBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String className = tree.getSelection()[0].getText();
				PatternClass selectedClass = null;
				for (PatternClass patternClass: classes) {
					if (patternClass.getName().equals(className)) {
						selectedClass = patternClass;
						break;
					}
				}
				classSetupWizard = new SetupWizard(selectedClass, "class");
				WizardDialog wizardDialog = new WizardDialog(getShell(), classSetupWizard);
				wizardDialog.open();
			}
		});
		
		
		
		
		
		Button editInterfaceBtn = new Button(container, SWT.NONE);
		editInterfaceBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				String interfaceName = tree.getSelection()[0].getText();
				PatternInterface selectedInterface = null;
				for (PatternInterface patternInterface: interfaces) {
					if (patternInterface.getName().equals(interfaceName)) {
						selectedInterface = patternInterface;
						break;
					}
				}
				interfaceSetupWizard = new SetupWizard(selectedInterface, "interface");
				
				WizardDialog wizardDialog = new WizardDialog(getShell(), interfaceSetupWizard);
				wizardDialog.open();
				
				
		
			}
		});
		editClassBtn.setEnabled(false);
		editInterfaceBtn.setEnabled(false);
		
		editInterfaceBtn.setBounds(448, 94, 99, 32);
		editInterfaceBtn.setText("Edit interface");
		
		btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternClass newClass = new PatternClass("NewClass", "public");
				classes.add(newClass);
				TreeItem trtmNewTreeitem_1 = new TreeItem(classesTreeItem, SWT.NONE);
				trtmNewTreeitem_1.setText(newClass.getName());
			}
		});
		btnNewButton.setBounds(448, 132, 99, 24);
		btnNewButton.setText("Add Class");
		
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				// TODO null pointer!
				
				if(tree.getSelection()[0].getParentItem() != null) { 
					if (tree.getSelection()[0].getParentItem().getText().equals("Classes")) {
						editInterfaceBtn.setEnabled(false);
						editClassBtn.setEnabled(true);
					} else if(tree.getSelection()[0].getParentItem().getText().equals("Interfaces")) {
						editClassBtn.setEnabled(false);
						editInterfaceBtn.setEnabled(true);
					}
					}
				}
		});

	}


	public List<PatternClass> getClasses() {
		return classes;
	}

	public List<PatternInterface> getInterfaces() {
		return interfaces;
	}

	
	public void addElements(String pattern) {
		setDescription(patternManager.getPatternDescription(pattern));
		classes = patternManager.getClasses(pattern);
		interfaces = patternManager.getInterfaces();
		classesTreeItem.removeAll();
		interfacesTreeItem.removeAll();
		for (PatternClass patternClass: classes) {
			TreeItem trtmNewTreeitem_1 = new TreeItem(classesTreeItem, SWT.NONE);
			trtmNewTreeitem_1.setText(patternClass.getName());
		}
		
		for (PatternInterface patternInterface: interfaces) {
			TreeItem trtmNewTreeitem_1 = new TreeItem(interfacesTreeItem, SWT.NONE);
			trtmNewTreeitem_1.setText(patternInterface.getName());
		}
	}
}
