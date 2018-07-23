package cn.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import cn.dao.*;
import cn.dao.Impl.*;
import cn.ui.BButton;
import cn.ui.GButton;
import cn.bean.*;


public class ListFrame
{
	private JFrame listFrame;
	private RentFrame rentFrame;
	private PerInter p;
	private Chuang ch;
	int readerId;
	List<RentInfo> lr1;
	List<RentInfo> lr2;
	
	
	AuthorDao ad = new AuthorDaoImpl();
	BookInfoDao bkd = new BookInfoDaoImpl();
	PublishDao pd = new PublishDaoImpl();
	TypeDao td = new TypeDaoImpl();
	RentInfoDao rd = new RentInfoDaoImpl();
	ReaderInfoDao red = new ReaderInfoDaoImpl();
	
    public static final Color GREENB = new Color(205, 255, 205);  //背景色
    public static final Color GREENF = new Color(51, 154, 47);    //浅字体色
    public static final Color BLUEB = new Color(125, 161, 237);  //背景色
    public static final Color BLUEF = new Color(91, 118, 173);   //浅色字体
	
	DefaultTableModel dtm;
	JTextField bookName;
	JTextArea bookTextList;
	JButton searchButton,rentButton,returnButton,rent,
			listButton,bookList,zoneButton,exitButton;
	JTable bookTable;
	
	final String[] index = {"请选择...","书名", "作者", "类型"};
	private String[] tableHead = {"编号","流水单号","图书名字","借阅人","借书时间","还书时间"};
	private Object[][] tableData = {};
	JComboBox<String> comboBox;
	String str1;
	int s = 0;
	
//	public static void main(String[] args)
//	{
//		EventQueue.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				try
//				{
//					ListFrame window = new ListFrame(1001);
//					window.listFrame.setVisible(true);
//				} catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public ListFrame()
	{
		initialize();
	}
	
	public ListFrame(int id)
	{
		readerId = id;
		initialize();
	}
	
	private void initialize()
	{	
		listFrame = new JFrame();
		
		JLayeredPane lay = new JLayeredPane();
		
		ImageIcon icon=new ImageIcon(getClass().getResource("/image/manager.png"));  
	    JLabel label=new JLabel(icon);  
	    label.setBounds(0,0,800,600);
	    lay.add(label,JLayeredPane.DEFAULT_LAYER);

	    JPanel container = (JPanel)listFrame.getContentPane();
	    container.setLayout(null);
	    container.setOpaque(false);
	    lay.add(container, JLayeredPane.MODAL_LAYER);
	    
		listFrame.setBounds(100, 100, 800, 600);
		listFrame.setLayeredPane(lay);
		listFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listFrame.setResizable(false);
		listFrame.setLocationRelativeTo(null); 
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel jp1 = new JPanel();
		jp1.setBounds(100, 10, 500, 35);
		comboBox = new JComboBox<String>();
		comboBox.setEnabled(false);
//		for(int i = 0; i < index.length; i++)
//		{
			comboBox.addItem(index[0]);
//		}
		jp1.add(comboBox);
		bookName = new JTextField(30);
		bookName.setEnabled(false);
		jp1.add(bookName);
		
		searchButton = new JButton("搜索");
		searchButton.setEnabled(false);
		jp1.add(searchButton);
		jp1.setOpaque(false);
		listFrame.add(jp1,BorderLayout.NORTH);
		
		rentButton = new GButton();
		rentButton.setText("　借书　");
		newButton(rentButton, 1);
		rentButton.addActionListener(new rentButtonListener());
		Box hBox1 = Box.createHorizontalBox();
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(rentButton);
		listButton = new GButton();
		listButton.setText("　书单　");
		listButton.addActionListener(new listButtonListener());
		Box hBox2 = Box.createHorizontalBox();
		hBox2.add(Box.createHorizontalStrut(5));
		hBox2.add(listButton);
		bookList = new GButton();
		bookList.setText("历史记录");
		bookList.addActionListener(new booklistListener());
		Box hBox3 = Box.createHorizontalBox();
		hBox3.add(Box.createHorizontalStrut(5));
		hBox3.add(bookList);
		zoneButton = new GButton();
		zoneButton.setText("个人空间");
		newButton(zoneButton, 1);
		zoneButton.addActionListener(new zoneButtonListener());
		Box hBox4 = Box.createHorizontalBox();
		hBox4.add(Box.createHorizontalStrut(5));
		hBox4.add(zoneButton);
		exitButton = new BButton();
		exitButton.setText("注销");
		exitButton.addActionListener(new exitButtonListener());
		Box hBox5 = Box.createHorizontalBox();
		hBox5.add(Box.createHorizontalStrut(5));
		hBox5.add(exitButton);
		
		Box vBox = Box.createVerticalBox();
		vBox.setBounds(8, 60, 90, 500);
		vBox.add(hBox1);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox2);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox3);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox4);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox5);
		vBox.add(Box.createVerticalStrut(100));
		vBox.setOpaque(false);
		listFrame.add(vBox,BorderLayout.WEST);
		
		JPanel jp3 = new JPanel();
		jp3.setBounds(100, 60, 600, 450);
		dtm = new DefaultTableModel(tableData, tableHead);
		bookTable = new JTable(dtm);
		bookTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
		scrollPane = new JScrollPane(bookTable);
		jp3.add(scrollPane);
		jp3.setOpaque(false);
		listFrame.add(jp3);
		
		JPanel jp4 = new JPanel();
		jp4.setBounds(280, 500, 200, 35);
		jp4.setLayout(new BorderLayout());
		jp4.setOpaque(false);
		rent = new BButton();
		rent.setText("续借");
		rent.addActionListener(new rentListener());
		jp4.add(rent,BorderLayout.WEST);
		returnButton = new BButton();
		returnButton.setText("还书");
		returnButton.setEnabled(false);
		returnButton.addActionListener(new returnButtonListener());
		jp4.add(returnButton,BorderLayout.EAST);
		listFrame.add(jp4);
	
		listFrame.setVisible(true);
	}
	
	private void newButton(JButton b, int k)
	{
		b.setFocusPainted(false);
		if(k == 1)
		{
			b.setForeground(GREENF);
		}
		else if(k == 2)
		{
			b.setForeground(Color.black);
		}
		else if(k == 3)
		{
			b.setForeground(Color.white);
		}
	}
	
	public void hisList()
	{
		newButton(listButton, 1);
		newButton(bookList, 2);
		rent.setEnabled(false);
		returnButton.setEnabled(false);
		lr2 = null;
		lr2 = rd.getAllRentInfoByReadId(readerId);
		show(lr2);
	}
	public void showList()
	{
		newButton(listButton, 2);
		newButton(bookList, 1);
		rent.setEnabled(true);
		returnButton.setEnabled(true);
		lr1 = null;
		lr1 = rd.getRentInfoByReadId(readerId);
		show(lr1);
	}
	
	private void show(List<RentInfo> lr)
	{
		dtm.setRowCount(0);
		Object o = "-";
		for(int j = 0; j < lr.size(); j++)
		{
			RentInfo r = lr.get(j);
			Vector v = new Vector();
			v.add(j+1);
			v.add(r.getId());
			v.add(bkd.showBookById(r.getBookid()).getName());
			v.add(red.queryIndi(readerId).getName());
			v.add(r.getBooktime());
			if(r.getReturntime() == null)
			{
				v.add(o);
			}
			else
			{
				v.add(r.getReturntime());
			}
			dtm.addRow(v);
		}
	}

	private class rentListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent paramActionEvent)
		{
			int i = bookTable.getSelectedRow();
			if(i >= 0)
			{
				//获得选中的书本id
				RentInfo r = lr1.get(i);
				int rentId = r.getId();
				Date d = new Date();
				//获得选中书本的状态
				if(r.getReturntime() == null)
				{
					if(rd.rebookRentInfo(rentId, d))
					{
						JOptionPane.showMessageDialog(listFrame, "续借成功");
					}
					else
					{
						JOptionPane.showMessageDialog(listFrame, "借阅失败","",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			showList();
		}
	}
	
	private class rentButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent paramActionEvent)
		{
			listFrame.setVisible(false);
			listFrame.dispose();
			rentFrame = new RentFrame(readerId);
		}		
	}
	
	private class listButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			showList();
		}
	}
	
	private class booklistListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			hisList();
		}	
	}
	
	private class returnButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			int i = bookTable.getSelectedRow();
			if(i >= 0)
			{
				int rentId = lr1.get(i).getId();
				Date booktime = new Date(lr1.get(i).getBooktime().getTime());
				Date currt = new Date();
				if(lr1.get(i).getReturntime() == null)
				{
					long sub = currt.getTime() - booktime.getTime();
					if(sub > 3628800000l)
					{
						JOptionPane.showMessageDialog(listFrame, "逾期,请到管理员处还书","",JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						if(rd.returnRentInfo(rentId, currt))
						{
							JOptionPane.showMessageDialog(listFrame, "归还成功");
						}
					}
				}
			}
			showList();
		}	
	}
	
	private class zoneButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			p = new PerInter(readerId);
		}
	}
	
	private class exitButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			listFrame.setVisible(false);
			listFrame.dispose();
			readerId = 0;
			ch = new Chuang();
		}	
	}
}
