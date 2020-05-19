package vrm_generate_tool;

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
	private static Table table;
	private static TableEditor editor;
	private static Combo combo;
	private static Text text;
	private static TableItem item;
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

	public static void getEventTable() {
		display = Display.getDefault();
		shell_event = new Shell(display);
		init(shell_event);
		shell_event.setText("事件");
		btn_add.setText("添加'与'事件行");
		btn_del_row.setText("删除事件行");
		//创建表格头
		TableColumn tableColumn = new TableColumn(table,SWT.NONE);
		tableColumn.setText("***");
		tableColumn.setWidth(80);
		
		tableColumn = new TableColumn(table,SWT.NONE);
		tableColumn.setText("Event");
		tableColumn.setWidth(100);
		
		tableColumn = new TableColumn(table,SWT.NONE);
		tableColumn.setText("Condition");
		tableColumn.setWidth(180);
		
		tableColumn = new TableColumn(table,SWT.NONE);
		tableColumn.setText("Guard");
		tableColumn.setWidth(100);
		
		tableColumn = new TableColumn(table,SWT.NONE);
		tableColumn.setText("Condition");
		tableColumn.setWidth(180);
		TableItem item1 = new TableItem(table,SWT.NONE);
		item1.setText(String.valueOf(++i));

		//创建几个数据
        TableItem[] items = table.getItems();

        for(int i = 0; i<items.length ;i++) {
        	for(int j=0;j<5;j++) {
        		if(j==1) {
        			editor = new TableEditor(table);
                	combo = new Combo(table, SWT.NONE);
                	combo.add("@T");
                	combo.add("@F");
                	combo.add("@C");
                	combo.select(0);
                	editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(combo, items[i], j);	
                	combo.setFont(new Font(display,"宋体",12,SWT.NORMAL));
        		}else if(j==3) {
        			editor = new TableEditor(table);
        			combo = new Combo(table, SWT.NONE);
                	combo.setFont(new Font(display,"宋体",12,SWT.NORMAL));
                	combo.add("_");
                	combo.add("WHERE");
                	combo.add("WHEN");
                	combo.add("WHILE");
                	combo.select(0);
                	editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(combo, items[i], j);	
        		}else if(j==2||j==4){
        			editor = new TableEditor(table);
        			text = new Text(table, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
        			text.setFont(new Font(display,"宋体",12,SWT.NORMAL));
        			text.addListener(SWT.MouseDoubleClick, new Listener() {
						@Override
						public void handleEvent(Event event) {
							// TODO Auto-generated method stub
							ConditionTable.getConditionTable(display);
						}
					});
        			editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(text, items[i], j);	
        		}
        	}
        }
        shell_event.open();
        
        
        btn_add.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				item = new TableItem(table,SWT.NONE);
				item.setText(String.valueOf(++i));
        			editor = new TableEditor(table);
                	combo = new Combo(table, SWT.NONE);
                	combo.add("@T");
                	combo.add("@F");
                	combo.add("@C");
                	combo.select(0);
                	editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(combo, item,1);	
                	combo.setFont(new Font(display,"宋体",12,SWT.NORMAL));


        			editor = new TableEditor(table);
        			text = new Text(table, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
        			text.setFont(new Font(display,"宋体",12,SWT.NORMAL));
        			text.addListener(SWT.MouseDoubleClick, new Listener() {
						
						@Override
						public void handleEvent(Event event) {
							// TODO Auto-generated method stub
							ConditionTable.getConditionTable(display);
						}
					});
        			editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(text, item, 2);	

        			editor = new TableEditor(table);
        			combo = new Combo(table, SWT.NONE);
                	combo.setFont(new Font(display,"宋体",12,SWT.NORMAL));
                	combo.add("_");
                	combo.add("WHERE");
                	combo.add("WHEN");
                	combo.add("WHILE");
                	combo.select(0);
                	editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(combo, item, 3);	
        			editor = new TableEditor(table);
        			text = new Text(table, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
        			text.setFont(new Font(display,"宋体",12,SWT.NORMAL));
        			text.addListener(SWT.MouseDoubleClick, new Listener() {
						
						@Override
						public void handleEvent(Event event) {
							// TODO Auto-generated method stub
							ConditionTable.getConditionTable(display);
						}
					});
        			editor.grabHorizontal = true;
                	editor.horizontalAlignment = SWT.CENTER;
                	editor.setEditor(text, item, 4);	
				}
			
		});
        
        btn_del_row.addListener(SWT.MouseDown, new Listener() {
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
        
        btn_cancel.addListener(SWT.MouseDown, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				shell_event.dispose();
				
			}
		});
	}
}
