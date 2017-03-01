package view.dialogView.fontDialogView;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import control.dialogControl.fontDialogControl.FontListSelectionEvents;
import control.menuControl.menuItemControl.MenuActionEvents;
import control.otherControl.Share;
import view.frameView.mainFrameView.NoteView;

/**
 * 
 * @author 叶雅芳
 * @date 2015-06-22
 * @desc 字体自定义对话框类
 *
 */
public class FontDialog extends JDialog{

	public NoteView view;	//记事本主界面设计类
	public Share share;		//共享类
	public FontListSelectionEvents listSelect;	//字体对话框中 列表框选择事件类
	public MenuActionEvents menuAction;	//主界面主菜单触发的动作事件类
	
	//所用的组件
	public JTextField show;	//显示字体设置预览效果的文本框
	public JLabel labFont,labStyle,labSize;	//字体，字形，大小 等标签
	public JTextField txtFont,txtStyle,txtSize;	//字体，字形，大小文本框,用来显示选择的内容
	public JList fontList,styleList,sizeList;	//字体，字形，大小列表框
	public JScrollPane rollFont,rollStyle,rollSize;	//滚动窗格
	
	public JButton butOk,butCancel;	//确定和取消按钮
	
	//获取系统中所有可用字体的名字
	public GraphicsEnvironment e=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public String[] UsableFont=e.getAvailableFontFamilyNames();
	
	//字形列表框中的选项内容
	public String[] style={"常规","倾斜","粗体","粗体 倾斜"};	
	
	//大小列表框中的选项内容
	public String[] size={"8","9","10","11","12","14","16","18","20","22","24","26","28",
			"36","48","72","初号","小初","一号","小一","二号","小二","三号","小三","四号",
			"小四","五号","小五","六号","小六","七号","八号"};	
	
	
	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 构造方法，传入记事本主界面设计类、共享类、字体对话框中 列表框选择事件类、主界面主菜单触发的动作事件类对象,
	 * 			实例化成员变量
	 * @param view	记事本主界面设计类对象
	 * @param share	共享类
	 * @param listSelect	字体对话框中 列表框选择事件类
	 * @param menuAction	主界面主菜单触发的动作事件类
	 */
	public FontDialog(NoteView view, Share share,
			FontListSelectionEvents listSelect, MenuActionEvents menuAction){
	
		//设计对话框
		super(view);	//拥有者为记事本图形用户界面类
		this.setTitle("字体");	//设置对话框标题
		this.setModal(true);	//设置对话框模式为模式状态
		this.setLayout(null);	//设置绝对布局
		this.setBounds(400,100,420,580);	//对话框位置及大小
		
		//实例化类成员变量
		this.view=view;
		this.share=share;
		this.listSelect=listSelect;
		this.menuAction=menuAction;
		
		//向字体对话框中 列表框选择事件类、主界面主菜单触发的动作事件类传递本类对象（字体对话框设计类对象）
		listSelect.setDialog(this);
		menuAction.setDialog(this);
		
		build();	//创建组件
		
		bound();//设置组件位置及大小
		
		insert();	//添加组件
		
		font();//设置组件内容字体大小
		
		addListSelection();//列表框选项事件注册监听
		
		buttonEvent();	//字体对话框按钮触发的动作事件处理
	}


	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 创建组件
	 */
	private void build() {
		// TODO 自动生成的方法存根
		
		//创建标签
		labFont=new JLabel("字体(F)");	
		labStyle=new JLabel("字形(Y)");
		labSize=new JLabel("大小(S)");
		
		//创建文本框
		txtFont=new JTextField();
		txtStyle=new JTextField(); 
		txtSize=new JTextField();
		show=new JTextField("仿微软中文软件\nAaBbYyZz");
		show.setBorder(new TitledBorder("示例"));
		show.setHorizontalAlignment(JTextField.CENTER);
		
		//创建列表框
		fontList=new JList(UsableFont);	//字体列表框
		styleList=new JList(style);	//字形列表框
		sizeList=new JList(size);	//大小列表框
		
		//创建滚动窗格
		rollFont=new JScrollPane(fontList);
		rollStyle=new JScrollPane(styleList);
		rollSize=new JScrollPane(sizeList);
		
		//确定和取消按钮
		butOk=new JButton("确定");
		butCancel=new JButton("取消");	
	
	}


	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 设置组件位置及大小
	 */
	private void bound() {
		// TODO 自动生成的方法存根
		
		//设置标签位置及大小
		labFont.setBounds(20, 20, 200, 30);
		labStyle.setBounds(240, 20, 70, 30);
		labSize.setBounds(330, 20, 50, 30);
		
		//设置文本框位置及大小
		txtFont.setBounds(20, 50, 200, 30);
		txtStyle.setBounds(240, 50, 70, 30);
		txtSize.setBounds(330, 50, 50, 30);
		
		
		//设置列表框的滚动窗格位置及大小
		rollFont.setBounds(20, 80, 200, 230);
		rollStyle.setBounds(240, 80, 70, 230);
		rollSize.setBounds(330, 80, 50, 230);
		
		//设置预览文本框位置及大小
		show.setBounds(20, 330, 360, 150);
		
		//设置按钮位置及大小
		butOk.setBounds(200, 500, 80, 30);
		butCancel.setBounds(300, 500, 80, 30);
		
	}


	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 添加组件
	 */
	private void insert() {
		// TODO 自动生成的方法存根
		
		//添加标签
		this.add(labFont);
		this.add(labStyle);
		this.add(labSize);
		
		//添加文本框
		this.add(txtFont);
		this.add(txtStyle);
		this.add(txtSize);
		
		//添加列表框滚动窗格
		this.add(rollFont);
		this.add(rollStyle);
		this.add(rollSize);
		
		//添加预览文本框
		this.add(show);
	
		//添加按钮
		this.add(butOk);
		this.add(butCancel);
	}


	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 设置组件文本字体
	 */
	private void font() {
		// TODO 自动生成的方法存根
		labFont.setFont(share.font1);
		labStyle.setFont(share.font1);
		labSize.setFont(share.font1);
		show.setFont(share.font1);
		
		txtFont.setFont(share.font1);
		txtStyle.setFont(share.font1);
		txtSize.setFont(share.font1);
		
		fontList.setFont(share.font1);
		styleList.setFont(share.font1);
		sizeList.setFont(share.font1);
		
		butOk.setFont(share.font1);
		butCancel.setFont(share.font1);
	}


	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 列表框选项事件注册监听
	 */
	private void addListSelection() {
		// TODO 自动生成的方法存根
		fontList.addListSelectionListener(listSelect);
		styleList.addListSelectionListener(listSelect);
		sizeList.addListSelectionListener(listSelect);
		
	}
	
	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 字体对话框按钮触发的动作事件处理
	 */
	private void buttonEvent(){
		//按钮动作事件
		butOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FontDialog.this.dispose();	//关闭对话框
				view.area.setFont(listSelect.font2);//设置文本区字体
			}

		});
		
		//取消按钮动作事件
		butCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FontDialog.this.dispose();	//点击取消按钮，自定义字体对话框关闭
				
			}
		});
	}
}
