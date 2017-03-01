package control.otherControl;

import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author 叶雅芳
 * @date 2015-06-22
 * @desc 声明创建此项目中某些类所共享的变量
 * 
 */

public class Share {

	public Font font1, font2;	//设置组件文本字体
	public JFileChooser fc; // 文件对话框
	public FileNameExtensionFilter filter; // 文件过滤器

	public Share() {
		font1 = new Font("宋体", Font.PLAIN, 14); // 设置字体
		font2 = new Font("宋体", Font.PLAIN, 16); // 设置字体
		fc = new JFileChooser(); // 实例化文件对话框对象(用于打开/保存文件操作)
		filter = new FileNameExtensionFilter("文本文档(*.txt)", "txt");// 过滤器的文本描述：文本文档(*.txt)
	}
}
