package com.nuaa.vrm.view;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;


/**
 * @PackageName:com.nuaa.vrm.view
 * @ClassName:RequirementSpecificationContentView
 * @Description:关于需求规范化的视图界面
 * @Author:展万里
 * @Date:2020/5/7 16:39
 */

public class RequirementSpecificationContentView extends ViewPart implements ISelectionListener{

	public static final String ID = "Variable-Relation-Model-Generate-Tool.RequirementSpecificationContentView";

	private static Label lab_requirement_id;//需求ID
	private static Label lab_original_requirement;//原始需求内容
	private static Label lab_standardized_requirements;//规范化需求
	
	private static Text txt_original_requirement_content;//原始需求内容
	private static Text txt_original_requirement_id;//原始需求ID
	
	private static Button btn_common_condition;//通用条件
	private static Button btn_common_event;//通用事件
	private static Button btn_dominant_condition;//显示条件
	private static Button btn_dominant_event;//显示事件
	
	private static Grid grid;
	private static GridColumnGroup btn_column_group;
	private static GridColumn ID_column;
	private static GridColumn content_column;
	private static GridColumn edit_column;
	private static GridColumn select_column;
	/**
	 * The text control that's displaying the content of the email message.
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		Display display = Display.getDefault();
		Font txt_font = new Font(display,"微软雅黑",12,SWT.NORMAL);
		Font txt_font_lab = new Font(display,"微软雅黑",12,SWT.NORMAL);
		Font lab_font = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
		lab_requirement_id = new Label(top, SWT.NONE);
		lab_requirement_id.setText("需求ID:");
		lab_requirement_id.setFont(lab_font);
		lab_requirement_id.setBounds(60, 40, 100, 30);
		
		txt_original_requirement_id = new Text(top, SWT.BORDER);
		txt_original_requirement_id.setFont(txt_font);
		txt_original_requirement_id.setEnabled(false);
		txt_original_requirement_id.setBounds(160, 40, 500, 30);
		txt_original_requirement_id.setText("ID");
		
		lab_original_requirement= new Label(top, SWT.NONE);
		lab_original_requirement.setText("原始需求内容:");
		lab_original_requirement.setFont(lab_font);
		lab_original_requirement.setBounds(60, 100, 100, 30);
		
		txt_original_requirement_content = new Text(top,SWT.BORDER|SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
		txt_original_requirement_content.setFont(txt_font);
		txt_original_requirement_content.setBounds(160, 100, 500, 300);
		
		lab_standardized_requirements = new Label(top, SWT.NONE);
		lab_standardized_requirements.setText("规范化需求:");
		lab_standardized_requirements.setFont(lab_font);
		lab_standardized_requirements.setBounds(60, 430, 100, 20);
		
		btn_common_condition = new Button(top, SWT.CENTER);
		btn_common_condition.setBounds(160,430,100,50);
		btn_common_condition.setText("通用条件");
		btn_common_condition.setFont(txt_font_lab);
		
		btn_common_event = new Button(top, SWT.CENTER);
		btn_common_event.setBounds(293,430,100,50);
		btn_common_event.setText("通用事件");
		btn_common_event.setFont(txt_font_lab);
		
		btn_dominant_condition = new Button(top, SWT.CENTER);
		btn_dominant_condition.setBounds(426,430,100,50);
		btn_dominant_condition.setText("显示条件");
		btn_dominant_condition.setFont(txt_font_lab);
		
		btn_dominant_event = new Button(top, SWT.CENTER);
		btn_dominant_event.setBounds(560,430,100,50);
		btn_dominant_event.setText("显示事件");
		btn_dominant_event.setFont(txt_font_lab);
		
		grid = new Grid(top,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		grid.setBounds(160, 490, 500, 300);
		grid.setHeaderVisible(true);
		grid.setAutoHeight(true);
		grid.setAutoWidth(true);
		
		ID_column=new GridColumn(grid, SWT.CENTER);
		ID_column.setText("需求ID");
		ID_column.setWidth(100);
		
		content_column = new GridColumn(grid, SWT.CENTER);
		content_column.setText("规范化内容");
		content_column.setWidth(300);
		
		btn_column_group = new GridColumnGroup(grid, SWT.CENTER);
		btn_column_group.setText("选项");
		
		edit_column = new GridColumn(btn_column_group, SWT.CENTER);
		edit_column.setText("编辑");
		edit_column.setWidth(50);
		select_column = new GridColumn(btn_column_group, SWT.CENTER);
		select_column.setText("查看");
		select_column.setWidth(50);
		
		GridItem item = new GridItem(grid, SWT.None);
		item.setText("123");
	
		btn_common_condition.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				System.out.println("通用条件");
				NormalizationView.commonCondition();
			}
		});
		
		btn_common_event.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				System.out.println("通用事件");
				NormalizationView.commonEvent();
				
			}
		});
		
		btn_dominant_condition.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				System.out.println("显示条件");
				NormalizationView.dominantCondition();
			}
		});
		
		
		btn_dominant_event.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				System.out.println("显示事件");
				NormalizationView.dominantEvent();
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
	public void setFocus() {
		return;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		if(selection!= null){
			IStructuredSelection is = (IStructuredSelection)selection;
			System.out.println(is.toString());
//			String id = is.toString().substring(8,is.toString().indexOf(']'));
		}
	}
}
