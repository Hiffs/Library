package cn.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.bean.ReaderInfo;
import cn.dao.ReaderInfoDao;
import cn.dao.Impl.ReaderInfoDaoImpl;

public class PerInter implements ActionListener
{
	ReaderInfoDao rid=new ReaderInfoDaoImpl();
	ReaderInfo reader;
	
	JFrame frame=new JFrame();
	JPanel panel=new JPanel();
	JPanel panel0=new JPanel();
	
	JTextField account0=new JTextField();
	JTextField name0=new JTextField(20);
	JTextField sex0=new JTextField();
	JTextField tel0=new JTextField();
	JButton alter=new JButton("�༭������Ϣ");
	JButton affirm=new JButton("ȷ���޸�");
	
	public PerInter(int readerId)
	{
		reader=rid.queryIndi(readerId);
		per();
	}
	
	public void per()
	{
		JFrame frame=new JFrame("��������");
		Icon icon=new ImageIcon(getClass().getResource("/image/008.jpg"));  

	    JLabel label=new JLabel(icon);  
	      
	    label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());  
	      
	    frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
	    JPanel j=(JPanel)frame.getContentPane();  
	    
	    j.setOpaque(false);
	    frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel.setLayout(null);
		panel.setOpaque(false);
		frame.add(panel);
		
		JLabel headline=new JLabel("��������");
		//headline.setForeground(Color.black);
		headline.setFont(new Font("����",1,20));
		headline.setBounds(440,100,100,25);
		panel.add(headline);
		
		JLabel account=new JLabel("�˺ţ�");
		account.setBounds(370,200,80,25);
		panel.add(account);
		
		
		account0.setText(String.valueOf(reader.getId()));
		account0.setBounds(420,200,150,25);
		account0.setEditable(false);
		panel.add(account0);
		
		JLabel name=new JLabel("������");
		name.setBounds(370,250,80,25);
		panel.add(name);
		
		name0.setText(reader.getName());
		name0.setBounds(420,250,150,25);
		name0.setEditable(false);
		panel.add(name0);
		
		JLabel sex=new JLabel("�Ա�");
		sex.setBounds(370,300,80,25);
		panel.add(sex);
		
		sex0.setText(reader.getSex());
		sex0.setBounds(420,300,150,25);
		sex0.setEditable(false);
		panel.add(sex0);
		
		JLabel tel=new JLabel("�绰��");
		tel.setBounds(370,350,80,25);
		panel.add(tel);
		
		tel0.setText(reader.getTel());
		tel0.setBounds(420,350,150,25);
		tel0.setEditable(false);
		panel.add(tel0);
		
		
		alter.setBounds(410,400,130,25);
		panel.add(alter);
		
		JButton altpwd=new JButton("�޸�����");
		altpwd.setForeground(Color.pink);
		altpwd.setContentAreaFilled(false);
		altpwd.setBounds(570,200,100,25);
		panel.add(altpwd);
		
		affirm.setBounds(410,400,100,25);
		
		alter.addActionListener(this);
		altpwd.addActionListener(this);
		
		panel0.setLayout(null);
		
		affirm.addActionListener(new affirmActionListsener());
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String source=e.getActionCommand();
		if(source.equals("�༭������Ϣ"))
		{
			name0.setEditable(true);
			sex0.setEditable(true);
			tel0.setEditable(true);
			alter.setVisible(false);
			affirm.setBounds(410,400,100,25);
			panel.add(affirm);
		}
		else if(source.equals("�޸�����"))
		{
			new PwdInter(reader.getId());
		}
	}
private class affirmActionListsener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String title="Message Dialog";
		String message="";
		int type=JOptionPane.PLAIN_MESSAGE;
		String source=e.getActionCommand();
		if(source.equals("ȷ���޸�"))
		{
			reader.setName(name0.getText());
			reader.setSex(sex0.getText());
			reader.setTel(tel0.getText());
			if(rid.updIndi(reader))
			{
				type=JOptionPane.PLAIN_MESSAGE;
				message="�޸ĳɹ�!";
				JOptionPane.showMessageDialog(frame, message, title, type);
			}
			else
			{
				type=JOptionPane.PLAIN_MESSAGE;
				message="�޸�ʧ��!";
				JOptionPane.showMessageDialog(frame, message, title, type);
			}
		}
	}
	
}
}