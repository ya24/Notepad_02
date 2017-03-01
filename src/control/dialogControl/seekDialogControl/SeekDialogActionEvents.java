package control.dialogControl.seekDialogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dialogView.seekDialogView.SeekDialog;
import view.frameView.mainFrameView.NoteView;

/**
 * 
 * @author 叶雅芳
 * @date 2015-07-22
 * @desc 查找对话框动作事件类
 *
 */

public class SeekDialogActionEvents implements ActionListener ,DocumentListener{

	SeekDialog seekDialog; // 查找对话框动作事件类
	SeekDialogItemEvents seekItem; // 查找对话框选项事件类
	NoteView view; // 记事本用户界面类

	String seekText = ""; // 需要查找的内容
	String allText = ""; // 记事本文本区域内的内容

	int cursorIndex; // 光标所在位置

	/**
	 * @author 叶雅芳
	 * @date 2015-07-22
	 * @desc 传入查找对话框选项事件类对象，实例化成员变量
	 * @param view 
	 * @param seekItem
	 */
	public SeekDialogActionEvents(NoteView view, SeekDialogItemEvents seekItem) {
		this.seekItem = seekItem;
		this.view=view;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-22
	 * @desc 传入查找对话框动作事件类对象，实例化成员变量
	 * @param seekDialog
	 */
	public void setSeekDialog(SeekDialog seekDialog) {
		this.seekDialog = seekDialog;
	}


	/**============ActionListener事件处理================================*/
	@Override
	public void actionPerformed(ActionEvent e) {

		// 获取事件源
		JButton source = (JButton) e.getSource();

		/* ------------“查找下一个”按钮操作 -------------- */
		if (source.equals(seekDialog.btSeekNext)) {

			if (seekItem.udFlag) {
				// 向下查找
				seekNextDown();
			} else {
				// 向上查找
				seekNextUp();
			}

		}

		/* --------------"取消"按钮操作----------------- */
		else if (source.equals(seekDialog.btnCancel)) {
			seekDialog.dispose();
		}
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-22
	 * @desc 向下查找操作
	 */
	private void seekNextDown() {

		// 记事本内容
		allText = view.area.getText();

		// 需要查找的内容
		seekText = seekDialog.txtSeek.getText();

		// 光标所在位置,确定查找的位置
		cursorIndex = view.area.getCaretPosition();

		// 如果查找的内容比文本区内光标后面的内容长 或 光标位于文本区尾部
		if (seekText.length() > allText.length()-cursorIndex
				|| cursorIndex == allText.length()) {
			JOptionPane.showMessageDialog(seekDialog, "找不到“" + seekText
					+ "”！！！", "记事本", JOptionPane.INFORMATION_MESSAGE);
		}
		// 如果可以查找
		else {
			if (seekItem.bsFlag) {
				// 区分大小写
				int index; // 记录查找的内容在文本区中的位置
				if ((index = allText.indexOf(seekText, cursorIndex)) != -1) {
					//所要查找的内容存在，在文本区中选中相应要查找的内容
					view.area.select(index, index + seekText.length());

				} else {
					//所要查找的内容不存在，弹出对话框提示用户
					JOptionPane.showMessageDialog(seekDialog, "找不到“" + seekText
							+ "”！！！", "记事本", JOptionPane.INFORMATION_MESSAGE);
				}

			} else {
				// 不区分大小写
				//用for循环，开始的位置由光标所在的位置确定，循环的条件是光标位置比文本区内容长度大
				for (; cursorIndex < allText.length(); cursorIndex++) {
					// 先获取文本区中需要和seekText比较的文本
					String str = allText.substring(cursorIndex, cursorIndex
							+ seekText.length());
					//将str与seekText忽略大小写比较，一旦能够匹配，在文本区中选中相应的内容，同时退出for循环
					if (str.equalsIgnoreCase(seekText)) {
						view.area.select(cursorIndex,
								cursorIndex + seekText.length());
						break;
					}
					// 如果光标所在位置到文本末尾的长度小于需要查找的文本长度时
					if (allText.length() - cursorIndex <= seekText.length()) {
						JOptionPane.showMessageDialog(seekDialog, "找不到“"
								+ seekText + "”！！！", "记事本",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
			}
		}
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-22
	 * @desc 向上查找操作
	 */
	private void seekNextUp() {
		// 记事本内容
		allText = view.area.getText();

		// 需要查找的内容
		seekText = seekDialog.txtSeek.getText();

		// 光标所在位置,确定查找的位置
		cursorIndex = view.area.getCaretPosition() - 1;

		// 如果查找的内容的长度比光标位置大 或 光标位于文本区首部
		if (seekText.length() > cursorIndex || cursorIndex == 0) {
			JOptionPane.showMessageDialog(seekDialog, "找不到“" + seekText
					+ "”！！！", "记事本", JOptionPane.INFORMATION_MESSAGE);
		}
		// 如果可以查找
		else {
			for (; cursorIndex > 0; cursorIndex--) {
				// 先获取文本区中需要和seekText比较的文本
				String str = allText.substring(cursorIndex - seekText.length(),
						cursorIndex);
				// 区分大小写
				if (seekItem.bsFlag) {
					//一旦能匹配，在文本区中选中相应的内容，同时退出for循环
					if (str.equals(seekText)) {
						view.area.select(cursorIndex - seekText.length(),
								cursorIndex);
						break;
					}
					//如果所要查找的内容在记事本文本区内从光标所在位置反向查找不存在时，弹出对话框提示用户，同时退出for循环
					if(allText.lastIndexOf(seekText, cursorIndex)==-1){
						JOptionPane.showMessageDialog(seekDialog, "找不到“" + seekText
								+ "”！！！", "记事本", JOptionPane.INFORMATION_MESSAGE);
						break;
					}

				}
				// 不区分大小写
				else {
					if (str.equalsIgnoreCase(seekText)) {
						//一旦能匹配，在文本区中选中相应的内容，同时退出for循环
						view.area.select(cursorIndex - seekText.length(),cursorIndex);
						break;
					}
					if (allText.lastIndexOf(seekText.toLowerCase(), cursorIndex) == -1
							&& allText.lastIndexOf(seekText.toUpperCase(),cursorIndex) == -1) {
						//如果所要查找的内容在记事本文本区内从光标所在位置反向忽略大小写查找不存在时，弹出对话框提示用户，同时退出for循环
						JOptionPane.showMessageDialog(seekDialog, "找不到“" + seekText
								+ "”！！！", "记事本", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}

			}

		}
	}

	
	/**=============DocumentListener事件处理=================================*/
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		if(!seekDialog.txtSeek.getText().equals("")){
			seekDialog.btSeekNext.setEnabled(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		if(seekDialog.txtSeek.getText().equals("")){
			seekDialog.btSeekNext.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		
	}

}

