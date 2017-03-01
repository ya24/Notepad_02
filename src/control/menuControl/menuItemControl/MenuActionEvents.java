package control.menuControl.menuItemControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;

import control.otherControl.Share;
import view.dialogView.fontDialogView.FontDialog;
import view.dialogView.goDialogView.GoDialog;
import view.dialogView.replaceDialogView.ReplaceDialog;
import view.dialogView.seekDialogView.SeekDialog;
import view.frameView.mainFrameView.NoteView;
import view.otherView.popupMenuView.PopMenu;

/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-06-22
 * @desc ���������˵������¼���
 */

public class MenuActionEvents implements ActionListener, DocumentListener {

	Share share; // ������
	NoteView view; // ���±������������
	PopMenu popMenu; // ����ʽ�˵�������
	FontDialog dialog;// �����Զ���Ի��������
	SeekDialog seekDialog; // �����Զ���Ի��������
	ReplaceDialog replaceDialog; // �滻�Զ���Ի��������
	GoDialog goDialog; // ת���Զ���Ի��������

	File f = null; // ��ȡ�ļ�ѡ����ѡ�е��ļ���
	public boolean flag = false; // ��ʶ�Ƿ�ִ�б��涯����true�����ѱ���

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���췽�����빲�������
	 * @param share
	 */
	public MenuActionEvents(Share share) {
		this.share = share;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ������±���������������
	 * @param view
	 */
	public void setView(NoteView view) {
		this.view = view;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���뵯��ʽ�˵����������
	 * @param fontDialog
	 */
	public void setDialog(FontDialog dialog) {
		this.dialog = dialog;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���������Զ���Ի�����������
	 * @param popMenu
	 */
	public void setPopMenu(PopMenu popMenu) {
		this.popMenu = popMenu;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-22
	 * @desc ��������Զ���Ի�����������
	 * @param seekDialog
	 */
	public void setSeekDialog(SeekDialog seekDialog) {
		this.seekDialog = seekDialog;

	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-24
	 * @desc �����滻�Զ���Ի�����������
	 * @param replaceDialog
	 */
	public void setReplaceDialog(ReplaceDialog replaceDialog) {
		this.replaceDialog = replaceDialog;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-25
	 * @desc ����ת���Զ���Ի�����������
	 * @param goDialog
	 */
	public void setGoDialog(GoDialog goDialog) {
		this.goDialog = goDialog;
	}

	/**
	 * ===================ActionListener��������====================================
	 * ==
	 */
	public void actionPerformed(ActionEvent e) {

		// ��ȡ�¼�Դ
		JMenuItem source = (JMenuItem) e.getSource();

		/* ----------------���½����Ĵ���------------------------ */
		if (view.newMi.equals(source)) {
			// ����½��˵���ʱ����Ҫ���ı����е����ݽ����жϣ������ݵ���ȷ�϶Ի�����ѯ�û��Ƿ���Ҫ���б���
			// ����ı�������
			if (!("".equals(view.area.getText()))) {
				if (flag) {
					// ����ı������Ա���
					view.area.setText(""); // ���±��������
					view.setTitle("�ޱ��� �� ���±�"); // ���±����⻹ԭ
				} else {
					// ����ı�����δ���棬�������������newDeal()����
					newDeal();
				}

			}
			f = null; // �½��˵�������f��ԭ
			flag = false; // �½��˵�������flag��ԭ
		}

		/* ---------------���򿪡��Ĵ���---------------------- */
		else if (view.openMi.equals(source)) {
			// �򿪵�ʱ����ȻҪ�����ı������ݵ��ж�

			// ���������
			if (!("".equals(view.area.getText()))) {
				if (flag) {
					// �ı��е������ѱ���
					openDeal();
				} else {
					// �ı��е�����δ���棬���ɴ˷�������
					beforeOpen();
				}

			}

			// ���û�����ݣ������崦��������openDeal()��������
			else if ("".equals(view.area.getText())) {
				if (view.getTitle().equals("�ޱ��� �� ���±�")) {
					openDeal();
				} else {
					beforeOpen();// ���ɴ˷�������
				}

			}
		}

		/* --------------�����桱�Ĵ���---------------- */
		else if (view.saveMi.equals(source)) {
			// �ж��Ƿ��ȡ���ļ�ѡ����ѡ�е��ļ�
			if (f == null) {
				// Ϊnull��δ��ȡ���������������saveDeal()����
				saveDeal();
			} else if (f != null && f.getName().equals(view.getTitle())) {
				try {
					// ��������ļ�
					FileWriter fwr = new FileWriter(f);
					// ���ı����е���Ϣд���ļ���
					fwr.write(view.area.getText());
					// �ͷ���Դ
					fwr.close();

					flag = true; // ���涯����ִ��

				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}

			}

		}

		/* ---------------�����Ϊ���Ĵ���----------------- */
		else if (view.saveAsMi.equals(source)) {
			saveDeal();
		}

		/*-------------- ��ҳ�����á��Ĵ���---------------*/
		else if (view.setPageMi.equals(source)) {
			JOptionPane.showMessageDialog(view, "�˹������ڿ���");
		}

		/*------------- ����ӡ���Ĵ���--------------*/
		else if (view.printMi.equals(source)) {
			JOptionPane.showMessageDialog(view, "�˹������ڿ���");
		}

		/* -------------���˳����Ĳ���---------------- */
		else if (view.exitMi.equals(source)) {
			exit();

		}

		/* -------------------���������Ĳ���--------------- */
		else if (view.undoMi.equals(source) || popMenu.popUndo.equals(source)) {
			// ������Գ���
			if (view.udm.canUndo()) {
				// ����UndoManager���undo����������ִ�г�������
				view.udm.undo();
			} else {
				JOptionPane.showMessageDialog(view, "�޷�����������");
			}

		}

		/* ----------------"����"�Ĳ���---------------- */
		else if (view.cutMi.equals(source) || popMenu.popCut.equals(source)) {

			// public void cut()�������ı�ģ���е�ǰѡ���ķ�Χ���䵽ϵͳ�����壬����ģ�����Ƴ����ݡ��������õ�ǰѡ��

			view.area.cut();

		}

		/* ------------------�����ơ��Ĳ���----------------- */
		else if (view.copyMi.equals(source) || popMenu.popCopy.equals(source)) {
			// public void copy()�������ı�ģ���е�ǰѡ���ķ�Χ���䵽ϵͳ�����壬�����ı�ģ���б������ݡ���ǰѡ������ԭ����

			view.area.copy();

		}

		/* -----------------��ճ�����Ĳ���----------------- */
		else if (view.pasteMi.equals(source) || popMenu.popPaste.equals(source)) {
			// public void paste()��ϵͳ����������ݴ��䵽�������ı�ģ���С�����ڹ�����ͼ����ѡ����
			// ���ݣ���ʹ�ü�����������滻�������û��ѡ�������ݣ��򽫼�������������ͼ�е�ǰ����λ�õ�ǰ
			// �档���������Ϊ�գ���ִ���κβ�����

			view.area.paste();
		}

		/* ------------------"ɾ��"�Ĳ���-------------------- */
		else if (view.delMi.equals(source) || popMenu.popDel.equals(source)) {
			// public void replaceSelection(String content)�ø����ַ�������ʾ���������滻��ǰ
			// ѡ�������ݡ����û��ѡ������ݣ���ò�������������ı������û���滻�ı�����ò����Ƴ���ǰѡ��
			// �����ݡ�
			view.area.replaceSelection("");
		}

		/* -----------------�����ҡ��Ĳ���--------------------- */
		else if (view.seekMi.equals(source)) {
			seekDialog.setVisible(true);
		}

		/* ---------------��������һ�����Ĳ��� ----------------- */
		else if (view.seekNextMi.equals(source)) {
			seekNextOne();
		}

		/* ---------------���滻������-------------------- */
		else if (view.replaceMi.equals(source)) {
			replaceDialog.setVisible(true);
		}

		/* ----------------��ת�����Ĳ���------------------- */
		else if (view.goMi.equals(source)) {
			// ��ʾת���Ի���
			goDialog.setVisible(true);
			int row = 0;
			try {
				// ��ȡ������ڵ��У���Ŵ�1��ʼ
				row = view.area.getLineOfOffset(view.area.getCaretPosition()) + 1;
			} catch (BadLocationException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			// ���ı�������ʾ��ǰ������ڵ���
			goDialog.txtRow.setText(row + "");

		}

		/* ---------------��ȫѡ���Ĳ���--------------- */
		else if (view.allMi.equals(source) || popMenu.popAll.equals(source)) {
			// public void selectAll()ѡ�� TextComponent �е������ı���
			view.area.selectAll();
		}

		/* -----------------��ʱ��/���ڡ�����--------------- */
		else if (view.dateMi.equals(source)) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
			String s = sdf.format(date);
			view.area.setText(view.area.getText() + s);

		}

		/* -------------�����塱����------------------ */
		else if (view.fontMi.equals(source)) {
			dialog.setVisible(true);
		}

		/* -----------�����ڼ��±��� �Ĵ���-------------- */
		else if (view.aboutMi.equals(source)) {
			JOptionPane.showMessageDialog(view,
					"�˼��±�Ϊ�ڶ��汾\n��Ȩ�����߸�������\n\n\n����QQ��3066253051", "���ڼ��±�",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-05-23
	 * @desc �½���������
	 */
	private void newDeal() {

		// ȷ�϶Ի������ݣ���Ϣ�����⣬3����ť��������Ϣ
		int type1 = JOptionPane.showConfirmDialog(view.area, "�Ƿ񽫸��ı���"
				+ "�� �ޱ��⣿", "���±�", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE);

		// ���������ǡ��ǡ����ͽ��б���
		if (type1 == JOptionPane.YES_OPTION) {
			// ���������ǡ��ǡ����������������saveDeal()����
			saveDeal();

			view.area.setText(""); // ���������±��������
			view.setTitle("�ޱ��� �� ���±�"); // ���±�����Ҳ��ԭ
		}

		// ���������ǡ���
		else if (type1 == JOptionPane.NO_OPTION) {
			view.area.setText(""); // ���±��������
			view.setTitle("�ޱ��� �� ���±�"); // ���±����⻹ԭ
		}

	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ����򿪲˵����ı���������ʱ�Ĵ�����
	 */
	public void beforeOpen() {

		// ȷ�϶Ի������ݣ���Ϣ�����⣬3����ť��������Ϣ
		int type2 = JOptionPane.showConfirmDialog(view.area, "�Ƿ񽫸��ı���"
				+ "�� �ޱ��⣿", "���±�", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE);

		// ���������ǡ��ǡ����ͽ��б��棬�ٴ��ļ�
		if (type2 == JOptionPane.YES_OPTION) {
			// ���������ǡ��ǡ����������������saveDeal()����
			saveDeal();

			// ����ú��ٵ����򿪶Ի��򣬴�ʱ������������openDeal()��������
			openDeal();
		}
		// ����������"��"���򵯳��򿪶Ի���ִ�д��ļ�����
		else if (type2 == JOptionPane.NO_OPTION) {
			openDeal();
		}

	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���ļ��Ի��򷽷�����
	 */
	public void openDeal() {

		// ���õ�ǰ�ļ������������ı��ļ�(.txt)
		share.fc.setFileFilter(share.filter);

		// ��ʾ�ļ��򿪶Ի���
		int open = share.fc.showOpenDialog(view.area);
		// ����������ǡ�ȷ������ť
		if (open == JFileChooser.APPROVE_OPTION) {

			// ��ȡ�ļ��Ի������û�ѡ�е��ļ���
			String fileName1 = share.fc.getSelectedFile().getName();
			// ��ȡ�ļ��Ի������û�ѡ�е��ļ����ڵ�·��
			String path1 = share.fc.getCurrentDirectory().toString();

			// �û�ѡ�е��ļ��Ƿ����
			File file1 = share.fc.getCurrentDirectory(); // ���ص�ǰĿ¼
			String[] strArray = file1.list(); // ��ȡ��·������ʾ��Ŀ¼�е��ļ���Ŀ¼������String����
			// ����������
			for (int x = 0; x < strArray.length; x++) {
				// ����ļ������ڣ��������ѱ�����ĩβ�������Ի�����ʾ�û�
				if (!strArray[x].equals(fileName1) && x == strArray.length - 1) {
					JOptionPane.showMessageDialog(view, file1
							+ "\n�Ҳ����ļ���\n�����ļ����Ƿ���ȷ��Ȼ�����ԡ�", "��",
							JOptionPane.WARNING_MESSAGE);
					share.fc.showOpenDialog(view.area);	//������ʾ�ļ��򿪶Ի���
					break;
				}
				// ����ļ�����
				else if (strArray[x].equals(fileName1)) {
					// �Ȱ��ı��ļ����
					view.area.setText("");
					
					// ��װ�ļ�
					f = new File(path1 + "/" + fileName1);
					try {
						// �����ַ�����������
						BufferedReader br = new BufferedReader(
								new FileReader(f));
						// ��д����
						String readLine = "";
						while ((readLine = br.readLine()) != null) {

							// view.area.append(readLine + "\n");
							view.area.setText(view.area.getText() + readLine
									+ "\n");
						}
						// �ͷ���Դ
						br.close();

					} catch (Exception e1) {
						e1.printStackTrace();
					}

					view.setTitle(fileName1);// ��֮�󣬱���Ҫ��
					flag = true; // �մ򿪵��ļ�flag���ӦΪtrue����ʾ�ļ��ѱ���
					break;
				}
			}

		}
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ����Ի��򷽷��������ļ�����
	 */
	public void saveDeal() {

		// ���õ�ǰ�ļ������������ı��ļ�(.txt)
		share.fc.setFileFilter(share.filter);

		// ���ñ����ļ�Ĭ����չ����".txt"
		share.fc.setSelectedFile(new File(".txt"));

		// ��ʾ�ļ�����Ի���
		int save = share.fc.showSaveDialog(view.area);

		// �����������"ȷ��"��ť
		if (save == JFileChooser.APPROVE_OPTION) {

			// ��ȡ�ļ��Ի�����ѡ����ļ���
			String fileName2 = share.fc.getSelectedFile().getName();
			// ��ȡ�ļ��Ի������û�ѡ�е��ļ����ڵ�·��
			String path2 = share.fc.getCurrentDirectory().toString();

			// �û�ѡ�е��ļ����ڵ�·�����Ƿ�����fileName2ͬ�����ļ�
			File file2 = share.fc.getCurrentDirectory(); // ���ص�ǰĿ¼
			String[] strArray = file2.list(); // ��ȡ��·������ʾ��Ŀ¼�е��ļ���Ŀ¼������String����
			// ����������
			for (String s : strArray) {
				// �û�ѡ�е��ļ����ڵ�·��������fileName2ͬ�����ļ�
				if (s.equals(fileName2)) {
					// ����ȷ�϶Ի�����ʾ��fileName2�ļ��Ѵ��ڡ�\nҪ�滻���𣿡�
					int type3 = JOptionPane.showConfirmDialog(view.area,
							fileName2 + "�ļ��Ѵ��ڡ�\nҪ�滻����", "ȷ�ϱ���",
							JOptionPane.YES_NO_OPTION);

					// ��,�滻��������д�ļ�����
					if (type3 == JOptionPane.YES_OPTION) {

						// ��װ�ļ�
						f = new File(path2 + "/" + fileName2);
						try {
							// �����ļ������д����
							FileWriter fw = new FileWriter(f);
							// ���ı����е���Ϣд���ļ���
							fw.write(view.area.getText());
							// �ͷ���Դ
							fw.close();

							flag = true; // ���涯����ִ��

						} catch (Exception e2) {
							e2.printStackTrace();
						}

						// ����֮�󣬱���Ҫ��
						view.setTitle(fileName2);

					}else{
						//�񣬲��滻��������ʾ����Ի���
						share.fc.showSaveDialog(view.area);
					}
				}
			}

		}

	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ����˳�ʱ�¼�����
	 */

	public void exit() {

		// �˳�ǰҪ�ж��ı������Ƿ�������
		// ��������ݣ���Ҫ�ж�flag
		if (!"".equals(view.area.getText())) {
			if (flag) {
				// flagΪtrue���ѱ��棬ֱ���˳�
				System.exit(0);
			} else {
				// flagΪfalse��δ���棬Ҫ�����ж�
				// ȷ�϶Ի������ݣ���Ϣ�����⣬3����ť��������Ϣ
				int type4 = JOptionPane.showConfirmDialog(view.area, "�Ƿ񽫸��ı���"
						+ "�� �ޱ��⣿", "���±�", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);

				// ���������ǡ��ǡ����ͽ��б���
				if (type4 == JOptionPane.YES_OPTION) {
					// ���������ǡ��ǡ����������������saveDeal()����
					saveDeal();
					// ���������˳�
					System.exit(0);
				} else if (type4 == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}

		} else {
			// ���û�����ݣ�ֱ���˳�
			System.exit(0);
		}

	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-22
	 * @desc ��������һ�����˵��� ���¼�����
	 */
	private void seekNextOne() {
		// ��ȡ�ı�������
		String allText = view.area.getText();
		// ��ȡѡ�е��ı�
		String seekText = view.area.getSelectedText();
		// �������λ��
		int cursorIndex = view.area.getCaretPosition();
		// ���ѡ�е��ı���Ϊ��
		if (seekText != null) {
			// ������ҵ����ݱ��ı������ݳ� �� ���λ���ı���β��
			if (seekText.length() > allText.length()
					|| cursorIndex == allText.length()) {
				JOptionPane.showMessageDialog(seekDialog, "�Ҳ�����" + seekText
						+ "��������", "���±�", JOptionPane.INFORMATION_MESSAGE);
			}
			// ������Բ���
			else {
				for (; cursorIndex < allText.length(); cursorIndex++) {
					// �Ȼ�ȡ�ı�������Ҫ��seekText�Ƚϵ��ı�
					String str = allText.substring(cursorIndex, cursorIndex
							+ seekText.length());
					if (str.equalsIgnoreCase(seekText)) {
						view.area.select(cursorIndex,
								cursorIndex + seekText.length());
						break;
					}
					// ����������λ�õ��ı�ĩβ�ĳ���С����Ҫ���ҵ��ı�����ʱ
					if (allText.length() - cursorIndex <= seekText.length()) {
						JOptionPane.showMessageDialog(seekDialog, "�Ҳ�����"
								+ seekText + "��������", "���±�",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} else {
			// ���ѡ�е��ı�Ϊ��,�򿪲��ҶԻ���
			seekDialog.setVisible(true);
		}

	}

	/** ============DocumentListener��������========================== */
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
		if (!view.area.getText().equals("")) {
			view.undoMi.setEnabled(true);
			view.seekMi.setEnabled(true);
			view.seekNextMi.setEnabled(true);
			popMenu.popUndo.setEnabled(true);
			popMenu.popAll.setEnabled(true);
			// ���ı����룬��Ҫ�ѱ����ʶ��Ϊfalse
			flag = false;
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
		if (view.area.getText().equals("")) {
			view.seekMi.setEnabled(false);
			view.seekNextMi.setEnabled(false);
			popMenu.popAll.setEnabled(false);
			// ���ı��Ƴ�����Ҫ�ѱ����ʶ��Ϊfalse
			flag = false;
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
	}

}
