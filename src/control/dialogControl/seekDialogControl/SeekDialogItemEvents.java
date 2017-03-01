package control.dialogControl.seekDialogControl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.dialogView.seekDialogView.SeekDialog;

/**
 * 
 * @author 叶雅芳
 * @date 2015-07-22
 * @desc 查找对话框选项事件类
 *
 */

public class SeekDialogItemEvents implements ItemListener{

	SeekDialog seekDialog;	//查找对话框动作事件类
	
	boolean udFlag=true;	//标识向上向下单选按钮，true为向下，false为向上
	boolean bsFlag=false;	//标识大小写，false不区分大小写，true区分大小写
	
	/**
	 * @author 叶雅芳
	 * @date 2015-07-22
	 * @desc 传入查找对话框动作事件类对象，实例化成员变量
	 * @param seekDialog
	 */
	public void setSeekDialog(SeekDialog seekDialog) {
		this.seekDialog=seekDialog;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		//获取事件源
		Object source=e.getSource();
		
		/* ----------------向上单选按钮处理------------------ */
		if(source.equals(seekDialog.rdbtnUp)){
			udFlag=false;
		}
		/* ----------------向下单选按钮处理------------------ */
		else if(source.equals(seekDialog.rdbtnDown)){
			udFlag=true;
		}
		
		/* ----------------区分大小写复选按钮处理------------------ */
		else if(source.equals(seekDialog.chckbxc)&&e.getStateChange()==ItemEvent.SELECTED){
			bsFlag=true;	//区分大小写
		}else if(source.equals(seekDialog.chckbxc)&& e.getStateChange()==ItemEvent.DESELECTED){
			bsFlag=false;	//不区分大小写
		}
		
	}

}
