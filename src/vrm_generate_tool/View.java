package vrm_generate_tool;

import java.util.ArrayList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.nebula.widgets.grid.*;


/**
 * 
 * @author 汪文轩
 * 
 * 这里是右边的表格界面
 *
 */
public class View extends ViewPart implements ISelectionListener{

	public static final String ID = "Variable-Relation-Model-Generate-Tool.view";

	/**
	 * The text control that's displaying the content of the email message.
	 */
	private Label requireID ;
	private Grid grid;
	@Override
	public void createPartControl(Composite parent) {

		getSite().getPage().addSelectionListener((ISelectionListener) this);
	}

	@Override
	public void addPartPropertyListener(IPropertyChangeListener listener) {
		super.addPartPropertyListener(listener);
	}

	@Override
	public void setFocus() {
		return;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		ConsoleHandler.info("detetct change\n");
		if(selection!= null){
			IStructuredSelection is = (IStructuredSelection)selection;
			
			try {
				System.out.print("-----------------"+is.toString());
				if((is.toString()).equals("[原始需求]")) {
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(RequirementsView.ID, "RequirementsView", IWorkbenchPage.VIEW_ACTIVATE);
					System.out.print("！！！！！"+is.toString());
				}else if((is.toString()).equals("[通用条件]")) {
//					System.out.print("！！！！！"+is.toString());
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(CommonConditionView.ID, "CommonConditionView", IWorkbenchPage.VIEW_ACTIVATE);
				}
					
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
