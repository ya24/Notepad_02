package control.menuControl.checkBoxMenuItemControl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.frameView.mainFrameView.NoteView;

public class LineCheckMenuItemEvents implements ItemListener {

	
	public NoteView view;	//���±��û����������
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-05-23
	 * @desc ������±��û�������������ʵ������Ա����
	 * @param view
	 */
	public void setView(NoteView view) {
		this.view =view;
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		//����Զ����и�ѡ�˵���ѡ��
		if(view.lineCheck.isSelected()){
			//public void setLineWrap(boolean wrap)�����ı����Ļ��в��ԡ��������Ϊ true�����еĳ�
			//����������Ŀ��ʱ�������С��������Ϊ false����ʼ�ղ����С�
			view.area.setLineWrap(true);
			
			//�Զ�����ѡ�к�״̬�����ɱ༭,����״̬��ʾ��ǩ����
			view.statusCheck.setEnabled(false);
			view.rcLab.setVisible(false);
			
			//�Զ�����ѡ�к�ת���˵����ɱ༭
			view.goMi.setEnabled(false);
		}else{
			//����Զ����и�ѡ�˵�δ��ѡ��
			view.area.setLineWrap(false);
			
			//״̬����ѡ�˵�����Ϊ�ɱ༭
			view.statusCheck.setEnabled(true);
			
			//���״̬����ѡ�˵���ѡ�У�����ʾ��ǩ��ʾ����
			if(view.statusCheck.isSelected()){	
				view.rcLab.setVisible(true);
			}else{
				//���״̬����ѡ�˵�δ��ѡ�У�����ʾ��ǩ����
				view.rcLab.setVisible(false);
			}
			
			//�Զ�����ѡ�к�ת���˵��ɱ༭
			view.goMi.setEnabled(true);
		}
	}

	

}
