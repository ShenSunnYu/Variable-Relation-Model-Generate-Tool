package vrm_generate_tool;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
 * @Description:关于条件的表格类
 * @Author:展万里
 * @Date:2020/5/7 16:39
 */

public class ConditionTable {
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
	private static Table table;
	private static Button btn_check;
	private static TableEditor editor;
	private static TableItem item;
	private static TableColumn tableColumn;
	private static int i = 0;
	public static void init(Shell shell) {
		i = 0;
        gl = new GridLayout(6,false);
		gl.marginBottom = 10;
		gl.marginTop = 10;
		gl.marginLeft = 10;
		gl.marginRight = 10;
		gl.verticalSpacing = 10;
		gl.horizontalSpacing = 10;
		shell.setLayout(gl);
        shell.setSize(750, 400);

		table = new Table(shell,SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.BORDER);
		table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,6,1));
		table.setHeaderVisible(true);//设置表格头可见
		table.setLinesVisible(true);//设置表格线可见
		
		comp1 = new Composite(shell, SWT.LEFT_TO_RIGHT);
		comp1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,4,1));
		comp1.setLayout(gl);
		comp2 = new Composite(shell, SWT.RIGHT_TO_LEFT);
		comp2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,2,1));
		comp2.setLayout(gl);
		
		btn_add = new Button(comp1,SWT.NONE);
		btn_add.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));

		btn_del_row = new Button(comp1,SWT.NONE);
		btn_del_row.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));

		
		btn_cancel = new Button(comp2,SWT.NONE);
		btn_cancel.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_cancel.setText("取消");
		
		btn_sub = new Button(comp2,SWT.NONE);
		btn_sub.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_sub.setText("确认");
		
	}

	public static void getConditionTable(Display display) {
		shell_condition = new Shell(display);
		init(shell_condition);
		shell_condition.setText("条件");
		btn_add.setText("添加AND行");
		btn_del_row.setText("删除AND行");
		btn_or = new Button(comp1,SWT.NONE);
		btn_or.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_or.setText("添加OR列");
		btn_del_col = new Button(comp1,SWT.NONE);
		btn_del_col.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		btn_del_col.setText("删除OR列");
		//创建表格头
		tableColumn = new TableColumn(table,SWT.NONE);
		tableColumn.setText("***");
		tableColumn.setWidth(100);
		
		tableColumn = new TableColumn(table,SWT.NONE);
		tableColumn.setText("条件");
		tableColumn.setWidth(250);
		
		tableColumn = new TableColumn(table,SWT.NONE);
		tableColumn.setWidth(15);
        
		item = new TableItem(table,SWT.NONE);
		item.setText(new String[]{String.valueOf(++i),"123"});
		editor = new TableEditor(table);
		

		Combo co_1 = new Combo(table, SWT.BORDER);
		co_1.add("主语1");
		co_1.add("主语2");
		co_1.add("主语3");
		co_1.select(0);
		Combo co_2 = new Combo(table, SWT.BORDER);
		co_2.add("主语1");
		co_2.add("主语2");
		co_2.add("主语3");
		co_2.select(0);
		Combo co_3 = new Combo(table, SWT.BORDER);
		co_3.add("主语1");
		co_3.add("主语2");
		co_3.add("主语3");
		co_3.select(0);
//		Text name = new Text(table, SWT.NONE);
//		name.setText(item.getText(1));
		editor.grabHorizontal = true;
		editor.setEditor(co_1, item, 1);
		editor.setEditor(co_2, item, 1);
		editor.setEditor(co_3, item, 1);
//        name.addModifyListener(new ModifyListener()
//        {
//            public void modifyText(ModifyEvent e)
//            {
//            	editor.getItem().setText(1, name.getText());
//            }
//
//        });
//		
		editor = new TableEditor(table);
    	btn_check = new Button(table, SWT.CHECK);
    	btn_check.pack();
		editor.grabHorizontal = true;
    	editor.horizontalAlignment = SWT.CENTER;
    	editor.setEditor(btn_check, item, 2);	
    	shell_condition.open();
        
        //添加监听事件
        btn_or.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				tableColumn = new TableColumn(table,SWT.NONE);
				tableColumn.setWidth(15);
				TableItem[] items = table.getItems();
				for(int i = 0;i<items.length;i++) {
					for(int j = 2; j<table.getColumnCount();j++) {
						editor = new TableEditor(table);
				    	btn_check = new Button(table, SWT.CHECK);
				    	btn_check.pack();
						editor.grabHorizontal = true;
				    	editor.horizontalAlignment = SWT.CENTER;
				    	editor.setEditor(btn_check, items[i],j);	
					}
				}
			}
		});
   	
        btn_add.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				item = new TableItem(table,SWT.NONE);
				item.setText(String.valueOf(++i));
				editor = new TableEditor(table);
				Text name = new Text(table, SWT.NONE);
				name.setText(item.getText(1));
				editor.grabHorizontal = true;
				editor.setEditor(name, item, 1);
		        name.addModifyListener(new ModifyListener()
		        {
		            public void modifyText(ModifyEvent e)
		            {
		            	editor.getItem().setText(1, name.getText());
		            }

		        });
		        int column = table.getColumnCount();
		        while(column>=2) {
					editor = new TableEditor(table);
			    	btn_check = new Button(table, SWT.CHECK);
			    	btn_check.pack();
					editor.grabHorizontal = true;
			    	editor.horizontalAlignment = SWT.CENTER;
			    	editor.setEditor(btn_check, item, column--);
		        }
			}
		});
        
        btn_del_row.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				TableItem[] items = table.getSelection();
				for(Item tItem : items) {
					tItem.dispose();
				}
				editor.layout();
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
