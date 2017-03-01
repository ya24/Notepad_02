package control.dialogControl.seekDialogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dialogView.seekDialogView.SeekDialog;
import view.frameView.mainFrameView.NoteView;

/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-07-22
 * @desc ���ҶԻ������¼���
 *
 */

public class SeekDialogActionEvents implements ActionListener ,DocumentListener{

	SeekDialog seekDialog; // ���ҶԻ������¼���
	SeekDialogItemEvents seekItem; // ���ҶԻ���ѡ���¼���
	NoteView view; // ���±��û�������

	String seekText = ""; // ��Ҫ���ҵ�����
	String allText = ""; // ���±��ı������ڵ�����

	int cursorIndex; // �������λ��

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-22
	 * @desc ������ҶԻ���ѡ���¼������ʵ������Ա����
	 * @param view 
	 * @param seekItem
	 */
	public SeekDialogActionEvents(NoteView view, SeekDialogItemEvents seekItem) {
		this.seekItem = seekItem;
		this.view=view;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-22
	 * @desc ������ҶԻ������¼������ʵ������Ա����
	 * @param seekDialog
	 */
	public void setSeekDialog(SeekDialog seekDialog) {
		this.seekDialog = seekDialog;
	}


	/**============ActionListener�¼�����================================*/
	@Override
	public void actionPerformed(ActionEvent e) {

		// ��ȡ�¼�Դ
		JButton source = (JButton) e.getSource();

		/* ------------��������һ������ť���� -------------- */
		if (source.equals(seekDialog.btSeekNext)) {

			if (seekItem.udFlag) {
				// ���²���
				seekNextDown();
			} else {
				// ���ϲ���
				seekNextUp();
			}

		}

		/* --------------"ȡ��"��ť����----------------- */
		else if (source.equals(seekDialog.btnCancel)) {
			seekDialog.dispose();
		}
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-22
	 * @desc ���²��Ҳ���
	 */
	private void seekNextDown() {

		// ���±�����
		allText = view.area.getText();

		// ��Ҫ���ҵ�����
		seekText = seekDialog.txtSeek.getText();

		// �������λ��,ȷ�����ҵ�λ��
		cursorIndex = view.area.getCaretPosition();

		// ������ҵ����ݱ��ı����ڹ���������ݳ� �� ���λ���ı���β��
		if (seekText.length() > allText.length()-cursorIndex
				|| cursorIndex == allText.length()) {
			JOptionPane.showMessageDialog(seekDialog, "�Ҳ�����" + seekText
					+ "��������", "���±�", JOptionPane.INFORMATION_MESSAGE);
		}
		// ������Բ���
		else {
			if (seekItem.bsFlag) {
				// ���ִ�Сд
				int index; // ��¼���ҵ��������ı����е�λ��
				if ((index = allText.indexOf(seekText, cursorIndex)) != -1) {
					//��Ҫ���ҵ����ݴ��ڣ����ı�����ѡ����ӦҪ���ҵ�����
					view.area.select(index, index + seekText.length());

				} else {
					//��Ҫ���ҵ����ݲ����ڣ������Ի�����ʾ�û�
					JOptionPane.showMessageDialog(seekDialog, "�Ҳ�����" + seekText
							+ "��������", "���±�", JOptionPane.INFORMATION_MESSAGE);
				}

			} else {
				// �����ִ�Сд
				//��forѭ������ʼ��λ���ɹ�����ڵ�λ��ȷ����ѭ���������ǹ��λ�ñ��ı������ݳ��ȴ�
				for (; cursorIndex < allText.length(); cursorIndex++) {
					// �Ȼ�ȡ�ı�������Ҫ��seekText�Ƚϵ��ı�
					String str = allText.substring(cursorIndex, cursorIndex
							+ seekText.length());
					//��str��seekText���Դ�Сд�Ƚϣ�һ���ܹ�ƥ�䣬���ı�����ѡ����Ӧ�����ݣ�ͬʱ�˳�forѭ��
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
						break;
					}
				}
			}
		}
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-22
	 * @desc ���ϲ��Ҳ���
	 */
	private void seekNextUp() {
		// ���±�����
		allText = view.area.getText();

		// ��Ҫ���ҵ�����
		seekText = seekDialog.txtSeek.getText();

		// �������λ��,ȷ�����ҵ�λ��
		cursorIndex = view.area.getCaretPosition() - 1;

		// ������ҵ����ݵĳ��ȱȹ��λ�ô� �� ���λ���ı����ײ�
		if (seekText.length() > cursorIndex || cursorIndex == 0) {
			JOptionPane.showMessageDialog(seekDialog, "�Ҳ�����" + seekText
					+ "��������", "���±�", JOptionPane.INFORMATION_MESSAGE);
		}
		// ������Բ���
		else {
			for (; cursorIndex > 0; cursorIndex--) {
				// �Ȼ�ȡ�ı�������Ҫ��seekText�Ƚϵ��ı�
				String str = allText.substring(cursorIndex - seekText.length(),
						cursorIndex);
				// ���ִ�Сд
				if (seekItem.bsFlag) {
					//һ����ƥ�䣬���ı�����ѡ����Ӧ�����ݣ�ͬʱ�˳�forѭ��
					if (str.equals(seekText)) {
						view.area.select(cursorIndex - seekText.length(),
								cursorIndex);
						break;
					}
					//�����Ҫ���ҵ������ڼ��±��ı����ڴӹ������λ�÷�����Ҳ�����ʱ�������Ի�����ʾ�û���ͬʱ�˳�forѭ��
					if(allText.lastIndexOf(seekText, cursorIndex)==-1){
						JOptionPane.showMessageDialog(seekDialog, "�Ҳ�����" + seekText
								+ "��������", "���±�", JOptionPane.INFORMATION_MESSAGE);
						break;
					}

				}
				// �����ִ�Сд
				else {
					if (str.equalsIgnoreCase(seekText)) {
						//һ����ƥ�䣬���ı�����ѡ����Ӧ�����ݣ�ͬʱ�˳�forѭ��
						view.area.select(cursorIndex - seekText.length(),cursorIndex);
						break;
					}
					if (allText.lastIndexOf(seekText.toLowerCase(), cursorIndex) == -1
							&& allText.lastIndexOf(seekText.toUpperCase(),cursorIndex) == -1) {
						//�����Ҫ���ҵ������ڼ��±��ı����ڴӹ������λ�÷�����Դ�Сд���Ҳ�����ʱ�������Ի�����ʾ�û���ͬʱ�˳�forѭ��
						JOptionPane.showMessageDialog(seekDialog, "�Ҳ�����" + seekText
								+ "��������", "���±�", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}

			}

		}
	}

	
	/**=============DocumentListener�¼�����=================================*/
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
		if(!seekDialog.txtSeek.getText().equals("")){
			seekDialog.btSeekNext.setEnabled(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
		if(seekDialog.txtSeek.getText().equals("")){
			seekDialog.btSeekNext.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������
		
	}

}

