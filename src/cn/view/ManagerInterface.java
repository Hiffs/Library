package cn.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Reader;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

import cn.bean.Author;
import cn.bean.BookInfo;
import cn.bean.Publish;
import cn.bean.ReaderInfo;
import cn.bean.RentInfo;
import cn.bean.Type;
import cn.dao.AuthorDao;
import cn.dao.BookInfoDao;
import cn.dao.ManagerInfoDao;
import cn.dao.PublishDao;
import cn.dao.ReaderInfoDao;
import cn.dao.RentInfoDao;
import cn.dao.TypeDao;
import cn.dao.Impl.AuthorDaoImpl;
import cn.dao.Impl.BookInfoDaoImpl;
import cn.dao.Impl.ManagerInfoDaoImpl;
import cn.dao.Impl.PublishDaoImpl;
import cn.dao.Impl.ReaderInfoDaoImpl;
import cn.dao.Impl.RentInfoDaoImpl;
import cn.dao.Impl.TypeDaoImpl;

public class ManagerInterface{
	Chuang ch;
	int readerId;
	JFrame frame=new JFrame();
	JLabel label=new JLabel();
	JPanel panel=new JPanel();
	JButton bbutton=new JButton();
	JButton rbutton=new JButton();
	JButton pbutton=new JButton();
	JButton tbutton=new JButton();
	JButton button1=new JButton();
	JButton button2=new JButton();
	JButton delbutton=new JButton(); //����ɾ��
	JTable table1=new JTable();
	JTable table2=new JTable();
	JScrollPane scrollPane=new JScrollPane();
	JPanel buttonpanel=new JPanel();
	JPanel readerpanel;
	ManagerInfoDao managerinfo=new ManagerInfoDaoImpl();
	ReaderInfoDao readerinfo=new ReaderInfoDaoImpl();
	BookInfoDao bookinfo=new BookInfoDaoImpl();
	RentInfoDao rentinfo=new RentInfoDaoImpl();
	AuthorDao authordao=new AuthorDaoImpl();
	TypeDao typedao=new TypeDaoImpl();
	PublishDao publishdao=new PublishDaoImpl();
	List<BookInfo> books=null;
	List<ReaderInfo> readers=null;
	
	public ManagerInterface(int readerId)
	{
		this.readerId = readerId;
	}
	
	public void showmain(){
		panel.removeAll();
		panel.setVisible(false);
		panel.setVisible(true);
		books=bookinfo.showAllBook();
		scrollPane = new JScrollPane();   //֧�ֹ���
        String[] columnNames = {"���","����","����","����","λ��","������","����״̬"};
        Vector columnNameV = new Vector();    //��ñ�ͷ
        for(int column = 0;column<columnNames.length;column++)
        {
            columnNameV.add(columnNames[column]);
        }
        Vector tableValueV = new Vector();
        for(int row = 0;row<books.size();row++)    //�������
        {
            Vector rowV = new Vector();
            rowV.add(books.get(row).getId());
            rowV.add(books.get(row).getName());
            rowV.add(authordao.getAuthorName(books.get(row).getAuthorid()));
            rowV.add(typedao.getTypeName(books.get(row).getTypeid()));
            rowV.add(books.get(row).getPosition());
            rowV.add(publishdao.getPublishName(books.get(row).getPublishid()));
            rowV.add(bookinfo.showBookStatus(books.get(row).getStatus()));
            tableValueV.add(rowV);
        }
        table1=new JTable(new DefaultTableModel(tableValueV,columnNameV));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        scrollPane.setViewportView(table1);	        
        panel.add(scrollPane); 
        scrollPane.setBounds(0,0,500,300);
        
        buttonpanel=new JPanel();
        buttonpanel.setLayout(new BoxLayout(buttonpanel,BoxLayout.X_AXIS));
        button1=new JButton("����");
        button2=new JButton("ɾ��");
        buttonpanel.add(button1);
        buttonpanel.add(button2);
        panel.add(buttonpanel);
	}
	
	public void showmain2(){
		panel.removeAll();
		panel.setVisible(false);
		panel.setVisible(true);
		readers=managerinfo.findAllReader();
		JScrollPane scrollPane = new JScrollPane();   //֧�ֹ���
        String[] columnNames = {"�˺�","����","�Ա�","�绰����"};
        Vector columnNameV = new Vector();    //��ñ�ͷ
        for(int column = 0;column<columnNames.length;column++)
        {
            columnNameV.add(columnNames[column]);
        }
        Vector tableValueV = new Vector();
        for(int row = 0;row<readers.size();row++)    //�������
        {
            Vector rowV = new Vector();
            rowV.add(readers.get(row).getId());
            rowV.add(readers.get(row).getName());
            rowV.add(readers.get(row).getSex());
            rowV.add(readers.get(row).getTel());
            tableValueV.add(rowV);
        }
        table2=new JTable(new DefaultTableModel(tableValueV,columnNameV));
        scrollPane.setViewportView(table2);
        panel.add(scrollPane);
        scrollPane.setBounds(0,0,500,300);
        
        readerpanel=new JPanel();
        delbutton=new JButton("ɾ��");
        readerpanel.add(delbutton);
        panel.add(readerpanel);
	}
	
	public void showInterface(){
		JLayeredPane layPane=new JLayeredPane();
		
		Icon icon=new ImageIcon(getClass().getResource("/image/manager.png"));
		JLabel jlabelback=new JLabel(icon);
		jlabelback.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		
	    JPanel container=(JPanel)frame.getContentPane();  
	    container.setLayout(null);
	    container.setOpaque(false);
		
		layPane.add(jlabelback,JLayeredPane.DEFAULT_LAYER);
		layPane.add(container,JLayeredPane.MODAL_LAYER);
		frame.setSize(icon.getIconWidth(), icon.getIconHeight());
		frame.setLayeredPane(layPane);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bbutton.setText("�鱾����");
		bbutton.setBounds(230, 150, 100, 50);
		bbutton.setForeground(new Color(51, 51, 51));
		bbutton.setBackground(new Color(180, 185, 250));
		container.add(bbutton);
		
		rbutton.setText("���߹���");
		rbutton.setBounds(230, 250, 100, 50);
		rbutton.setForeground(new Color(51, 51, 51));
		rbutton.setBackground(new Color(180, 185, 250));
		container.add(rbutton);
		
		pbutton.setText("�޸�����");
		pbutton.setBounds(230, 350, 100, 50);
		pbutton.setForeground(new Color(51, 51, 51));
		pbutton.setBackground(new Color(180, 185, 250));
		container.add(pbutton);
		
		tbutton.setText("ע��");
		tbutton.setBounds(230, 450, 100, 50);
		tbutton.setForeground(new Color(51, 51, 51));
		tbutton.setBackground(new Color(180, 185, 250));
		container.add(tbutton);
		
		panel.setBounds(350,150,500,350);
//		panel.setBackground(Color.lightGray);
		container.add(panel);
        
		showmain();
		buttonpanel.setVisible(false);
		
        frame.setVisible(true);
		bbutton.addActionListener(new BbuttonAction());
		rbutton.addActionListener(new RbuttonAction());
		pbutton.addActionListener(new PbuttonAction());
		tbutton.addActionListener(new TbuttonAction());
	}
	
	public class Button1Listener implements ActionListener {
    	JFrame frame1=new JFrame();
    	JPanel panel1=new JPanel();
    	JLabel label1=new JLabel("����");
    	JLabel label2=new JLabel("����");
    	JLabel label3=new JLabel("����");
    	JLabel label4=new JLabel("λ��");
    	JLabel label5=new JLabel("������");
    	JLabel label6=new JLabel("���");
    	JButton button0=new JButton("ȷ��");
    	JTextField text1=new JTextField("");
    	JComboBox text2=new JComboBox();
    	JComboBox text3=new JComboBox();
    	JTextField text4=new JTextField("");
    	JComboBox text5=new JComboBox();
    	JTextField text6=new JTextField("");
//    	ManagerInterface minter=new ManagerInterface(readerId);
    	ManagerInfoDao managerinfo=new ManagerInfoDaoImpl();
    	ReaderInfoDao readerinfo=new ReaderInfoDaoImpl();
    	BookInfoDao bookinfo=new BookInfoDaoImpl();
    	RentInfoDao rentinfo=new RentInfoDaoImpl();
    	AuthorDao authordao=new AuthorDaoImpl();
    	TypeDao typedao=new TypeDaoImpl();
    	PublishDao publishdao=new PublishDaoImpl();
    	@Override
    	public void actionPerformed(ActionEvent arg0) {
    		panel1.removeAll();
    		frame1.setSize(new Dimension(800,600));
    		frame1.setLocation(200,200);
    		Container container1=frame1.getContentPane();
    		container1.setLayout(null);		
    		text1.setBackground(Color.white);
    		text1.setBounds(270, 50, 250, 30);
    		text1.setEnabled(true);
    		text2.setBackground(Color.white);
    		text2.setBounds(270, 120, 250, 30);
    		text2.setEnabled(true);
    		text3.setBackground(Color.white);
    		text3.setBounds(270, 190, 250, 30);
    		text3.setEnabled(true);
    		text4.setBackground(Color.white);
    		text4.setBounds(270, 260, 250, 30);
    		text4.setEnabled(true);
    		text5.setBackground(Color.white);
    		text5.setBounds(270, 330, 250, 30);
    		text5.setEnabled(true);
    		text6.setBackground(Color.white);
    		text6.setBounds(270, 400, 250, 30);
    		text6.setEnabled(true);
    		label1.setBounds(150, 50,  100, 30);	
    		label2.setBounds(150, 120, 100, 30);	
    		label3.setBounds(150, 190, 100, 30);	
    		label4.setBounds(150, 260, 100, 30);	
    		label5.setBounds(150, 330, 100, 30);
    		label6.setBounds(150, 400, 100, 30);
    		button0.setBounds(300, 470, 100, 30);
    		for(Author a:authordao.showAllAuthor()){
    			text2.addItem(a.getName());
    		}
    		for(Type a:typedao.showAllType()){
    			text3.addItem(a.getType());
    		}
    		for(Publish a:publishdao.showAllPublish()){
    			text5.addItem(a.getPublish());
    		}
    		text2.setMaximumRowCount(4);
    		text3.setMaximumRowCount(4);
    		text5.setMaximumRowCount(4);
    		container1.add(label1);
    		container1.add(label2);
    		container1.add(label3);
    		container1.add(label4);
    		container1.add(label5);
    		container1.add(label6);
    		container1.add(button0);
    		container1.add(text1);
    		container1.add(text2);
    		container1.add(text3);
    		container1.add(text4);
    		container1.add(text5);
    		container1.add(text6);
    		frame1.setVisible(true);
    		button0.addActionListener(new Button0Action());
    	}
       class Button0Action implements ActionListener {
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			String str1=text1.getText();
    			String str4=text4.getText();
    			String str6=text6.getText();
    			BookInfo book=new BookInfo();
    			book.setName(str1);                                             //��ȡ����                                   
    			for(int i=0;i<authordao.showAllAuthor().size();i++){                //��ȡ���ͱ��
    				book.setAuthorid(authordao.getAuthorId((String)text2.getItemAt(i)));
    			}
    			for(int i=0;i<typedao.showAllType().size();i++){                //��ȡ���ͱ��
    				book.setTypeid(typedao.getTypeId((String)text3.getItemAt(i)));
    			}
    			book.setPosition(str4);                                         //��ȡλ��
    			for(int i=0;i<publishdao.showAllPublish().size();i++){                 //��ȡ��������
    				book.setPublishid(publishdao.getPublishId((String)text5.getItemAt(i)));
    			}
    			book.setInfo(str6);
    			book.setStatus(0);
    			if(!str1.equals("")){
    				if(bookinfo.addBook(book)){
    					JOptionPane.showMessageDialog(button0, "��ӳɹ���", "��ʾ",JOptionPane.WARNING_MESSAGE); 
    					showmain();
    					buttonpanel.setVisible(false);
    				}
    			}
    			else{
    				JOptionPane.showMessageDialog(button0, "���ʧ�ܣ���������ӣ�", "��ʾ",JOptionPane.WARNING_MESSAGE);  
    			}
    		}
    	}
    }
	
	private class BbuttonAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			showmain();
			bbutton.setForeground(Color.white);
			bbutton.setBackground(new Color(0, 128, 255));
			rbutton.setForeground(new Color(51, 51, 51));
			rbutton.setBackground(new Color(180, 185, 250));
			panel.setVisible(false);
			panel.setVisible(true);
	        buttonpanel.setVisible(true);
	        
	        button1.addActionListener(new Button1Listener()); 
	        button2.addActionListener(new Button2Listener());
		}
	}
	public class Button2Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0;i<books.size();i++){
				if(i==table1.getSelectedRow()){
					if(bookinfo.delBook(books.get(i).getId())){
						JOptionPane.showMessageDialog(button2, "ɾ���ɹ���", "��ʾ",JOptionPane.WARNING_MESSAGE);
						showmain();
						buttonpanel.setVisible(false);
					}
				}
			}
		}
    }
	
	public class RbuttonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			showmain2();
			bbutton.setForeground(new Color(51, 51, 51));
			bbutton.setBackground(new Color(180, 185, 250));
			rbutton.setForeground(Color.white);
			rbutton.setBackground(new Color(0, 128, 255));
	        delbutton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i=table2.getSelectedRow();
					if(managerinfo.DelAReader(readers.get(i).getId())){
						JOptionPane.showMessageDialog(delbutton, "ɾ���ɹ���", "��ʾ",JOptionPane.WARNING_MESSAGE);
						showmain2();
						readerpanel.setVisible(false);
					}
					
				}
			});
		}
	}

	public class PbuttonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			PwdInter2 in=new PwdInter2(readerId);
			in.PwdAlter();
		}
	}
	
	public class TbuttonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.setVisible(false);
			frame.dispose();
			ch = new Chuang();
		}
	}

}
