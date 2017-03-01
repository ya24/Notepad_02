package control.mainControl;

import view.dialogView.fontDialogView.FontDialog;
import view.dialogView.goDialogView.GoDialog;
import view.dialogView.replaceDialogView.ReplaceDialog;
import view.dialogView.seekDialogView.SeekDialog;
import view.frameView.mainFrameView.NoteView;
import view.otherView.popupMenuView.PopMenu;
import control.dialogControl.fontDialogControl.FontListSelectionEvents;
import control.dialogControl.goDialogControl.GoDialogActionEvents;
import control.dialogControl.replaceDialogControl.ReplaceDialogActionEvents;
import control.dialogControl.seekDialogControl.SeekDialogActionEvents;
import control.dialogControl.seekDialogControl.SeekDialogItemEvents;
import control.menuControl.checkBoxMenuItemControl.LineCheckMenuItemEvents;
import control.menuControl.checkBoxMenuItemControl.StatusCheckMenuItemEvents;
import control.menuControl.menuItemControl.MenuActionEvents;
import control.otherControl.MyCaretListener;
import control.otherControl.Share;

/**
 * 
 * @author Ҷ�ŷ�
 * @version V1.1
 * @date 2015-06-22
 * @desc ��window���±�
 *
 */

public class Notepad_02 {

	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ����ר������ʵ���������Ŀ���漰������
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		
		Share share=new Share();	//������
		
		MenuActionEvents menuAction=new MenuActionEvents(share);	//���������˵������¼���
		
		LineCheckMenuItemEvents lineCheckEvent=new LineCheckMenuItemEvents();	//�������Զ����и�ѡ�˵�������ѡ���¼���
		StatusCheckMenuItemEvents statusCheckEvent= new StatusCheckMenuItemEvents();	//������״̬����ѡ�˵�������ѡ���¼���
		
		MyCaretListener my=new MyCaretListener(statusCheckEvent);	//���ı������λ����ʱ��������
		
		PopMenu popMenu=new PopMenu(share,menuAction);	//����ʽ�˵�������
		
		NoteView view=new NoteView(share,menuAction,popMenu,lineCheckEvent,statusCheckEvent,my);	//���±������������
		
		FontListSelectionEvents listSelect=new FontListSelectionEvents();	//����Ի����� �б��ѡ���¼���
		
		SeekDialogItemEvents seekItem=new SeekDialogItemEvents();	//���ҶԻ���ѡ���¼���
		SeekDialogActionEvents seekAction=new SeekDialogActionEvents(view,seekItem);	//���ҶԻ������¼���
		
		ReplaceDialogActionEvents replaceAction=new ReplaceDialogActionEvents(view);	//�滻�Ի������¼���
		
		GoDialogActionEvents goAction=new GoDialogActionEvents(view);	//ת���Ի������¼���
		
		FontDialog dialog=new FontDialog(view,share,listSelect,menuAction);	//�����Զ���Ի��������
		
		SeekDialog seekDialog=new SeekDialog(menuAction,seekAction,seekItem);	//�����Զ���Ի��������
		
		ReplaceDialog replaceDialog=new ReplaceDialog(menuAction,replaceAction);	//�滻�Զ���Ի��������
		
		GoDialog goDialog=new GoDialog(menuAction,goAction);		//ת���Զ���Ի��������


	}

}
