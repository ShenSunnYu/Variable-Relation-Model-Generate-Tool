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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.eclipse.nebula.widgets.grid.*;


/**
 * 
 * @author 崔国楠、沈翔宇
 * 
 * 原始需求页面
 *
 */
public class RequirementsView extends ViewPart implements ISelectionListener{

	public static final String ID = "Variable-Relation-Model-Generate-Tool.RequirementsView";

	/**
	 * The text control that's displaying the content of the email message.
	 */
	//private Label lab_id;//需求ID
	//private Label lab_title;//标题
	//private Text  txt_id;//需求输入框
	//private Text  txt_title;//标题输入框
	//private Text  txt_content;//内容输入框
//	public static String requirementID = "R1.1";
//	private Label requireID ;
	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns=8;
		layout.horizontalSpacing=10;
		top.setLayout(layout);
		
		new Label(top,SWT.NONE).setText("需求ID:");
		Text text1=new Text(top, SWT.SINGLE | SWT.BORDER);		
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);         
		gridData.horizontalSpan = 3;         
		text1.setLayoutData(gridData);
		//text1.setBounds(150, 40, 80, 60);
		
		List categories = new List(top, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);        
		//categories.setItems(new String [] {"Best of Breed", "Prettiest Female", "Handsomest Male","Best Dressed", "Fluffiest Ears", "Most Colors",             "Best Performer", "Loudest Bark", "Best Behaved","Prettiest Eyes", "Most Hair", "Longest Tail","Cutest Trick"});        
		gridData = new GridData(GridData.FILL_HORIZONTAL,GridData.FILL_VERTICAL);  
		gridData.horizontalSpan = 4;  
		gridData.verticalSpan = 3;   
		//gridData1.grabExcessVerticalSpace=true;
		//int listHeight = categories.getItemHeight() * 12;        
		//Rectangle trim = categories.computeTrim(0, 0, 0, listHeight);        
		//gridData1.heightHint = trim.height;        
		categories.setLayoutData(gridData);  
		
		new Label(top,SWT.NONE).setText("数量:");
		Text text2=new Text(top, SWT.SINGLE | SWT.BORDER);
		text2.setBounds(150, 100, 80, 60);
		gridData = new GridData(GridData.FILL_HORIZONTAL);         
		gridData.horizontalSpan = 3;         
		text2.setLayoutData(gridData);
		
		new Label(top,SWT.NONE).setText("内容:");
		Text text3=new Text(top, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL,GridData.FILL_VERTICAL);         
		gridData.horizontalSpan = 3;
		//gridData3.grabExcessVerticalSpace=true;
		text3.setLayoutData(gridData);
		// top banner
//		Composite banner = new Composite(top, SWT.NONE);
//		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
//		layout = new GridLayout();
//		layout.marginHeight = 5;
//		layout.marginWidth = 10;
//		layout.numColumns = 3;
//		banner.setLayout(layout);
		
		//GridData gridData=new GridData();
		//gridData.verticalAlignment=GridData.FILL;//垂直方向充满
		// setup bold font
		/*		
		Display display = Display.getDefault();
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    	
    	lab_id = new Label(top, SWT.NONE);
    	lab_id.setText("需求ID:");
    	lab_id.setFont(boldFont);
    	lab_id.setBounds(10, 40, 80, 60);
    	lab_id.setLayoutData(gridData);
		//l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
    	txt_id = new Text(top, SWT.BORDER);
    	txt_id.setFont(new Font(display,"宋体",16,SWT.NORMAL));
		txt_id.setBounds(90, 40, 300, 26);
		txt_id.setLayoutData(gridData);
	   // text.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
//		requireID = new Label(banner, SWT.WRAP);
//		requireID.setText("");
//		requireID.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		
		lab_title = new Label(top, SWT.NONE);
		lab_title.setText("数量:");
		lab_title.setFont(boldFont);
		lab_title.setBounds(10, 100, 80, 60);
		lab_title.setLayoutData(gridData);

		txt_title = new Text(top, SWT.BORDER);
		txt_title.setFont(new Font(display,"宋体",16,SWT.NORMAL));
		txt_title.setBounds(90, 100, 300, 26);
		txt_title.setLayoutData(gridData);
		
		lab_content = new Label(top, SWT.NONE);
		lab_content.setText("内容:");
		lab_content.setFont(boldFont);
		lab_content.setBounds(10, 160, 80,60);
		lab_content.setLayoutData(gridData);
		
		txt_content = new Text(top, SWT.BORDER|SWT.WRAP|SWT.V_SCROLL|SWT.MULTI);
		txt_content.setFont(new Font(display,"宋体",16,SWT.NORMAL));
		txt_content.setBounds(90, 160, 300, 200);
		txt_content.setLayoutData(gridData);*/
		
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
