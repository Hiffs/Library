package cn.dao;

import java.util.List;

import cn.bean.Author;

public interface AuthorDao {
	//展示所有作者
	List<Author> showAllAuthor();
	//作者id返回作者名字
	String getAuthorName(int authorid);
	//通过作者名找作者id
	int getAuthorId(String author);
}
