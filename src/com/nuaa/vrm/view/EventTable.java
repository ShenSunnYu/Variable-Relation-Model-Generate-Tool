package com.nuaa.vrm.view;

import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.nebula.widgets.grid.GridEditor;
import org.eclipse.nebula.widgets.grid.GridItem;
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
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @PackageName:vrm_generate_tool
 * @ClassName:Condition_Event_Table
 * @Description:关于事件的表格类
 * @Author:展万里
 * @Date:2020/5/7 16:39
 */

public class EventTable {
	private static Display display;
	private static Shell shell_event;
	private static Composite comp1;
	private static Composite comp2;
	private static GridLayout gl;
	private static Button btn_add;
	private static Button btn_del_row;
	private static Button btn_sub;
	private static Button btn_cancel;

	private static Combo combo;
	private static Text text;
	private static int i = 0;
	
	private static Grid grid;
	private static GridColumn id_column;
	private static GridColumn event_column;
	private static GridColumn condition_one_column;
	private static GridColumn guard_column;
	private static GridColumn condition_two_column;
	private static GridItem item;
	private static GridEditor editor;
	

	public static void init() {
		display = Display.getDefault();
		shell_event = new Shell(display,SWT.SHELL_TRIM |SWT.ON_TOP|SWT.SYSTEM_MODAL );
		shell_event.setText("事件");
		i = 0;
        gl = new GridLayout(6,false);
		gl.marginBottom = 10;
		gl.marginTop = 10;
		gl.marginLeft = 10;
		gl.marginRight = 10;
		gl.verticalSpacing = 10;
		gl.horizontalSpacing = 10;
		shell_event.setLayout(gl);
		shell_event.setSize(850, 400);
        
		comp1 = new Composite(shell_event, SWT.LEFT_TO_RIGHT);
		comp1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,4,1));
		comp1.setLayout(gl);
		comp2 = new Composite(shell_event, SWT.RIGHT_TO_LEFT);
		comp2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,2,1));
		comp2.setLayout(gl);
		
		
		grid = new Grid(shell_event,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		grid.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,6,1));
		grid.setHeaderVisible(true);
		grid.setAutoHeight(true);
		grid.setAutoWidth(true);
		
		id_column = new GridColumn(grid, SWT.None);
		id_column.setText("ID");
		id_column.setWidth(50);
		
		event_column = new GridColumn(grid, SWT.None);
		event_column.setText("事件");
		event_column.setWidth(200);
		
		condition_one_column = new GridColumn(grid, SWT.None);
		condition_one_column.setText("条件");
		condition_one_column.setWidth(200);
		
		guard_column = new GridColumn(grid, SWT.None);
		guard_column.setText("守卫");
		guard_column.setWidth(200);
		
		condition_two_column = new GridColumn(grid, SWT.None);
		condition_two_column.setText("条件");
		condition_two_column.setWidth(200);
		
		btn_add = new Button(comp1,SWT.NONE);
		btn_add.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_add.setText("添加'与'事件行");
		
		btn_del_row = new Button(comp1,SWT.NONE);
		btn_del_row.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_del_row.setText("删除已选择的'与'事件行");
		
		btn_cancel = new Button(comp2,SWT.NONE);
		btn_cancel.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_cancel.setText("取消");
		
		btn_sub = new Button(comp2,SWT.NONE);
		btn_sub.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_sub.setText("确认");
		shell_event.open();
	}

	public static void getEventTable() {
		init();
        btn_add.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				item = new GridItem(grid,SWT.NONE);
				item.setText(String.valueOf(++i));
        			editor = new GridEditor(grid);
                	combo = new Combo(grid, SWT.NONE);
                	combo.add("@T");
                	combo.add("@F");
                	combo.add("@C");
                	combo.select(0);
                	editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(combo, item,1);	
                	combo.setFont(new Font(display,"宋体",12,SWT.NORMAL));
                	
                	editor = new GridEditor(grid);
        			text = new Text(grid, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
        			text.setFont(new Font(display,"宋体",12,SWT.NORMAL));
        			text.addListener(SWT.MouseDoubleClick, new Listener() {
						@Override
						public void handleEvent(Event event) {
							// TODO Auto-generated method stub
							ConditionTable.getConditionTable();
						}
					});
        			editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(text, item, 2);	

                	editor = new GridEditor(grid);
        			combo = new Combo(grid, SWT.NONE);
                	combo.setFont(new Font(display,"宋体",12,SWT.NORMAL));
                	combo.add("_");
                	combo.add("WHERE");
                	combo.add("WHEN");
                	combo.add("WHILE");
                	combo.select(0);
                	editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(combo, item, 3);	

                	editor = new GridEditor(grid);
        			text = new Text(grid, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
        			text.setFont(new Font(display,"宋体",12,SWT.NORMAL));
        			text.addListener(SWT.MouseDoubleClick, new Listener() {
						
						@Override
						public void handleEvent(Event event) {
							// TODO Auto-generated method stub
							ConditionTable.getConditionTable();
						}
					});
        			editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(text, item, 4);	
				}
			
		});
//        
//        btn_del_row.addListener(SWT.MouseDown, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				// TODO Auto-generated method stub
//				TableItem[] items = table.getSelection();
//				for(Item tItem : items) {
//					tItem.dispose();
//				}
//				editor.layout();
//			}
//		});
//        
//        btn_cancel.addListener(SWT.MouseDown, new Listener() {
//			
//			@Override
//			public void handleEvent(Event event) {
//				// TODO Auto-generated method stub
//				shell_event.dispose();
//				
//			}
//		});
	}
}
