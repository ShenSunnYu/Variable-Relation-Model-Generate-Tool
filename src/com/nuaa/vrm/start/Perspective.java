package com.nuaa.vrm.start;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;

import com.nuaa.vrm.view.RequirementSpecificationContentView;
import com.nuaa.vrm.view.NavigationView;
import com.nuaa.vrm.view.RequirementsView;
import com.nuaa.vrm.view.View;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "Variable-Relation-Model-Generate-Tool.perspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		IFolderLayout navigationFolder = layout.createFolder("需求管理器", IPageLayout.LEFT, 0.75f, editorArea);		
		navigationFolder.addView(NavigationView.ID);
		
//		IFolderLayout folder = layout.createFolder("测试用例集", IPageLayout.RIGHT, 0.25f, NavigationView.ID);
//		folder.addPlaceholder(View.ID+":*");
//		folder.addView(View.ID);
		
		IFolderLayout MessageFolder = layout.createFolder("原始需求", IPageLayout.RIGHT, 0.25f, NavigationView.ID);
		MessageFolder.addPlaceholder(RequirementsView.ID+"*");
		MessageFolder.addView(RequirementsView.ID);
		
		IFolderLayout MFolder = layout.createFolder("通用条件", IPageLayout.RIGHT, 0.75f, NavigationView.ID);
		MFolder.addView(RequirementSpecificationContentView.ID);
		
//		IFolderLayout MessageFolder = layout.createFolder("处理", IPageLayout.RIGHT, 0.75f, View.ID);
//		MessageFolder.addView(ProcessView.ID);
					
		
		ConsoleFactory cf = new ConsoleFactory();
		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW, IPageLayout.BOTTOM,
				0.70f, View.ID);

		cf.openConsole();
		
	}
}
