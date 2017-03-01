package control.dialogControl.fontDialogControl;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.dialogView.fontDialogView.FontDialog;


/**
 * 
 * @author 叶雅芳
 * @date 2015-06-22
 * @desc 字体对话框中 列表框选项事件
 *
 */



public class FontListSelectionEvents implements ListSelectionListener{

	FontDialog dialog;	//字体自定义对话框设计类
	
	public int style=Font.PLAIN;//根据用户选中设置字体的字形
	public int  defaultSize=16;	//设置字体的默认大小
	public float size;	//根据用户选中设置的字体大小
	
	//记录字体，字形，大小被选中选项的名称
	public String fontStr,styleStr,sizeStr;
	
	//字体对象
	public Font font1,font2;
	
	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 传入字体自定义对话框设计类对象，实例化成员变量
	 * @param dialog
	 */
	public void setDialog(FontDialog dialog){
		this.dialog=dialog;
	}
	
	public void valueChanged(ListSelectionEvent e) {
		// TODO 自动生成的方法存根
		
		//获取事件源
		JList list=(JList)e.getSource();
		
		
		//选中的是字体
		if(list==dialog.fontList){
			//获取被选中选项的名称
			fontStr=(String)list.getSelectedValue();
			//同时在字体文本框中显示选择的内容
			dialog.txtFont.setText(fontStr);
			
		}
		
		
		//选中的是字形
		else if(list==dialog.styleList){
			//获取被选中选项的名称
			styleStr=(String)list.getSelectedValue();
			//同时在字形文本框中显示选择的内容
			dialog.txtStyle.setText(styleStr);
			
			//常规字形设置
			if(styleStr.equals("常规")){
				style=Font.PLAIN;
			}
			
			//倾斜字形设置
			else if(styleStr.equals("倾斜")){
				style=Font.ITALIC;
			}
			
			//粗体字形设置
			else if(styleStr.equals("粗体")){
				style=Font.BOLD;
			}
			
			//粗体 倾斜 字形设置
			else if(styleStr.equals("粗体 倾斜")){
				style=Font.BOLD+Font.ITALIC;
			}
		}
		
		//选中的是大小
		else if(list==dialog.sizeList){
			//获取被选中选项的名称
			sizeStr=(String)list.getSelectedValue();
			//同时在大小文本框中显示选择的内容
			dialog.txtSize.setText(sizeStr);
			
			//字体大小设置
			if(sizeStr.equals("8"))
				size=8;
			else if(sizeStr.equals("9"))
				size=9;
			else if(sizeStr.equals("10"))
				size=10;
			else if(sizeStr.equals("11"))
				size=11;
			else if(sizeStr.equals("12"))
				size=12;
			else if(sizeStr.equals("14"))
				size=14;
			else if(sizeStr.equals("16"))
				size=16;
			else if(sizeStr.equals("18"))
				size=18;
			else if(sizeStr.equals("20"))
				size=20;
			else if(sizeStr.equals("22"))
				size=22;
			else if(sizeStr.equals("24"))
				size=24;
			else if(sizeStr.equals("26"))
				size=26;
			else if(sizeStr.equals("28"))
				size=28;
			else if(sizeStr.equals("36"))
				size=36;
			else if(sizeStr.equals("48"))
				size=48;
			else if(sizeStr.equals("72"))
				size=72;
			
			else if(sizeStr.equals("初号"))
				size=42;
			else if(sizeStr.equals("小初"))
				size=36;
			else if(sizeStr.equals("一号"))
				size=26;
			else if(sizeStr.equals("小一"))
				size=24;
			else if(sizeStr.equals("二号"))
				size=22;
			else if(sizeStr.equals("小二"))
				size=18;
			else if(sizeStr.equals("三号"))
				size=16;
			else if(sizeStr.equals("小三"))
				size=15;
			else if(sizeStr.equals("四号"))
				size=14;
			else if(sizeStr.equals("小四"))
				size=12;
			else if(sizeStr.equals("五号"))
				size=10.5f;
			else if(sizeStr.equals("小五"))
				size=9;
			else if(sizeStr.equals("六号"))
				size=7.5f;
			else if(sizeStr.equals("小六"))
				size=6.5f;
			else if(sizeStr.equals("七号"))
				size=5.5f;
			else if(sizeStr.equals("八号"))
				size=5;
			
		}
		
		
		//创建字体对象
		font1=new Font(fontStr,style,defaultSize);	//传入默认的整数字体大小
		font2=font1.deriveFont(size);	//传入用户选定的字体大小，涉及有小数
		
		//将设置效果在示例在显示
		dialog.show.setFont(font2);
	}

}
