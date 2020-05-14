package vrm_generate_tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

import vrm_generate_tool.NavigationView.TreeParent;

/**
 * 
 * @author 崔国楠、展万里、沈翔宇
 * 
 * 左边的目录
 *
 */
public class NavigationView extends ViewPart implements ISelectionListener{
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
	
	TreeParent require = new TreeParent("原始需求");
	TreeParent dataType = new TreeParent("数据类型");
	TreeParent properNoun = new TreeParent("专有名词");
	TreeParent constNum = new TreeParent("常量");
	TreeParent inputVariable = new TreeParent("输入变量");
	TreeParent outputVariable = new TreeParent("输出变量");
	TreeParent middleVariable = new TreeParent("中间变量");
	TreeParent pattern = new TreeParent("模式类");
	TreeParent commonCondition = new TreeParent("通用条件");
	TreeParent commonEvent = new TreeParent("通用事件");
	TreeParent showCondition = new TreeParent("显示条件");
	TreeParent showEvent = new TreeParent("显示事件");
	
	protected TreeObject createDummyModel() {
	    //TreeParent require = new TreeParent("原始需求");
		//TreeParent dataType = new TreeParent("数据类型");
		
		//领域概念库的树形结构
		TreeParent conceptLib = new TreeParent("领域概念库");
		//TreeParent properNoun = new TreeParent("专有名词");
		//TreeObject constNum = new TreeObject("常量");
		//TreeObject inputVariable = new TreeObject("输入变量");
		//TreeObject outputVariable = new TreeObject("输出变量");
		//TreeObject middleVariable = new TreeObject("中间变量");
		//TreeObject pattern = new TreeObject("模式类");
		
		conceptLib.addChild(properNoun);
		conceptLib.addChild(constNum);
		conceptLib.addChild(inputVariable);
		conceptLib.addChild(outputVariable);
		conceptLib.addChild(middleVariable);
		conceptLib.addChild(pattern);
		
		//需求规约
		TreeParent requirements = new TreeParent("需求规约");
		//TreeObject commonCondition = new TreeObject("通用条件");
		//TreeObject commonEvent = new TreeObject("通用事件");
		//TreeObject showCondition = new TreeObject("显示条件");
		//TreeObject showEvent = new TreeObject("显示事件");
			
		requirements.addChild(commonCondition);
		requirements.addChild(commonEvent);
		requirements.addChild(showCondition);
		requirements.addChild(showEvent);
		
		TreeParent root = new TreeParent("");
		root.addChild(require);
		root.addChild(dataType);
		root.addChild(conceptLib);
		root.addChild(requirements);
		 
		return root;
		
	}

	@Override
	public void createPartControl(Composite parent) {
		MenuManager menuManager = new MenuManager();

	    Menu menu = menuManager.createContextMenu(parent);

	    parent.setMenu(menu);
	    getSite().registerContextMenu(menuManager, null);
	    	    
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(createDummyModel());	
		
		hookContextMenu();
		
		getSite().getPage().addSelectionListener((ISelectionListener) this);
		
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

	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		Tree tree = viewer.getTree();
		Menu menu=new Menu(tree);
        MenuItem newItem=new MenuItem(menu,SWT.PUSH);
        newItem.setText("新建");
        tree.setMenu(menu);
        
        MenuItem deleteItem=new MenuItem(menu, SWT.PUSH);    
        deleteItem.setText("删除");
        tree.setMenu(menu);
        
		ConsoleHandler.info("detetct change\n");
		if(selection!= null){
			IStructuredSelection is = (IStructuredSelection)selection;
		    String iString = is.toString();
			iString = iString.substring(1,iString.indexOf(']'));
			try {
			if(iString.equals("原始需求")) {
				newItem.addSelectionListener(new SelectionAdapter(){
			    	   @Override
			    	   public void widgetSelected(SelectionEvent e) {
			    		   createSubMenu(is.toString());
							try {
								PlatformUI.getWorkbench().
								getActiveWorkbenchWindow().
								getActivePage().
								showView(RequirementsView.ID, "原始需求", IWorkbenchPage.VIEW_ACTIVATE);
							} catch (PartInitException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
				}
				else if(iString.equals("数据类型")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		 //  createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(DataTypeView.ID, "数据类型", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
				}else if(iString.equals("专有名词")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		//   createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(ProperNounView.ID, "专有名词", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
				}else if(iString.equals("常量")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		 //  createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(ConstNumView.ID, "常量", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});					
				}else if(iString.equals("输入变量")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		 //  createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(InputVariableView.ID, "输入变量", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});						
				}else if(iString.equals("输出变量")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		  // createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(OutputVariableView.ID, "输出变量", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});								
				}else if(iString.equals("中间变量")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		 //  createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(MiddleVariableView.ID, "中间变量", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});								
				}else if(iString.equals("模式类")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		  // createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(PatternView.ID, "模式类", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});						
				}else if(iString.equals("通用条件")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		 //  createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(CommonConditionView.ID, "通用条件", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});							
				}else if(iString.equals("通用事件")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		 //  createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(CommonEventView.ID, "通用事件", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});								
				}else if(iString.equals("显示条件")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		   //createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(DominantConditionView.ID, "显示条件", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});				
				}else if(iString.equals("显示事件")) {
					newItem.addSelectionListener(new SelectionAdapter(){
				    	   @Override
				    	   public void widgetSelected(SelectionEvent e) {
				    		   //createSubMenu(is.toString());
								try {
									PlatformUI.getWorkbench().
									getActiveWorkbenchWindow().
									getActivePage().
									showView(DominantEventView.ID, "显示事件", IWorkbenchPage.VIEW_ACTIVATE);
								} catch (PartInitException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});									
				}
					
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}

	//点击新建，创建子目录
	protected void createSubMenu(String b) {
		// TODO Auto-generated method stub
		//Scanner scan = new Scanner(System.in);
		//String input=scan.next();
		
		switch(b)
		{
			case "[原始需求]":
				TreeObject child1=new TreeObject("子节点");
				require.addChild(child1);
				break;
			case "数据类型":
				TreeObject child2=new TreeObject("");
				dataType.addChild(child2);
				break;
			case "专有名词":
				TreeObject child3=new TreeObject("");
				properNoun.addChild(child3);
				break;
			case "常量":
				TreeObject child4=new TreeObject("");
				constNum.addChild(child4);
				break;
			case "输入变量":
				TreeObject child5=new TreeObject("");
				inputVariable.addChild(child5);
				break;
			case "输出变量":
				TreeObject child6=new TreeObject("");
				outputVariable.addChild(child6);
				break;
			case "中间变量":
				TreeObject child7=new TreeObject("");
				middleVariable.addChild(child7);
				break;
			case "模式类":
				TreeObject child8=new TreeObject("");
				pattern.addChild(child8);
				break;
			case "通用条件":
				TreeObject child9=new TreeObject("");
				commonCondition.addChild(child9);
				break;
			case "通用事件":
				TreeObject child10=new TreeObject("");
				commonEvent.addChild(child10);
				break;
			case "显示条件":
				TreeObject child11=new TreeObject("");
				showCondition.addChild(child11);
				break;
			case "显示事件":
				TreeObject child12=new TreeObject("");
				showEvent.addChild(child12);
				break;
			
		}
		viewer.refresh();
	}
}