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
 * @author 叶雅芳
 * @version V1.1
 * @date 2015-06-22
 * @desc 仿window记事本
 *
 */

public class Notepad_02 {

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 主类专门用来实例化这个项目所涉及到的类
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		Share share=new Share();	//共享类
		
		MenuActionEvents menuAction=new MenuActionEvents(share);	//主界面主菜单动作事件类
		
		LineCheckMenuItemEvents lineCheckEvent=new LineCheckMenuItemEvents();	//主界面自动换行复选菜单触发的选项事件类
		StatusCheckMenuItemEvents statusCheckEvent= new StatusCheckMenuItemEvents();	//主界面状态栏复选菜单触发的选项事件类
		
		MyCaretListener my=new MyCaretListener(statusCheckEvent);	//对文本区光标位置随时监听的类
		
		PopMenu popMenu=new PopMenu(share,menuAction);	//弹出式菜单界面类
		
		NoteView view=new NoteView(share,menuAction,popMenu,lineCheckEvent,statusCheckEvent,my);	//记事本主界面设计类
		
		FontListSelectionEvents listSelect=new FontListSelectionEvents();	//字体对话框中 列表框选择事件类
		
		SeekDialogItemEvents seekItem=new SeekDialogItemEvents();	//查找对话框选项事件类
		SeekDialogActionEvents seekAction=new SeekDialogActionEvents(view,seekItem);	//查找对话框动作事件类
		
		ReplaceDialogActionEvents replaceAction=new ReplaceDialogActionEvents(view);	//替换对话框动作事件类
		
		GoDialogActionEvents goAction=new GoDialogActionEvents(view);	//转到对话框动作事件类
		
		FontDialog dialog=new FontDialog(view,share,listSelect,menuAction);	//字体自定义对话框设计类
		
		SeekDialog seekDialog=new SeekDialog(menuAction,seekAction,seekItem);	//查找自定义对话框设计类
		
		ReplaceDialog replaceDialog=new ReplaceDialog(menuAction,replaceAction);	//替换自定义对话框设计类
		
		GoDialog goDialog=new GoDialog(menuAction,goAction);		//转到自定义对话框设计类


	}

}
