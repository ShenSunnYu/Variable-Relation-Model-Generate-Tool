package vrm_generate_tool;


import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class RequirementsSpecification {
	private static Label lab_retitle;//原始需求
	private static Label lab_recontent;//标题
	private static Label lab_requirement;//标题
	private static Label lab_conditiion_event;//标题
	private static Combo co_retitle;//需求输入框
	private static Text  txt_recontent;//标题输入框
	private static Button btn_conditiion_event;
	
	public static void init(Composite parent,String id) {
		Composite top = new Composite(parent, SWT.NONE);

		Display display = Display.getDefault();
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    	
		lab_retitle = new Label(top, SWT.NONE);
		lab_retitle.setText("原始需求标题:");
		lab_retitle.setFont(boldFont);
		lab_retitle.setBounds(56, 40, 100, 60);

		co_retitle = new Combo(top, SWT.NONE);
		co_retitle.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		co_retitle.add("标题1");
		co_retitle.add("标题2");
		co_retitle.add("标题3");
		co_retitle.setBounds(160, 40, 500, 28);

		lab_recontent= new Label(top, SWT.NONE);
		lab_recontent.setText("原始需求内容:");
		lab_recontent.setFont(boldFont);
		lab_recontent.setBounds(56, 120, 100, 60);
		
		txt_recontent = new Text(top, SWT.BORDER);
		txt_recontent.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		txt_recontent.setBounds(160, 120, 500, 26);
		
		lab_requirement = new Label(top, SWT.NONE);
		lab_requirement.setText("规范化需求:");
		lab_requirement.setFont(boldFont);
		lab_requirement.setBounds(56, 210, 100, 60);
		
		txt_recontent = new Text(top, SWT.BORDER);
		txt_recontent.setText("当满足以下条件");
		txt_recontent.setEnabled(false);
		txt_recontent.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		txt_recontent.setBounds(160, 210, 120, 26);

		txt_recontent = new Text(top, SWT.BORDER);
		txt_recontent.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		txt_recontent.setBounds(290, 210, 370, 26);
		
		txt_recontent = new Text(top, SWT.BORDER);
		txt_recontent.setText("应该能够");
		txt_recontent.setEnabled(false);
		txt_recontent.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		txt_recontent.setBounds(160, 240, 120, 26);
		
		txt_recontent = new Text(top, SWT.BORDER);
		txt_recontent.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		txt_recontent.setBounds(290, 240, 370, 26);
		
		txt_recontent = new Text(top, SWT.BORDER);
		txt_recontent.setText(":");
		txt_recontent.setEnabled(false);
		txt_recontent.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		txt_recontent.setBounds(160, 270, 120, 26);
		
		txt_recontent = new Text(top, SWT.BORDER);
		txt_recontent.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		txt_recontent.setBounds(290, 270, 370, 26);
		
		lab_conditiion_event = new Label(top, SWT.NONE);
		
		lab_conditiion_event.setFont(boldFont);
		lab_conditiion_event.setBounds(56, 300, 100, 60);
		
		txt_recontent = new Text(top, SWT.BORDER);
		txt_recontent.setFont(new Font(display,"宋体",12,SWT.NORMAL));
		txt_recontent.setBounds(160, 300, 500, 26);
		
		btn_conditiion_event = new Button(top, SWT.BORDER);
		btn_conditiion_event.setBounds(160,330,100,50);
		
		if(id.contains("Condition")) {
			lab_conditiion_event.setText("条件:");
			btn_conditiion_event.setText("条件编辑");
			btn_conditiion_event.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("条件按钮已启动");
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});;;
		}else {
			lab_conditiion_event.setText("事件:");
			btn_conditiion_event.setText("事件编辑");
			btn_conditiion_event.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("事件按钮已启动");
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	
	
	
	
	
	
	
}
