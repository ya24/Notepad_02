package control.menuControl.checkBoxMenuItemControl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.frameView.mainFrameView.NoteView;
/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-07-25
 * @desc ״̬����ѡ�˵��¼�������ʾ������ڵ�λ��
 *
 */
public class StatusCheckMenuItemEvents implements ItemListener{

	public NoteView view;	//���±��û����������
	
	int col;	//�����������
	int row;	//�����������
	
	public boolean showFlag=false;		//��ʾ��ʶ������trueΪ��ʾ��false����ʾ
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-05-23
	 * @desc ������±��û�������������ʵ������Ա����
	 * @param view
	 */
	public void setView(NoteView view) {
		this.view =view;
	}

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-05-23
	 * @desc �Ӷ��ı������λ����ʱ�������ഫ�ݹ����Ĺ���λ��
	 * @param row
	 * @param col
	 */
	public void setLocation(int row, int col) {
		// TODO �Զ����ɵķ������
		this.row=row;
		this.col=col;
	}

	
	//״̬���������¼�
	public void itemStateChanged(ItemEvent e) {
		//���״̬����ѡ��ѡ��
		if(e.getStateChange()==ItemEvent.SELECTED){
			
			showFlag=true;	//��ʶ������Ϊtrue����ʾ��ǩ
			
			view.rcLab.setVisible(true);	//����ǩ��ʾ����
			
			view.rcLab.setText("��"+(row+1)+"�У���"+(col+1)+"��     ");	//�Ѽ��������кź��к���ʾ�ڱ�ǩ��
		}
		//���״̬����ѡ��δ��ѡ��
		else if(e.getStateChange()==ItemEvent.DESELECTED){
		
			showFlag=false;		//��ʶ������Ϊfalse�����ر�ǩ
			view.rcLab.setVisible(false);	//����ǩ����
		
		}
	}

}
