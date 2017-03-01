package control.dialogControl.replaceDialogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dialogView.replaceDialogView.ReplaceDialog;
import view.frameView.mainFrameView.NoteView;

/**
 * 
 * @author 叶雅芳
 * @date 2015-07-24
 * @desc 替换对话框动作事件类
 *
 */

public class ReplaceDialogActionEvents implements ActionListener ,DocumentListener{

	ReplaceDialog replaceDialog; // 替换自定义对话框设计类
	NoteView view; // 记事本用户界面设计类

	boolean seekFlag = false; // 标识是否查找成功，true为成功，false为失败

	String allText; // 记事本内容
	String seekText; // 需要查找的内容
	String replaceText; // 替换的内容
	int cursorIndex; // 光标所在位置
	String str;// 获取文本区中需要和seekText比较的内容

	/**
	 * @author 叶雅芳
	 * @date 2015-07-24
	 * @desc 构造方法传入记事本用户界面设计类对象，实例化成员变量
	 * @param view
	 */
	public ReplaceDialogActionEvents(NoteView view) {
		this.view = view;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-24
	 * @desc 传入替换自定义对话框设计类对象，实例化成员变量
	 * @param replaceDialog
	 */
	public void setReplaceDialog(ReplaceDialog replaceDialog) {
		this.replaceDialog = replaceDialog;
	}

	/**==================ActionListener事件处理===================================*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取事件源
		JButton source = (JButton) e.getSource();

		/* -----------------"查找下一个"按钮的操作---------------------- */
		if (source.equals(replaceDialog.btSeekNext)) {
			seekNextOne();
		}

		/* -----------------"替换"按钮的操作---------------------- */
		else if (source.equals(replaceDialog.btReplace)) {
			replace();
		}

		/* -----------------"全部替换"按钮的操作---------------------- */
		else if (source.equals(replaceDialog.btReplaceAll)) {
			replaceAll();
		}

		/* -----------------"取消"按钮的操作---------------------- */
		else if (source.equals(replaceDialog.btCancel)) {
			replaceDialog.dispose();
		}
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-24
	 * @desc 查找下一个按钮的事件处理
	 */
	private boolean seekNextOne() {
		// 获取记事本内容
		allText = view.area.getText();

		// 获取要查找的内容
		seekText = replaceDialog.txtSeek.getText();

		// 获取光标所在的位置
		cursorIndex = view.area.getCaretPosition();

		// 如果查找的内容比文本区内光标后面的内容长 或 光标位于文本区尾部
		if (seekText.length() > allText.length() - cursorIndex
				|| cursorIndex == allText.length()) {
			JOptionPane.showMessageDialog(replaceDialog, "找不到“" + seekText
					+ "”！！！", "记事本", JOptionPane.INFORMATION_MESSAGE);
		}
		// 如果可以查找
		else {
			for (; cursorIndex < allText.length(); cursorIndex++) {
				// 获取文本区中需要和seekText比较的内容
				str = allText.substring(cursorIndex,
						cursorIndex + seekText.length());

				// 区分大小写
				if (replaceDialog.flag) {
					if (str.equals(seekText)) {
						view.area.select(cursorIndex,
								cursorIndex + seekText.length());
						seekFlag = true;
						break;
					} else {
						seekFlag = false;
					}
				}
				// 不区分大小写
				else {
					if (str.equalsIgnoreCase(seekText)) {
						view.area.select(cursorIndex,
								cursorIndex + seekText.length());
						seekFlag = true;
						break;
					} else {
						seekFlag = false;
					}
				}
				// 如果光标所在位置到文本末尾的长度小于需要查找的文本长度时
				if (allText.length() - cursorIndex <= seekText.length()) {
					JOptionPane.showMessageDialog(replaceDialog, "找不到“"
							+ seekText + "”！！！", "记事本",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		}
		return seekFlag;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-24
	 * @desc 替换按钮的事件处理
	 */
	private void replace() {
		// 如果查找成功
		if (seekNextOne()) {
			// 获取替换的内容
			replaceText = replaceDialog.txtReplace.getText();
			// 将文本区内选中的内容用replaceText替换
			view.area.replaceSelection(replaceText);
		}
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-24
	 * @desc 全部替换按钮的事件处理
	 */
	private void replaceAll() {

		// 获取替换的内容
		replaceText = replaceDialog.txtReplace.getText();

		// 获取需要替换的内容(所查找的内容)
		String needReplaceText = replaceDialog.txtSeek.getText();

		// 获取文本内容
		allText = view.area.getText();

		//区分大小写时
		if(replaceDialog.flag){
			view.area.setText(allText.replace(needReplaceText, replaceText));
		}
		//不区分大小写时
		else{
			//=================未完成===============================================
	//		view.area.setText(allText.replace(needReplaceText.toLowerCase(), replaceText));
	//		view.area.setText(allText.replace(needReplaceText.toUpperCase(), replaceText));
		}
		
	}

	/**===================DocumentListener的方法处理=================================*/
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		if(!replaceDialog.txtSeek.getText().equals("")){
			replaceDialog.btSeekNext.setEnabled(true);
			replaceDialog.btReplace.setEnabled(true);
			replaceDialog.btReplaceAll.setEnabled(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		if(replaceDialog.txtSeek.getText().equals("")){
			replaceDialog.btSeekNext.setEnabled(false);
			replaceDialog.btReplace.setEnabled(false);
			replaceDialog.btReplaceAll.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		
	}

	
	
	

}



