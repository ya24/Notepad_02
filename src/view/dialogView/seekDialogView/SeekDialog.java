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
 * @author Ҷ�ŷ�
 * @date 2015-07-22
 * @desc �����Զ���Ի��������
 *
 */

public class SeekDialog extends JDialog {

	//���õ����
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public JTextField txtSeek;	//"��������"�ı���
	public JButton btSeekNext ;	//"������һ��"��ť
	public JButton btnCancel;	//"ȡ��"��ť
	public JRadioButton rdbtnUp ;	//���ϵ�ѡ��ť
	public JRadioButton rdbtnDown ;	//���µ�ѡ��ť
	public JCheckBox chckbxc ;	//���ִ�Сд��ѡ��ť

	
	MenuActionEvents menuAction;	//���������˵������¼���
	SeekDialogActionEvents seekAction;	//���ҶԻ������¼���
	SeekDialogItemEvents seekItem;	//���ҶԻ���ѡ���¼���
	

	/**
	 * Create the dialog.
	 * @param menuAction 
	 * @param seekItem 
	 * @param seekAction 
	 * @param seekEvent 
	 */
	public SeekDialog(MenuActionEvents menuAction, SeekDialogActionEvents seekAction, SeekDialogItemEvents seekItem) {
		
		//ʵ�������������˵������¼��࣬�����뱾�����
		this.menuAction=menuAction;
		menuAction.setSeekDialog(this);
		
		//ʵ�������ҶԻ������¼��࣬�����뱾�����
		this.seekAction=seekAction;
		seekAction.setSeekDialog(this);
		
		//ʵ�������ҶԻ���ѡ���¼��࣬�����뱾�����
		this.seekItem=seekItem;
		seekItem.setSeekDialog(this);
		
		
		//�Ի������
		setTitle("\u67E5\u627E");
		setBounds(100, 100, 500, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//���������ݡ���ǩ
		JLabel lbSeek = new JLabel("\u67E5\u627E\u5185\u5BB9(N):");
		lbSeek.setFont(new Font("����", Font.PLAIN, 14));
		lbSeek.setBounds(10, 10, 89, 28);
		contentPanel.add(lbSeek);
		
		//"��������"�ı���
		txtSeek = new JTextField();
		txtSeek.setFont(new Font("����", Font.PLAIN, 14));
		txtSeek.setBounds(97, 11, 198, 27);
		contentPanel.add(txtSeek);
		txtSeek.setColumns(10);
		
		txtSeek.getDocument().addDocumentListener(seekAction);
		
		//"������һ��"��ť
		btSeekNext = new JButton("\u67E5\u627E\u4E0B\u4E00\u4E2A(F)");
		btSeekNext.setFont(new Font("����", Font.PLAIN, 14));
		btSeekNext.setBounds(321, 10, 135, 28);
		contentPanel.add(btSeekNext);
		btSeekNext.addActionListener(seekAction);
		btSeekNext.setEnabled(false);
		
		//��ȡ������ť
		btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setFont(new Font("����", Font.PLAIN, 14));
		btnCancel.setBounds(321, 64, 135, 28);
		contentPanel.add(btnCancel);
		btnCancel.addActionListener(seekAction);
		
		//���÷���ѡ��ť�����
		JPanel DirectionPan = new JPanel();
		DirectionPan.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u65B9\u5411", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		DirectionPan.setBounds(120, 64, 175, 64);
		contentPanel.add(DirectionPan);
		DirectionPan.setLayout(null);
		
		//���ϵ�ѡ��ť 
		rdbtnUp = new JRadioButton("\u5411\u4E0A(U)");
		buttonGroup.add(rdbtnUp);
		rdbtnUp.setFont(new Font("����", Font.PLAIN, 14));
		rdbtnUp.setBounds(6, 24, 75, 23);
		DirectionPan.add(rdbtnUp);
		rdbtnUp.addItemListener(seekItem);
		
		//���µ�ѡ��ť
		rdbtnDown = new JRadioButton("\u5411\u4E0B(D)");
		buttonGroup.add(rdbtnDown);
		rdbtnDown.setSelected(true);
		rdbtnDown.setFont(new Font("����", Font.PLAIN, 14));
		rdbtnDown.setBounds(83, 24, 86, 23);
		DirectionPan.add(rdbtnDown);
		rdbtnDown.addItemListener(seekItem);
		
		//���ִ�Сд��ѡ��ť
		chckbxc = new JCheckBox("\u533A\u5206\u5927\u5C0F\u5199(C)");
		chckbxc.setFont(new Font("����", Font.PLAIN, 14));
		chckbxc.setBounds(10, 134, 123, 28);
		contentPanel.add(chckbxc);
		chckbxc.addItemListener(seekItem);
	}
	

}
