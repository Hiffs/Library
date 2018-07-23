package cn.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.bean.Author;
import cn.bean.BookInfo;
import cn.bean.Publish;
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

public class Button1Listener implements ActionListener {
	JFrame frame=new JFrame();
	JPanel panel=new JPanel();
	JLabel label1=new JLabel("书名");
	JLabel label2=new JLabel("作者");
	JLabel label3=new JLabel("类型");
	JLabel label4=new JLabel("位置");
	JLabel label5=new JLabel("出版社");
	JLabel label6=new JLabel("简介");
	JButton button=new JButton("确定");
	JTextField text1=new JTextField("");
	JComboBox text2=new JComboBox();
	JComboBox text3=new JComboBox();
	JTextField text4=new JTextField("");
	JComboBox text5=new JComboBox();
	JTextField text6=new JTextField("");
//	ManagerInterface minter=new ManagerInterface();
	ManagerInfoDao managerinfo=new ManagerInfoDaoImpl();
	ReaderInfoDao readerinfo=new ReaderInfoDaoImpl();
	BookInfoDao bookinfo=new BookInfoDaoImpl();
	RentInfoDao rentinfo=new RentInfoDaoImpl();
	AuthorDao authordao=new AuthorDaoImpl();
	TypeDao typedao=new TypeDaoImpl();
	PublishDao publishdao=new PublishDaoImpl();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		panel.removeAll();
		frame.setSize(new Dimension(800,600));
		frame.setLocation(200,200);
		Container container=frame.getContentPane();
		container.setLayout(null);		
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
		button.setBounds(300, 470, 100, 30);
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
		container.add(label1);
		container.add(label2);
		container.add(label3);
		container.add(label4);
		container.add(label5);
		container.add(label6);
		container.add(button);
		container.add(text1);
		container.add(text2);
		container.add(text3);
		container.add(text4);
		container.add(text5);
		container.add(text6);
		frame.setVisible(true);
		button.addActionListener(new ButtonAction());
	}
	public class ButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String str1=text1.getText();
			String str4=text4.getText();
			String str6=text6.getText();
			BookInfo book=new BookInfo();
			book.setName(str1);                                             //获取书名                                   
			for(int i=0;i<authordao.showAllAuthor().size();i++){                //获取类型编号
				book.setAuthorid(authordao.getAuthorId((String)text2.getItemAt(i)));
			}
			for(int i=0;i<typedao.showAllType().size();i++){                //获取类型编号
				book.setTypeid(typedao.getTypeId((String)text3.getItemAt(i)));
			}
			book.setPosition(str4);                                         //获取位置
			for(int i=0;i<publishdao.showAllPublish().size();i++){                 //获取出版社编号
				book.setPublishid(publishdao.getPublishId((String)text5.getItemAt(i)));
			}
			book.setInfo(str6);
			book.setStatus(0);
			if(!str1.equals("")){
				if(bookinfo.addBook(book)){
					JOptionPane.showMessageDialog(button, "添加成功！", "提示",JOptionPane.WARNING_MESSAGE); 
				}
			}
			else{
				JOptionPane.showMessageDialog(button, "添加失败，请重新添加！", "提示",JOptionPane.WARNING_MESSAGE);   
			}
		}
	}

}
