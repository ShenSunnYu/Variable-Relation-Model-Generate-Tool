package vrm_generate_tool;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

/** 
* 
* 模式类界面
* @author 沈翔宇 
*
*/
public class PatternView extends ViewPart implements ISelectionListener{

	public static final String ID = "Variable-Relation-Model-Generate-Tool.PatternView";

	/**
	 * The text control that's displaying the content of the email message.
	 */
	private Label lab_name;//名称
	private Label lab_describe;//描述

	private Text  txt_name;//名称输入框
	private Text  txt_describe;//描述输入框

	private Table table;

//	protected Shell shell;
//	public static String requirementID = "R1.1";
//	private Label requireID ;
	@Override
	public void createPartControl(Composite parent) {
//		Composite top = new Composite(parent, SWT.NONE);
		Composite top = new Composite(parent, SWT.H_SCROLL|SWT.V_SCROLL);
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
	    	
	    txt_name = new Text(top, SWT.BORDER);
	    txt_name.setFont(new Font(display,"宋体",16,SWT.NORMAL));
	    txt_name.setBounds(120, 40, 500, 26);
	    		
	    lab_describe = new Label(top, SWT.NONE);
	    lab_describe.setText("描述:");
	    lab_describe.setFont(boldFont);
	    lab_describe.setBounds(20, 100, 100, 60);
	    		
	    txt_describe = new Text(top, SWT.BORDER);
	    txt_describe.setFont(new Font(display,"宋体",16,SWT.NORMAL));
	    txt_describe.setBounds(120, 100, 500, 26);

				
		//shell=new Shell();
	
		//shell.setSize(350,100);
		//shell.setLayout(new FillLayout(SWT.VERTICAL));
		
		//Composite composite = new Composite(shell, SWT.H_SCROLL|SWT.V_SCROLL);
		//composite.setLayout(new FillLayout(SWT.HORIZONTAL));
	    /*
	    ViewForm viewForm=new ViewForm(parent,SWT.NONE);
		viewForm.setTopCenterSeparate(true);
	    GridLayout gridLayout = new GridLayout();
		top = new Composite(viewForm, SWT.NONE);
		top.setLayout(gridLayout);
		*/
	    
	    // 表格布局
	 	GridData gridDate = new GridData();
	 	gridDate.horizontalAlignment = SWT.FILL;
	 	gridDate.grabExcessHorizontalSpace = true ;
	 	gridDate.grabExcessVerticalSpace = true;
	 	gridDate.verticalAlignment = SWT.FILL;
	 				
		//CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer.newCheckList(top, SWT.BORDER | SWT.FULL_SELECTION);
		//table = checkboxTableViewer.getTable();//获取表格
	 	table = new Table(top,SWT.BORDER | SWT.MULTI);
		table.setBounds(20, 160, 1200, 600);
		
		table.setHeaderVisible(true);//表格头部显示
		table.setLinesVisible(true);//设置表格线
		table.setLayoutData(gridDate);//设置表格布局
		
		 for (int i = 0; i < 6; i++) {
		      TableColumn column = new TableColumn(table, SWT.NONE);
		      column.setWidth(200);
		    }
		 for (int i = 0; i < 12; i++) {
		      new TableItem(table, SWT.NONE);
		  }
		    
		// 创建表头的字符串数组  
        //String[] tableHeader = {"名称", "Isintital", "Isfinal", "Numberical Value","Source","描述"};
        //TableItem item = new TableItem(table, SWT.NONE); 
        /*for(int i=0;i<tableHeader.length;i++)
		{
			TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
			tableColumn.setText(tableHeader[i]);
			tableColumn.setMoveable(true);
		}*/
        TableItem[] items = table.getItems();
        for (int i = 0; i < items.length; i++) {
        	for(int j=0;j<6;j++)
        	{
        		TableEditor editor = new TableEditor(table);
        		Text text = new Text(table, SWT.CENTER);
        	if(i==0 && j==0)
        	{
        		text.setText("名称");
            	editor.grabHorizontal = true;
            	editor.setEditor(text, items[i], j);
        	}else if(i==0 && j==1)
        	{
            	text.setText("Isintital");
            	editor.grabHorizontal = true;
            	editor.setEditor(text, items[i], j);
        	}else if(i==0 && j==2)
        	{
            	text.setText("Isfinal");
            	editor.grabHorizontal = true;
            	editor.setEditor(text, items[i], j);
        	}else if(i==0 && j==3)
        	{
            	text.setText("Numberical Value");
            	editor.grabHorizontal = true;
            	editor.setEditor(text, items[i], j);
        	}else if(i==0 && j==4)
        	{
            	text.setText("Source");
            	editor.grabHorizontal = true;
            	editor.setEditor(text, items[i], j);
        	}else if(i==0 && j==5)
        	{
            	text.setText("描述");
            	editor.grabHorizontal = true;
            	editor.setEditor(text, items[i], j);
        	}
            editor = new TableEditor(table);
        	Button button = new Button(table, SWT.CHECK);
        	button.pack();
        	editor.minimumWidth = button.getSize().x;
        	editor.grabHorizontal = true;
        	editor.horizontalAlignment = SWT.CENTER;
        	if(i==1 && j==1)
        	{
        		editor.setEditor(button, items[i], j);		
        	}else if(i==1 && j==2) {
        		editor.setEditor(button, items[i], j);
        	}
        	else
        	{
        		editor.setEditor(text, items[i], j);;
        	}
        }
    }
        
       //viewForm.setContent(top);
       /* 
        for (int i = 0; i < tableHeader.length; i++)  
        {  
            TableColumn tableColumn = new TableColumn(table, SWT.NONE);  
            tableColumn.setText(tableHeader[i]);  
            // 设置表头可移动，默认为false  
            tableColumn.setMoveable(true);  
        }  
        
        // 重新布局表格  
        for (int i = 0; i < tableHeader.length; i++)  
        {  
            table.getColumn(i).pack();  
        }
        */
       //parent.pack();
	   // shell.layout();
       /*
		while (!parent.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
			display.dispose();
		}
		*/
	    /*
		
		  	TabFolder tabFolder = new TabFolder(shell,SWT.BORDER);
	        
	        TabItem tabItem1 = new TabItem(tabFolder,SWT.NONE);
	      
	        
	        Composite compsoite1 = new Composite(tabFolder,SWT.NONE);
	        tabItem1.setControl(compsoite1);
	        
	        GridLayout layout = new GridLayout();
	        layout.numColumns = 1;
	        compsoite1.setLayout(layout);
	        Group treeGroup = new Group(compsoite1,SWT.NONE);
	        GridData griddata = new GridData(GridData.FILL_BOTH);
	        griddata.heightHint = 50;
	        treeGroup.setLayoutData(griddata);
	        treeGroup.setLayout(new GridLayout(1,false));
	        {
	        	Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    	
	        	lab_name = new Label(top, SWT.NONE);
	        	lab_name.setText("名称:");
	        	lab_name.setFont(boldFont);
	        	lab_name.setBounds(20, 40, 100, 60);
	    		//l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
	    		
	        	txt_name = new Text(top, SWT.BORDER);
	        	txt_name.setFont(new Font(display,"宋体",16,SWT.NORMAL));
	    		txt_name.setBounds(120, 40, 500, 26);
	    		
	    		
	    		lab_describe = new Label(top, SWT.NONE);
	    		lab_describe.setText("描述:");
	    		lab_describe.setFont(boldFont);
	    		lab_describe.setBounds(20, 100, 100, 60);
	    		
	    		txt_describe = new Text(top, SWT.BORDER|SWT.WRAP|SWT.V_SCROLL|SWT.MULTI);
	    		txt_describe.setFont(new Font(display,"宋体",16,SWT.NORMAL));
	    		txt_describe.setBounds(120, 100, 500, 26);

	        }
	        Group tableGroup = new Group(compsoite1,SWT.NONE);
	        GridData gd = new GridData(GridData.FILL_BOTH);
	        gd.heightHint = 20;
	        tableGroup.setLayoutData(gd);
	        tableGroup.setLayout(new GridLayout(1,false));
	        {    //创建一个单选的，有边界的，一行全选的表格
	            table = new Table(tableGroup,SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
	            table.setHeaderVisible(true);//设置表头可见
	            table.setLinesVisible(true);//设置线条可见
	            table.setLayoutData(new GridData(GridData.FILL_BOTH));
	            
	            TableColumn column1 = new TableColumn(table,SWT.NULL);
	            column1.setText("名称");
	            column1.pack();
	            column1.setWidth(150);
	            
	            TableColumn column2 = new TableColumn(table,SWT.NULL);
	            column2.setText("Isintital");
	            column2.pack();
	            column2.setWidth(150);   
	            
	            TableColumn column3 = new TableColumn(table,SWT.NULL);
	            column3.setText("Isfinal");
	            column3.pack();
	            column3.setWidth(150);  
	            
	            TableColumn column4 = new TableColumn(table,SWT.NULL);
	            column4.setText("Numberical Value");
	            column4.pack();
	            column4.setWidth(150);  
	            
	            TableColumn column5 = new TableColumn(table,SWT.NULL);
	            column5.setText("Source");
	            column5.pack();
	            column5.setWidth(150);  
	            
	            TableColumn column6 = new TableColumn(table,SWT.NULL);
	            column6.setText("描述");
	            column6.pack();
	            column6.setWidth(150);  
	        }
	        shell.open();
	        
	        while(!shell.isDisposed()){
	            if(!display.readAndDispatch())
	                display.sleep();
	        }
	        //dispose the resource
	        display.beep();
	        display.dispose();

*/		
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