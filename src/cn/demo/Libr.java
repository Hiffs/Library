package cn.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.bean.BookInfo;
import cn.dao.BookInfoDao;
import cn.dao.Impl.BookInfoDaoImpl;
import cn.ui.GButton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class Libr
{

	private JFrame frame;
	BookInfoDao bid = new BookInfoDaoImpl();
	public static final Color GREENF = new Color(205, 255, 205);  
	public static final Color GREENB = new Color(51, 154, 47);  
	public static final Color BLUEF = new Color(125, 161, 237); 
	public static final Color BLUEB = new Color(91, 118, 173); 
	GButton jb2,jb3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Libr window = new Libr();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Libr()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyButtonUI bu = new MyButtonUI();
		
		
		Icon ic = new ImageIcon("C:/Users/Administrator/Desktop/图书管理/rrr.png");
		JPanel jp1 = new JPanel();
		JLabel jl1 = new JLabel(ic);
		final JComboBox jcb = new JComboBox();
		final String[] jcbs = {"书名","作者","类型"};
		jp1.setLayout(new FlowLayout());
		for(int i = 0; i < jcbs.length; i++)
		{
			jcb.addItem(jcbs[i]);
		}
		
		final JTextField jtf = new JTextField(30);
		JButton searchB = new JButton();
		searchB.setUI(bu);
		searchB.setBorder(null);
		searchB.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StringBuffer s = new StringBuffer();
				s.append(jtf.getText());
				List<BookInfo> lb = bid.showBookByName(s.substring(0));	
				if(lb.size() > 0)
				{
					System.out.println(lb.get(0).getName());;
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "查找失败", "", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		jp1.add(jl1);
		jp1.add(jcb);
		jp1.add(jtf);
		jp1.add(searchB);
		frame.add(jp1,BorderLayout.NORTH);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
		JButton jb1 = new JButton("查书");
//		jb1.setAlignmentX(Component.CENTER_ALIGNMENT);
		jb1.setFont(new Font("宋体", 1, 20));
		jb1.setBackground(Color.yellow);
		jb2 = new GButton();
		jb2.setText("借书");
		jb2.addActionListener(new jb2listener());
//		jb1.setAlignmentX(Component.CENTER_ALIGNMENT);
		jb2.setFont(new Font("宋体", 0, 20));
		jb3 = new GButton();
		jb3.setText("还书");
		jb3.addActionListener(new jb3listener());
//		jb1.setAlignmentX(Component.CENTER_ALIGNMENT);
		jb3.setFont(new Font("宋体", 0, 20));
		JButton jb4 = new JButton("查询借阅记录");
//		jb1.setAlignmentX(Component.CENTER_ALIGNMENT);
		jb4.setFont(new Font("宋体", 0, 20));
		
		jp2.add(jb1);
		jp2.add(Box.createVerticalStrut(10));
		jp2.add(jb2);
		jp2.add(Box.createVerticalStrut(10));
		jp2.add(jb3);
		jp2.add(Box.createVerticalStrut(10));
		jp2.add(jb4);
		frame.add(jp2,BorderLayout.WEST);
		
		JPanel jp3 = new JPanel();
		JLabel jl2 = new JLabel("<html>=========================" +
				"<br>=========================</html>");
		jl2.setSize(200, 50);
		jp3.add(jl2);
		frame.add(jp3,BorderLayout.CENTER);
	}
	
	private class jb3listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			jb2.setForeground(GREENF);
			jb3.setForeground(BLUEF);
		}
	}
	
	private class jb2listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			jb2.setForeground(BLUEF);
			jb3.setForeground(GREENF);
		}
	}

}
