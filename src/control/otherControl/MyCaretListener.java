package control.otherControl;

import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import view.frameView.mainFrameView.NoteView;
import control.menuControl.checkBoxMenuItemControl.StatusCheckMenuItemEvents;

/**
 * 
 * @author 叶雅芳
 * @date 2015-07-25
 * @desc 对文本区光标位置随时监听的类
 * 
 */
public class MyCaretListener implements CaretListener {

	NoteView view; // 记事本用户界面类
	StatusCheckMenuItemEvents statusCheckEvent; // 状态栏复选菜单触发的选项事件类

	int row, col; // 记录记事本文本区光标所在的位置（行与列）

	
	/**
	 * @author 叶雅芳
	 * @date 2015-07-25
	 * @desc 构造方法传入状态栏复选菜单触发的选项事件类对象，实例化成员变量
	 * @param statusCheckEvent
	 */
	public MyCaretListener(StatusCheckMenuItemEvents statusCheckEvent) {
		// 实例化成员变量
		this.statusCheckEvent = statusCheckEvent;

		// 将监听到的记事本文本区内光标的位置传递给 状态栏复选菜单触发的选项事件类对象
		statusCheckEvent.setLocation(row, col);
	}

	
	/**
	 * @author 叶雅芳
	 * @date 2015-07-25
	 * @desc 传入记事本用户界面类对象，实例化成员变量
	 * @param view
	 */
	public void setView(NoteView view) {
		this.view = view;
	}

	
	/**
	 * @author 叶雅芳
	 * @date 2015-07-25
	 * @desc 监听记事本文本区内光标的位置
	 */
	@Override
	public void caretUpdate(CaretEvent e) {
		
		// 光标所在行的起始和结束位置
		int start = 0, end = 0;
		try {
			// 获取光标所在的行数
			row = view.area.getLineOfOffset(view.area.getCaretPosition());

			// 光标所在行的起始位置
			start = view.area.getLineStartOffset(row);

			// 文本组件的文本插入符的位置
			end=view.area.getCaretPosition();
			
			// 获取光标所在的列数
			col = end - start;

		} catch (BadLocationException e1) {
			// TODO 自动生成的 catch 块
			JOptionPane.showMessageDialog(view, "您的操作有误！！！");
		}

		// 将随时监听得到的光标位置显示在状态栏标签上
		if (statusCheckEvent.showFlag) {
			view.rcLab.setText("第" + (row + 1) + "行，第" + (col + 1) + "列     ");
		}

	}

}
