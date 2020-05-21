package com.nuaa.vrm.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.spi.RootCategory;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.nuaa.vrm.view.NavigationView.TreeParent;

/**
 * 
 * @author �޹�骡�չ���������
 * 
 * ��ߵ�Ŀ¼
 *
 */
public class NavigationView extends ViewPart {
	public static final String ID = "Variable-Relation-Model-Generate-Tool.navigationView";
	private TreeViewer viewer;
	   private MenuManager fMenuMgr; 
	   private Menu fMenu; 

	class TreeObject{
		private String name;
		private TreeParent parent;

		public TreeObject(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setParent(TreeParent parent) {
			this.parent = parent;
		}
		public TreeParent getParent() {
			return parent;
		}
		public String toString() {
			return getName();
		}
	}

	class TreeParent extends TreeObject {
		private List<TreeObject> children;
		public TreeParent(String name) {
			super(name);
			children = new ArrayList<>();
		}
		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}
		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}
		public TreeObject[] getChildren() {
			return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
		}
		public boolean hasChildren() {
			return children.size()>0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider{

		@Override
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject)child).getParent();
			}
			return null;
		}

		@Override
		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent)parent).getChildren();
			}
			return new Object[0];
		}

		@Override
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent)parent).hasChildren();
			return false;
		}

	}

	class ViewLabelProvider extends LabelProvider {

		@Override
		public String getText(Object obj) {
			return obj.toString();
		}

		@Override
		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}

	/**
	 * We will set up a dummy model to initialize tree heararchy. In real
	 * code, you will connect to a real model and expose its hierarchy.
	 */
	private TreeParent root;
	TreeParent require = new TreeParent("ԭʼ����");
	TreeParent dataType = new TreeParent("��������");
	TreeParent properNoun = new TreeParent("ר������");
	TreeParent constNum = new TreeParent("����");
	TreeParent inputVariable = new TreeParent("�������");
	TreeParent outputVariable = new TreeParent("�������");
	TreeParent middleVariable = new TreeParent("�м����");
	TreeParent pattern = new TreeParent("ģʽ��");
	
	protected TreeParent createDummyModel() {
		
		//������������νṹ
		TreeParent conceptLib = new TreeParent("��������");
		
		conceptLib.addChild(properNoun);
		conceptLib.addChild(constNum);
		conceptLib.addChild(inputVariable);
		conceptLib.addChild(outputVariable);
		conceptLib.addChild(middleVariable);
		conceptLib.addChild(pattern);
		
		//�����Լ
		TreeParent requirements = new TreeParent("����淶��");
		
		TreeParent root = new TreeParent("");
		root.addChild(require);
		root.addChild(dataType);
		root.addChild(conceptLib);
		root.addChild(requirements);
		 
		
		try {
		PlatformUI.getWorkbench().
		getActiveWorkbenchWindow().
		getActivePage().
		showView(RequirementSpecificationContentView.ID, "ͨ������", IWorkbenchPage.VIEW_ACTIVATE);
	} catch (PartInitException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
		
		return root;
		
	}

	@Override
	public void createPartControl(Composite parent) {
		MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(parent);
	    parent.setMenu(menu);
	    root = createDummyModel();
	    getSite().registerContextMenu(menuManager, null);
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(createDummyModel());	
		hookContextMenu();
		getSite().setSelectionProvider(viewer);
     }
       
	private void hookContextMenu() { 
	       fMenuMgr = new MenuManager("#PopupMenu"); 
	       fMenuMgr.setRemoveAllWhenShown(true); 
	       fMenuMgr.addMenuListener(new IMenuListener() { 
	           public void menuAboutToShow(IMenuManager manager) {                
	           } 
	       }); 
	       fMenu = fMenuMgr.createContextMenu(viewer.getControl()); 
	       viewer.getControl().setMenu(fMenu); 
	       getSite().registerContextMenu(fMenuMgr, viewer);              
	}    
	
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void add(TreeObject treeObject) {
		root.addChild(treeObject);
	}
//	@Override
//	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//		Tree tree = viewer.getTree();
//		Menu menu=new Menu(tree);
//        MenuItem newItem=new MenuItem(menu,SWT.PUSH);
//        newItem.setText("�½�");
//        tree.setMenu(menu);
//        
//        MenuItem openItem=new MenuItem(menu,SWT.PUSH);
//        openItem.setText("��");
//        tree.setMenu(menu);
//        
//        MenuItem deleteItem=new MenuItem(menu, SWT.PUSH);    
//        deleteItem.setText("ɾ��");
//        tree.setMenu(menu);
//        
//		ConsoleHandler.info("detetct change\n");
//		if(selection!= null){
//			IStructuredSelection is = (IStructuredSelection)selection;
//			System.out.println(is.toString()+"________"+is.getClass());
//		    String iString = is.toString();
//			iString = iString.substring(1,iString.indexOf(']'));
//			try {
//			if(iString.equals("ԭʼ����")) {
//				newItem.addSelectionListener(new SelectionAdapter(){
//			    	   @Override
//			    	   public void widgetSelected(SelectionEvent e) {
//			    		   createSubMenu(is.toString());
//			    		   openItem.addSelectionListener(new SelectionAdapter(){
//					    	   @Override
//					    	   public void widgetSelected(SelectionEvent e) {		    		   
//					    		   try {
//					    			   PlatformUI.getWorkbench().
//					    			   getActiveWorkbenchWindow().
//					    			   getActivePage().
//					    			   showView(RequirementsView.ID);
//					    			  // showView(RequirementsView.ID, "ԭʼ����", IWorkbenchPage.VIEW_ACTIVATE);
//					    		   } catch (PartInitException e1) {
//					    			   // TODO Auto-generated catch block
//					    			   e1.printStackTrace();
//					    		   }
//					    	   }
//			    		   });
//						}
//					});
//				}
//				else if(iString.equals("��������")) {
//					newItem.addSelectionListener(new SelectionAdapter(){
//				    	   @Override
//				    	   public void widgetSelected(SelectionEvent e) {
//				    		 //  createSubMenu(is.toString());
//								try {
//									PlatformUI.getWorkbench().
//									getActiveWorkbenchWindow().
//									getActivePage().
//									showView(DataTypeView.ID, "��������", IWorkbenchPage.VIEW_ACTIVATE);
//								} catch (PartInitException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//						});
//				}else if(iString.equals("ר������")) {
//					newItem.addSelectionListener(new SelectionAdapter(){
//				    	   @Override
//				    	   public void widgetSelected(SelectionEvent e) {
//				    		//   createSubMenu(is.toString());
//								try {
//									PlatformUI.getWorkbench().
//									getActiveWorkbenchWindow().
//									getActivePage().
//									showView(ProperNounView.ID, "ר������", IWorkbenchPage.VIEW_ACTIVATE);
//								} catch (PartInitException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//						});
//				}else if(iString.equals("����")) {
//					newItem.addSelectionListener(new SelectionAdapter(){
//				    	   @Override
//				    	   public void widgetSelected(SelectionEvent e) {
//				    		 //  createSubMenu(is.toString());
//								try {
//									PlatformUI.getWorkbench().
//									getActiveWorkbenchWindow().
//									getActivePage().
//									showView(ConstNumView.ID, "����", IWorkbenchPage.VIEW_ACTIVATE);
//								} catch (PartInitException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//						});					
//				}else if(iString.equals("�������")) {
//					newItem.addSelectionListener(new SelectionAdapter(){
//				    	   @Override
//				    	   public void widgetSelected(SelectionEvent e) {
//				    		 //  createSubMenu(is.toString());
//								try {
//									PlatformUI.getWorkbench().
//									getActiveWorkbenchWindow().
//									getActivePage().
//									showView(InputVariableView.ID, "�������", IWorkbenchPage.VIEW_ACTIVATE);
//								} catch (PartInitException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//						});						
//				}else if(iString.equals("�������")) {
//					newItem.addSelectionListener(new SelectionAdapter(){
//				    	   @Override
//				    	   public void widgetSelected(SelectionEvent e) {
//				    		  // createSubMenu(is.toString());
//								try {
//									PlatformUI.getWorkbench().
//									getActiveWorkbenchWindow().
//									getActivePage().
//									showView(OutputVariableView.ID, "�������", IWorkbenchPage.VIEW_ACTIVATE);
//								} catch (PartInitException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//						});								
//				}else if(iString.equals("�м����")) {
//					newItem.addSelectionListener(new SelectionAdapter(){
//				    	   @Override
//				    	   public void widgetSelected(SelectionEvent e) {
//				    		 //  createSubMenu(is.toString());
//								try {
//									PlatformUI.getWorkbench().
//									getActiveWorkbenchWindow().
//									getActivePage().
//									showView(MiddleVariableView.ID, "�м����", IWorkbenchPage.VIEW_ACTIVATE);
//								} catch (PartInitException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//						});								
//				}else if(iString.equals("ģʽ��")) {
//					newItem.addSelectionListener(new SelectionAdapter(){
//				    	   @Override
//				    	   public void widgetSelected(SelectionEvent e) {
//				    		  // createSubMenu(is.toString());
//								try {
//									PlatformUI.getWorkbench().
//									getActiveWorkbenchWindow().
//									getActivePage().
//									showView(PatternView.ID, "ģʽ��", IWorkbenchPage.VIEW_ACTIVATE);
//								} catch (PartInitException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//						});						
//				}else if(iString.contains("ԭʼ����ID:")) {
//					int id = Integer.parseInt(iString.substring(7));
//					System.out.println(id);
//
//					openItem.addSelectionListener(new SelectionAdapter(){
//				    	   @Override
//				    	   public void widgetSelected(SelectionEvent e) {
//				    		 //  createSubMenu(is.toString());
//								try {
//									PlatformUI.getWorkbench().
//									getActiveWorkbenchWindow().
//									getActivePage().
//									showView(CommonConditionView.ID, "ͨ������", IWorkbenchPage.VIEW_ACTIVATE);
//								} catch (PartInitException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//						});	
//					
//				}
//			}catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//		
//	}

//	//����½���������Ŀ¼
//	protected void createSubMenu(String b) {
//		// TODO Auto-generated method stub
//		switch(b)
//		{
//			case "[ԭʼ����]":
//				TreeObject child1=new TreeObject("�ӽڵ�1");
//				require.addChild(child1);
//				break;
//			case "[��������]":
//				TreeObject child2=new TreeObject("�ӽڵ�1");
//				dataType.addChild(child2);
//				break;
//			case "[ר������]":
//				TreeObject child3=new TreeObject("�ӽڵ�1");
//				properNoun.addChild(child3);
//				break;
//			case "[����]":
//				TreeObject child4=new TreeObject("�ӽڵ�1");
//				constNum.addChild(child4);
//				break;
//			case "[�������]":
//				TreeObject child5=new TreeObject("�ӽڵ�1");
//				inputVariable.addChild(child5);
//				break;
//			case "[�������]":
//				TreeObject child6=new TreeObject("�ӽڵ�1");
//				outputVariable.addChild(child6);
//				break;
//			case "[�м����]":
//				TreeObject child7=new TreeObject("�ӽڵ�1");
//				middleVariable.addChild(child7);
//				break;
//			case "[ģʽ��]":
//				TreeObject child8=new TreeObject("�ӽڵ�1");
//				pattern.addChild(child8);
//				break;
//		}
//		viewer.refresh();
//	}
}