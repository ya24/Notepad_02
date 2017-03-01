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
 * @author Ҷ�ŷ�
 * @date 2015-07-24
 * @desc �滻�Զ���Ի��������
 *
 */

public class ReplaceDialog extends JDialog {
	
	ReplaceDialogActionEvents replaceAction;	//�滻�Ի������¼���
	MenuActionEvents menuAction;	//���������˵������¼���

	//�滻�Ի������
	private final JPanel contentPanel = new JPanel();
	public JTextField txtSeek;		//�����ı���
	public JTextField txtReplace;	//�滻�ı���
	public JButton btSeekNext;		//������һ����ť
	public JButton btReplace;		//�滻��ť
	public JButton btReplaceAll;	//ȫ���滻��ť
	public JButton btCancel; 	//ȡ����ť
	private JCheckBox chckbxc;	 //���ִ�Сд��ѡ��ť
	
	public boolean flag=false;		//���ִ�Сд��ʶ������trueΪ���ִ�Сд��falseΪ�����ִ�Сд
	
	/**
	 * Create the dialog.
	 * @param menuAction 
	 * @param replaceAction 
	 */
	public ReplaceDialog(MenuActionEvents menuAction, ReplaceDialogActionEvents replaceAction) {
		
		this.replaceAction=replaceAction;
		replaceAction.setReplaceDialog(this);	//�����滻�Զ���Ի�����������
		this.menuAction=menuAction;
		menuAction.setReplaceDialog(this);	//�������������˵������¼������
		
		//����滻�Ի���
		setTitle("\u66FF\u6362");
		setBounds(100, 100, 520, 229);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//���ұ�ǩ
		JLabel lbSeek = new JLabel("\u67E5\u627E\u5185\u5BB9(N):");
		lbSeek.setFont(new Font("����", Font.PLAIN, 14));
		lbSeek.setBounds(10, 10, 84, 25);
		contentPanel.add(lbSeek);
		
		//�����ı���
		txtSeek = new JTextField();
		txtSeek.setBounds(104, 12, 210, 25);
		contentPanel.add(txtSeek);
		txtSeek.setColumns(10);
		txtSeek.getDocument().addDocumentListener(replaceAction);
		
		//�滻��ǩ
		JLabel lbReplace = new JLabel("\u66FF\u6362\u4E3A(P):");
		lbReplace.setFont(new Font("����", Font.PLAIN, 14));
		lbReplace.setBounds(10, 45, 84, 25);
		contentPanel.add(lbReplace);
		
		//�滻�ı���
		txtReplace = new JTextField();
		txtReplace.setBounds(104, 47, 210, 25);
		contentPanel.add(txtReplace);
		txtReplace.setColumns(10);
		
		//������һ����ť
		btSeekNext = new JButton("\u67E5\u627E\u4E0B\u4E00\u4E2A(F)");
		btSeekNext.setFont(new Font("����", Font.PLAIN, 14));
		btSeekNext.setBounds(337, 11, 140, 25);
		contentPanel.add(btSeekNext);
		btSeekNext.addActionListener(replaceAction);
		btSeekNext.setEnabled(false);
		
		//�滻��ť
		btReplace = new JButton("\u66FF\u6362(R)");
		btReplace.setFont(new Font("����", Font.PLAIN, 14));
		btReplace.setBounds(337, 47, 140, 25);
		contentPanel.add(btReplace);
		btReplace.addActionListener(replaceAction);
		btReplace.setEnabled(false);
		
		//ȫ���滻��ť
		btReplaceAll = new JButton("\u5168\u90E8\u66FF\u6362(A)");
		btReplaceAll.setFont(new Font("����", Font.PLAIN, 14));
		btReplaceAll.setBounds(337, 82, 140, 25);
		contentPanel.add(btReplaceAll);
		btReplaceAll.addActionListener(replaceAction);
		btReplaceAll.setEnabled(false);
		
		//ȡ����ť
		btCancel = new JButton("\u53D6\u6D88");
		btCancel.setFont(new Font("����", Font.PLAIN, 14));
		btCancel.setBounds(337, 117, 140, 25);
		contentPanel.add(btCancel);
		btCancel.addActionListener(replaceAction);
		
		//���ִ�Сд��ѡ��ť
		chckbxc = new JCheckBox("\u533A\u5206\u5927\u5C0F\u5199(C)");
		chckbxc.setFont(new Font("����", Font.PLAIN, 14));
		chckbxc.setBounds(6, 138, 133, 23);
		contentPanel.add(chckbxc);
		//��ѡ��ť���ѡ���¼�����ȡ�û���ѡ��
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
