package cn.dao;

import java.util.List;

import cn.bean.BookInfo;

public interface BookInfoDao {
	//չʾ��������Ϣ
	List<BookInfo> showAllBook();
	//ͨ��id������
	BookInfo showBookById(int id);
	//ͨ����������
	List<BookInfo> showBookByName(String name);
	//ͨ�����߲���
	List<BookInfo> showBookByAuthor(String author);
	//ͨ�����Ͳ���
	List<BookInfo> showBookByType(String type);
	//ͨ�����������
	List<BookInfo> showBookByPublish(String publish);
	//�����鼮
	boolean addBook(BookInfo book);
	//ɾ���鼮
	boolean delBook(int bookid);
	//�����鼮��Ϣ
	boolean updateBook(BookInfo book);
	//�鱾״̬
	String showBookStatus(int status);
	int showBookStatus(String status);
}
