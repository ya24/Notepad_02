package control.otherControl;

import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-06-22
 * @desc ������������Ŀ��ĳЩ��������ı���
 * 
 */

public class Share {

	public Font font1, font2;	//��������ı�����
	public JFileChooser fc; // �ļ��Ի���
	public FileNameExtensionFilter filter; // �ļ�������

	public Share() {
		font1 = new Font("����", Font.PLAIN, 14); // ��������
		font2 = new Font("����", Font.PLAIN, 16); // ��������
		fc = new JFileChooser(); // ʵ�����ļ��Ի������(���ڴ�/�����ļ�����)
		filter = new FileNameExtensionFilter("�ı��ĵ�(*.txt)", "txt");// ���������ı��������ı��ĵ�(*.txt)
	}
}
