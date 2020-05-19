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
 * @Description:�����Լ�ĸ���ҳ��ģ��
 * @Author:չ����
 * @Date:2020/5/6 15:39
 */
public class RequirementsSpecification {
	private static Label lab_requirement_id;//����ID
	private static Label lab_original_requirement;//ԭʼ��������
	private static Label lab_standardized_requirements;//�淶������
	
	private static Combo co_retitle;//����ID������
	private static Combo co_subject;//����������
	private static Combo co_predicate;//ν��������
	private static Combo co_object;//����������
	
	private static Text txt_original_requirement_content;//���������
	private static Text txt_conditiion_event;//����/�¼�����
	private static Text txt_can;//Ӧ���ܹ�
	private static Text txt_satisfy;//��������/�¼�
	private static Text txt_subject;//��������
	private static Text txt_predicate;//ν������
	private static Text txt_object;//��������
	
	
	private static Button btn_conditiion_event;
	private static Button btn_submit;
	
	public static void init(Composite parent,String id) {
		Composite top = new Composite(parent, SWT.NONE);
		Display display = Display.getDefault();
		Font txt_font = new Font(display,"΢���ź�",12,SWT.NORMAL);
		Font txt_font_lab = new Font(display,"΢���ź�",12,SWT.NORMAL);
		Font lab_font = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
		lab_requirement_id = new Label(top, SWT.NONE);
		lab_requirement_id.setText("����ID:");
		lab_requirement_id.setFont(lab_font);
		lab_requirement_id.setBounds(60, 40, 100, 30);

		co_retitle = new Combo(top, SWT.BORDER|SWT.READ_ONLY);
		co_retitle.setFont(txt_font);
		co_retitle.add("����1");
		co_retitle.add("����2");
		co_retitle.add("����3");
		co_retitle.setBounds(160, 40, 500, 30);
		co_retitle.setFont(txt_font_lab);

		lab_original_requirement= new Label(top, SWT.NONE);
		lab_original_requirement.setText("ԭʼ��������:");
		lab_original_requirement.setFont(lab_font);
		lab_original_requirement.setBounds(60, 100, 100, 30);
		
		txt_original_requirement_content = new Text(top, SWT.BORDER);
		txt_original_requirement_content.setFont(txt_font);
		txt_original_requirement_content.setBounds(160, 100, 500, 30);
		
		lab_standardized_requirements = new Label(top, SWT.NONE);
		lab_standardized_requirements.setText("�淶������:");
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
		co_subject.add("����1");
		co_subject.add("����2");
		co_subject.add("����3");
		co_subject.select(0);
		co_subject.setBounds(160, 260, 340, 30);
		co_subject.setFont(txt_font_lab);
		
		txt_subject = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);

		txt_subject.setFont(txt_font_lab);
		txt_subject.setBounds(510, 260, 150, 30);
		
		txt_can = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_can.setText("Ӧ���ܹ�");
		txt_can.setFont(txt_font_lab);
		txt_can.setBounds(160, 300, 120, 30);
		
		co_predicate = new Combo(top, SWT.BORDER|SWT.READ_ONLY);
		co_predicate.setText("ν��");
		co_predicate.add("ν��1");
		co_predicate.add("ν��2");
		co_predicate.add("ν��3");
		co_predicate.select(0);
		co_predicate.setBounds(160, 340, 340, 30);
		co_predicate.setFont(txt_font_lab);
		
		
		txt_predicate = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);
		txt_predicate.setText("<����>");
		txt_predicate.setFont(txt_font_lab);
		txt_predicate.setBounds(510, 340, 150, 30);
		
		
		co_object = new Combo(top, SWT.BORDER|SWT.READ_ONLY);
		co_object.setText("����");
		co_object.add("����1");
		co_object.add("����2");
		co_object.add("����3");
		co_object.select(0);
		co_object.setBounds(160, 380, 340, 30);
		co_object.setFont(txt_font_lab);
		
		txt_object = new Text(top, SWT.READ_ONLY|SWT.BORDER|SWT.CENTER);

		txt_object.setFont(txt_font_lab);
		txt_object.setBounds(510, 380, 150, 30);
		
		btn_submit = new Button(top, SWT.CENTER);
		btn_submit.setBounds(560,420,100,50);
		btn_submit.setText("ȷ��");
		btn_submit.setFont(txt_font_lab);
		if(id.contains("Condition")) {
			txt_satisfy.setText("����������:");
			txt_conditiion_event.setText("��������");
			
			btn_conditiion_event.setText("�����༭");
			btn_conditiion_event.addListener(SWT.MouseDown,new Listener() {
				@Override
				public void handleEvent(Event event) {
					// TODO Auto-generated method stub
					ConditionTable.getConditionTable(Display.getDefault());
					System.out.println("������ť������");
				}
			});
		}else {
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
		}
		if(id.contains("Common")) {
			txt_subject.setText("<�ɻ�/ϵͳ/�豸>");
			txt_object.setText("<����>");
		}else {
			txt_subject.setText("<����>");
			txt_object.setText("<��׼/��ʽ/Ҫ��>");
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
