package view.dialogView.seekDialogView;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;

import control.dialogControl.seekDialogControl.SeekDialogActionEvents;
import control.dialogControl.seekDialogControl.SeekDialogItemEvents;
import control.menuControl.menuItemControl.MenuActionEvents;

/**
 * 
 * @author 叶雅芳
 * @date 2015-07-22
 * @desc 查找自定义对话框设计类
 *
 */

public class SeekDialog extends JDialog {

	//所用的组件
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public JTextField txtSeek;	//"查找内容"文本框
	public JButton btSeekNext ;	//"查找下一个"按钮
	public JButton btnCancel;	//"取消"按钮
	public JRadioButton rdbtnUp ;	//向上单选按钮
	public JRadioButton rdbtnDown ;	//向下单选按钮
	public JCheckBox chckbxc ;	//区分大小写复选按钮

	
	MenuActionEvents menuAction;	//主界面主菜单动作事件类
	SeekDialogActionEvents seekAction;	//查找对话框动作事件类
	SeekDialogItemEvents seekItem;	//查找对话框选项事件类
	

	/**
	 * Create the dialog.
	 * @param menuAction 
	 * @param seekItem 
	 * @param seekAction 
	 * @param seekEvent 
	 */
	public SeekDialog(MenuActionEvents menuAction, SeekDialogActionEvents seekAction, SeekDialogItemEvents seekItem) {
		
		//实例化主界面主菜单动作事件类，并传入本类对象
		this.menuAction=menuAction;
		menuAction.setSeekDialog(this);
		
		//实例化查找对话框动作事件类，并传入本类对象
		this.seekAction=seekAction;
		seekAction.setSeekDialog(this);
		
		//实例化查找对话框选项事件类，并传入本类对象
		this.seekItem=seekItem;
		seekItem.setSeekDialog(this);
		
		
		//对话框设计
		setTitle("\u67E5\u627E");
		setBounds(100, 100, 500, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//“查找内容”标签
		JLabel lbSeek = new JLabel("\u67E5\u627E\u5185\u5BB9(N):");
		lbSeek.setFont(new Font("宋体", Font.PLAIN, 14));
		lbSeek.setBounds(10, 10, 89, 28);
		contentPanel.add(lbSeek);
		
		//"查找内容"文本框
		txtSeek = new JTextField();
		txtSeek.setFont(new Font("宋体", Font.PLAIN, 14));
		txtSeek.setBounds(97, 11, 198, 27);
		contentPanel.add(txtSeek);
		txtSeek.setColumns(10);
		
		txtSeek.getDocument().addDocumentListener(seekAction);
		
		//"查找下一个"按钮
		btSeekNext = new JButton("\u67E5\u627E\u4E0B\u4E00\u4E2A(F)");
		btSeekNext.setFont(new Font("宋体", Font.PLAIN, 14));
		btSeekNext.setBounds(321, 10, 135, 28);
		contentPanel.add(btSeekNext);
		btSeekNext.addActionListener(seekAction);
		btSeekNext.setEnabled(false);
		
		//“取消”按钮
		btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 14));
		btnCancel.setBounds(321, 64, 135, 28);
		contentPanel.add(btnCancel);
		btnCancel.addActionListener(seekAction);
		
		//放置方向单选按钮的面板
		JPanel DirectionPan = new JPanel();
		DirectionPan.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u65B9\u5411", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DirectionPan.setBounds(120, 64, 175, 64);
		contentPanel.add(DirectionPan);
		DirectionPan.setLayout(null);
		
		//向上单选按钮 
		rdbtnUp = new JRadioButton("\u5411\u4E0A(U)");
		buttonGroup.add(rdbtnUp);
		rdbtnUp.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnUp.setBounds(6, 24, 75, 23);
		DirectionPan.add(rdbtnUp);
		rdbtnUp.addItemListener(seekItem);
		
		//向下单选按钮
		rdbtnDown = new JRadioButton("\u5411\u4E0B(D)");
		buttonGroup.add(rdbtnDown);
		rdbtnDown.setSelected(true);
		rdbtnDown.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnDown.setBounds(83, 24, 86, 23);
		DirectionPan.add(rdbtnDown);
		rdbtnDown.addItemListener(seekItem);
		
		//区分大小写复选按钮
		chckbxc = new JCheckBox("\u533A\u5206\u5927\u5C0F\u5199(C)");
		chckbxc.setFont(new Font("宋体", Font.PLAIN, 14));
		chckbxc.setBounds(10, 134, 123, 28);
		contentPanel.add(chckbxc);
		chckbxc.addItemListener(seekItem);
	}
	

}
