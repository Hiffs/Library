package cn.dao;

import java.util.List;

import cn.bean.Author;

public interface AuthorDao {
	//չʾ��������
	List<Author> showAllAuthor();
	//����id������������
	String getAuthorName(int authorid);
	//ͨ��������������id
	int getAuthorId(String author);
}
