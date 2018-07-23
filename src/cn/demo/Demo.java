package cn.demo;

import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import cn.bean.*;
import cn.blz.Show;
import cn.dao.*;
import cn.dao.Impl.*;


public class Demo {
	BookInfoDao bookinfo=new BookInfoDaoImpl();
	AuthorDao authordao=new AuthorDaoImpl();
	RentInfoDao rentdao = new RentInfoDaoImpl();
	@Test
	public void test01(){
		List<BookInfo> books=bookinfo.showAllBook();
		for(int i=0;i<books.size();i++){
			int id=books.get(i).getAuthorid();
			String bookname=authordao.getAuthorName(id);
			System.out.println("����Ϊ"+books.get(i).getId()+",����Ϊ"+bookname);
		}
	}
	
	@Test
	public void test02(){
		List<Author> list=authordao.showAllAuthor();
		for(int i=0;i<list.size();i++){
			System.out.println("���߱��Ϊ"+list.get(i).getId()+",������Ϊ"+list.get(i).getName());
		}
	}
	
	@Test
	public void test03(){
		System.out.println(authordao.getAuthorId("�ܹ�ƽ"));
	}
	
	@Test
	public void test04(){
		BookInfo book=bookinfo.showBookById(1001);
		System.out.println(book.getId()+book.getName());
	}
	
	@Test
	public void test05(){
		BookInfo book=new BookInfo( "��������", 5, 1, "��¥",2, 0, "��");
		if(bookinfo.addBook(book)){
			System.out.println("��ӳɹ���");
		}
	}
	
	@Test
	public void test06(){
		if(bookinfo.delBook(1005)){
			System.out.println("ɾ���ɹ���");
		}
	}
	
	@Test
	public void test07(){
		BookInfo book=new BookInfo(1001, "��", 5, 2, "��¥", 2, 1, "��");
		if(bookinfo.updateBook(book)){
			System.out.println("�޸ĳɹ���");
		}
	}
	
	@Test
	public void testshow()
	{
		Show s = new Show();
		s.showAllRent();
	}
	
	@Test
	public void testrent()
	{
		List<RentInfo> lr = rentdao.getRentInfoByReadId(1002);
		System.out.println(lr);
		RentInfo r = lr.get(0);
		System.out.println(r.getId());
		if(r.getReturntime() == null)
			System.out.println("-");
	}
	
	@Test
	public void addRent()
	{
		rentdao.addRentInfo(1001, 1001);
	}
	
	@Test
	public void testtttt()
	{
		List<RentInfo> lr = rentdao.getAllRentInfoByReadId(1002);
		RentInfo r = lr.get(0);
		RentInfo rr = rentdao.getRentInfoById(1);
		System.out.println(rr.getBookid());
		long g = r.getReturntime().getTime() - r.getBooktime().getTime();
		System.out.println(g);
	}
	
}
