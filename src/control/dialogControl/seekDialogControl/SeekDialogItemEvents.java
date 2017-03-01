package control.dialogControl.seekDialogControl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.dialogView.seekDialogView.SeekDialog;

/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-07-22
 * @desc ���ҶԻ���ѡ���¼���
 *
 */

public class SeekDialogItemEvents implements ItemListener{

	SeekDialog seekDialog;	//���ҶԻ������¼���
	
	boolean udFlag=true;	//��ʶ�������µ�ѡ��ť��trueΪ���£�falseΪ����
	boolean bsFlag=false;	//��ʶ��Сд��false�����ִ�Сд��true���ִ�Сд
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-07-22
	 * @desc ������ҶԻ������¼������ʵ������Ա����
	 * @param seekDialog
	 */
	public void setSeekDialog(SeekDialog seekDialog) {
		this.seekDialog=seekDialog;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		//��ȡ�¼�Դ
		Object source=e.getSource();
		
		/* ----------------���ϵ�ѡ��ť����------------------ */
		if(source.equals(seekDialog.rdbtnUp)){
			udFlag=false;
		}
		/* ----------------���µ�ѡ��ť����------------------ */
		else if(source.equals(seekDialog.rdbtnDown)){
			udFlag=true;
		}
		
		/* ----------------���ִ�Сд��ѡ��ť����------------------ */
		else if(source.equals(seekDialog.chckbxc)&&e.getStateChange()==ItemEvent.SELECTED){
			bsFlag=true;	//���ִ�Сд
		}else if(source.equals(seekDialog.chckbxc)&& e.getStateChange()==ItemEvent.DESELECTED){
			bsFlag=false;	//�����ִ�Сд
		}
		
	}

}
