package view.dialogView.goDialogView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import control.dialogControl.goDialogControl.GoDialogActionEvents;
import control.menuControl.menuItemControl.MenuActionEvents;

/**
 * 
 * @author 叶雅芳
 * @date 2015-07-25
 * @desc 转到自定义对话框设计类
 *
 */

public class GoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField txtRow;	//显示行号的文本框
	public JButton btGo,btCancel;	//转到，取消按钮
	
	
	MenuActionEvents menuAction;	//主界面主菜单动作事件类
	GoDialogActionEvents goAction;	//转到对话框动作事件类



	/**
	 * Create the dialog.
	 * @param goAction 
	 * @param menuAction 
	 */
	public GoDialog(MenuActionEvents menuAction, GoDialogActionEvents goAction) {
		
		
		this.menuAction=menuAction;
		menuAction.setGoDialog(this);//向主界面主菜单动作事件类传递本类对象
		this.goAction=goAction;
		goAction.setGoDialog(this);//向转到对话框动作事件类传递本类对象
		
		
		//设置对话框
		setModal(true);
		setTitle("\u8F6C\u5230\u6307\u5B9A\u884C");
		setBounds(100, 100, 450, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//行号标签
		JLabel lbRow = new JLabel("\u884C\u53F7(L):");
		lbRow.setFont(new Font("宋体", Font.PLAIN, 14));
		lbRow.setBounds(40, 25, 61, 25);
		contentPanel.add(lbRow);
		
		//显示行号的文本框
		txtRow = new JTextField();
		txtRow.setFont(new Font("宋体", Font.PLAIN, 14));
		txtRow.setBounds(40, 60, 345, 25);
		contentPanel.add(txtRow);
		txtRow.setColumns(10);
		
		//取消按钮
		btCancel = new JButton("\u53D6\u6D88");
		btCancel.setFont(new Font("宋体", Font.PLAIN, 14));
		btCancel.setBounds(292, 115, 95, 25);
		contentPanel.add(btCancel);
		btCancel.addActionListener(goAction);
		
		//转到按钮
		btGo = new JButton("\u8F6C\u5230");
		btGo.setFont(new Font("宋体", Font.PLAIN, 14));
		btGo.setBounds(160, 116, 95, 25);
		contentPanel.add(btGo);
		btGo.addActionListener(goAction);
	}
}
