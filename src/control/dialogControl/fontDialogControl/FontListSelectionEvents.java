package control.dialogControl.fontDialogControl;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.dialogView.fontDialogView.FontDialog;


/**
 * 
 * @author Ҷ�ŷ�
 * @date 2015-06-22
 * @desc ����Ի����� �б��ѡ���¼�
 *
 */



public class FontListSelectionEvents implements ListSelectionListener{

	FontDialog dialog;	//�����Զ���Ի��������
	
	public int style=Font.PLAIN;//�����û�ѡ���������������
	public int  defaultSize=16;	//���������Ĭ�ϴ�С
	public float size;	//�����û�ѡ�����õ������С
	
	//��¼���壬���Σ���С��ѡ��ѡ�������
	public String fontStr,styleStr,sizeStr;
	
	//�������
	public Font font1,font2;
	
	/**
	 * @author Ҷ�ŷ�
	 * @date 2015-06-22
	 * @desc ���������Զ���Ի�����������ʵ������Ա����
	 * @param dialog
	 */
	public void setDialog(FontDialog dialog){
		this.dialog=dialog;
	}
	
	public void valueChanged(ListSelectionEvent e) {
		// TODO �Զ����ɵķ������
		
		//��ȡ�¼�Դ
		JList list=(JList)e.getSource();
		
		
		//ѡ�е�������
		if(list==dialog.fontList){
			//��ȡ��ѡ��ѡ�������
			fontStr=(String)list.getSelectedValue();
			//ͬʱ�������ı�������ʾѡ�������
			dialog.txtFont.setText(fontStr);
			
		}
		
		
		//ѡ�е�������
		else if(list==dialog.styleList){
			//��ȡ��ѡ��ѡ�������
			styleStr=(String)list.getSelectedValue();
			//ͬʱ�������ı�������ʾѡ�������
			dialog.txtStyle.setText(styleStr);
			
			//������������
			if(styleStr.equals("����")){
				style=Font.PLAIN;
			}
			
			//��б��������
			else if(styleStr.equals("��б")){
				style=Font.ITALIC;
			}
			
			//������������
			else if(styleStr.equals("����")){
				style=Font.BOLD;
			}
			
			//���� ��б ��������
			else if(styleStr.equals("���� ��б")){
				style=Font.BOLD+Font.ITALIC;
			}
		}
		
		//ѡ�е��Ǵ�С
		else if(list==dialog.sizeList){
			//��ȡ��ѡ��ѡ�������
			sizeStr=(String)list.getSelectedValue();
			//ͬʱ�ڴ�С�ı�������ʾѡ�������
			dialog.txtSize.setText(sizeStr);
			
			//�����С����
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
			
			else if(sizeStr.equals("����"))
				size=42;
			else if(sizeStr.equals("С��"))
				size=36;
			else if(sizeStr.equals("һ��"))
				size=26;
			else if(sizeStr.equals("Сһ"))
				size=24;
			else if(sizeStr.equals("����"))
				size=22;
			else if(sizeStr.equals("С��"))
				size=18;
			else if(sizeStr.equals("����"))
				size=16;
			else if(sizeStr.equals("С��"))
				size=15;
			else if(sizeStr.equals("�ĺ�"))
				size=14;
			else if(sizeStr.equals("С��"))
				size=12;
			else if(sizeStr.equals("���"))
				size=10.5f;
			else if(sizeStr.equals("С��"))
				size=9;
			else if(sizeStr.equals("����"))
				size=7.5f;
			else if(sizeStr.equals("С��"))
				size=6.5f;
			else if(sizeStr.equals("�ߺ�"))
				size=5.5f;
			else if(sizeStr.equals("�˺�"))
				size=5;
			
		}
		
		
		//�����������
		font1=new Font(fontStr,style,defaultSize);	//����Ĭ�ϵ����������С
		font2=font1.deriveFont(size);	//�����û�ѡ���������С���漰��С��
		
		//������Ч����ʾ������ʾ
		dialog.show.setFont(font2);
	}

}
