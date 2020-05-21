package com.nuaa.vrm.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

import com.nuaa.vrm.view.NavigationView;
import com.nuaa.vrm.view.RequirementsView;

/**
 * 
 * �˵������빦��ʵ��
 * 
 * @author ������
 *
 */
public class ImportHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		FileDialog fd = new FileDialog((PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()), SWT.OPEN);
		fd.setFilterPath(System.getProperty("JAVA.HOME"));
		fd.setFilterExtensions(new String[] { "*.xlsx", "*.*" });
		fd.setFilterNames(new String[] { "EXCEL Files(*.xlsx)", "All Files(*.*)" });
		String file = fd.open();
		if (file != null) {
			File path = new File(file);
//			ConsoleHandler.info("����:" + path.getPath());
			System.out.println(path.getAbsolutePath());
			try {  
				FileInputStream fis = new FileInputStream(path);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheetAt = workbook.getSheetAt(0);
				//4��ѭ����ȡ�������
				for (Row row : sheetAt) {
				//���У�����ͷ������ȡ
	             if (row.getRowNum() < 2) {
	                 continue;
	             }
	             //��ȡ��ǰ���е�Ԫ�����ݣ�������3��ʼ
				 int reqNum = (int)row.getCell(0).getNumericCellValue(); 
				 if(reqNum == 0)
					 continue;
	             String content = row.getCell(1).getStringCellValue();
	             System.out.println(reqNum+":"+content);
//	             ConsoleHandler.info("�ļ����ݶ�ȡ�ɹ���");    
//	             ((RequirementsView)PlatformUI.getWorkbench().
//						   getActiveWorkbenchWindow().
//						   getActivePage().findView(RequirementsView.ID)).fileContent(reqNum+"",content);
//	             
//	             ConsoleHandler.info("�ļ���Ŷ�ȡ�ɹ���");    
//	             ((RequirementsView)PlatformUI.getWorkbench().
//						   getActiveWorkbenchWindow().
//						   getActivePage().findView(RequirementsView.ID)).rightFileContent("REQ"+reqNum+"");
				 }
				
				  //5���ر���
				         workbook.close();
				     } catch (IOException e) {
				         e.printStackTrace();
				     }			   
			  
			   }  
    	return null;
}
	



}
