package dpb.wizards.setupWizard;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import dpb.model.Method;

public class MethodParameterSetupPage extends WizardPage {
	private Method method;
	private Table table;

	public MethodParameterSetupPage(Method method) {
		super("wizardPage");
		setTitle("Setup method parameters");
		setDescription("Setup name & type every parameter");
		this.method = method;
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
				String[] parameter = new String[] {"Object", "arg"};
				method.addParameter(parameter);
				tableViewer.refresh();
				
			}
		});
		addBtn.setBounds(605, 23, 87, 24);
		addBtn.setText("Add");
		
		Button deleteBtn = new Button(container, SWT.NONE);
		deleteBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] selectedParameter = (String[]) tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex());				
				for (String[] parameter: method.getParameters()) {
					if (selectedParameter[1].equals(parameter[1])) {
						method.removeParameter(selectedParameter);
						break;
					}
				}
				tableViewer.refresh();
			}
		});
		deleteBtn.setBounds(605, 53, 87, 24);
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
		
		TableViewerColumn typeTableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn typeColumn = typeTableViewerColumn.getColumn();
		typeColumn.setResizable(false);
		typeColumn.setWidth(282);
		typeColumn.setText("Type");
		typeTableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				String[] parameter = (String[]) element;
				return parameter[0];
			}
		});
		
		typeTableViewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
			
			@Override
			protected void setValue(Object arg0, Object arg1) {
				String[] parameter = (String[]) arg0;
				parameter[0] = arg1.toString();
				tableViewer.update(arg0, null);
				
			}
			
			@Override
			protected Object getValue(Object arg0) {
				String[] parameter = (String[]) arg0;
				return parameter[0];
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
		nameColumn.setWidth(282);
		nameColumn.setText("Name");
		
		nameTableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				String[] parameter = (String[]) element;
				return parameter[1];
			}
		});
		
		
		nameTableViewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
		
			
			@Override
			protected void setValue(Object arg0, Object arg1) {
				String[] parameter = (String[]) arg0;
				parameter[1] = arg1.toString();
				tableViewer.update(arg0, null);
				
			}
			
			@Override
			protected Object getValue(Object arg0) {
				String[] parameter = (String[]) arg0;
				return parameter[1];
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
		
		
		tableViewer.setInput(method.getParameters());
		
		return tableViewer;

	}
	

}
