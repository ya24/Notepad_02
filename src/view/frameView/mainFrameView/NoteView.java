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
 * @author Ҷ�ŷ�
 * @date 2015-06-22
 * @desc ���±�ͼ���û���������
 * 
 */

public class NoteView extends JFrame {

	public Share share; // �����¼���
	public MenuActionEvents menuAction; // ������˵������Ķ����¼���
	public PopMenu popMenu; // ����ʽ�˵�������
	public LineCheckMenuItemEvents lineCheckEvent; // �������Զ����и�ѡ�˵�������ѡ���¼���
	public StatusCheckMenuItemEvents statusCheckEvent; // ������״̬����ѡ�˵�������ѡ���¼���
	MyCaretListener my; // ���ı������λ����ʱ��������

	//���õ����
	public JMenuBar menuBar; // �˵���

	public JMenu fileMenu, editMenu, formMenu, seeMenu, helpMenu; // �˵�

	public JMenuItem newMi, openMi, saveMi, saveAsMi, setPageMi, printMi,
			exitMi; // �ļ��˵��µĲ˵���

	public JMenuItem undoMi, cutMi, copyMi, pasteMi, delMi, seekMi, seekNextMi,
			replaceMi, goMi, allMi, dateMi; // �༭�˵��µĲ˵���

	public JMenuItem fontMi; // ��ʽ�˵��µ�����˵���
	public JCheckBoxMenuItem lineCheck; // ��ʽ�˵��µ��Զ����и�ѡ�˵�

	public JCheckBoxMenuItem statusCheck; // �鿴�˵��µ�״̬����ѡ�˵���

	public JMenuItem aboutMi; // �����˵��µĲ˵���

	public JTextArea area; // �ı���
	public JScrollPane roll; // ������

	public JLabel rcLab; // ��ǩ����ʾ״̬����¼�Ĺ�����ڵ��кź��к�

	public UndoManager udm; // ����ʵ�ֳ������ܵ���

	/**
	 * @author Ҷ�ŷ�
	 * @param my
	 * @date 2015-06-22
	 * @desc ���췽�������빲���¼��ࡢ������˵������Ķ����¼��ࡢ����ʽ�˵������ࡢ�������Զ����и�ѡ�˵�������ѡ���¼��ࡢ
	 *       ������״̬����ѡ�˵�������ѡ���¼��ࡢ���ҶԻ������¼���\���ı���������ʱ�����������,ʵ������Ա����
	 * @param share	�����¼������
	 * @param menuAction	������˵������Ķ����¼������
	 * @param popMenu	����ʽ�˵����������
	 * @param lineCheckEvent	�������Զ����и�ѡ�˵�������ѡ���¼������
	 * @param statusCheckEvent	������״̬����ѡ�˵�������ѡ���¼������
	 * @param my	���ı������λ����ʱ��������
	 *            
	 */
	public NoteView(Share share, final MenuActionEvents menuAction,
			final PopMenu popMenu, LineCheckMenuItemEvents lineCheckEvent,
			StatusCheckMenuItemEvents statusCheckEvent, MyCaretListener my) {

		// ʵ���������
		this.share = share;
		this.menuAction = menuAction;
		this.popMenu = popMenu;
		this.lineCheckEvent = lineCheckEvent;
		this.statusCheckEvent = statusCheckEvent;
		this.my = my;

		// ���ݱ������
		statusCheckEvent.setView(this);
		lineCheckEvent.setView(this);
		menuAction.setView(this);
		my.setView(this);

		
		menuBar = new JMenuBar(); // �����˵���
		this.setJMenuBar(menuBar); // ��Ӳ˵���

		build(); // �����˵����ı������

		addMenuBar(); // ���˵���ӵ��˵���

		addMenuItem();// ���˵�����ӵ��˵���

		setFont(); // ���ò˵����˵���������С

		setAccelerator(); // ���ÿ�ݼ�
		
		setEnable();//���ò˵���״̬

		// �¼�������ע��

		// �˵��ഥ���Ķ����¼�
		addMenuActionListener();

		// ��ѡ�˵�������ѡ���¼�
		addCheckItemListener();

		/**------ �ı����ڵ������ ���� ����ʽ�˵��¼� ---------------*/
		area.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // �ͷ����ʱ�������¼�
				if (e.isPopupTrigger()) { // �ж�����¼��Ƿ����ɵ����˵�����
					// public void show(Component invoker, int x, int
					// y)����������ߵ�����ռ��е�
					// λ�� X��Y ��ʾ�����˵���
					popMenu.show((Component) e.getSource(), e.getX(), e.getY());
				}
				if(area.getSelectedText()!=null){
					popMenu.popCut.setEnabled(true);
					popMenu.popCopy.setEnabled(true);
					popMenu.popDel.setEnabled(true);
				}
			}
		});
		
		
		/**=============�༭�˵��¼�����=======================================*/
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

		

		// �����������
		this.setBounds(300, 100, 800, 500); // ���ô����С
		this.setTitle("�ޱ��� �� ���±�"); // ���ü��±�����
		this.setIconImage((new ImageIcon("window���±�.jpg")).getImage()); // ����ͼ�꣬
		this.setVisible(true); // ��������Ϊ�ɼ�
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // �رղ���
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				menuAction.exit();
			}
		});
		
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc �����˵����
	 */
	private void build() {
		// TODO �Զ����ɵķ������

		// �����˵�
		fileMenu = new JMenu("�ļ�(F)"); // �����ļ��˵�
		editMenu = new JMenu("�༭(E)"); // �����༭�˵�
		formMenu = new JMenu("��ʽ(O)"); // ������ʽ�˵�
		seeMenu = new JMenu("�鿴(V)"); // �����鿴�˵�
		helpMenu = new JMenu("����(H)"); // ���������˵�

		// �����ļ��˵��µĲ˵���
		newMi = new JMenuItem("�½�(N)");
		openMi = new JMenuItem("��(O)");
		saveMi = new JMenuItem("����(S)");
		saveAsMi = new JMenuItem("���Ϊ(A)");
		setPageMi = new JMenuItem("ҳ������(U)");
		printMi = new JMenuItem("��ӡ(P)");
		exitMi = new JMenuItem("�˳�(X)");

		// �����༭�˵��µĲ˵���
		undoMi = new JMenuItem("����(U)");
		cutMi = new JMenuItem("����(T)");
		copyMi = new JMenuItem("����(C)");
		pasteMi = new JMenuItem("ճ��(P)");
		delMi = new JMenuItem("ɾ��(L)");
		seekMi = new JMenuItem("����(F)");
		seekNextMi = new JMenuItem("������һ��(N)");
		replaceMi = new JMenuItem("�滻(R)");
		goMi = new JMenuItem("ת��(G)");
		allMi = new JMenuItem("ȫѡ(A)");
		dateMi = new JMenuItem("ʱ��/����(D)");

		// ������ʽ�˵��µĲ˵���
		lineCheck = new JCheckBoxMenuItem("�Զ�����(W)");
		fontMi = new JMenuItem("����(F)");

		// �����鿴�˵��µĲ˵���
		statusCheck = new JCheckBoxMenuItem("״̬��(S)");

		// ���������˵��µĲ˵���
		aboutMi = new JMenuItem("���ڼ��±�(A)");

		area = new JTextArea(); // �����ı���
		roll = new JScrollPane(area); // ������������
		this.add(roll); // ������������ӵ���ܴ�����

		// ���ı������ݽ�����ʱ����
		area.addCaretListener(my);

		// ���ı����ݵĴ���
		udm = new UndoManager();
		area.getDocument().addUndoableEditListener(udm);
		
		//���ı��ĵ����ĵļ���
		area.getDocument().addDocumentListener(menuAction);

		// ������ǩ
		rcLab = new JLabel();
		// ���ñ�ǩ��С
		rcLab.setSize(800, 50);
		// ��ǩ���ı����뷽ʽ�����Ҷ���
		rcLab.setHorizontalAlignment(JLabel.RIGHT);
		// �ѱ�ǩ��ӵ����±���ܵ��ϲ�
		this.add(rcLab, BorderLayout.SOUTH);

	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���˵���ӵ��˵���
	 */
	private void addMenuBar() {
		// TODO �Զ����ɵķ������
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formMenu);
		menuBar.add(seeMenu);
		menuBar.add(helpMenu);
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���˵�����ӵ��˵���
	 */
	private void addMenuItem() {
		// TODO �Զ����ɵķ������
		// ���ļ��˵��µĲ˵�����ӵ��ļ��˵���
		fileMenu.add(newMi);
		fileMenu.add(openMi);
		fileMenu.add(saveMi);
		fileMenu.add(saveAsMi);
		fileMenu.addSeparator();// ��ӷָ���
		fileMenu.add(setPageMi);
		fileMenu.add(printMi);
		fileMenu.addSeparator();// ��ӷָ���
		fileMenu.add(exitMi);

		// ���༭�˵��µĲ˵�����ӵ��༭�˵���
		editMenu.add(undoMi);
		editMenu.addSeparator();// ��ӷָ���
		editMenu.add(cutMi);
		editMenu.add(copyMi);
		editMenu.add(pasteMi);
		editMenu.add(delMi);
		editMenu.addSeparator();// ��ӷָ���
		editMenu.add(seekMi);
		editMenu.add(seekNextMi);
		editMenu.add(replaceMi);
		editMenu.add(goMi);
		editMenu.addSeparator();// ��ӷָ���
		editMenu.add(allMi);
		editMenu.add(dateMi);

		// ����ʽ�˵��µĲ˵�����ӵ���ʽ�˵���
		formMenu.add(lineCheck);
		formMenu.add(fontMi);

		// ���鿴�˵��µĲ˵�����ӵ��鿴�˵���
		seeMenu.add(statusCheck);

		// �������˵��µĲ˵�����ӵ������˵���
		helpMenu.add(aboutMi);
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���ò˵����˵���������С
	 */
	private void setFont() {
		// TODO �Զ����ɵķ������
		// ���ò˵������С
		fileMenu.setFont(share.font2);
		editMenu.setFont(share.font2);
		formMenu.setFont(share.font2);
		seeMenu.setFont(share.font2);
		helpMenu.setFont(share.font2);

		// �����ļ��˵��²˵���������С
		newMi.setFont(share.font1);
		openMi.setFont(share.font1);
		saveMi.setFont(share.font1);
		saveAsMi.setFont(share.font1);
		setPageMi.setFont(share.font1);
		printMi.setFont(share.font1);
		exitMi.setFont(share.font1);

		// ���ñ༭�˵��µĲ˵���������С
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

		// ���ø�ʽ�˵��µĲ˵���������С
		lineCheck.setFont(share.font1);
		fontMi.setFont(share.font1);

		// ���ò鿴�˵��µĲ˵���������С
		statusCheck.setFont(share.font1);

		// ���ð����˵��µĲ˵���������С
		aboutMi.setFont(share.font1);

		// �����ı��������С
		area.setFont(share.font2);

		// ����״̬����ǩ�������С
		rcLab.setFont(share.font2);

	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���ÿ�ݼ�
	 */
	private void setAccelerator() {
		// TODO �Զ����ɵķ������
		// �½��˵���ݼ�Ctrl+N
		newMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		// �򿪲˵���ݼ�Ctrl+O
		openMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		// ����˵���ݼ�Ctrl+S
		saveMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		// ��ӡ�˵���ݼ�Ctrl+P
		printMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		// �����˵���ݼ�Ctrl+Z
		undoMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		// ���в˵���ݼ�Ctrl+X
		cutMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		// ���Ʋ˵���ݼ�Ctrl+C
		copyMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		// ճ���˵���ݼ�Ctrl+V
		pasteMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		// �滻�˵���ݼ�Ctrl+H
		replaceMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				ActionEvent.CTRL_MASK));
		// ת���˵���ݼ�Ctrl+G
		goMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				ActionEvent.CTRL_MASK));
		// ȫѡ�˵���ݼ�Ctrl+A
		allMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.CTRL_MASK));

	}
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���ò˵�������
	 */
	private void setEnable(){
		//�տ�ʼ�ı������ݣ����������С����ơ�ɾ�������ҡ�������һ���˵����ɱ༭
		undoMi.setEnabled(false);
		cutMi.setEnabled(false);
		copyMi.setEnabled(false);
		delMi.setEnabled(false);
		seekMi.setEnabled(false);
		seekNextMi.setEnabled(false);
	}
	
	
	

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc �˵��ഥ���Ķ����¼�
	 */
	private void addMenuActionListener() {
		// TODO �Զ����ɵķ������

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
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ��ѡ�˵�������ѡ���¼�
	 */
	private void addCheckItemListener() {
		// TODO �Զ����ɵķ������
		lineCheck.addItemListener(lineCheckEvent);
		statusCheck.addItemListener(statusCheckEvent);
	}

	

}
