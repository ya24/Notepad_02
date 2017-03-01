package view.frameView.mainFrameView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.undo.UndoManager;

import view.otherView.popupMenuView.PopMenu;
import control.menuControl.checkBoxMenuItemControl.LineCheckMenuItemEvents;
import control.menuControl.checkBoxMenuItemControl.StatusCheckMenuItemEvents;
import control.menuControl.menuItemControl.MenuActionEvents;
import control.otherControl.MyCaretListener;
import control.otherControl.Share;

/**
 * 
 * @author 叶雅芳
 * @date 2015-06-22
 * @desc 记事本图形用户主界面类
 * 
 */

public class NoteView extends JFrame {

	public Share share; // 共享事件类
	public MenuActionEvents menuAction; // 主界面菜单触发的动作事件类
	public PopMenu popMenu; // 弹出式菜单界面类
	public LineCheckMenuItemEvents lineCheckEvent; // 主界面自动换行复选菜单触发的选项事件类
	public StatusCheckMenuItemEvents statusCheckEvent; // 主界面状态栏复选菜单触发的选项事件类
	MyCaretListener my; // 对文本区光标位置随时监听的类

	//所用的组件
	public JMenuBar menuBar; // 菜单栏

	public JMenu fileMenu, editMenu, formMenu, seeMenu, helpMenu; // 菜单

	public JMenuItem newMi, openMi, saveMi, saveAsMi, setPageMi, printMi,
			exitMi; // 文件菜单下的菜单项

	public JMenuItem undoMi, cutMi, copyMi, pasteMi, delMi, seekMi, seekNextMi,
			replaceMi, goMi, allMi, dateMi; // 编辑菜单下的菜单项

	public JMenuItem fontMi; // 格式菜单下的字体菜单项
	public JCheckBoxMenuItem lineCheck; // 格式菜单下的自动换行复选菜单

	public JCheckBoxMenuItem statusCheck; // 查看菜单下的状态栏复选菜单项

	public JMenuItem aboutMi; // 帮助菜单下的菜单项

	public JTextArea area; // 文本区
	public JScrollPane roll; // 滚动条

	public JLabel rcLab; // 标签，显示状态栏记录的光标所在的行号和列号

	public UndoManager udm; // 负责实现撤销功能的类

	/**
	 * @author 叶雅芳
	 * @param my
	 * @date 2015-06-22
	 * @desc 构造方法，传入共享事件类、主界面菜单触发的动作事件类、弹出式菜单界面类、主界面自动换行复选菜单触发的选项事件类、
	 *       主界面状态栏复选菜单触发的选项事件类、查找对话框动作事件类\对文本区内容随时监听的类对象,实例化成员变量
	 * @param share	共享事件类对象
	 * @param menuAction	主界面菜单触发的动作事件类对象
	 * @param popMenu	弹出式菜单界面类对象
	 * @param lineCheckEvent	主界面自动换行复选菜单触发的选项事件类对象
	 * @param statusCheckEvent	主界面状态栏复选菜单触发的选项事件类对象
	 * @param my	对文本区光标位置随时监听的类
	 *            
	 */
	public NoteView(Share share, final MenuActionEvents menuAction,
			final PopMenu popMenu, LineCheckMenuItemEvents lineCheckEvent,
			StatusCheckMenuItemEvents statusCheckEvent, MyCaretListener my) {

		// 实例化类对象
		this.share = share;
		this.menuAction = menuAction;
		this.popMenu = popMenu;
		this.lineCheckEvent = lineCheckEvent;
		this.statusCheckEvent = statusCheckEvent;
		this.my = my;

		// 传递本类对象
		statusCheckEvent.setView(this);
		lineCheckEvent.setView(this);
		menuAction.setView(this);
		my.setView(this);

		
		menuBar = new JMenuBar(); // 创建菜单栏
		this.setJMenuBar(menuBar); // 添加菜单栏

		build(); // 创建菜单和文本区组件

		addMenuBar(); // 将菜单添加到菜单栏

		addMenuItem();// 将菜单项添加到菜单中

		setFont(); // 设置菜单及菜单项的字体大小

		setAccelerator(); // 设置快捷键
		
		setEnable();//设置菜单的状态

		// 事件监听者注册

		// 菜单类触发的动作事件
		addMenuActionListener();

		// 复选菜单触发的选项事件
		addCheckItemListener();

		/**------ 文本区内单击鼠标 弹出 弹出式菜单事件 ---------------*/
		area.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 释放鼠标时触发的事件
				if (e.isPopupTrigger()) { // 判断鼠标事件是否是由弹出菜单引发
					// public void show(Component invoker, int x, int
					// y)在组件调用者的坐标空间中的
					// 位置 X、Y 显示弹出菜单。
					popMenu.show((Component) e.getSource(), e.getX(), e.getY());
				}
				if(area.getSelectedText()!=null){
					popMenu.popCut.setEnabled(true);
					popMenu.popCopy.setEnabled(true);
					popMenu.popDel.setEnabled(true);
				}
			}
		});
		
		
		/**=============编辑菜单事件监听=======================================*/
		editMenu.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				if(area.getSelectedText()!=null){
					cutMi.setEnabled(true);
					copyMi.setEnabled(true);
					delMi.setEnabled(true);
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {
			}
			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});

		

		// 框架属性设置
		this.setBounds(300, 100, 800, 500); // 设置窗体大小
		this.setTitle("无标题 — 记事本"); // 设置记事本标题
		this.setIconImage((new ImageIcon("window记事本.jpg")).getImage()); // 设置图标，
		this.setVisible(true); // 窗体设置为可见
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 关闭操作
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				menuAction.exit();
			}
		});
		
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 创建菜单组件
	 */
	private void build() {
		// TODO 自动生成的方法存根

		// 创建菜单
		fileMenu = new JMenu("文件(F)"); // 创建文件菜单
		editMenu = new JMenu("编辑(E)"); // 创建编辑菜单
		formMenu = new JMenu("格式(O)"); // 创建格式菜单
		seeMenu = new JMenu("查看(V)"); // 创建查看菜单
		helpMenu = new JMenu("帮助(H)"); // 创建帮助菜单

		// 创建文件菜单下的菜单项
		newMi = new JMenuItem("新建(N)");
		openMi = new JMenuItem("打开(O)");
		saveMi = new JMenuItem("保存(S)");
		saveAsMi = new JMenuItem("另存为(A)");
		setPageMi = new JMenuItem("页面设置(U)");
		printMi = new JMenuItem("打印(P)");
		exitMi = new JMenuItem("退出(X)");

		// 创建编辑菜单下的菜单项
		undoMi = new JMenuItem("撤销(U)");
		cutMi = new JMenuItem("剪切(T)");
		copyMi = new JMenuItem("复制(C)");
		pasteMi = new JMenuItem("粘贴(P)");
		delMi = new JMenuItem("删除(L)");
		seekMi = new JMenuItem("查找(F)");
		seekNextMi = new JMenuItem("查找下一个(N)");
		replaceMi = new JMenuItem("替换(R)");
		goMi = new JMenuItem("转到(G)");
		allMi = new JMenuItem("全选(A)");
		dateMi = new JMenuItem("时间/日期(D)");

		// 创建格式菜单下的菜单项
		lineCheck = new JCheckBoxMenuItem("自动换行(W)");
		fontMi = new JMenuItem("字体(F)");

		// 创建查看菜单下的菜单项
		statusCheck = new JCheckBoxMenuItem("状态栏(S)");

		// 创建帮助菜单下的菜单项
		aboutMi = new JMenuItem("关于记事本(A)");

		area = new JTextArea(); // 创建文本区
		roll = new JScrollPane(area); // 创建滚动窗格
		this.add(roll); // 将滚动窗格添加到框架窗体中

		// 对文本区内容进行随时监听
		area.addCaretListener(my);

		// 对文本内容的处理
		udm = new UndoManager();
		area.getDocument().addUndoableEditListener(udm);
		
		//对文本文档更改的监听
		area.getDocument().addDocumentListener(menuAction);

		// 创建标签
		rcLab = new JLabel();
		// 设置标签大小
		rcLab.setSize(800, 50);
		// 标签中文本对齐方式——右对齐
		rcLab.setHorizontalAlignment(JLabel.RIGHT);
		// 把标签添加到记事本框架的南部
		this.add(rcLab, BorderLayout.SOUTH);

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 将菜单添加到菜单栏
	 */
	private void addMenuBar() {
		// TODO 自动生成的方法存根
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formMenu);
		menuBar.add(seeMenu);
		menuBar.add(helpMenu);
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 将菜单项添加到菜单中
	 */
	private void addMenuItem() {
		// TODO 自动生成的方法存根
		// 将文件菜单下的菜单项添加到文件菜单中
		fileMenu.add(newMi);
		fileMenu.add(openMi);
		fileMenu.add(saveMi);
		fileMenu.add(saveAsMi);
		fileMenu.addSeparator();// 添加分隔线
		fileMenu.add(setPageMi);
		fileMenu.add(printMi);
		fileMenu.addSeparator();// 添加分隔线
		fileMenu.add(exitMi);

		// 将编辑菜单下的菜单项添加到编辑菜单中
		editMenu.add(undoMi);
		editMenu.addSeparator();// 添加分隔线
		editMenu.add(cutMi);
		editMenu.add(copyMi);
		editMenu.add(pasteMi);
		editMenu.add(delMi);
		editMenu.addSeparator();// 添加分隔线
		editMenu.add(seekMi);
		editMenu.add(seekNextMi);
		editMenu.add(replaceMi);
		editMenu.add(goMi);
		editMenu.addSeparator();// 添加分隔线
		editMenu.add(allMi);
		editMenu.add(dateMi);

		// 将格式菜单下的菜单项添加到格式菜单中
		formMenu.add(lineCheck);
		formMenu.add(fontMi);

		// 将查看菜单下的菜单项添加到查看菜单中
		seeMenu.add(statusCheck);

		// 将帮助菜单下的菜单项添加到帮助菜单中
		helpMenu.add(aboutMi);
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 设置菜单及菜单项的字体大小
	 */
	private void setFont() {
		// TODO 自动生成的方法存根
		// 设置菜单字体大小
		fileMenu.setFont(share.font2);
		editMenu.setFont(share.font2);
		formMenu.setFont(share.font2);
		seeMenu.setFont(share.font2);
		helpMenu.setFont(share.font2);

		// 设置文件菜单下菜单项的字体大小
		newMi.setFont(share.font1);
		openMi.setFont(share.font1);
		saveMi.setFont(share.font1);
		saveAsMi.setFont(share.font1);
		setPageMi.setFont(share.font1);
		printMi.setFont(share.font1);
		exitMi.setFont(share.font1);

		// 设置编辑菜单下的菜单项的字体大小
		undoMi.setFont(share.font1);
		cutMi.setFont(share.font1);
		copyMi.setFont(share.font1);
		pasteMi.setFont(share.font1);
		delMi.setFont(share.font1);
		seekMi.setFont(share.font1);
		seekNextMi.setFont(share.font1);
		replaceMi.setFont(share.font1);
		goMi.setFont(share.font1);
		allMi.setFont(share.font1);
		dateMi.setFont(share.font1);

		// 设置格式菜单下的菜单项的字体大小
		lineCheck.setFont(share.font1);
		fontMi.setFont(share.font1);

		// 设置查看菜单下的菜单项的字体大小
		statusCheck.setFont(share.font1);

		// 设置帮助菜单下的菜单项的字体大小
		aboutMi.setFont(share.font1);

		// 设置文本区字体大小
		area.setFont(share.font2);

		// 设置状态栏标签的字体大小
		rcLab.setFont(share.font2);

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 设置快捷键
	 */
	private void setAccelerator() {
		// TODO 自动生成的方法存根
		// 新建菜单快捷键Ctrl+N
		newMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		// 打开菜单快捷键Ctrl+O
		openMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		// 保存菜单快捷键Ctrl+S
		saveMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		// 打印菜单快捷键Ctrl+P
		printMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		// 撤销菜单快捷键Ctrl+Z
		undoMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		// 剪切菜单快捷键Ctrl+X
		cutMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		// 复制菜单快捷键Ctrl+C
		copyMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		// 粘贴菜单快捷键Ctrl+V
		pasteMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		// 替换菜单快捷键Ctrl+H
		replaceMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				ActionEvent.CTRL_MASK));
		// 转到菜单快捷键Ctrl+G
		goMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				ActionEvent.CTRL_MASK));
		// 全选菜单快捷键Ctrl+A
		allMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.CTRL_MASK));

	}
	
	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 设置菜单的字体
	 */
	private void setEnable(){
		//刚开始文本无内容，撤销、剪切、复制、删除、查找、查找下一个菜单不可编辑
		undoMi.setEnabled(false);
		cutMi.setEnabled(false);
		copyMi.setEnabled(false);
		delMi.setEnabled(false);
		seekMi.setEnabled(false);
		seekNextMi.setEnabled(false);
	}
	
	
	

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 菜单类触发的动作事件
	 */
	private void addMenuActionListener() {
		// TODO 自动生成的方法存根

		newMi.addActionListener(menuAction);
		openMi.addActionListener(menuAction);
		saveMi.addActionListener(menuAction);
		saveAsMi.addActionListener(menuAction);
		setPageMi.addActionListener(menuAction);
		printMi.addActionListener(menuAction);
		exitMi.addActionListener(menuAction);
		undoMi.addActionListener(menuAction);
		cutMi.addActionListener(menuAction);
		copyMi.addActionListener(menuAction);
		pasteMi.addActionListener(menuAction);
		delMi.addActionListener(menuAction);
		seekMi.addActionListener(menuAction);
		seekNextMi.addActionListener(menuAction);
		replaceMi.addActionListener(menuAction);
		goMi.addActionListener(menuAction);
		allMi.addActionListener(menuAction);
		dateMi.addActionListener(menuAction);
		fontMi.addActionListener(menuAction);
		aboutMi.addActionListener(menuAction);

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 复选菜单触发的选项事件
	 */
	private void addCheckItemListener() {
		// TODO 自动生成的方法存根
		lineCheck.addItemListener(lineCheckEvent);
		statusCheck.addItemListener(statusCheckEvent);
	}

	

}
