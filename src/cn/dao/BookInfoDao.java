package cn.dao;

import java.util.List;

import cn.bean.BookInfo;

public interface BookInfoDao {
	//展示所有书信息
	List<BookInfo> showAllBook();
	//通过id查找书
	BookInfo showBookById(int id);
	//通过书名查书
	List<BookInfo> showBookByName(String name);
	//通过作者查书
	List<BookInfo> showBookByAuthor(String author);
	//通过类型查书
	List<BookInfo> showBookByType(String type);
	//通过出版社查书
	List<BookInfo> showBookByPublish(String publish);
	//增加书籍
	boolean addBook(BookInfo book);
	//删除书籍
	boolean delBook(int bookid);
	//更改书籍信息
	boolean updateBook(BookInfo book);
	//书本状态
	String showBookStatus(int status);
	int showBookStatus(String status);
}
