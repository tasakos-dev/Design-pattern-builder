package dpb.wizards.mapWizard;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;

public class MapItems extends WizardPage {
	List<String> items;
	List<String> patternItems;

	public MapItems(List<String> items, List<String> patternItems) {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
		this.items = items;
		this.patternItems = patternItems;
		System.out.println(patternItems.size());
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		

		setControl(container);
		
		
		
//		Combo combo = new Combo(container, SWT.NONE);
//		combo.setBounds(79, 34, 201, 38);
//		
//		Label lblNewLabel = new Label(container, SWT.NONE);
//		lblNewLabel.setBounds(10, 34, 63, 16);
//		lblNewLabel.setText("New Label");
		String[] itemStrings = new String[items.size()];
		for (int i = 0; i<items.size();i++) {
			itemStrings[i] =items.get(i);
			
		}
		
		for (int i = 0; i<patternItems.size();i++) {
			String item = patternItems.get(i);
			Combo combo = new Combo(container, SWT.NONE);
			combo.setBounds(103, 28+44*i, 201, 38);
			combo.setItems(itemStrings);
			
			Label lblNewLabel = new Label(container, SWT.NONE);
			lblNewLabel.setBounds(10, 28+44*i, 200, 16);
			lblNewLabel.setText(item);
			
		}

		

		
	}
}
