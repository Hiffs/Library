package cn.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import cn.bean.BookInfo;
import cn.dao.AuthorDao;
import cn.dao.BookInfoDao;
import cn.dao.PublishDao;
import cn.dao.TypeDao;
import cn.dbc.Base;

public class BookInfoDaoImpl implements BookInfoDao{
	Base bs=new Base();
	AuthorDao authordao=new AuthorDaoImpl();
	TypeDao typedao=new TypeDaoImpl();
	PublishDao publishdao=new PublishDaoImpl();
	@Override
	public List<BookInfo> showAllBook() {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from BookInfo";
		List<BookInfo> li=bs.getExecute(sql, list, BookInfo.class);
		return li;
	}

	@Override
	public BookInfo showBookById(int id) {
		String sql="select * from BookInfo where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		List<BookInfo> li=bs.getExecute(sql, list, BookInfo.class);
		return li.get(0);
	}

	@Override
	public List<BookInfo> showBookByName(String name) {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from BookInfo where name=?";
		list.add(name);
		List<BookInfo> li=bs.getExecute(sql, list, BookInfo.class);
		return li;
	}

	@Override
	public List<BookInfo> showBookByAuthor(String author) {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from BookInfo where authorid=?";
		int authorid=authordao.getAuthorId(author);
		list.add(authorid);
		List<BookInfo> li=bs.getExecute(sql, list, BookInfo.class);
		return li;
	}

	@Override
	public List<BookInfo> showBookByType(String type) {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from BookInfo where typeid=?";
		int typeid=typedao.getTypeId(type);
		list.add(typeid);
		List<BookInfo> li=bs.getExecute(sql, list, BookInfo.class);
		return li;
	}

	@Override
	public List<BookInfo> showBookByPublish(String publish) {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from BookInfo where publishid=?";
		int publishid=publishdao.getPublishId(publish);
		list.add(publishid);
		List<BookInfo> li=bs.getExecute(sql, list, BookInfo.class);
		return li;
	}

	@Override
	public boolean addBook(BookInfo book) {
		String sql="insert into BookInfo(name,authorid,typeid,position,publishid,status,info) values(?,?,?,?,?,?,?)";
		List<Object> list=new ArrayList<Object>();
		list.add(book.getName());
		list.add(book.getAuthorid());
		list.add(book.getTypeid());
		list.add(book.getPosition());
		list.add(book.getPublishid());
		list.add(book.getStatus());
		list.add(book.getInfo());
		return bs.getUpdate(sql, list);
	}

	@Override
	public boolean delBook(int bookid) {
		String sql="delete from BookInfo where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(bookid);
		return bs.getUpdate(sql, list);
	}

	@Override
	public boolean updateBook(BookInfo book) {
		String sql="update BookInfo set name=?,authorid=?,typeid=?,position=?,publishid=?,status=?,info=? where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(book.getName());
		list.add(book.getAuthorid());
		list.add(book.getTypeid());
		list.add(book.getPosition());
		list.add(book.getPublishid());
		list.add(book.getStatus());
		list.add(book.getInfo());
		list.add(book.getId());
		return bs.getUpdate(sql, list);
	}

	@Override
	public String showBookStatus(int status)
	{
		if(status == 1)
		{
			return "借出";
		}
		else if(status == 0)
		{
			return "在馆";
		}
		else
			return null;
	}

	@Override
	public int showBookStatus(String status)
	{
		if(status.equals("在馆"))
			return 0;
		else if(status.equals("借出"))
			return 1;
		else
			return -1;
	}

}
