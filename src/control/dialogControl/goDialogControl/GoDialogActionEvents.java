package control.dialogControl.goDialogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import view.dialogView.goDialogView.GoDialog;
import view.frameView.mainFrameView.NoteView;

/**
 * 
 * @author 叶雅芳
 * @date 2015-07-25
 * @desc 转到对话框动作事件类
 *
 */

public class GoDialogActionEvents implements ActionListener {

	GoDialog goDialog;	//转到自定义对话框设计类
	
	NoteView view;	//记事本用户界面类
	
	/**
	 * @author 叶雅芳
	 * @date 2015-07-25
	 * @desc 传入记事本用户界面类
	 * @param view
	 */
	public GoDialogActionEvents(NoteView view) {
		this.view=view;
	}


	/**
	 * @author 叶雅芳
	 * @date 2015-07-25
	 * @desc 传入转到自定义对话框设计类
	 * @param goDialog
	 */
	public void setGoDialog(GoDialog goDialog) {
		this.goDialog=goDialog;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		//获取事件源
		JButton source = (JButton)e.getSource();
		
		//转到按钮
		if(source.equals(goDialog.btGo)){
			//获取文本框所表示的行数
			int row=Integer.parseInt(goDialog.txtRow.getText());
			//如果要跳转的行数比文本区总行数大
			if(row>view.area.getLineCount()){
				JOptionPane.showMessageDialog(goDialog, "行数超过了总行数", "记事本——跳行", JOptionPane.PLAIN_MESSAGE);
				goDialog.txtRow.setText(""+view.area.getLineCount());
			}else{
				//转到指定行,方法public int getLineStartOffset(int line)与public void setCaretPosition(int position)结合
				try {
					//将光标设置在指定的行号
					view.area.setCaretPosition(view.area.getLineStartOffset(row-1));
					//关闭对话框
					goDialog.dispose();
				} catch (BadLocationException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		}
		
		//取消按钮
		else if(source.equals(goDialog.btCancel)){
			goDialog.dispose();
		}
	}

}
