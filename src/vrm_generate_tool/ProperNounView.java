package vrm_generate_tool;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

/** 
* 
* 专有名词界面 
* @author 沈翔宇 
*
*/
public class ProperNounView extends ViewPart implements ISelectionListener{

	public static final String ID = "Variable-Relation-Model-Generate-Tool.ProperNounView";

	/**
	 * The text control that's displaying the content of the email message.
	 */
	private Label lab_name;//名称
	private Label lab_source;//来源
	private Label lab_describe;//描述

	
	private Text  txt_name;//名称输入框
	private Text  txt_source;//来源输入框
	private Text  txt_describe;//描述输入框

//	public static String requirementID = "R1.1";
//	private Label requireID ;
	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
//		GridLayout layout = new GridLayout();
//		layout.marginHeight = 2;
//		layout.marginWidth = 2;
//		top.setLayout(layout);
		
		// top banner
//		Composite banner = new Composite(top, SWT.NONE);
//		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
//		layout = new GridLayout();
//		layout.marginHeight = 5;
//		layout.marginWidth = 10;
//		layout.numColumns = 3;
//		banner.setLayout(layout);
		
		// setup bold font
		Display display = Display.getDefault();
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    	
		lab_name = new Label(top, SWT.NONE);
    	lab_name.setText("名称:");
    	lab_name.setFont(boldFont);
    	lab_name.setBounds(20, 40, 100, 60);
		//l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
    	txt_name = new Text(top, SWT.BORDER);
    	txt_name.setFont(new Font(display,"宋体",16,SWT.NORMAL));
		txt_name.setBounds(120, 40, 500, 26);
	   // text.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
//		requireID = new Label(banner, SWT.WRAP);
//		requireID.setText("");
//		requireID.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		
		lab_source = new Label(top, SWT.NONE);
		lab_source.setText("Source:");
		lab_source.setFont(boldFont);
		lab_source.setBounds(20, 100, 100, 60);
		
		txt_source = new Text(top, SWT.BORDER);
		txt_source.setFont(new Font(display,"宋体",16,SWT.NORMAL));
		txt_source.setBounds(120, 100, 500, 26);
			
		lab_describe = new Label(top, SWT.NONE);
		lab_describe.setText("描述:");
		lab_describe.setFont(boldFont);
		lab_describe.setBounds(20, 160, 100, 60);
		
		txt_describe = new Text(top, SWT.BORDER|SWT.WRAP|SWT.V_SCROLL|SWT.MULTI);
		txt_describe.setFont(new Font(display,"宋体",16,SWT.NORMAL));
		txt_describe.setBounds(120, 160, 500, 400);

		
		
//		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
//    
//		final Link link = new Link(top, SWT.NONE);
//		link.setText("3");
//		link.addSelectionListener(new SelectionAdapter() {    
//			public void widgetSelected(SelectionEvent e) {
//				MessageDialog.openInformation(getSite().getShell(), "Not Implemented", "Imagine the address book or a new message being created now.");
//			}    
//		});
//		link.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
//    
//		l = new Label(top, SWT.NONE);
//		l.setText("Date:");
//		l.setFont(boldFont);
//		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
//		
//		l = new Label(top, SWT.WRAP);
//		l.setText("2020-04-09 10:34:23");
//		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
//		
//		Tree tree = new Tree(top, SWT.SINGLE);
//		tree.setHeaderVisible(true);
//		tree.setLinesVisible(true);
//        tree.setLayoutData(new GridData(GridData.FILL_BOTH));
//        
//		TreeColumn nameColumn = new TreeColumn(tree, SWT.NONE);
//		nameColumn.setWidth(200);
//		nameColumn.setText("Name");
//		
//		TreeColumn typeColumn = new TreeColumn(tree, SWT.NONE);
//		typeColumn.setWidth(100);
//		typeColumn.setText("type");
//		
//		TreeColumn sizeColumn = new TreeColumn(tree, SWT.NONE);
//		sizeColumn.setWidth(100);
//		sizeColumn.setText("size");
//		
//		TreeColumn rangeColumn = new TreeColumn(tree, SWT.NONE);
//		rangeColumn.setWidth(200);
//		rangeColumn.setText("range");
//		
//		TreeItem input = new TreeItem(tree, SWT.NONE);
//		input.setText(new String[] {"Input","","",""});
//		
//		TreeItem preCon = new TreeItem(tree, SWT.NONE);
//		preCon.setText(new String[] {"Pre-Condition","","",""});
//		
//		TreeItem output = new TreeItem(tree, SWT.NONE);
//		output.setText(new String[] {"output","","",""});
//		
//		TreeItem i1 = new TreeItem(input, SWT.NONE);
//		i1.setText(new String[] {"S_A","INT","4","[-65536,65535]"});
//		
//		TreeItem i2 = new TreeItem(input, SWT.NONE);
//		i2.setText(new String[] {"S_B","INT","4","[-65536,65535]"});
//		
//		TreeItem i3 = new TreeItem(input, SWT.NONE);
//		i3.setText(new String[] {"S_C","INT","4","[-65536,65535]"});
//		
//		TreeItem o1 = new TreeItem(output, SWT.NONE);
//		o1.setText(new String[] {"MAIN_WARING","Enum","4","[OFF,ON]"});
//		
//		TreeItem o2 = new TreeItem(output, SWT.NONE);
//		o2.setText(new String[] {"PROTECTOR","Enum","4","[OFF,ON]"});
//		
//		TreeItem p1 = new TreeItem(preCon, SWT.NONE);
//		p1.setText(new String[] {"MODE","Enum","4","[WAIT,WARD,OFF]"});
//		TreeItem p2 = new TreeItem(preCon, SWT.NONE);
//		p2.setText(new String[] {"SENSOR","Enum","4","[OFF,ON]"});
//		TreeItem p3 = new TreeItem(preCon, SWT.NONE);
//		p3.setText(new String[] {"PROTECTOR_SWITCH","Enum","4","[OFF,ON]"});
//	
//		output.setExpanded(true);
//		input.setExpanded(true);
//		preCon.setExpanded(true);
//		
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
