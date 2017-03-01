package view.otherView.popupMenuView;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import control.menuControl.menuItemControl.MenuActionEvents;
import control.otherControl.Share;

/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-06-22
 * @desc ����ʽ�˵���������ࣨ����ʽ�˵��˵���:���������У����ƣ�ճ����ɾ����ȫѡ��
 *
 */
public class PopMenu extends JPopupMenu{
	
	public JMenuItem popUndo,popCut,popCopy,popPaste,popDel,popAll;	//����ʽ�˵��Ĳ˵���
	
	public Share share;	//������
	public MenuActionEvents menuAction;	//���������˵������¼���
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-23
	 * @desc ���췽�������빲���࣬���������˵������¼��࣬ʵ������Ա����
	 * @param share
	 * @param menuAction
	 */
	public PopMenu(Share share,MenuActionEvents menuAction){
		
		this.share=share;
		this.menuAction=menuAction;
		//�����������˵������¼��ഫ�뱾�����(����ʽ�˵����������)
		menuAction.setPopMenu(this);
		
		builder();	//��������ʽ�˵��Ĳ˵���
		
		insert();	//���˵�����ӵ�����ʽ�˵���
		
		font();	//���ò˵�������
		
		addListener();	//ע�����
		
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ��������ʽ�˵��Ĳ˵���
	 */
	private void builder() {
		// TODO �Զ����ɵķ������
		
		popUndo=new JMenuItem("����");
		popCut=new JMenuItem("����");
		popCopy=new JMenuItem("����");
		popPaste=new JMenuItem("ճ��");
		popDel=new JMenuItem("ɾ��");
		popAll=new JMenuItem("ȫѡ");
		
		popUndo.setEnabled(false);
		popCut.setEnabled(false);
		popCopy.setEnabled(false);
		popDel.setEnabled(false);
		popAll.setEnabled(false);
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���˵�����ӵ�����ʽ�˵���
	 */
	private void insert() {
		// TODO �Զ����ɵķ������
		
		this.add(popUndo);
		this.add(popCut);
		this.add(popCopy);
		this.add(popPaste);
		this.add(popDel);
		this.add(popAll);

	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���ò˵�������
	 */
	private void font() {
		// TODO �Զ����ɵķ������
		
		popUndo.setFont(share.font1);
		popCut.setFont(share.font1);
		popCopy.setFont(share.font1);
		popPaste.setFont(share.font1);
		popDel.setFont(share.font1);
		popAll.setFont(share.font1);
		
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ע�����
	 */
	private void addListener() {
		// TODO �Զ����ɵķ������
		
		popUndo.addActionListener(menuAction);
		popCut.addActionListener(menuAction);
		popCopy.addActionListener(menuAction);
		popPaste.addActionListener(menuAction);
		popDel.addActionListener(menuAction);
		popAll.addActionListener(menuAction);
	}
	
}
