package cn.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.bean.ReaderInfo;
import cn.dao.ReaderInfoDao;
import cn.dao.Impl.ReaderInfoDaoImpl;

public class RegisterInter extends JFrame
{
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame=new JFrame();
	RentFrame rentFrame;
	String sex = "男";
	ReaderInfoDao rid=new ReaderInfoDaoImpl();
	ReaderInfo reader=new ReaderInfo();
	JTextField name0=new JTextField(20);
	JPasswordField pwd10 = new JPasswordField(20);
	JPasswordField pwd20 = new JPasswordField(20);
	JComboBox<String> sex0=new JComboBox<String>();
	JTextField tel0=new JTextField(11);
	
	public void RegisterInte()
	{ 
	    Icon icon=new ImageIcon(getClass().getResource("/image/002.png"));  

	    JLabel label=new JLabel(icon);  
	      
	    label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());  
	      
	    frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
	    JPanel j=(JPanel)frame.getContentPane();  
	    j.setOpaque(false);
	   
		frame.setVisible(true);
		frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		frame.setTitle("欢迎注册");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		JLabel name=new JLabel("姓名：");
		name.setBounds(270,150,80,25);
		panel.add(name);
		
		
		name0.setBounds(320,150,165,25);
		panel.add(name0);
		
		JLabel pwd1=new JLabel("密码:");
		pwd1.setBounds(270,190,80,25);
		panel.add(pwd1);
		
		JLabel pwd2=new JLabel("确认密码:");
		pwd2.setBounds(245,230,80,25);
		panel.add(pwd2);
		
		
		pwd10.setBounds(320,190,165,25);
		panel.add(pwd10);
		
		
		pwd20.setBounds(320,230,165,25);
		panel.add(pwd20);
		
		JLabel sex=new JLabel("性别：");
		sex.setBounds(270,270,80,25);
		panel.add(sex);
		
		
		sex0.setBounds(320,270,165,25);
		panel.add(sex0);
		String[] sexx={"男","女"};
		for(int i=0;i<sexx.length;i++)
		sex0.addItem(sexx[i]);
		sex0.addItemListener(new sexListener());
		
		JLabel tel=new JLabel("电话号码：");
		tel.setBounds(245,310,80,25);
		panel.add(tel);
		
		
		tel0.setBounds(320,310,165,25);
		panel.add(tel0);
		
		JButton registerbutton=new JButton("注册");
		registerbutton.setBounds(330,360,80,25);
		panel.add(registerbutton);
		panel.setOpaque(false);
		registerbutton.addActionListener(new registerListener());
		
		frame.add(panel);
	}
	
	private class sexListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			sex = (String)e.getItem();
		}
	}
	
	private class registerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String source=e.getActionCommand();
			String title="Message Dialog";
			String message="";
			int type=JOptionPane.PLAIN_MESSAGE;
			if(source.equals("注册"))
			{
				if(name0.getText().length()>0&&pwd10.getPassword().length>0&&pwd20.getPassword().length>0&&tel0.getText().length()>0)
				{
					reader.setName(name0.getText());
					reader.setPassword(String.valueOf(pwd10.getPassword()));
					reader.setSex(sex);
					reader.setTel(tel0.getText());
					
					if(rid.checkPwd(String.valueOf(pwd10.getPassword()), String.valueOf(pwd20.getPassword())))
					{
						if(rid.checkTel(tel0.getText()))
						{
							type=JOptionPane.PLAIN_MESSAGE;
							message="该电话号码已被绑定！";
							JOptionPane.showMessageDialog(frame, message, title, type);
						}
						else
						{
							
							ReaderInfo re=rid.register(reader);
							if(re!=null)
							{
								int r=rid.queryId(tel0.getText());
								type=JOptionPane.PLAIN_MESSAGE;
								message="注册成功，欢迎新用户"+re.getName()+"你的登录ID为"+r;
								JOptionPane.showMessageDialog(frame, message, title, type);
								frame.setVisible(false);
								frame.dispose();
								rentFrame = new RentFrame(r);
							}
						}
					}
					else
					{
						type=JOptionPane.PLAIN_MESSAGE;
						message="两次输入密码不一致！";
						JOptionPane.showMessageDialog(frame, message, title, type);
					}
				}
				else
				{
					type=JOptionPane.PLAIN_MESSAGE;
					message="请输入完整信息！";
					JOptionPane.showMessageDialog(frame, message, title, type);
				}
			}
		}
	}
}
