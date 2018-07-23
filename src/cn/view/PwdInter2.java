package cn.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.bean.ManagerInfo;
import cn.bean.ReaderInfo;
import cn.dao.ManagerInfoDao;
import cn.dao.ReaderInfoDao;
import cn.dao.Impl.ManagerInfoDaoImpl;
import cn.dao.Impl.ReaderInfoDaoImpl;

public class PwdInter2
{
	ManagerInfoDao minfo=new ManagerInfoDaoImpl();
	ManagerInfo manager=new ManagerInfo();
	
	JFrame frame=new JFrame();
	JPanel panel1=new JPanel();
	JLabel reminder=new JLabel("为了您的账户安全，请填写原密码");
	JLabel oldpwd=new JLabel("原密码：");
	JLabel newpwd=new JLabel("新密码：");
	JLabel newpwd2 = new JLabel("确认密码：");
	JPasswordField oldpwd0=new JPasswordField(20);
	JPasswordField newpwd0=new JPasswordField(20);
	JPasswordField newpwd20=new JPasswordField(20);
	JButton affirm=new JButton("确认");
	JButton affalt=new JButton("确认修改");
	
	public PwdInter2(int managerId)
	{
		manager = minfo.manager(managerId);
	}
	
	public void PwdAlter()
	{
		Icon icon=new ImageIcon(getClass().getResource("/image/009.jpg"));  

	    JLabel label=new JLabel(icon);  
	      
	    label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());  
	    frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
	    JPanel j=(JPanel)frame.getContentPane();  
	    j.setOpaque(false);
	    
		frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(panel1);
		panel1.setLayout(null);
		panel1.setOpaque(false);
		
		
		reminder.setFont(new Font("楷体",1,18));
		reminder.setForeground(Color.white);
		reminder.setBounds(250,270,300,25);
		panel1.add(reminder);
		
		
		oldpwd.setBounds(300,300,100,25);
		oldpwd.setForeground(Color.white);
		panel1.add(oldpwd);
		
		
		oldpwd0.setBounds(350,300,90,25);
		panel1.add(oldpwd0);
		
		newpwd.setBounds(280,300,100,25);
		newpwd.setForeground(Color.white);
		newpwd0.setBounds(350,300,90,25);
		
		newpwd2.setBounds(280,350,100,25);
		newpwd2.setForeground(Color.white);
		newpwd20.setBounds(350,350,90,25);
		
		affirm.setBounds(350,400,100,25);
		panel1.add(affirm);
		
		affalt.setBounds(350,400,100,25);		
		
		affirm.addActionListener(new affirmActionListsener());
		affalt.addActionListener(new affaltActionListsener());
		
	}
	private class affirmActionListsener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String title="Message Dialog";
			String message="";
			int type=JOptionPane.PLAIN_MESSAGE;
			if(oldpwd0.getPassword().length>0)
			{
				boolean flag=false;
				for(int i=0;i<minfo.findAllReader().size();i++){
					if(String.valueOf(oldpwd0.getPassword()).equals(manager.getPassword()))
						flag=true;
				}
				if(flag)
				{
					reminder.setText("请输入新密码!");
					reminder.setBounds(330,270,300,25);
					oldpwd.setVisible(false);
					oldpwd0.setVisible(false);
					affirm.setVisible(false);;
					panel1.add(affalt);
					panel1.add(newpwd);
					panel1.add(newpwd0);
					panel1.add(newpwd2);
					panel1.add(newpwd20);
				}
				else
				{
					type=JOptionPane.PLAIN_MESSAGE;
					message="密码错误！";
					JOptionPane.showMessageDialog(frame, message, title, type);
				}
				
			}
			else if(oldpwd0.getPassword().length==0)
			{
				type=JOptionPane.PLAIN_MESSAGE;
				message="请输入原密码！";
				JOptionPane.showMessageDialog(frame, message, title, type);
			}
		}
	}
	private class affaltActionListsener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String title="Message Dialog";
			String message="";
			int type=JOptionPane.PLAIN_MESSAGE;
			if(newpwd0.getPassword().length>0)
			{
				ManagerInfo re=minfo.manager(manager.getId());
				re.setPassword(String.valueOf(newpwd0.getPassword()));
				if(newpwd20.getPassword().length > 0)
				{
					String pwd20 = new String(newpwd20.getPassword());
					if(pwd20.equals(re.getPassword()))
					{
						if(minfo.getUpdateManager(re))
						{
							type=JOptionPane.PLAIN_MESSAGE;
							message="修改密码成功！";
							JOptionPane.showMessageDialog(frame, message, title, type);
						}
						else
						{
							type=JOptionPane.PLAIN_MESSAGE;
							message="修改密码失败！";
							JOptionPane.showMessageDialog(frame, message, title, type);
						}
					}
					else
					{
						type = JOptionPane.WARNING_MESSAGE;
						message = "新密码与确认密码不一致";
						newpwd0.setText(null);
						newpwd20.setText(null);
						JOptionPane.showMessageDialog(frame, message, title, type);
					}
				}
				else
				{
					type = JOptionPane.INFORMATION_MESSAGE;
					message = "请输入确认密码";
					newpwd0.setText(null);
					newpwd20.setText(null);
					JOptionPane.showMessageDialog(frame, message, title, type);
				}
			}
			else if(newpwd0.getPassword().length==0)
			{
				type=JOptionPane.PLAIN_MESSAGE;
				message="请输入新密码！";
				JOptionPane.showMessageDialog(frame, message, title, type);
			}
		}
	}
}
