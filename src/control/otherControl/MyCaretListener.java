package control.otherControl;

import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import view.frameView.mainFrameView.NoteView;
import control.menuControl.checkBoxMenuItemControl.StatusCheckMenuItemEvents;

/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-07-25
 * @desc ���ı������λ����ʱ��������
 * 
 */
public class MyCaretListener implements CaretListener {

	NoteView view; // ���±��û�������
	StatusCheckMenuItemEvents statusCheckEvent; // ״̬����ѡ�˵�������ѡ���¼���

	int row, col; // ��¼���±��ı���������ڵ�λ�ã������У�

	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-25
	 * @desc ���췽������״̬����ѡ�˵�������ѡ���¼������ʵ������Ա����
	 * @param statusCheckEvent
	 */
	public MyCaretListener(StatusCheckMenuItemEvents statusCheckEvent) {
		// ʵ������Ա����
		this.statusCheckEvent = statusCheckEvent;

		// ���������ļ��±��ı����ڹ���λ�ô��ݸ� ״̬����ѡ�˵�������ѡ���¼������
		statusCheckEvent.setLocation(row, col);
	}

	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-25
	 * @desc ������±��û����������ʵ������Ա����
	 * @param view
	 */
	public void setView(NoteView view) {
		this.view = view;
	}

	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-25
	 * @desc �������±��ı����ڹ���λ��
	 */
	@Override
	public void caretUpdate(CaretEvent e) {
		
		// ��������е���ʼ�ͽ���λ��
		int start = 0, end = 0;
		try {
			// ��ȡ������ڵ�����
			row = view.area.getLineOfOffset(view.area.getCaretPosition());

			// ��������е���ʼλ��
			start = view.area.getLineStartOffset(row);

			// �ı�������ı��������λ��
			end=view.area.getCaretPosition();
			
			// ��ȡ������ڵ�����
			col = end - start;

		} catch (BadLocationException e1) {
			// TODO �Զ����ɵ� catch ��
			JOptionPane.showMessageDialog(view, "���Ĳ������󣡣���");
		}

		// ����ʱ�����õ��Ĺ��λ����ʾ��״̬����ǩ��
		if (statusCheckEvent.showFlag) {
			view.rcLab.setText("��" + (row + 1) + "�У���" + (col + 1) + "��     ");
		}

	}

}
