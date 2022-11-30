package dpb.wizards.setupWizard;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import dpb.model.Method;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public abstract class MethodsSetup extends WizardPage {
	private String className;
	private List<Method> methods;
	private Table table;

	public MethodsSetup(String className) {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.className = className;
		
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 10, 589, 324);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		
		TableViewer tableViewer = buildTable(scrolledComposite);
		
		Button addBtn = new Button(container, SWT.NONE);
		addBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				methods.add(new Method("test", "test", "test", null));
				tableViewer.refresh();
				
			}
		});
		addBtn.setBounds(605, 23, 70, 24);
		addBtn.setText("Add");
		
		Button deleteBtn = new Button(container, SWT.NONE);
		deleteBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (Method method: methods) {
					if( tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()).equals(method)) {
						methods.remove(method);
						break;
						
					}
		
				}
				tableViewer.refresh();
			}
		});
		deleteBtn.setBounds(605, 53, 70, 24);
		deleteBtn.setText("Delete");
		

		
	}
	
	private TableViewer buildTable(ScrolledComposite scrolledComposite) {
		
		TableViewer tableViewer = new TableViewer(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		TableViewerColumn modifierTableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn modifierColumn = modifierTableViewerColumn.getColumn();
		modifierColumn.setText("Modifier");
		modifierColumn.setResizable(false);
		modifierColumn.setWidth(188);
		modifierTableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Method method = (Method) element;
				return method.getModifier();
			}
		});
		
		modifierTableViewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
			
			@Override
			protected void setValue(Object arg0, Object arg1) {
				Method method = (Method) arg0;
				method.setModifier(className);
				tableViewer.update(arg0, null);
				
			}
			
			@Override
			protected Object getValue(Object arg0) {
				Method method = (Method) arg0;
				return method.getModifier();
			}
			
			@Override
			protected CellEditor getCellEditor(Object arg0) {
				return new TextCellEditor(tableViewer.getTable());
			}
			
			@Override
			protected boolean canEdit(Object arg0) {				
				return true;
			}
		});
		
		TableViewerColumn returnTypeTableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn returnTypeColumn = returnTypeTableViewerColumn.getColumn();
		returnTypeColumn.setResizable(false);
		returnTypeColumn.setWidth(188);
		returnTypeColumn.setText("Return type");
		returnTypeTableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Method method = (Method) element;
				return method.getType();
			}
		});
		
		returnTypeTableViewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
			
			@Override
			protected void setValue(Object arg0, Object arg1) {
				Method method = (Method) arg0;
				method.setType(arg1.toString());
				tableViewer.update(arg0, null);
				
			}
			
			@Override
			protected Object getValue(Object arg0) {
				Method method = (Method) arg0;
				return method.getType();
			}
			
			@Override
			protected CellEditor getCellEditor(Object arg0) {
				return new TextCellEditor(tableViewer.getTable());
			}
			
			@Override
			protected boolean canEdit(Object arg0) {				
				return true;
			}
		});
		
		TableViewerColumn nameTableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn nameColumn = nameTableViewerColumn.getColumn();
		nameColumn.setResizable(false);
		nameColumn.setWidth(188);
		nameColumn.setText("Name");
		
		nameTableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Method method = (Method) element;
				return method.getName();
			}
		});
		
		
		nameTableViewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
		
			
			@Override
			protected void setValue(Object arg0, Object arg1) {
				Method method = (Method) arg0;
				method.setName(arg1.toString());
				tableViewer.update(arg0, null);
				
			}
			
			@Override
			protected Object getValue(Object arg0) {
				Method method = (Method) arg0;
				return method.getName();
			}
			
			@Override
			protected CellEditor getCellEditor(Object arg0) {
				return new TextCellEditor(tableViewer.getTable());
			}
			
			@Override
			protected boolean canEdit(Object arg0) {				
				return true;
			}
		});
		methods = getElements(className);
		
		tableViewer.setInput(methods);
		
		return tableViewer;

	}
	abstract protected List<Method> getElements(String name);
}
