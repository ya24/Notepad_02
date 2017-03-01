package control.dialogControl.goDialogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import view.dialogView.goDialogView.GoDialog;
import view.frameView.mainFrameView.NoteView;

/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-07-25
 * @desc ת���Ի������¼���
 *
 */

public class GoDialogActionEvents implements ActionListener {

	GoDialog goDialog;	//ת���Զ���Ի��������
	
	NoteView view;	//���±��û�������
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-25
	 * @desc ������±��û�������
	 * @param view
	 */
	public GoDialogActionEvents(NoteView view) {
		this.view=view;
	}


	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-25
	 * @desc ����ת���Զ���Ի��������
	 * @param goDialog
	 */
	public void setGoDialog(GoDialog goDialog) {
		this.goDialog=goDialog;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		//��ȡ�¼�Դ
		JButton source = (JButton)e.getSource();
		
		//ת����ť
		if(source.equals(goDialog.btGo)){
			//��ȡ�ı�������ʾ������
			int row=Integer.parseInt(goDialog.txtRow.getText());
			//���Ҫ��ת���������ı�����������
			if(row>view.area.getLineCount()){
				JOptionPane.showMessageDialog(goDialog, "����������������", "���±���������", JOptionPane.PLAIN_MESSAGE);
				goDialog.txtRow.setText(""+view.area.getLineCount());
			}else{
				//ת��ָ����,����public int getLineStartOffset(int line)��public void setCaretPosition(int position)���
				try {
					//�����������ָ�����к�
					view.area.setCaretPosition(view.area.getLineStartOffset(row-1));
					//�رնԻ���
					goDialog.dispose();
				} catch (BadLocationException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
			
		}
		
		//ȡ����ť
		else if(source.equals(goDialog.btCancel)){
			goDialog.dispose();
		}
	}

}
