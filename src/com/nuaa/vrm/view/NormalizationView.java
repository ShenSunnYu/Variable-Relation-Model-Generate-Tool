package com.nuaa.vrm.view;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class NormalizationView {
	private static Display display;
	private static Shell shell;
	
	private static Label lab_standardized_requirements;//规范化需求
	
	private static Combo co_subject;//主语下拉框
	private static Combo co_predicate;//谓语下拉框
	private static Combo co_object;//宾语下拉框
	
	private static Text txt_conditiion_event;//条件/事件内容
	private static Text txt_can;//应该能够
	private static Text txt_satisfy;//满足条件/事件
	private static Text txt_subject;//主语内容
	private static Text txt_predicate;//谓语内容
	private static Text txt_object;//宾语内容
	
	private static Button btn_conditiion_event;
	private static Button btn_submit;

	public static void init() {
		display = Display.getDefault();
		shell = new Shell(display,SWT.DIALOG_TRIM|SWT.ON_TOP|SWT.APPLICATION_MODAL);
		shell.setSize(720, 450);
		shell.open();
		
		Font txt_font = new Font(display,"微软雅黑",12,SWT.NORMAL);
		Font txt_font_lab1 = new Font(display,"微软雅黑",10,SWT.NORMAL);
		Font txt_font_lab2 = new Font(display,"宋体",10,SWT.NORMAL);
		Font lab_font = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
		
		lab_standardized_requirements = new Label(shell, SWT.NONE);
		lab_standardized_requirements.setText("规范化需求:");
		lab_standardized_requirements.setFont(lab_font);
		lab_standardized_requirements.setBounds(60, 30, 100, 30);
		
		txt_satisfy = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_satisfy.setFont(txt_font_lab1);
		txt_satisfy.setBounds(160, 30, 120, 30);
		
		btn_conditiion_event = new Button(shell, SWT.CENTER);
		btn_conditiion_event.setFont(txt_font_lab1);
		btn_conditiion_event.setBounds(290,30,100,30);
		
		txt_conditiion_event = new Text(shell, SWT.BORDER|SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
		txt_conditiion_event.setFont(txt_font);
		txt_conditiion_event.setBounds(160, 70, 500, 100);

		co_subject = new Combo(shell, SWT.BORDER);
		co_subject.add("主语1");
		co_subject.add("主语2");
		co_subject.add("主语3");
		co_subject.select(0);
		co_subject.setBounds(160, 180, 340, 30);
		co_subject.setFont(txt_font_lab1);
		
		txt_subject = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_subject.setFont(txt_font_lab2);
		txt_subject.setBounds(510, 180, 150, 30);
		
		txt_can = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_can.setText("应该能够");
		txt_can.setFont(txt_font_lab1);
		txt_can.setBounds(160, 220, 120, 30);
		
		co_predicate = new Combo(shell, SWT.BORDER|SWT.READ_ONLY);
		co_predicate.setText("谓语");
		co_predicate.add("谓语1");
		co_predicate.add("谓语2");
		co_predicate.add("谓语3");
		co_predicate.select(0);
		co_predicate.setBounds(160, 260, 340, 30);
		co_predicate.setFont(txt_font_lab1);
		
		
		txt_predicate = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_predicate.setText("<功能>");
		txt_predicate.setFont(txt_font_lab2);
		txt_predicate.setBounds(510, 260, 150, 30);
		
		
		co_object = new Combo(shell, SWT.BORDER|SWT.READ_ONLY);
		co_object.setText("宾语");
		co_object.add("宾语1");
		co_object.add("宾语2");
		co_object.add("宾语3");
		co_object.select(0);
		co_object.setBounds(160, 300, 340, 30);
		co_object.setFont(txt_font_lab1);
		
		txt_object = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);

		txt_object.setFont(txt_font_lab2);
		txt_object.setBounds(510, 300, 150, 30);
		
		btn_submit = new Button(shell, SWT.CENTER);
		btn_submit.setBounds(560,340,100,50);
		btn_submit.setText("确定");
		btn_submit.setFont(txt_font_lab1);
		
	}

	public static void commonCondition() {
		init();
		
		txt_subject.setText("<飞机/系统/设备>");
		txt_object.setText("<对象>");
		
		txt_satisfy.setText("当满足条件:");
		txt_conditiion_event.setText("条件内容");
		
		btn_conditiion_event.setText("条件编辑");
		btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				ConditionTable.getConditionTable();
				System.out.println("条件按钮已启动");
			}
		});
		
		
		shell.setText("规范化需求——通用条件");
	}
	
	public static void commonEvent() {
		init();
		
		
		txt_subject.setText("<飞机/系统/设备>");
		txt_object.setText("<对象>");
		
		
		txt_satisfy.setText("当发生事件:");
		txt_conditiion_event.setText("事件内容");
		
		btn_conditiion_event.setText("事件编辑");
		btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				EventTable.getEventTable();
				System.out.println("事件按钮已启动");
			}

		});
		
		shell.setText("规范化需求——通用事件");
	}
	
	public static void dominantCondition() {
		init();
		txt_subject.setText("<对象>");
		txt_object.setText("<标准/格式/要求>");
		
		txt_satisfy.setText("当满足条件:");
		txt_conditiion_event.setText("条件内容");
		
		btn_conditiion_event.setText("条件编辑");
		btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				ConditionTable.getConditionTable();
				System.out.println("条件按钮已启动");
			}
		});
		
		
		shell.setText("规范化需求——显示条件");
	}
	
	public static void dominantEvent() {
		init();
		txt_subject.setText("<对象>");
		txt_object.setText("<标准/格式/要求>");
		
		txt_satisfy.setText("当发生事件:");
		txt_conditiion_event.setText("事件内容");
		
		btn_conditiion_event.setText("事件编辑");
		btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				EventTable.getEventTable();
				System.out.println("事件按钮已启动");
			}

		});
		shell.setText("规范化需求——显示事件");
	}
}
