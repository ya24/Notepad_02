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
 * @author Ҷ�ŷ�
 * @date 2015-06-22
 * @desc �����Զ���Ի�����
 *
 */
public class FontDialog extends JDialog{

	public NoteView view;	//���±������������
	public Share share;		//������
	public FontListSelectionEvents listSelect;	//����Ի����� �б��ѡ���¼���
	public MenuActionEvents menuAction;	//���������˵������Ķ����¼���
	
	//���õ����
	public JTextField show;	//��ʾ��������Ԥ��Ч�����ı���
	public JLabel labFont,labStyle,labSize;	//���壬���Σ���С �ȱ�ǩ
	public JTextField txtFont,txtStyle,txtSize;	//���壬���Σ���С�ı���,������ʾѡ�������
	public JList fontList,styleList,sizeList;	//���壬���Σ���С�б��
	public JScrollPane rollFont,rollStyle,rollSize;	//��������
	
	public JButton butOk,butCancel;	//ȷ����ȡ����ť
	
	//��ȡϵͳ�����п������������
	public GraphicsEnvironment e=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public String[] UsableFont=e.getAvailableFontFamilyNames();
	
	//�����б���е�ѡ������
	public String[] style={"����","��б","����","���� ��б"};	
	
	//��С�б���е�ѡ������
	public String[] size={"8","9","10","11","12","14","16","18","20","22","24","26","28",
			"36","48","72","����","С��","һ��","Сһ","����","С��","����","С��","�ĺ�",
			"С��","���","С��","����","С��","�ߺ�","�˺�"};	
	
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���췽����������±�����������ࡢ�����ࡢ����Ի����� �б��ѡ���¼��ࡢ���������˵������Ķ����¼������,
	 * 			ʵ������Ա����
	 * @param view	���±���������������
	 * @param share	������
	 * @param listSelect	����Ի����� �б��ѡ���¼���
	 * @param menuAction	���������˵������Ķ����¼���
	 */
	public FontDialog(NoteView view, Share share,
			FontListSelectionEvents listSelect, MenuActionEvents menuAction){
	
		//��ƶԻ���
		super(view);	//ӵ����Ϊ���±�ͼ���û�������
		this.setTitle("����");	//���öԻ������
		this.setModal(true);	//���öԻ���ģʽΪģʽ״̬
		this.setLayout(null);	//���þ��Բ���
		this.setBounds(400,100,420,580);	//�Ի���λ�ü���С
		
		//ʵ�������Ա����
		this.view=view;
		this.share=share;
		this.listSelect=listSelect;
		this.menuAction=menuAction;
		
		//������Ի����� �б��ѡ���¼��ࡢ���������˵������Ķ����¼��ഫ�ݱ����������Ի�����������
		listSelect.setDialog(this);
		menuAction.setDialog(this);
		
		build();	//�������
		
		bound();//�������λ�ü���С
		
		insert();	//������
		
		font();//����������������С
		
		addListSelection();//�б��ѡ���¼�ע�����
		
		buttonEvent();	//����Ի���ť�����Ķ����¼�����
	}


	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc �������
	 */
	private void build() {
		// TODO �Զ����ɵķ������
		
		//������ǩ
		labFont=new JLabel("����(F)");	
		labStyle=new JLabel("����(Y)");
		labSize=new JLabel("��С(S)");
		
		//�����ı���
		txtFont=new JTextField();
		txtStyle=new JTextField(); 
		txtSize=new JTextField();
		show=new JTextField("��΢���������\nAaBbYyZz");
		show.setBorder(new TitledBorder("ʾ��"));
		show.setHorizontalAlignment(JTextField.CENTER);
		
		//�����б��
		fontList=new JList(UsableFont);	//�����б��
		styleList=new JList(style);	//�����б��
		sizeList=new JList(size);	//��С�б��
		
		//������������
		rollFont=new JScrollPane(fontList);
		rollStyle=new JScrollPane(styleList);
		rollSize=new JScrollPane(sizeList);
		
		//ȷ����ȡ����ť
		butOk=new JButton("ȷ��");
		butCancel=new JButton("ȡ��");	
	
	}


	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc �������λ�ü���С
	 */
	private void bound() {
		// TODO �Զ����ɵķ������
		
		//���ñ�ǩλ�ü���С
		labFont.setBounds(20, 20, 200, 30);
		labStyle.setBounds(240, 20, 70, 30);
		labSize.setBounds(330, 20, 50, 30);
		
		//�����ı���λ�ü���С
		txtFont.setBounds(20, 50, 200, 30);
		txtStyle.setBounds(240, 50, 70, 30);
		txtSize.setBounds(330, 50, 50, 30);
		
		
		//�����б��Ĺ�������λ�ü���С
		rollFont.setBounds(20, 80, 200, 230);
		rollStyle.setBounds(240, 80, 70, 230);
		rollSize.setBounds(330, 80, 50, 230);
		
		//����Ԥ���ı���λ�ü���С
		show.setBounds(20, 330, 360, 150);
		
		//���ð�ťλ�ü���С
		butOk.setBounds(200, 500, 80, 30);
		butCancel.setBounds(300, 500, 80, 30);
		
	}


	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ������
	 */
	private void insert() {
		// TODO �Զ����ɵķ������
		
		//��ӱ�ǩ
		this.add(labFont);
		this.add(labStyle);
		this.add(labSize);
		
		//����ı���
		this.add(txtFont);
		this.add(txtStyle);
		this.add(txtSize);
		
		//����б���������
		this.add(rollFont);
		this.add(rollStyle);
		this.add(rollSize);
		
		//���Ԥ���ı���
		this.add(show);
	
		//��Ӱ�ť
		this.add(butOk);
		this.add(butCancel);
	}


	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ��������ı�����
	 */
	private void font() {
		// TODO �Զ����ɵķ������
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
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc �б��ѡ���¼�ע�����
	 */
	private void addListSelection() {
		// TODO �Զ����ɵķ������
		fontList.addListSelectionListener(listSelect);
		styleList.addListSelectionListener(listSelect);
		sizeList.addListSelectionListener(listSelect);
		
	}
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ����Ի���ť�����Ķ����¼�����
	 */
	private void buttonEvent(){
		//��ť�����¼�
		butOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FontDialog.this.dispose();	//�رնԻ���
				view.area.setFont(listSelect.font2);//�����ı�������
			}

		});
		
		//ȡ����ť�����¼�
		butCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FontDialog.this.dispose();	//���ȡ����ť���Զ�������Ի���ر�
				
			}
		});
	}
}
