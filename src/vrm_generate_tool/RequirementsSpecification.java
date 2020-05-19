package vrm_generate_tool;


import javax.swing.text.TableView.TableRow;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @PackageName:vrm_generate_tool
 * @ClassName:RequirementsSpecification
 * @Description:需求规约的各个页面模板
 * @Author:展万里
 * @Date:2020/5/6 15:39
 */
public class RequirementsSpecification {
	private static Label lab_requirement_id;//需求ID
	private static Label lab_original_requirement;//原始需求内容
	private static Label lab_standardized_requirements;//规范化需求
	
	private static Combo co_retitle;//需求ID下拉框
	private static Combo co_subject;//主语下拉框
	private static Combo co_predicate;//谓语下拉框
	private static Combo co_object;//宾语下拉框
	
	private static Text txt_original_requirement_content;//内容输入框
	private static Text txt_conditiion_event;//条件/事件内容
	private static Text txt_can;//应该能够
	private static Text txt_satisfy;//满足条件/事件
	private static Text txt_subject;//主语内容
	private static Text txt_predicate;//谓语内容
	private static Text txt_object;//宾语内容
	
	
	private static Button btn_conditiion_event;
	private static Button btn_submit;
	
	public static void init(Composite parent,String id) {
		Composite top = new Composite(parent, SWT.NONE);
		Display display = Display.getDefault();
		Font txt_font = new Font(display,"微软雅黑",12,SWT.NORMAL);
		Font txt_font_lab = new Font(display,"微软雅黑",12,SWT.NORMAL);
		Font lab_font = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
		lab_requirement_id = new Label(top, SWT.NONE);
		lab_requirement_id.setText("需求ID:");
		lab_requirement_id.setFont(lab_font);
		lab_requirement_id.setBounds(60, 40, 100, 30);

		co_retitle = new Combo(top, SWT.BORDER|SWT.READ_ONLY);
		co_retitle.setFont(txt_font);
		co_retitle.add("标题1");
		co_retitle.add("标题2");
		co_retitle.add("标题3");
		co_retitle.setBounds(160, 40, 500, 30);
		co_retitle.setFont(txt_font_lab);

		lab_original_requirement= new Label(top, SWT.NONE);
		lab_original_requirement.setText("原始需求内容:");
		lab_original_requirement.setFont(lab_font);
		lab_original_requirement.setBounds(60, 100, 100, 30);
		
		txt_original_requirement_content = new Text(top, SWT.BORDER);
		txt_original_requirement_content.setFont(txt_font);
		txt_original_requirement_content.setBounds(160, 100, 500, 30);
		
		lab_standardized_requirements = new Label(top, SWT.NONE);
		lab_standardized_requirements.setText("规范化需求:");
		lab_standardized_requirements.setFont(lab_font);
		lab_standardized_requirements.setBounds(60, 160, 100, 30);
		
		
		txt_satisfy = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_satisfy.setFont(txt_font_lab);
		txt_satisfy.setBounds(160, 160, 120, 30);
		
		btn_conditiion_event = new Button(top, SWT.CENTER);
		btn_conditiion_event.setFont(txt_font_lab);
		btn_conditiion_event.setBounds(290,160,100,30);
		
		txt_conditiion_event = new Text(top, SWT.BORDER|SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
		txt_conditiion_event.setFont(txt_font);
		txt_conditiion_event.setBounds(160, 200, 500, 50);

		
		co_subject = new Combo(top, SWT.BORDER);
		co_subject.add("主语1");
		co_subject.add("主语2");
		co_subject.add("主语3");
		co_subject.select(0);
		co_subject.setBounds(160, 260, 340, 30);
		co_subject.setFont(txt_font_lab);
		
		txt_subject = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);

		txt_subject.setFont(txt_font_lab);
		txt_subject.setBounds(510, 260, 150, 30);
		
		txt_can = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_can.setText("应该能够");
		txt_can.setFont(txt_font_lab);
		txt_can.setBounds(160, 300, 120, 30);
		
		co_predicate = new Combo(top, SWT.BORDER|SWT.READ_ONLY);
		co_predicate.setText("谓语");
		co_predicate.add("谓语1");
		co_predicate.add("谓语2");
		co_predicate.add("谓语3");
		co_predicate.select(0);
		co_predicate.setBounds(160, 340, 340, 30);
		co_predicate.setFont(txt_font_lab);
		
		
		txt_predicate = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_predicate.setText("<功能>");
		txt_predicate.setFont(txt_font_lab);
		txt_predicate.setBounds(510, 340, 150, 30);
		
		
		co_object = new Combo(top, SWT.BORDER|SWT.READ_ONLY);
		co_object.setText("宾语");
		co_object.add("宾语1");
		co_object.add("宾语2");
		co_object.add("宾语3");
		co_object.select(0);
		co_object.setBounds(160, 380, 340, 30);
		co_object.setFont(txt_font_lab);
		
		txt_object = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);

		txt_object.setFont(txt_font_lab);
		txt_object.setBounds(510, 380, 150, 30);
		
		btn_submit = new Button(top, SWT.CENTER);
		btn_submit.setBounds(560,420,100,50);
		btn_submit.setText("确定");
		btn_submit.setFont(txt_font_lab);
		if(id.contains("Condition")) {
			txt_satisfy.setText("当满足条件:");
			txt_conditiion_event.setText("条件内容");
			
			btn_conditiion_event.setText("条件编辑");
			btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
				@Override
				public void handleEvent(Event event) {
					// TODO Auto-generated method stub
					ConditionTable.getConditionTable(Display.getDefault());
					System.out.println("条件按钮已启动");
				}
			});
		}else {
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
		}
		if(id.contains("Common")) {
			txt_subject.setText("<飞机/系统/设备>");
			txt_object.setText("<对象>");
		}else {
			txt_subject.setText("<对象>");
			txt_object.setText("<标准/格式/要求>");
		}
		
		
		
		btn_submit.addListener(SWT.MouseDown, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				System.out.println(co_retitle.getText());
			}
		});
		
		
		
	}
	
	
	
	
	
	
	
}
