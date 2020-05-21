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
 * 菜单栏导入功能实现
 * 
 * @author 沈翔宇
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
//			ConsoleHandler.info("导入:" + path.getPath());
			System.out.println(path.getAbsolutePath());
			try {  
				FileInputStream fis = new FileInputStream(path);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheetAt = workbook.getSheetAt(0);
				//4、循环读取表格数据
				for (Row row : sheetAt) {
				//首行（即表头）不读取
	             if (row.getRowNum() < 2) {
	                 continue;
	             }
	             //读取当前行中单元格数据，索引从3开始
				 int reqNum = (int)row.getCell(0).getNumericCellValue(); 
				 if(reqNum == 0)
					 continue;
	             String content = row.getCell(1).getStringCellValue();
	             System.out.println(reqNum+":"+content);
//	             ConsoleHandler.info("文件内容读取成功！");    
//	             ((RequirementsView)PlatformUI.getWorkbench().
//						   getActiveWorkbenchWindow().
//						   getActivePage().findView(RequirementsView.ID)).fileContent(reqNum+"",content);
//	             
//	             ConsoleHandler.info("文件编号读取成功！");    
//	             ((RequirementsView)PlatformUI.getWorkbench().
//						   getActiveWorkbenchWindow().
//						   getActivePage().findView(RequirementsView.ID)).rightFileContent("REQ"+reqNum+"");
				 }
				
				  //5、关闭流
				         workbook.close();
				     } catch (IOException e) {
				         e.printStackTrace();
				     }			   
			  
			   }  
    	return null;
}
	



}
