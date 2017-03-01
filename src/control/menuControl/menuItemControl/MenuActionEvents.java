package control.menuControl.menuItemControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;

import control.otherControl.Share;
import view.dialogView.fontDialogView.FontDialog;
import view.dialogView.goDialogView.GoDialog;
import view.dialogView.replaceDialogView.ReplaceDialog;
import view.dialogView.seekDialogView.SeekDialog;
import view.frameView.mainFrameView.NoteView;
import view.otherView.popupMenuView.PopMenu;

/**
 * 
 * @author 叶雅芳
 * @date 2015-06-22
 * @desc 主界面主菜单动作事件类
 */

public class MenuActionEvents implements ActionListener, DocumentListener {

	Share share; // 共享类
	NoteView view; // 记事本主界面设计类
	PopMenu popMenu; // 弹出式菜单界面类
	FontDialog dialog;// 字体自定义对话框设计类
	SeekDialog seekDialog; // 查找自定义对话框设计类
	ReplaceDialog replaceDialog; // 替换自定义对话框设计类
	GoDialog goDialog; // 转到自定义对话框设计类

	File f = null; // 获取文件选择器选中的文件夹
	public boolean flag = false; // 标识是否执行保存动作，true代表已保存

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 构造方法传入共享类对象
	 * @param share
	 */
	public MenuActionEvents(Share share) {
		this.share = share;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 传入记事本主界面设计类对象
	 * @param view
	 */
	public void setView(NoteView view) {
		this.view = view;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 传入弹出式菜单界面类对象
	 * @param fontDialog
	 */
	public void setDialog(FontDialog dialog) {
		this.dialog = dialog;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 传入字体自定义对话框设计类对象
	 * @param popMenu
	 */
	public void setPopMenu(PopMenu popMenu) {
		this.popMenu = popMenu;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-22
	 * @desc 传入查找自定义对话框设计类对象
	 * @param seekDialog
	 */
	public void setSeekDialog(SeekDialog seekDialog) {
		this.seekDialog = seekDialog;

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-24
	 * @desc 传入替换自定义对话框设计类对象
	 * @param replaceDialog
	 */
	public void setReplaceDialog(ReplaceDialog replaceDialog) {
		this.replaceDialog = replaceDialog;
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-25
	 * @desc 传入转到自定义对话框设计类对象
	 * @param goDialog
	 */
	public void setGoDialog(GoDialog goDialog) {
		this.goDialog = goDialog;
	}

	/**
	 * ===================ActionListener方法处理====================================
	 * ==
	 */
	public void actionPerformed(ActionEvent e) {

		// 获取事件源
		JMenuItem source = (JMenuItem) e.getSource();

		/* ----------------“新建”的处理------------------------ */
		if (view.newMi.equals(source)) {
			// 点击新建菜单项时，需要对文本区中的内容进行判断，有内容弹出确认对话框咨询用户是否需要进行保存
			// 如果文本有内容
			if (!("".equals(view.area.getText()))) {
				if (flag) {
					// 如果文本内容以保存
					view.area.setText(""); // 记事本内容清空
					view.setTitle("无标题 — 记事本"); // 记事本标题还原
				} else {
					// 如果文本内容未保存，将具体操作交给newDeal()方法
					newDeal();
				}

			}
			f = null; // 新建菜单操作后，f还原
			flag = false; // 新建菜单操作后，flag还原
		}

		/* ---------------“打开”的处理---------------------- */
		else if (view.openMi.equals(source)) {
			// 打开的时候依然要进行文本区内容的判断

			// 如果有内容
			if (!("".equals(view.area.getText()))) {
				if (flag) {
					// 文本中的内容已保存
					openDeal();
				} else {
					// 文本中的内容未保存，交由此方法处理
					beforeOpen();
				}

			}

			// 如果没有内容，将具体处理方法交由openDeal()方法处理
			else if ("".equals(view.area.getText())) {
				if (view.getTitle().equals("无标题 — 记事本")) {
					openDeal();
				} else {
					beforeOpen();// 交由此方法处理
				}

			}
		}

		/* --------------“保存”的处理---------------- */
		else if (view.saveMi.equals(source)) {
			// 判断是否获取了文件选择器选中的文件
			if (f == null) {
				// 为null，未获取，将具体操作交由saveDeal()方法
				saveDeal();
			} else if (f != null && f.getName().equals(view.getTitle())) {
				try {
					// 创建输出文件
					FileWriter fwr = new FileWriter(f);
					// 将文本区中的信息写入文件中
					fwr.write(view.area.getText());
					// 释放资源
					fwr.close();

					flag = true; // 保存动作已执行

				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}

			}

		}

		/* ---------------“另存为”的处理----------------- */
		else if (view.saveAsMi.equals(source)) {
			saveDeal();
		}

		/*-------------- “页面设置”的处理---------------*/
		else if (view.setPageMi.equals(source)) {
			JOptionPane.showMessageDialog(view, "此功能正在开发");
		}

		/*------------- “打印”的处理--------------*/
		else if (view.printMi.equals(source)) {
			JOptionPane.showMessageDialog(view, "此功能正在开发");
		}

		/* -------------“退出”的操作---------------- */
		else if (view.exitMi.equals(source)) {
			exit();

		}

		/* -------------------“撤销”的操作--------------- */
		else if (view.undoMi.equals(source) || popMenu.popUndo.equals(source)) {
			// 如果可以撤销
			if (view.udm.canUndo()) {
				// 调用UndoManager类的undo（）方法，执行撤销功能
				view.udm.undo();
			} else {
				JOptionPane.showMessageDialog(view, "无法撤销！！！");
			}

		}

		/* ----------------"剪切"的操作---------------- */
		else if (view.cutMi.equals(source) || popMenu.popCut.equals(source)) {

			// public void cut()将关联文本模型中当前选定的范围传输到系统剪贴板，并从模型中移除内容。重新设置当前选定

			view.area.cut();

		}

		/* ------------------“复制”的操作----------------- */
		else if (view.copyMi.equals(source) || popMenu.popCopy.equals(source)) {
			// public void copy()将关联文本模型中当前选定的范围传输到系统剪贴板，并在文本模型中保留内容。当前选定保持原样。

			view.area.copy();

		}

		/* -----------------“粘贴”的操作----------------- */
		else if (view.pasteMi.equals(source) || popMenu.popPaste.equals(source)) {
			// public void paste()将系统剪贴板的内容传输到关联的文本模型中。如果在关联视图中有选定的
			// 内容，则使用剪贴板的内容替换它。如果没有选定的内容，则将剪贴板插入关联视图中当前插入位置的前
			// 面。如果剪贴板为空，则不执行任何操作。

			view.area.paste();
		}

		/* ------------------"删除"的操作-------------------- */
		else if (view.delMi.equals(source) || popMenu.popDel.equals(source)) {
			// public void replaceSelection(String content)用给定字符串所表示的新内容替换当前
			// 选定的内容。如果没有选择的内容，则该操作插入给定的文本。如果没有替换文本，则该操作移除当前选择
			// 的内容。
			view.area.replaceSelection("");
		}

		/* -----------------“查找”的操作--------------------- */
		else if (view.seekMi.equals(source)) {
			seekDialog.setVisible(true);
		}

		/* ---------------“查找下一个”的操作 ----------------- */
		else if (view.seekNextMi.equals(source)) {
			seekNextOne();
		}

		/* ---------------“替换”操作-------------------- */
		else if (view.replaceMi.equals(source)) {
			replaceDialog.setVisible(true);
		}

		/* ----------------“转到”的操作------------------- */
		else if (view.goMi.equals(source)) {
			// 显示转到对话框
			goDialog.setVisible(true);
			int row = 0;
			try {
				// 获取光标所在的行，编号从1开始
				row = view.area.getLineOfOffset(view.area.getCaretPosition()) + 1;
			} catch (BadLocationException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			// 在文本框中显示当前光标所在的行
			goDialog.txtRow.setText(row + "");

		}

		/* ---------------“全选”的操作--------------- */
		else if (view.allMi.equals(source) || popMenu.popAll.equals(source)) {
			// public void selectAll()选择 TextComponent 中的所有文本。
			view.area.selectAll();
		}

		/* -----------------“时间/日期”操作--------------- */
		else if (view.dateMi.equals(source)) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
			String s = sdf.format(date);
			view.area.setText(view.area.getText() + s);

		}

		/* -------------“字体”操作------------------ */
		else if (view.fontMi.equals(source)) {
			dialog.setVisible(true);
		}

		/* -----------“关于记事本” 的处理-------------- */
		else if (view.aboutMi.equals(source)) {
			JOptionPane.showMessageDialog(view,
					"此记事本为第二版本\n版权属作者个人所有\n\n\n作者QQ：3066253051", "关于记事本",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-05-23
	 * @desc 新建方法处理
	 */
	private void newDeal() {

		// 确认对话框内容：消息，标题，3个按钮，警告消息
		int type1 = JOptionPane.showConfirmDialog(view.area, "是否将更改保存"
				+ "到 无标题？", "记事本", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE);

		// 如果点击的是“是”，就进行保存
		if (type1 == JOptionPane.YES_OPTION) {
			// 如果点击的是“是”，将保存操作交由saveDeal()方法
			saveDeal();

			view.area.setText(""); // 保存完后记事本内容清空
			view.setTitle("无标题 — 记事本"); // 记事本标题也还原
		}

		// 如果点击的是“否”
		else if (type1 == JOptionPane.NO_OPTION) {
			view.area.setText(""); // 记事本内容清空
			view.setTitle("无标题 — 记事本"); // 记事本标题还原
		}

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 点击打开菜单，文本区有内容时的处理方法
	 */
	public void beforeOpen() {

		// 确认对话框内容：消息，标题，3个按钮，警告消息
		int type2 = JOptionPane.showConfirmDialog(view.area, "是否将更改保存"
				+ "到 无标题？", "记事本", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE);

		// 如果点击的是“是”，就进行保存，再打开文件
		if (type2 == JOptionPane.YES_OPTION) {
			// 如果点击的是“是”，将保存操作交由saveDeal()方法
			saveDeal();

			// 保存好后再弹出打开对话框，此时将处理方法交由openDeal()方法处理
			openDeal();
		}
		// 如果点击的是"否"，则弹出打开对话框，执行打开文件操作
		else if (type2 == JOptionPane.NO_OPTION) {
			openDeal();
		}

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 打开文件对话框方法处理
	 */
	public void openDeal() {

		// 设置当前文件过滤器——文本文件(.txt)
		share.fc.setFileFilter(share.filter);

		// 显示文件打开对话框
		int open = share.fc.showOpenDialog(view.area);
		// 如果单击的是“确定”按钮
		if (open == JFileChooser.APPROVE_OPTION) {

			// 获取文件对话框中用户选中的文件名
			String fileName1 = share.fc.getSelectedFile().getName();
			// 获取文件对话框中用户选中的文件所在的路径
			String path1 = share.fc.getCurrentDirectory().toString();

			// 用户选中的文件是否存在
			File file1 = share.fc.getCurrentDirectory(); // 返回当前目录
			String[] strArray = file1.list(); // 获取此路径名表示的目录中的文件和目录，返回String数组
			// 遍历此数组
			for (int x = 0; x < strArray.length; x++) {
				// 如果文件不存在，且数组已遍历至末尾，弹出对话框提示用户
				if (!strArray[x].equals(fileName1) && x == strArray.length - 1) {
					JOptionPane.showMessageDialog(view, file1
							+ "\n找不到文件。\n请检查文件名是否正确，然后重试。", "打开",
							JOptionPane.WARNING_MESSAGE);
					share.fc.showOpenDialog(view.area);	//重新显示文件打开对话框
					break;
				}
				// 如果文件存在
				else if (strArray[x].equals(fileName1)) {
					// 先把文本文件清空
					view.area.setText("");
					
					// 封装文件
					f = new File(path1 + "/" + fileName1);
					try {
						// 创建字符缓冲输入流
						BufferedReader br = new BufferedReader(
								new FileReader(f));
						// 读写数据
						String readLine = "";
						while ((readLine = br.readLine()) != null) {

							// view.area.append(readLine + "\n");
							view.area.setText(view.area.getText() + readLine
									+ "\n");
						}
						// 释放资源
						br.close();

					} catch (Exception e1) {
						e1.printStackTrace();
					}

					view.setTitle(fileName1);// 打开之后，标题要改
					flag = true; // 刚打开的文件flag标记应为true，表示文件已保存
					break;
				}
			}

		}
	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 保存对话框方法处理——文件保存
	 */
	public void saveDeal() {

		// 设置当前文件过滤器——文本文件(.txt)
		share.fc.setFileFilter(share.filter);

		// 设置保存文件默认扩展名是".txt"
		share.fc.setSelectedFile(new File(".txt"));

		// 显示文件保存对话框
		int save = share.fc.showSaveDialog(view.area);

		// 如果单击的是"确定"按钮
		if (save == JFileChooser.APPROVE_OPTION) {

			// 获取文件对话框中选择的文件名
			String fileName2 = share.fc.getSelectedFile().getName();
			// 获取文件对话框中用户选中的文件所在的路径
			String path2 = share.fc.getCurrentDirectory().toString();

			// 用户选中的文件所在的路径下是否有与fileName2同名的文件
			File file2 = share.fc.getCurrentDirectory(); // 返回当前目录
			String[] strArray = file2.list(); // 获取此路径名表示的目录中的文件和目录，返回String数组
			// 遍历此数组
			for (String s : strArray) {
				// 用户选中的文件所在的路径下有与fileName2同名的文件
				if (s.equals(fileName2)) {
					// 弹出确认对话框，提示“fileName2文件已存在。\n要替换他吗？”
					int type3 = JOptionPane.showConfirmDialog(view.area,
							fileName2 + "文件已存在。\n要替换他吗？", "确认保存",
							JOptionPane.YES_NO_OPTION);

					// 是,替换他：进行写文件操作
					if (type3 == JOptionPane.YES_OPTION) {

						// 封装文件
						f = new File(path2 + "/" + fileName2);
						try {
							// 创建文件输出流写数据
							FileWriter fw = new FileWriter(f);
							// 将文本区中的信息写入文件中
							fw.write(view.area.getText());
							// 释放资源
							fw.close();

							flag = true; // 保存动作已执行

						} catch (Exception e2) {
							e2.printStackTrace();
						}

						// 保存之后，标题要改
						view.setTitle(fileName2);

					}else{
						//否，不替换，重新显示保存对话框
						share.fc.showSaveDialog(view.area);
					}
				}
			}

		}

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-06-22
	 * @desc 点击退出时事件处理
	 */

	public void exit() {

		// 退出前要判断文本区中是否有内容
		// 如果有内容，还要判断flag
		if (!"".equals(view.area.getText())) {
			if (flag) {
				// flag为true，已保存，直接退出
				System.exit(0);
			} else {
				// flag为false，未保存，要进行判断
				// 确认对话框内容：消息，标题，3个按钮，警告消息
				int type4 = JOptionPane.showConfirmDialog(view.area, "是否将更改保存"
						+ "到 无标题？", "记事本", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);

				// 如果点击的是“是”，就进行保存
				if (type4 == JOptionPane.YES_OPTION) {
					// 如果点击的是“是”，将保存操作交由saveDeal()方法
					saveDeal();
					// 保存完后就退出
					System.exit(0);
				} else if (type4 == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}

		} else {
			// 如果没有内容，直接退出
			System.exit(0);
		}

	}

	/**
	 * @author 叶雅芳
	 * @date 2015-07-22
	 * @desc “查找下一个”菜单项 的事件处理
	 */
	private void seekNextOne() {
		// 获取文本区内容
		String allText = view.area.getText();
		// 获取选中的文本
		String seekText = view.area.getSelectedText();
		// 光标所在位置
		int cursorIndex = view.area.getCaretPosition();
		// 如果选中的文本不为空
		if (seekText != null) {
			// 如果查找的内容比文本区内容长 或 光标位于文本区尾部
			if (seekText.length() > allText.length()
					|| cursorIndex == allText.length()) {
				JOptionPane.showMessageDialog(seekDialog, "找不到“" + seekText
						+ "”！！！", "记事本", JOptionPane.INFORMATION_MESSAGE);
			}
			// 如果可以查找
			else {
				for (; cursorIndex < allText.length(); cursorIndex++) {
					// 先获取文本区中需要和seekText比较的文本
					String str = allText.substring(cursorIndex, cursorIndex
							+ seekText.length());
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
					}
				}
			}
		} else {
			// 如果选中的文本为空,打开查找对话框
			seekDialog.setVisible(true);
		}

	}

	/** ============DocumentListener方法处理========================== */
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		if (!view.area.getText().equals("")) {
			view.undoMi.setEnabled(true);
			view.seekMi.setEnabled(true);
			view.seekNextMi.setEnabled(true);
			popMenu.popUndo.setEnabled(true);
			popMenu.popAll.setEnabled(true);
			// 有文本插入，需要把保存标识设为false
			flag = false;
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		if (view.area.getText().equals("")) {
			view.seekMi.setEnabled(false);
			view.seekNextMi.setEnabled(false);
			popMenu.popAll.setEnabled(false);
			// 有文本移除，需要把保存标识设为false
			flag = false;
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
	}

}
