package vrm_generate_tool;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
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
 * @author �޹��
 * 
 * ��������ߵ�Ŀ¼
 *
 */
public class NavigationView extends ViewPart implements ISelectionListener{
	public static final String ID = "Variable-Relation-Model-Generate-Tool.NavigationView";
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
	
	private TreeObject createDummyModel() {
		final TreeObject require = new TreeObject("ԭʼ����");
		TreeObject dataType = new TreeObject("��������");
		
		//������������νṹ
		TreeParent conceptLib = new TreeParent("��������");
		TreeObject properNoun = new TreeObject("ר������");
		TreeObject constNum = new TreeObject("����");
		TreeObject inputVariable = new TreeObject("�������");
		TreeObject outputVariable = new TreeObject("�������");
		TreeObject middleVariable = new TreeObject("�м����");
		TreeObject pattern = new TreeObject("ģʽ��");
		
		conceptLib.addChild(properNoun);
		conceptLib.addChild(constNum);
		conceptLib.addChild(inputVariable);
		conceptLib.addChild(outputVariable);
		conceptLib.addChild(middleVariable);
		conceptLib.addChild(pattern);
		
		//�����Լ
		TreeParent requirements = new TreeParent("�����Լ");
		TreeObject commonCondition = new TreeObject("ͨ������");
		TreeObject commonEvent = new TreeObject("ͨ���¼�");
		TreeObject showCondition = new TreeObject("��ʾ����");
		TreeObject showEvent = new TreeObject("��ʾ�¼�");
		
		
		
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
		
		//Tree tree = new Tree(parent, SWT.BORDER | SWT.CHECK);
		//viewer = new TreeViewer(group, SWT.BORDER);
		Tree tree = viewer.getTree();
		menu=new Menu(tree);
        MenuItem newItem=new MenuItem(menu,SWT.PUSH);
        newItem.setText("�½�");
        tree.setMenu(menu);
        
        //�Ӽ���
        newItem.addSelectionListener(new SelectionAdapter(){
    	   @Override
    	   public void widgetSelected(SelectionEvent e) {
    		   ISelection selection=(IStructuredSelection)e;
    		   if(selection!= null){
    			   IStructuredSelection is = (IStructuredSelection)selection;
    			   try {
   					if(is.toString().equals("[ԭʼ����]")) {
   						//iString = iString.substring(1,iString.indexOf(']'));
   						if(e.toString().equals("[�½�]")) {
   							PlatformUI.getWorkbench().
   							getActiveWorkbenchWindow().
   							getActivePage().
   							showView(RequirementsView.ID, "ԭʼ����", IWorkbenchPage.VIEW_ACTIVATE);
   						}
   					}
   				}catch (Exception ex) {
   						// TODO: handle exception
   						ex.printStackTrace();
   					}
    		   }
    	   }
       });
        
        MenuItem deleteItem=new MenuItem(menu, SWT.PUSH);    
        deleteItem.setText("ɾ��");
        tree.setMenu(menu);
        
       /* menu.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });*/
       
        /*
        tree.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				//Point point = Display.getCurrent().getCursorLocation();  
				//IStructuredSelection is = (IStructuredSelection)e;
				//String iString = is.toString();
				//iString = iString.substring(3,iString.indexOf(']'));
				try {
					if(e.toString().equals("ԭʼ����")) {
						//iString = iString.substring(1,iString.indexOf(']'));
						if(e.toString().equals("�½�")) {
							PlatformUI.getWorkbench().
							getActiveWorkbenchWindow().
							getActivePage().
							showView(RequirementsView.ID, "ԭʼ����", IWorkbenchPage.VIEW_ACTIVATE);
						}
					}
				}catch (Exception ex) {
						// TODO: handle exception
						ex.printStackTrace();
					}
				
				//System.out.println("�½���ɾ��������");
        }			
     });
        /*	 tree.addSelectionListener(new SelectionAdapter() {
        	public void widgetSelected(SelectionEvent e) {
        		try {
        				if(e.toString().equals("ԭʼ����")) {
        						if(e.toString().equals("�½�")) {
        							PlatformUI.getWorkbench().
        							getActiveWorkbenchWindow().
        							getActivePage().
        							showView(RequirementsView.ID, "ԭʼ����", IWorkbenchPage.VIEW_ACTIVATE);
        					}
        				}
        			}catch (Exception ex) {
    						// TODO: handle exception
    						ex.printStackTrace();
    					}
        			}       	
        	});*/
     }
        /* 
        MenuItem addNode=new MenuItem(menu,SWT.PUSH);
        addNode.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });
        //addNode.setText("��ӽڵ�");
        
        
        MenuItem deleteNode=new MenuItem(menu, SWT.PUSH);
        deleteNode.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });
        //deleteNode.setText("ɾ���ڵ�");
        tree.setMenu(menu);
        
            
        fMenu.addMenuListener(new MenuAdapter()
        {
            public void menuShown(MenuEvent e)
            {
                MenuItem[] items = fMenu.getItems();
                for (int i = 0; i < items.length; i++)
                {
                    items[i].dispose();
                }
                MenuItem newItem = new MenuItem(fMenu,SWT.NONE);
                newItem.setText("Menu for " + tree.getSelection()[0].getText());
            }
        });*/
		

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
		ConsoleHandler.info("detetct change\n");
		if(selection!= null){
			IStructuredSelection is = (IStructuredSelection)selection;
			String iString = is.toString();
			iString = iString.substring(1,iString.indexOf(']'));
			try {
			/*	if(iString.equals("ԭʼ����")) {
						iString = iString.substring(1,iString.indexOf(']'));
						if(iString.equals("�½�")) {
							PlatformUI.getWorkbench().
							getActiveWorkbenchWindow().
							getActivePage().
							showView(RequirementsView.ID, "ԭʼ����", IWorkbenchPage.VIEW_ACTIVATE);
							System.out.print("����������"+is.toString());
					}
				}else*/
					if(iString.equals("��������")) {
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(DataTypeView.ID, "��������", IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("ר������")) {
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(ProperNounView.ID, "ר������", IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("����")) {
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(ConstNumView.ID, "����", IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("�������")) {
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(InputVariableView.ID, "�������", IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("�������")) {
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(OutputVariableView.ID, "�������", IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("�м����")) {
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(MiddleVariableView.ID, "�м����", IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("ģʽ��")) {
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(PatternView.ID, "ģʽ��", IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("ͨ������")) {
					System.out.println(iString.toString());
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(CommonConditionView.ID, iString, IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("ͨ���¼�")) {
					System.out.println(iString.toString());
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(CommonEventView.ID, iString, IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("��ʾ����")) {
					System.out.println(iString.toString());
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(DominantConditionView.ID, iString, IWorkbenchPage.VIEW_ACTIVATE);
				}else if(iString.equals("��ʾ�¼�")) {
					System.out.println(iString.toString());
					PlatformUI.getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					showView(DominantEventView.ID, iString, IWorkbenchPage.VIEW_ACTIVATE);
				}
					
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
}