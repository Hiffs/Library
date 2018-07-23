package cn.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cn.bean.*;
import cn.dao.*;
import cn.dao.Impl.*;
import cn.ui.*;
import cn.view.RegisterInter;

public class Chuang extends JFrame implements ActionListener
{
	ReaderInfoDao rid=new ReaderInfoDaoImpl();
	RegisterInter ri=new RegisterInter();
	ManagerInfoDao mad = new ManagerInfoDaoImpl();
	RentFrame rentFrame;
	ManagerInterface managerInterface;
	
	JTextField userText = new JTextField(255);
	JPasswordField passwordText = new JPasswordField(255);
	JFrame frame=new JFrame(); 
	static String jrb=null;
	static String key=null;
	
	//��¼����
	public Chuang()
	{
		initialize();
	}
	
	
	private void initialize()
	{  
	    //����ͼƬ  
	    Icon icon=new ImageIcon(getClass().getResource("/image/001.jpg"));  
	    //System.out.println(icon.getIconHeight());
	    //System.out.println(icon.getIconWidth());
	    //Image im=new Image(icon);  
	    //��ͼƬ����label��  
	    JLabel label=new JLabel(icon);  

	    //����label�Ĵ�С  
	    label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());  
	      
	    //��ȡ���ڵĵڶ��㣬��label����  
	    frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));  
	          
	    //��ȡframe�Ķ�������,������Ϊ͸��  
	    JPanel j=(JPanel)frame.getContentPane();  
	    j.setOpaque(false);  
	
	    JPanel panel=new JPanel();  
	    panel.setLayout(null);
	    //JTextArea jta=new JTextArea(10,60);  
	    
	    ButtonGroup bg=new ButtonGroup();
	    
	    JRadioButton guan=new JRadioButton("����Ա");
	    guan.setForeground(Color.white);
	    guan.setBounds(420,360, 80, 25);
	    guan.setContentAreaFilled(false); 
	    panel.add(guan);
	    bg.add(guan);
	    guan.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent ae){
	            jrb= "����Ա";
	        }
	    });
	    
	    JRadioButton pu=new JRadioButton("����");
	    pu.setForeground(Color.white);
	    pu.setBounds(520,360, 80, 25);
	    pu.setContentAreaFilled(false);
	    panel.add(pu);
	    bg.add(pu);
	    pu.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent ae){
	            jrb= "����";
	        }
	    });
	    
	    
	    
	    JLabel headline=new JLabel("��ӭ�������ħ������С��");
	    headline.setForeground(Color.white);
	    headline.setFont(new Font("����",Font.BOLD,31));
	    headline.setBounds(305,200,400,50);
	    panel.add(headline);
	    
	    JLabel userLabel = new JLabel("�˺�:");  
	    userLabel.setForeground(Color.white);
	    userLabel.setBounds(390,280,80,25);
	    panel.add(userLabel);
	    
	    
	    userText.setBounds(440,280,165,25);
	    panel.add(userText);
	    
	    JLabel passwordLabel = new JLabel("����:");
	    passwordLabel.setForeground(Color.white);
	    passwordLabel.setBounds(390,320,80,25);
	    panel.add(passwordLabel);
	    
	    
	    passwordText.setBounds(440,320,165,25);
	    panel.add(passwordText);
	    
	    GButton loginButton = new GButton();
	    loginButton.setText("��¼");
	    loginButton.setBounds(400,400, 80, 25);
	    panel.add(loginButton);
	    
	    GButton registerButton = new GButton();
	    registerButton.setText("ע��");
	    registerButton.setBounds(520,400, 80, 25);
	    panel.add(registerButton);
	    //��������Ϊ͸���ġ����򿴲���ͼƬ  
	    panel.setOpaque(false);  
	
	    frame.add(panel);   
	    frame.setTitle("");
	    frame.setSize(icon.getIconWidth(),icon.getIconHeight());  
	    frame.setLocationRelativeTo(null);  
	    frame.setResizable(false);
	    frame.setVisible(true); 
	   
	    guan.addActionListener(this);
	    pu.addActionListener(this);
	    loginButton.addActionListener(this);
	    registerButton.addActionListener(this);
	    
	}  
	public static void main(String[] args)   
	{  
	    new Chuang();
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String source = e.getActionCommand();
		String title="Message Dialog";
		String message="";
		int type=JOptionPane.PLAIN_MESSAGE;
		if(source.equals("��¼")) 
		{
			if (userText.getText().matches("[0-9]+"))
			{  
				if(userText.getText().length()>0 && String.valueOf(passwordText.getPassword()).length()>0)
				{
					if(jrb==null)
					{
						type=JOptionPane.PLAIN_MESSAGE;
						message="��ȷ�������ݣ�";
						JOptionPane.showMessageDialog(frame, message, title, type);
					}
					else if(jrb.equals("����"))
					{
						ReaderInfo re=null;
						re=rid.login(Integer.parseInt(userText.getText()),String.valueOf(passwordText.getPassword()));
						if(re!=null)
						{
							frame.setVisible(false);
							frame.dispose();
//							mi.MainInte();
							rentFrame = new RentFrame(Integer.parseInt(userText.getText()));
						}
						else
						{
							type=JOptionPane.PLAIN_MESSAGE;
							message="�˺Ż��������";
							JOptionPane.showMessageDialog(frame, message, title, type);
						}
					}
					else if(jrb.equals("����Ա"))
					{
						ManagerInfo ma=null;
						ma=mad.Login(Integer.parseInt(userText.getText()),String.valueOf(passwordText.getPassword()));
						if(ma!=null)
						{
							frame.setVisible(false);
							frame.dispose();
							managerInterface = new ManagerInterface(ma.getId());
							managerInterface.showInterface();
						}
						else
						{
							type=JOptionPane.PLAIN_MESSAGE;
							message="�˺Ż��������";
							JOptionPane.showMessageDialog(frame, message, title, type);
						}
					}
				}
				else if(userText.getText().length()>0 && String.valueOf(passwordText.getPassword()).length()==0)
				{
					type=JOptionPane.PLAIN_MESSAGE;
					message="���벻��Ϊ�գ�";
					JOptionPane.showMessageDialog(frame, message, title, type);
				}
				else
				{
					type=JOptionPane.PLAIN_MESSAGE;
					message="�˺Ų���Ϊ�գ�";
					JOptionPane.showMessageDialog(frame, message, title, type);
				}
			}
			else if(userText.getText().length()==0)
			{
				type=JOptionPane.PLAIN_MESSAGE;
				message="�˺Ų���Ϊ�գ�";
				JOptionPane.showMessageDialog(frame, message, title, type);
			}
			else
			{
				type=JOptionPane.PLAIN_MESSAGE;
				message="��������ȷ���˺ţ�";
				JOptionPane.showMessageDialog(frame, message, title, type);
			}
		}
		else if(source.equals("ע��"))
		{
			frame.setVisible(false);
			frame.dispose();
			ri.RegisterInte();
		}
	}
}
