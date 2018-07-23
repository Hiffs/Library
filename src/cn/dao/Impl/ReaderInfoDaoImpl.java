package cn.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import cn.bean.BookInfo;
import cn.bean.ReaderInfo;
import cn.dao.ReaderInfoDao;
import cn.dbc.Base;

public class ReaderInfoDaoImpl implements ReaderInfoDao
{
	Base bs=new Base();
	@Override
	public ReaderInfo login(int id, String pwd)
	{
		ReaderInfo reader=null;
		String sql="select * from readerinfo where id=? and password=?";
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		list.add(pwd);
		List<ReaderInfo> re=bs.getExecute(sql, list, ReaderInfo.class);
		if(re.size()>0)
		{
			reader=re.get(0);
		}
		return reader;
	}

	@Override
	public ReaderInfo register(ReaderInfo reader)
	{
		ReaderInfo re=new ReaderInfo();
		String sql="insert into readerinfo(name,password,sex,tel) values(?,?,?,?)";
		List<Object> list=new ArrayList<Object>();
		list.add(reader.getName());
		list.add(reader.getPassword());
		list.add(reader.getSex());
		list.add(reader.getTel());
		if(bs.getUpdate(sql, list))
		{
			re=reader;
		}
		return re;
	}

	@Override
	public boolean borrow(BookInfo book)
	{
		boolean isFlag=false;
		String sql="update bookinfo set status=1 where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(book.getId());
		if(bs.getUpdate(sql, list))
		{
			isFlag=true;
		}
		return isFlag;
	}

	@Override
	public boolean returnbook(BookInfo book)
	{
		boolean isFlag=false;
		String sql="update bookinfo set status=0 where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(book.getId());
		if(bs.getUpdate(sql, list))
		{
			isFlag=true;
		}
		return isFlag;
	}

	@Override
	public ReaderInfo queryIndi(int id)
	{
		String sql="select * from readerinfo where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		List<ReaderInfo> re=bs.getExecute(sql, list, ReaderInfo.class);
		return re.get(0);
	}

	@Override
	public boolean updIndi(ReaderInfo reader)
	{
		boolean isFlag=false;
		String sql="update readerinfo set name=?,password=?,sex=?,tel=? where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(reader.getName());
		list.add(reader.getPassword());
		list.add(reader.getSex());
		list.add(reader.getTel());
		list.add(reader.getId());
		if(bs.getUpdate(sql, list)) 
		{
			isFlag=true;
		}
		return isFlag;
	}

	@Override
	public boolean checkPwd(String pwd1, String pwd2)
	{
		return pwd1.equals(pwd2);
	}

	@Override
	public boolean checkTel(String tel)
	{
		boolean isFlag=false;
		String sql="select * from readerinfo where tel=?";
		List<Object> list=new ArrayList<Object>();
		list.add(tel);
		List<ReaderInfo> re=bs.getExecute(sql, list,ReaderInfo.class);
		if(re.size()>0)
		{
			isFlag=true;
		}
		return isFlag;
	}

	@Override
	public int queryId(String tel)
	{
		int id=0;
		String sql="select * from readerinfo where tel=?";
		List<Object> list=new ArrayList<Object>();
		list.add(tel);
		List<ReaderInfo> re=bs.getExecute(sql, list, ReaderInfo.class);
		if(re.size()>0)
		{
			id=re.get(0).getId();
		}
		return id;
	}
	
	@Override
	public boolean checkOPwd(String opwd,String pwd)
	{
		return opwd.equals(pwd);
	}

}