package view.dialogView.replaceDialogView;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

import control.dialogControl.replaceDialogControl.ReplaceDialogActionEvents;
import control.menuControl.menuItemControl.MenuActionEvents;

/**
 * 
 * @author 叶雅芳
 * @date 2015-07-24
 * @desc 替换自定义对话框设计类
 *
 */

public class ReplaceDialog extends JDialog {
	
	ReplaceDialogActionEvents replaceAction;	//替换对话框动作事件类
	MenuActionEvents menuAction;	//主界面主菜单动作事件类

	//替换对话框组件
	private final JPanel contentPanel = new JPanel();
	public JTextField txtSeek;		//查找文本框
	public JTextField txtReplace;	//替换文本框
	public JButton btSeekNext;		//查找下一个按钮
	public JButton btReplace;		//替换按钮
	public JButton btReplaceAll;	//全部替换按钮
	public JButton btCancel; 	//取消按钮
	private JCheckBox chckbxc;	 //区分大小写复选按钮
	
	public boolean flag=false;		//区分大小写标识变量，true为区分大小写，false为不区分大小写
	
	/**
	 * Create the dialog.
	 * @param menuAction 
	 * @param replaceAction 
	 */
	public ReplaceDialog(MenuActionEvents menuAction, ReplaceDialogActionEvents replaceAction) {
		
		this.replaceAction=replaceAction;
		replaceAction.setReplaceDialog(this);	//传递替换自定义对话框设计类对象
		this.menuAction=menuAction;
		menuAction.setReplaceDialog(this);	//传递主界面主菜单动作事件类对象
		
		//设计替换对话框
		setTitle("\u66FF\u6362");
		setBounds(100, 100, 520, 229);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//查找标签
		JLabel lbSeek = new JLabel("\u67E5\u627E\u5185\u5BB9(N):");
		lbSeek.setFont(new Font("宋体", Font.PLAIN, 14));
		lbSeek.setBounds(10, 10, 84, 25);
		contentPanel.add(lbSeek);
		
		//查找文本框
		txtSeek = new JTextField();
		txtSeek.setBounds(104, 12, 210, 25);
		contentPanel.add(txtSeek);
		txtSeek.setColumns(10);
		txtSeek.getDocument().addDocumentListener(replaceAction);
		
		//替换标签
		JLabel lbReplace = new JLabel("\u66FF\u6362\u4E3A(P):");
		lbReplace.setFont(new Font("宋体", Font.PLAIN, 14));
		lbReplace.setBounds(10, 45, 84, 25);
		contentPanel.add(lbReplace);
		
		//替换文本框
		txtReplace = new JTextField();
		txtReplace.setBounds(104, 47, 210, 25);
		contentPanel.add(txtReplace);
		txtReplace.setColumns(10);
		
		//查找下一个按钮
		btSeekNext = new JButton("\u67E5\u627E\u4E0B\u4E00\u4E2A(F)");
		btSeekNext.setFont(new Font("宋体", Font.PLAIN, 14));
		btSeekNext.setBounds(337, 11, 140, 25);
		contentPanel.add(btSeekNext);
		btSeekNext.addActionListener(replaceAction);
		btSeekNext.setEnabled(false);
		
		//替换按钮
		btReplace = new JButton("\u66FF\u6362(R)");
		btReplace.setFont(new Font("宋体", Font.PLAIN, 14));
		btReplace.setBounds(337, 47, 140, 25);
		contentPanel.add(btReplace);
		btReplace.addActionListener(replaceAction);
		btReplace.setEnabled(false);
		
		//全部替换按钮
		btReplaceAll = new JButton("\u5168\u90E8\u66FF\u6362(A)");
		btReplaceAll.setFont(new Font("宋体", Font.PLAIN, 14));
		btReplaceAll.setBounds(337, 82, 140, 25);
		contentPanel.add(btReplaceAll);
		btReplaceAll.addActionListener(replaceAction);
		btReplaceAll.setEnabled(false);
		
		//取消按钮
		btCancel = new JButton("\u53D6\u6D88");
		btCancel.setFont(new Font("宋体", Font.PLAIN, 14));
		btCancel.setBounds(337, 117, 140, 25);
		contentPanel.add(btCancel);
		btCancel.addActionListener(replaceAction);
		
		//区分大小写复选按钮
		chckbxc = new JCheckBox("\u533A\u5206\u5927\u5C0F\u5199(C)");
		chckbxc.setFont(new Font("宋体", Font.PLAIN, 14));
		chckbxc.setBounds(6, 138, 133, 23);
		contentPanel.add(chckbxc);
		//复选按钮添加选项事件，获取用户的选择
		chckbxc.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					flag=true;
				}else{
					flag=false;
				}
			}
		});
	}

}
