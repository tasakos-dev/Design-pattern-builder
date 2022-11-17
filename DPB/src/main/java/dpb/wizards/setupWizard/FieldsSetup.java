package dpb.wizards.setupWizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import dpb.controller.IPatternManager;
import dpb.controller.PatternManager;
import dpb.model.Field;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;

public class FieldsSetup extends WizardPage {
	private String className;
	private IPatternManager patternManager;
	private Table table;
	public FieldsSetup(String className) {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.className = className;
		this.patternManager = new PatternManager();
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 10, 564, 296);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		buildTable(scrolledComposite);
		
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	private void buildTable(ScrolledComposite scrolledComposite) {
		TableViewer tableViewer = new TableViewer(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		
		
		
		TableViewerColumn tableViewerTypeColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn typeColumn = tableViewerTypeColumn.getColumn();
		typeColumn.setWidth(280);
		typeColumn.setAlignment(SWT.LEFT);
		typeColumn.setResizable(false);
		typeColumn.setText("Type");
		
		tableViewerTypeColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Field field = (Field) element;
				return field.getType();
			}
		});
		
		tableViewerTypeColumn.setEditingSupport(new EditingSupport(tableViewer) {
			
			@Override
			protected void setValue(Object arg0, Object arg1) {
				
				Field field = (Field) arg0;
				field.setType(arg1.toString());
				tableViewer.update(arg0, null);
				
			}
			
			@Override
			protected Object getValue(Object arg0) {
				
				return ((Field) arg0).getType();
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
		
		TableViewerColumn tableViewerNameColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn nameColumn = tableViewerNameColumn.getColumn();
		nameColumn.setAlignment(SWT.LEFT);
		nameColumn.setResizable(false);
		nameColumn.setWidth(280);
		nameColumn.setText("Name");
		
		tableViewerNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Field field = (Field) element;
				return field.getName();
			}
		});
		
		tableViewerNameColumn.setEditingSupport(new EditingSupport(tableViewer) {
			
			@Override
			protected void setValue(Object arg0, Object arg1) {
				
				Field field = (Field) arg0;
				field.setName(arg1.toString());
				tableViewer.update(arg0, null);
				
			}
			
			@Override
			protected Object getValue(Object arg0) {
				
				return ((Field) arg0).getName();
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

		 
		
		List<Field> fieldsList = patternManager.getClassFields(className);
		tableViewer.setInput(fieldsList);
	}
}
