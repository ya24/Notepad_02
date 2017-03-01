package control.menuControl.checkBoxMenuItemControl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.frameView.mainFrameView.NoteView;
/**
 * 
 * @author 叶雅芳
 * @date 2015-07-25
 * @desc 状态栏复选菜单事件处理，显示光标所在的位置
 *
 */
public class StatusCheckMenuItemEvents implements ItemListener{

	public NoteView view;	//记事本用户界面设计类
	
	int col;	//光标所在列数
	int row;	//光标所在行数
	
	public boolean showFlag=false;		//显示标识变量；true为显示，false不显示
	
	/**
	 * @author 叶雅芳
	 * @date 2015-05-23
	 * @desc 传入记事本用户界面设计类对象，实例化成员变量
	 * @param view
	 */
	public void setView(NoteView view) {
		this.view =view;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-05-23
	 * @desc 从对文本区光标位置随时监听的类传递过来的光标的位置
	 * @param row
	 * @param col
	 */
	public void setLocation(int row, int col) {
		// TODO 自动生成的方法存根
		this.row=row;
		this.col=col;
	}

	
	//状态栏触发的事件
	public void itemStateChanged(ItemEvent e) {
		//如果状态栏复选框被选中
		if(e.getStateChange()==ItemEvent.SELECTED){
			
			showFlag=true;	//标识变量设为true，显示标签
			
			view.rcLab.setVisible(true);	//将标签显示出来
			
			view.rcLab.setText("第"+(row+1)+"行，第"+(col+1)+"列     ");	//把监听到的行号和列号显示在标签上
		}
		//如果状态栏复选框未被选中
		else if(e.getStateChange()==ItemEvent.DESELECTED){
		
			showFlag=false;		//标识变量设为false，隐藏标签
			view.rcLab.setVisible(false);	//将标签隐藏
		
		}
	}

}
