package control.menuControl.checkBoxMenuItemControl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.frameView.mainFrameView.NoteView;

public class LineCheckMenuItemEvents implements ItemListener {

	
	public NoteView view;	//记事本用户界面设计类
	
	/**
	 * @author 叶雅芳
	 * @date 2015-05-23
	 * @desc 传入记事本用户界面设计类对象，实例化成员变量
	 * @param view
	 */
	public void setView(NoteView view) {
		this.view =view;
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		//如果自动换行复选菜单被选中
		if(view.lineCheck.isSelected()){
			//public void setLineWrap(boolean wrap)设置文本区的换行策略。如果设置为 true，则当行的长
			//大于所分配的宽度时，将换行。如果设置为 false，则始终不换行。
			view.area.setLineWrap(true);
			
			//自动换行选中后，状态栏不可编辑,并把状态显示标签隐藏
			view.statusCheck.setEnabled(false);
			view.rcLab.setVisible(false);
			
			//自动换行选中后，转到菜单不可编辑
			view.goMi.setEnabled(false);
		}else{
			//如果自动换行复选菜单未被选中
			view.area.setLineWrap(false);
			
			//状态栏复选菜单设置为可编辑
			view.statusCheck.setEnabled(true);
			
			//如果状态栏复选菜单被选中，把显示标签显示出来
			if(view.statusCheck.isSelected()){	
				view.rcLab.setVisible(true);
			}else{
				//如果状态栏复选菜单未被选中，把显示标签隐藏
				view.rcLab.setVisible(false);
			}
			
			//自动换行选中后，转到菜单可编辑
			view.goMi.setEnabled(true);
		}
	}

	

}
