package cn.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import cn.dao.*;
import cn.dao.Impl.*;
import cn.ui.*;
import cn.bean.*;


public class RentFrame
{
	private JFrame rentFrame;
	private ListFrame lf;
	Chuang ch;
//	BackgroundPanel bgp; 
	PerInter p;
	int readerId;
	
   public static final Color GREENF = new Color(205, 255, 205);  
   public static final Color GREENB = new Color(51, 154, 47);  
   public static final Color BLUEF = new Color(125, 161, 237); 
   public static final Color BLUEB = new Color(91, 118, 173); 

	List<BookInfo> lb = new ArrayList<BookInfo>();
	
	AuthorDao ad = new AuthorDaoImpl();
	BookInfoDao bkd = new BookInfoDaoImpl();
	PublishDao pd = new PublishDaoImpl();
	TypeDao td = new TypeDaoImpl();
	RentInfoDao rd = new RentInfoDaoImpl();
	ReaderInfoDao red = new ReaderInfoDaoImpl();
	
	DefaultTableModel dtm;
	JTextField bookName;
	JTextArea bookTextList;
	JButton rentButton,listButton,bookList,zoneButton,
		exitButton,searchButton,rent,returnButton;
	JTable bookTable;
	
	final String[] index = {"请选择...","书名", "作者", "类型"};
	private String[] tableHead = {"编号","图书名字","图书作者","类型","图书位置","出版社","借阅状态","图书描述"};
	private Object[][] tableData = {};
	JComboBox<String> comboBox;
	String str1;
	int s = 0;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					RentFrame w = new RentFrame(1001);
					w.rentFrame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	private RentFrame()
	{
		initialize();
	}
	
	public RentFrame(int readerId)
	{
		this.readerId = readerId;
		initialize();
	}
	
	private void initialize()
	{	
	    rentFrame = new JFrame();		
		JLayeredPane lay = new JLayeredPane();
		
		ImageIcon icon=new ImageIcon(getClass().getResource("/image/manager.png"));  
	    JLabel label=new JLabel(icon);  
	    label.setBounds(0,0,800,600);
	    lay.add(label,JLayeredPane.DEFAULT_LAYER);
	    
	    JPanel container = (JPanel)rentFrame.getContentPane();
	    container.setLayout(null);
	    container.setOpaque(false);
	    lay.add(container, JLayeredPane.MODAL_LAYER);
		
	    rentFrame.setBounds(0, 0, 800, 600);
	    rentFrame.setLayeredPane(lay);
		rentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rentFrame.setResizable(false);
		rentFrame.setLocationRelativeTo(null); 
	    
		JScrollPane scrollPane = new JScrollPane();
		
		//面板1
		JPanel jp1 = new JPanel();
		jp1.setBounds(80, 10, 600, 35);
		comboBox = new JComboBox<String>();
		for(int i = 0; i < index.length; i++)
		{
			comboBox.addItem(index[i]);
		}
		comboBox.addItemListener(new comboBoxListener());
		jp1.add(comboBox);
		
		bookName = new JTextField(35);
		jp1.add(bookName);
		
		searchButton = new GButton();
		searchButton.setText("搜索");
		searchButton.addActionListener(new searchButtonListener());
		jp1.add(searchButton);
		jp1.setOpaque(false);
		//放在北
		rentFrame.add(jp1,BorderLayout.NORTH);
//		container.add(jp1,BorderLayout.NORTH);
		
		//箱子
		rentButton = new GButton();
		rentButton.setText("　借书　");
		newButton(rentButton, 2);
		Box hBox1 = Box.createHorizontalBox();
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(rentButton);
		listButton = new GButton();
		listButton.setText("　书单　");
		newButton(listButton, 1);
		listButton.addActionListener(new listButtonListener());
		Box hBox2 = Box.createHorizontalBox();
		hBox2.add(Box.createHorizontalStrut(5));
		hBox2.add(listButton);
		bookList = new GButton();
		bookList.setText("历史记录");
		newButton(bookList, 1);
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
		//放在西
		rentFrame.add(vBox,BorderLayout.WEST);
		
		//面板2
		JPanel jp3 = new JPanel();
		jp3.setBounds(100, 60, 660, 450);
		dtm = new DefaultTableModel(tableData, tableHead);
		bookTable = new JTable(dtm);
		bookTable.setPreferredScrollableViewportSize(new Dimension(650, 400));
		scrollPane = new JScrollPane(bookTable);
		jp3.add(scrollPane);
		jp3.setOpaque(false);
		//放中间
		rentFrame.add(jp3,BorderLayout.CENTER);
//		container.add(jp3,BorderLayout.CENTER);
		
		//面板3
		JPanel jp4 = new JPanel();
		jp4.setBounds(300, 500, 200, 35);
		jp4.setLayout(new BorderLayout());
		rent = new BButton();
		rent.setText("借书");
		rent.addActionListener(new rentListener());
		jp4.add(rent,BorderLayout.WEST);
		returnButton = new BButton();
		returnButton.setText("详情");
		returnButton.addActionListener(new returnButtonListener());
		jp4.add(returnButton,BorderLayout.EAST);
		jp4.setOpaque(false);
		//放南
		rentFrame.add(jp4,BorderLayout.SOUTH);
//		container.add(jp4,BorderLayout.SOUTH);
		
		showList();
		rentFrame.setVisible(true);
	}
	
	private void showList()
	{
		lb = null;
		lb = bkd.showAllBook();
		dtm.setRowCount(0);
		addBookData(lb);
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
	}
	
	private class comboBoxListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			str1 = (String) e.getItem();
			if(str1.equals("书名"))
			{
				s = 1;
			}
			else if(str1.equals("作者"))
			{
				s = 2;
			}	
			else if(str1.equals("类型"))
			{
				s = 3;
			}		
		}
	}
	
	
	private class searchButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			lb = null;
			String searchText = bookName.getText();
			dtm.setRowCount(0);
			if(s == 1)
			{
				lb = bkd.showBookByName(searchText);
				addBookData(lb);
			}
			else if(s == 2)
			{
				lb = bkd.showBookByAuthor(searchText);
				addBookData(lb);
			}
			else if(s == 3)
			{
				lb = bkd.showBookByType(searchText);
				addBookData(lb);
			}
		}
	}
		
	private void addBookData(List<BookInfo> lb)
	{
		for(BookInfo b : lb)
		{
			Vector v = new Vector();
			v.add(b.getId());
			v.add(b.getName());
			v.add(ad.getAuthorName(b.getAuthorid()));
			v.add(td.getTypeName(b.getTypeid()));
			v.add(b.getPosition());
			v.add(pd.getPublishName(b.getPublishid()));
			v.add(bkd.showBookStatus(b.getStatus()));
			v.add(b.getInfo());
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
				int bookId = lb.get(i).getId();
				//获得选中书本的状态
				int bookSta = lb.get(i).getStatus();
				if(bookSta == 0)
				{
					if(red.borrow(lb.get(i)) && rd.addRentInfo(readerId, bookId))
					{
						JOptionPane.showMessageDialog(rentFrame, "借阅成功");
						rentFrame.validate();
						
					}
					else
					{
						JOptionPane.showMessageDialog(rentFrame, "借阅失败","",JOptionPane.INFORMATION_MESSAGE);
						rentFrame.validate();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(rentFrame, "图书已借出，请选择其他在馆书籍","",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			showList();
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
				int bookId = lb.get(i).getId();
			}
		}
	}
	
	private class listButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			rentFrame.setVisible(false);
			rentFrame.dispose();
			lf = new ListFrame(readerId);
			lf.showList();
		}
	}
	
	private class booklistListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			rentFrame.setVisible(false);
			rentFrame.dispose();
			lf = new ListFrame(readerId);
			lf.hisList();
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
			rentFrame.setVisible(false);
			rentFrame.dispose();
			readerId = 0;
			ch = new Chuang();
		}
	}
	
//	class BackgroundPanel extends JPanel  
//	{  
//	    Image im;  
//	    public BackgroundPanel(Image im)  
//	    {  
//	        this.im=im;  
//	        this.setOpaque(true);  
//	    }  
//	    //Draw the back ground.  
//	    public void paintComponent(Graphics g)  
//	    {  
//	        super.paintComponents(g);  
//	        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  
//	          
//	    }  
//	}
}
