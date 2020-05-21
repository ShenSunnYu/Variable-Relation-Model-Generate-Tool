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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.nuaa.vrm.handler.ConsoleHandler;

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

	private static String  output_string1=null;
	private static String  output_string2=null;
	private static String  output_string3=null;
//	public static String requirementID = "R1.1";
//	private Label requireID ;
	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		
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
		lab_source.setText("来源:");
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

		
		
		Button b1=new Button(top,SWT.PUSH);
		b1.setBounds(70, 620, 100, 26);
		b1.setText("确定");
		b1.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) {
				output_string1 = txt_name.getText();
				output_string2 = txt_source.getText();		
				output_string3 = txt_describe.getText();
				
				// DocumentHelper提供了创建Document对象的方法
				Document document = DocumentHelper.createDocument();
				// 添加节点信息
				Element rootElement = document.addElement("VRM");
				// 这里可以继续添加子节点，也可以指定内容
				rootElement.setText("这个是专有名词的文本信息");
				Element element = rootElement.addElement("view name");
				element.addAttribute("view name", "专有名词");

				Element nameElement = element.addElement("name");
				Element sourceElement = element.addElement("source");
				Element describeElement = element.addElement("describe");
				nameElement.addAttribute("name",output_string1);//为节点添加属性值
				sourceElement.addAttribute("source", output_string2);
				describeElement.addAttribute("describe", output_string3);
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
					File file = new File("C:\\Users\\10182\\Desktop\\properNoun.xml");
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
		b2.setBounds(230, 620, 100, 26);
		b2.setText("取消");
		b2.addMouseListener(new MouseAdapter()
		{
			public void mouseDown(MouseEvent e) {
				txt_name.setText("");
				txt_source.setText("");
				txt_describe.setText("");
				ConsoleHandler.info("输入已成功删除！");
			}
		});
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
