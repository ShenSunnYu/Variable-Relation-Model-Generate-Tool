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
	
	private static Label lab_standardized_requirements;//�淶������
	
	private static Combo co_subject;//����������
	private static Combo co_predicate;//ν��������
	private static Combo co_object;//����������
	
	private static Text txt_conditiion_event;//����/�¼�����
	private static Text txt_can;//Ӧ���ܹ�
	private static Text txt_satisfy;//��������/�¼�
	private static Text txt_subject;//��������
	private static Text txt_predicate;//ν������
	private static Text txt_object;//��������
	
	private static Button btn_conditiion_event;
	private static Button btn_submit;

	public static void init() {
		display = Display.getDefault();
		shell = new Shell(display,SWT.DIALOG_TRIM|SWT.ON_TOP|SWT.APPLICATION_MODAL);
		shell.setSize(720, 450);
		shell.open();
		
		Font txt_font = new Font(display,"΢���ź�",12,SWT.NORMAL);
		Font txt_font_lab1 = new Font(display,"΢���ź�",10,SWT.NORMAL);
		Font txt_font_lab2 = new Font(display,"����",10,SWT.NORMAL);
		Font lab_font = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
		
		lab_standardized_requirements = new Label(shell, SWT.NONE);
		lab_standardized_requirements.setText("�淶������:");
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
		co_subject.add("����1");
		co_subject.add("����2");
		co_subject.add("����3");
		co_subject.select(0);
		co_subject.setBounds(160, 180, 340, 30);
		co_subject.setFont(txt_font_lab1);
		
		txt_subject = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_subject.setFont(txt_font_lab2);
		txt_subject.setBounds(510, 180, 150, 30);
		
		txt_can = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_can.setText("Ӧ���ܹ�");
		txt_can.setFont(txt_font_lab1);
		txt_can.setBounds(160, 220, 120, 30);
		
		co_predicate = new Combo(shell, SWT.BORDER|SWT.READ_ONLY);
		co_predicate.setText("ν��");
		co_predicate.add("ν��1");
		co_predicate.add("ν��2");
		co_predicate.add("ν��3");
		co_predicate.select(0);
		co_predicate.setBounds(160, 260, 340, 30);
		co_predicate.setFont(txt_font_lab1);
		
		
		txt_predicate = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_predicate.setText("<����>");
		txt_predicate.setFont(txt_font_lab2);
		txt_predicate.setBounds(510, 260, 150, 30);
		
		
		co_object = new Combo(shell, SWT.BORDER|SWT.READ_ONLY);
		co_object.setText("����");
		co_object.add("����1");
		co_object.add("����2");
		co_object.add("����3");
		co_object.select(0);
		co_object.setBounds(160, 300, 340, 30);
		co_object.setFont(txt_font_lab1);
		
		txt_object = new Text(shell, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);

		txt_object.setFont(txt_font_lab2);
		txt_object.setBounds(510, 300, 150, 30);
		
		btn_submit = new Button(shell, SWT.CENTER);
		btn_submit.setBounds(560,340,100,50);
		btn_submit.setText("ȷ��");
		btn_submit.setFont(txt_font_lab1);
		
	}

	public static void commonCondition() {
		init();
		
		txt_subject.setText("<�ɻ�/ϵͳ/�豸>");
		txt_object.setText("<����>");
		
		txt_satisfy.setText("����������:");
		txt_conditiion_event.setText("��������");
		
		btn_conditiion_event.setText("�����༭");
		btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				ConditionTable.getConditionTable();
				System.out.println("������ť������");
			}
		});
		
		
		shell.setText("�淶�����󡪡�ͨ������");
	}
	
	public static void commonEvent() {
		init();
		
		
		txt_subject.setText("<�ɻ�/ϵͳ/�豸>");
		txt_object.setText("<����>");
		
		
		txt_satisfy.setText("�������¼�:");
		txt_conditiion_event.setText("�¼�����");
		
		btn_conditiion_event.setText("�¼��༭");
		btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				EventTable.getEventTable();
				System.out.println("�¼���ť������");
			}

		});
		
		shell.setText("�淶�����󡪡�ͨ���¼�");
	}
	
	public static void dominantCondition() {
		init();
		txt_subject.setText("<����>");
		txt_object.setText("<��׼/��ʽ/Ҫ��>");
		
		txt_satisfy.setText("����������:");
		txt_conditiion_event.setText("��������");
		
		btn_conditiion_event.setText("�����༭");
		btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				ConditionTable.getConditionTable();
				System.out.println("������ť������");
			}
		});
		
		
		shell.setText("�淶�����󡪡���ʾ����");
	}
	
	public static void dominantEvent() {
		init();
		txt_subject.setText("<����>");
		txt_object.setText("<��׼/��ʽ/Ҫ��>");
		
		txt_satisfy.setText("�������¼�:");
		txt_conditiion_event.setText("�¼�����");
		
		btn_conditiion_event.setText("�¼��༭");
		btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				EventTable.getEventTable();
				System.out.println("�¼���ť������");
			}

		});
		shell.setText("�淶�����󡪡���ʾ�¼�");
	}
}
