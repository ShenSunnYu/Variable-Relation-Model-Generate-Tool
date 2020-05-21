package com.nuaa.vrm.view;


import java.util.ArrayList;

import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.nebula.widgets.grid.GridEditor;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * @PackageName:com.nuaa.vrm.view
 * @ClassName:Condition_Event_Table
 * @Description:关于条件的表格类
 * @Author:展万里
 * @Date:2020/5/7 16:39
 */

public class ConditionTable {
	private static Display display;
	private static Shell shell_condition;
	private static Composite comp1;
	private static Composite comp2;
	private static GridLayout gl;
	private static Button btn_or;
	private static Button btn_add;
	private static Button btn_del_row;
	private static Button btn_del_col;
	private static Button btn_sub;
	private static Button btn_cancel;
	private static Button btn_check;
	
	private static int i = 0;
	private static Grid grid;
	private static GridColumnGroup condition_columnGroup;
	private static GridColumnGroup or_columnGroup;
	private static GridColumn id_column;
	private static GridColumn gc1;
	private static GridColumn gc2;
	private static GridColumn gc3;
	private static GridColumn or_column;
	private static GridItem item;
	private static GridEditor editor;

	private static ArrayList<GridEditor> editors = new ArrayList<GridEditor>();
	
	public static void init() {
		display = Display.getDefault();
		shell_condition = new Shell(display,SWT.SHELL_TRIM |SWT.ON_TOP|SWT.SYSTEM_MODAL );
		i = 0;
        gl = new GridLayout(6,false);
		gl.marginBottom = 10;
		gl.marginTop = 10;
		gl.marginLeft = 10;
		gl.marginRight = 10;
		gl.verticalSpacing = 10;
		gl.horizontalSpacing = 10;
		
		shell_condition.setLayout(gl);
		shell_condition.setSize(750, 400);

        
		comp1 = new Composite(shell_condition, SWT.LEFT_TO_RIGHT);
		comp1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,4,1));
		comp1.setLayout(gl);
		
		comp2 = new Composite(shell_condition, SWT.RIGHT_TO_LEFT);
		comp2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,2,1));
		comp2.setLayout(gl);
        
        grid = new Grid(shell_condition,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        grid.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,6,1));
		grid.setHeaderVisible(true);
		grid.setLinesVisible(true);
		grid.setAutoHeight(true);
		grid.setAutoWidth(true);
		
		id_column = new GridColumn(grid, SWT.None);
		id_column.setText("ID");
		id_column.setWidth(50);
		
		condition_columnGroup = new GridColumnGroup(grid, SWT.None);
		condition_columnGroup.setText("条件");
		
		gc1 = new GridColumn(condition_columnGroup, SWT.NONE);
		gc1.setText("变量");
		gc1.setWidth(100);
		
		gc2 = new GridColumn(condition_columnGroup, SWT.NONE);
		gc2.setText("逻辑");
		gc2.setWidth(100);
		
		gc3 = new GridColumn(condition_columnGroup, SWT.NONE);
		gc3.setText("值");
		gc3.setWidth(100);
		
		or_columnGroup = new GridColumnGroup(grid, SWT.None);
		or_columnGroup.setText("OR");
		
		or_column = new GridColumn(or_columnGroup, SWT.NONE);
		or_column.setText("or");
		or_column.setWidth(30);
			
		btn_add = new Button(comp1,SWT.NONE);
		btn_add.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_add.setText("添加AND行");
		
		btn_or = new Button(comp1,SWT.NONE);
		btn_or.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_or.setText("添加OR列");
		
		btn_del_col = new Button(comp1,SWT.NONE);
		btn_del_col.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_del_col.setText("删除已选择的OR列");
		
		btn_del_row = new Button(comp1,SWT.NONE);
		btn_del_row.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_del_row.setText("删除已选择的AND行");
		
		btn_cancel = new Button(comp2,SWT.NONE);
		btn_cancel.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_cancel.setText("取消");
		
		btn_sub = new Button(comp2,SWT.NONE);
		btn_sub.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_sub.setText("确认");

        shell_condition.open();
	}

	public static void getConditionTable() {
		init();
		System.out.println("init结束");
		shell_condition.setText("条件");
        //添加监听事件
        btn_or.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				or_column = new GridColumn(or_columnGroup, SWT.CENTER);
				or_column.setText("or");
				or_column.setWidth(30);
				GridItem[] items = grid.getItems();
				for(int i = 0;i<items.length;i++) {
					for(int j = 4; j<grid.getColumnCount();j++) {
						editor = new GridEditor(grid);
						btn_check = new Button(grid, SWT.CHECK);
				    	btn_check.pack();
						editor.grabHorizontal = true;
				    	editor.horizontalAlignment = SWT.CENTER;
				    	editor.setEditor(btn_check, items[i],j);
				    	editors.add(editor);
					}
				}
			}
		});
  	
        btn_add.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				item = new GridItem(grid, SWT.CENTER);
				item.setText(String.valueOf(++i));
				editor = new GridEditor(grid);
				
				Combo co_1 = new Combo(grid, SWT.BORDER);
				co_1.add("V1");
				co_1.add("V2");
				co_1.add("V3");
				co_1.select(0);
				
				editor.grabHorizontal = true;
				editor.setEditor(co_1, item, 1);
		    	editors.add(editor);
				editor = new GridEditor(grid);
				Combo co_2 = new Combo(grid, SWT.BORDER);
				co_2.add(">");
				co_2.add("=");
				co_2.add("<");
				co_2.select(0);
				editor.grabHorizontal = true;
				editor.setEditor(co_2, item, 2);
		    	editors.add(editor);
				editor = new GridEditor(grid);
				Combo co_3 = new Combo(grid, SWT.BORDER);
				co_3.add("1");
				co_3.add("2");
				co_3.add("3");
				co_3.select(0);
				editor.grabHorizontal = true;
				editor.setEditor(co_3, item, 3);
		    	editors.add(editor);
		    	
				editor = new GridEditor(grid);
				editor.grabHorizontal = true;
				btn_check = new Button(grid, SWT.CHECK);
		    	btn_check.pack();
				editor.setEditor(btn_check, item, 4);
		    	editors.add(editor);
		    	
		    	
		        int column = grid.getColumnCount();
		        while(column>=4) {
					editor = new GridEditor(grid);
					editor.grabHorizontal = true;
					btn_check = new Button(grid, SWT.CHECK);
			    	btn_check.pack();
			    	editor.setEditor(btn_check, item, --column);
			    	editors.add(editor);
		        }
			}
		});
//        
        btn_del_row.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				GridItem[] items = grid.getSelection();
				for(Item tItem : items) {
					tItem.dispose();
				}
				for(GridEditor gridEditor : editors) {
					gridEditor.layout();
				}
			}
		});
        
        btn_sub.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				System.out.println("提交条件");
			}
		});
       btn_cancel.addListener(SWT.MouseDown, new Listener() {
		
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			shell_condition.dispose();
		}
	});
	}
}
