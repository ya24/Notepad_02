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
 * @author Ҷ�ŷ�
 * @date 2015-07-25
 * @desc ת���Զ���Ի��������
 *
 */

public class GoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField txtRow;	//��ʾ�кŵ��ı���
	public JButton btGo,btCancel;	//ת����ȡ����ť
	
	
	MenuActionEvents menuAction;	//���������˵������¼���
	GoDialogActionEvents goAction;	//ת���Ի������¼���



	/**
	 * Create the dialog.
	 * @param goAction 
	 * @param menuAction 
	 */
	public GoDialog(MenuActionEvents menuAction, GoDialogActionEvents goAction) {
		
		
		this.menuAction=menuAction;
		menuAction.setGoDialog(this);//�����������˵������¼��ഫ�ݱ������
		this.goAction=goAction;
		goAction.setGoDialog(this);//��ת���Ի������¼��ഫ�ݱ������
		
		
		//���öԻ���
		setModal(true);
		setTitle("\u8F6C\u5230\u6307\u5B9A\u884C");
		setBounds(100, 100, 450, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//�кű�ǩ
		JLabel lbRow = new JLabel("\u884C\u53F7(L):");
		lbRow.setFont(new Font("����", Font.PLAIN, 14));
		lbRow.setBounds(40, 25, 61, 25);
		contentPanel.add(lbRow);
		
		//��ʾ�кŵ��ı���
		txtRow = new JTextField();
		txtRow.setFont(new Font("����", Font.PLAIN, 14));
		txtRow.setBounds(40, 60, 345, 25);
		contentPanel.add(txtRow);
		txtRow.setColumns(10);
		
		//ȡ����ť
		btCancel = new JButton("\u53D6\u6D88");
		btCancel.setFont(new Font("����", Font.PLAIN, 14));
		btCancel.setBounds(292, 115, 95, 25);
		contentPanel.add(btCancel);
		btCancel.addActionListener(goAction);
		
		//ת����ť
		btGo = new JButton("\u8F6C\u5230");
		btGo.setFont(new Font("����", Font.PLAIN, 14));
		btGo.setBounds(160, 116, 95, 25);
		contentPanel.add(btGo);
		btGo.addActionListener(goAction);
	}
}
