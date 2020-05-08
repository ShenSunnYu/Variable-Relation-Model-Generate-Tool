package vrm_generate_tool;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;


/**
 * 
 * @author 展万里
 * 
 * 显示事件页面
 *
 */
public class DominantEventView extends ViewPart implements ISelectionListener{

	public static final String ID = "Variable-Relation-Model-Generate-Tool.DominantEventView";

	/**
	 * The text control that's displaying the content of the email message.
	 */

	@Override
	public void createPartControl(Composite parent) {
		RequirementsSpecification.init(parent, ID);
		getSite().getPage().addSelectionListener((ISelectionListener) this);
	
	}
	@Override
	public void addPartPropertyListener(IPropertyChangeListener listener) {
		// TODO Auto-generated method stub
		super.addPartPropertyListener(listener);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		ConsoleHandler.info("process detetct change\n");
		if(selection!= null){
//			requireID.setText(selection.toString());
		}
	}
	@Override
	public void setFocus() {
		return;
	}
}
