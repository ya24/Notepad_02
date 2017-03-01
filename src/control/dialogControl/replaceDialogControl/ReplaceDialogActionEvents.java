package control.dialogControl.replaceDialogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dialogView.replaceDialogView.ReplaceDialog;
import view.frameView.mainFrameView.NoteView;

/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-07-24
 * @desc �滻�Ի������¼���
 *
 */

public class ReplaceDialogActionEvents implements ActionListener ,DocumentListener{

	ReplaceDialog replaceDialog; // �滻�Զ���Ի��������
	NoteView view; // ���±��û����������

	boolean seekFlag = false; // ��ʶ�Ƿ���ҳɹ���trueΪ�ɹ���falseΪʧ��

	String allText; // ���±�����
	String seekText; // ��Ҫ���ҵ�����
	String replaceText; // �滻������
	int cursorIndex; // �������λ��
	String str;// ��ȡ�ı�������Ҫ��seekText�Ƚϵ�����

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-24
	 * @desc ���췽��������±��û�������������ʵ������Ա����
	 * @param view
	 */
	public ReplaceDialogActionEvents(NoteView view) {
		this.view = view;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-24
	 * @desc �����滻�Զ���Ի�����������ʵ������Ա����
	 * @param replaceDialog
	 */
	public void setReplaceDialog(ReplaceDialog replaceDialog) {
		this.replaceDialog = replaceDialog;
	}

	/**==================ActionListener�¼�����===================================*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// ��ȡ�¼�Դ
		JButton source = (JButton) e.getSource();

		/* -----------------"������һ��"��ť�Ĳ���---------------------- */
		if (source.equals(replaceDialog.btSeekNext)) {
			seekNextOne();
		}

		/* -----------------"�滻"��ť�Ĳ���---------------------- */
		else if (source.equals(replaceDialog.btReplace)) {
			replace();
		}

		/* -----------------"ȫ���滻"��ť�Ĳ���---------------------- */
		else if (source.equals(replaceDialog.btReplaceAll)) {
			replaceAll();
		}

		/* -----------------"ȡ��"��ť�Ĳ���---------------------- */
		else if (source.equals(replaceDialog.btCancel)) {
			replaceDialog.dispose();
		}
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-24
	 * @desc ������һ����ť���¼�����
	 */
	private boolean seekNextOne() {
		// ��ȡ���±�����
		allText = view.area.getText();

		// ��ȡҪ���ҵ�����
		seekText = replaceDialog.txtSeek.getText();

		// ��ȡ������ڵ�λ��
		cursorIndex = view.area.getCaretPosition();

		// ������ҵ����ݱ��ı����ڹ���������ݳ� �� ���λ���ı���β��
		if (seekText.length() > allText.length() - cursorIndex
				|| cursorIndex == allText.length()) {
			JOptionPane.showMessageDialog(replaceDialog, "�Ҳ�����" + seekText
					+ "��������", "���±�", JOptionPane.INFORMATION_MESSAGE);
		}
		// ������Բ���
		else {
			for (; cursorIndex < allText.length(); cursorIndex++) {
				// ��ȡ�ı�������Ҫ��seekText�Ƚϵ�����
				str = allText.substring(cursorIndex,
						cursorIndex + seekText.length());

				// ���ִ�Сд
				if (replaceDialog.flag) {
					if (str.equals(seekText)) {
						view.area.select(cursorIndex,
								cursorIndex + seekText.length());
						seekFlag = true;
						break;
					} else {
						seekFlag = false;
					}
				}
				// �����ִ�Сд
				else {
					if (str.equalsIgnoreCase(seekText)) {
						view.area.select(cursorIndex,
								cursorIndex + seekText.length());
						seekFlag = true;
						break;
					} else {
						seekFlag = false;
					}
				}
				// ����������λ�õ��ı�ĩβ�ĳ���С����Ҫ���ҵ��ı�����ʱ
				if (allText.length() - cursorIndex <= seekText.length()) {
					JOptionPane.showMessageDialog(replaceDialog, "�Ҳ�����"
							+ seekText + "��������", "���±�",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		}
		return seekFlag;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-24
	 * @desc �滻��ť���¼�����
	 */
	private void replace() {
		// ������ҳɹ�
		if (seekNextOne()) {
			// ��ȡ�滻������
			replaceText = replaceDialog.txtReplace.getText();
			// ���ı�����ѡ�е�������replaceText�滻
			view.area.replaceSelection(replaceText);
		}
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-24
	 * @desc ȫ���滻��ť���¼�����
	 */
	private void replaceAll() {

		// ��ȡ�滻������
		replaceText = replaceDialog.txtReplace.getText();

		// ��ȡ��Ҫ�滻������(�����ҵ�����)
		String needReplaceText = replaceDialog.txtSeek.getText();

		// ��ȡ�ı�����
		allText = view.area.getText();

		//���ִ�Сдʱ
		if(replaceDialog.flag){
			view.area.setText(allText.replace(needReplaceText, replaceText));
		}
		//�����ִ�Сдʱ
		else{
			//=================δ���===============================================
	//		view.area.setText(allText.replace(needReplaceText.toLowerCase(), replaceText));
	//		view.area.setText(allText.replace(needReplaceText.toUpperCase(), replaceText));
		}
		
	}

	/**===================DocumentListener�ķ�������=================================*/
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
		if(!replaceDialog.txtSeek.getText().equals("")){
			replaceDialog.btSeekNext.setEnabled(true);
			replaceDialog.btReplace.setEnabled(true);
			replaceDialog.btReplaceAll.setEnabled(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
		if(replaceDialog.txtSeek.getText().equals("")){
			replaceDialog.btSeekNext.setEnabled(false);
			replaceDialog.btReplace.setEnabled(false);
			replaceDialog.btReplaceAll.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	
	
	

}



