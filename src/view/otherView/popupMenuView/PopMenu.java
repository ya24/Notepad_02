package view.otherView.popupMenuView;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import control.menuControl.menuItemControl.MenuActionEvents;
import control.otherControl.Share;

/**
 * 
 * @author 叶雅芳
 * @date 2015-06-22
 * @desc 弹出式菜单界面设计类（弹出式菜单菜单项:撤销，剪切，复制，粘贴，删除，全选）
 *
 */
public class PopMenu extends JPopupMenu{
	
	public JMenuItem popUndo,popCut,popCopy,popPaste,popDel,popAll;	//弹出式菜单的菜单项
	
	public Share share;	//共享类
	public MenuActionEvents menuAction;	//主界面主菜单动作事件类
	
	/**
	 * @author 叶雅芳
	 * @date 2015-06-23
	 * @desc 构造方法，传入共享类，主界面主菜单动作事件类，实例化成员变量
	 * @param share
	 * @param menuAction
	 */
	public PopMenu(Share share,MenuActionEvents menuAction){
		
		this.share=share;
		this.menuAction=menuAction;
		//向主界面主菜单动作事件类传入本类对象(弹出式菜单界面设计类)
		menuAction.setPopMenu(this);
		
		builder();	//创建弹出式菜单的菜单项
		
		insert();	//将菜单项添加到弹出式菜单中
		
		font();	//设置菜单项字体
		
		addListener();	//注册监听
		
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 创建弹出式菜单的菜单项
	 */
	private void builder() {
		// TODO 自动生成的方法存根
		
		popUndo=new JMenuItem("撤销");
		popCut=new JMenuItem("剪切");
		popCopy=new JMenuItem("复制");
		popPaste=new JMenuItem("粘贴");
		popDel=new JMenuItem("删除");
		popAll=new JMenuItem("全选");
		
		popUndo.setEnabled(false);
		popCut.setEnabled(false);
		popCopy.setEnabled(false);
		popDel.setEnabled(false);
		popAll.setEnabled(false);
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 将菜单项添加到弹出式菜单中
	 */
	private void insert() {
		// TODO 自动生成的方法存根
		
		this.add(popUndo);
		this.add(popCut);
		this.add(popCopy);
		this.add(popPaste);
		this.add(popDel);
		this.add(popAll);

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 设置菜单项字体
	 */
	private void font() {
		// TODO 自动生成的方法存根
		
		popUndo.setFont(share.font1);
		popCut.setFont(share.font1);
		popCopy.setFont(share.font1);
		popPaste.setFont(share.font1);
		popDel.setFont(share.font1);
		popAll.setFont(share.font1);
		
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 注册监听
	 */
	private void addListener() {
		// TODO 自动生成的方法存根
		
		popUndo.addActionListener(menuAction);
		popCut.addActionListener(menuAction);
		popCopy.addActionListener(menuAction);
		popPaste.addActionListener(menuAction);
		popDel.addActionListener(menuAction);
		popAll.addActionListener(menuAction);
	}
	
}
