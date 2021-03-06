package com.nuaa.vrm.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

import com.nuaa.vrm.handler.ConsoleHandler;

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

	private static String  output_string1=null;
	private static String  output_string2=null;
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
	
		
        TableItem[] items = table.getItems();
        final TableEditor editor = new TableEditor(table);
		Text text = new Text(table, SWT.CENTER);
        for (int i = 0; i < items.length; i++) {
        	for(int j=0;j<6;j++)
        	{
        		
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
        	TableEditor editor1 = new TableEditor(table);
        		Button button = new Button(table, SWT.CHECK);
	        	button.pack();
	        	editor1.minimumWidth = button.getSize().x;
        		editor1.grabHorizontal = true;
        		editor1.horizontalAlignment = SWT.CENTER;
        	
        		if(i==1 && j==1)
        		{
        			editor1.setEditor(button, items[i], j);		
        		}else if(i==1 && j==2) {
        			editor1.setEditor(button, items[i], j);
        		}
        		else
        		{	
        			editor.setEditor(text, items[i], j);;
        		}
        		
        		if(j==0) {
        			if(i == items.length-1) {
        				text.addKeyListener(new KeyAdapter(){
        					public void keyPressed(KeyEvent e) {   
        						for (int i =  items.length; i < items.length+1; i++) {
        				        	for(int j=0;j<6;j++)
        				        	{
        				        		if(j==1)
        				        		{
        				        			editor1.setEditor(button, items[items.length], 1);		
        				        		}else if(j==2) {
        				        			editor1.setEditor(button, items[items.length], 2);
        				        		}
        				        		else
        				        		{	
        				        			editor.setEditor(text, items[items.length], j);;
        								}
        				        	}
        						}
        				}
        			});
        		}
        		
        	}
        }
        }
        
        
        Button b1=new Button(top,SWT.PUSH);
		b1.setBounds(70, 830, 100, 26);
		b1.setText("确定");
		b1.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) {
				output_string1 = txt_name.getText();		
				output_string2 = txt_describe.getText();
				
				// DocumentHelper提供了创建Document对象的方法
				Document document = DocumentHelper.createDocument();
				// 添加节点信息
				Element rootElement = document.addElement("VRM");
				// 这里可以继续添加子节点，也可以指定内容
				rootElement.setText("这个是模式类的文本信息");
				Element element = rootElement.addElement("view name");
				element.addAttribute("view name", "模式类");

				Element nameElement = element.addElement("name");
				Element describeElement = element.addElement("describe");
				nameElement.addAttribute("name",output_string1);//为节点添加属性值
				describeElement.addAttribute("describe", output_string2);
				// 设置XML文档格式
				// OutputFormat outputFormat = OutputFormat.createPrettyPrint();//默认样式

				// 自定义xml样式
				OutputFormat outputFormat = new OutputFormat();
				// 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1  
				outputFormat.setEncoding("UTF-8");
				// outputFormat.setSuppressDeclaration(true); //是否生产xml头
				outputFormat.setIndent(true); // 设置是否缩进
				outputFormat.setIndentSize(2); // 以2个空格方式实现缩进
				outputFormat.setNewlines(true); // 设置是否换行，一个结点为一行
				outputFormat.setTrimText(true); // 去重空格

				try {
					// Writer fileWriter = new
					// FileWriter("C:\\Users\\10182\\Desktop\\dataType.xml");
					File file = new File("C:\\Users\\10182\\Desktop\\pattern.xml");
					// dom4j提供了专门写入文件的对象XMLWriter
					XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file), outputFormat);
					xmlWriter.write(document);
					xmlWriter.flush();
					xmlWriter.close();
					ConsoleHandler.info("xml文档已保存至桌面！");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		Button b2=new Button(top,SWT.PUSH);
		b2.setBounds(230, 830, 100, 26);
		b2.setText("取消");
		b2.addMouseListener(new MouseAdapter()
		{
			public void mouseDown(MouseEvent e) {
				txt_name.setText("");
				txt_describe.setText("");
				ConsoleHandler.info("输入已成功删除！");
			}
		});
		
		
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