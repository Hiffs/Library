package cn.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import cn.bean.Author;
import cn.dao.AuthorDao;
import cn.dbc.Base;

public class AuthorDaoImpl implements AuthorDao {
	Base bs=new Base();
	
	@Override
	public List<Author> showAllAuthor() {
		String sql="select * from Author";
		List<Object> list=new ArrayList<Object>();
		List<Author> li=bs.getExecute(sql, list, Author.class);
		return li;
	}

	@Override
	public String getAuthorName(int authorid) {
		String sql="select * from Author where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(authorid);
		List<Author> names=bs.getExecute(sql, list, Author.class);
		return names.get(0).getName();
	}

	@Override
	public int getAuthorId(String author) {
		String sql="select * from Author where name=?";
		List<Object> list=new ArrayList<Object>();
		list.add(author);
		List<Author> li=bs.getExecute(sql, list, Author.class);
		return li.get(0).getId();
	}

}
