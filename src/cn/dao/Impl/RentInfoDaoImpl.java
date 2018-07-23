package cn.dao.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bean.BookInfo;
import cn.bean.RentInfo;
import cn.dao.RentInfoDao;
import cn.dbc.Base;

public class RentInfoDaoImpl implements RentInfoDao
{
	Base bd = new Base();
	
	@Override
	public List<RentInfo> getAllRentInfo()
	{
		List<RentInfo> li = new ArrayList<RentInfo>();
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from rentinfo";
		li = bd.getExecute(sql, list, RentInfo.class);
		return li;
	}

	@Override
	public List<RentInfo> getAllRentInfoByReadId(int Readerid)
	{
		List<RentInfo> li = new ArrayList<RentInfo>();
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from rentinfo where readerid = ?";
		list.add(Readerid);
		li = bd.getExecute(sql, list, RentInfo.class);
		return li;
	}

	@Override
	public List<RentInfo> getRentInfoByReadId(int Readerid)
	{
		List<RentInfo> li = new ArrayList<RentInfo>();
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from rentinfo where readerid = ? and returntime is null";
		list.add(Readerid);
		li = bd.getExecute(sql, list, RentInfo.class);
		return li;
	}

	@Override
	public List<RentInfo> getRentInfoByBook(int bookid)
	{
		List<RentInfo> li = new ArrayList<RentInfo>();
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from rentinfo where bookid = ?";
		list.add(bookid);
		li = bd.getExecute(sql, list, BookInfo.class);
		return li;
	}

	@Override
	public boolean delAllRentInfoByReadId(int readerid)
	{
		boolean flag = false;
		List<Object> list = new ArrayList<Object>();
		String sql = "delet from rentinfo where readerid = ?";
		list.add(readerid);
		flag = bd.getUpdate(sql, list);
		return flag;
	}

	@Override
	public boolean addRentInfo(int readerid, int bookid)
	{
		boolean flag = false;
		Date d = new Date();
		java.sql.Date jd = new java.sql.Date(d.getTime());
		List<Object> list = new ArrayList<Object>();
		list.add(bookid);
		list.add(readerid);
		list.add(jd);
		String sql = "insert into rentinfo(bookid,readerid,booktime) values(?,?,?)";
		flag = bd.getUpdate(sql, list);
		return flag;
	}

	@Override
	public RentInfo getRentInfoById(int id)
	{
		RentInfo r = null;
		List<RentInfo> lr = new ArrayList<RentInfo>();
		List<Object> list = new ArrayList<Object>();
		list.add(id);
		String sql = "select * from rentinfo where id = ?";
		lr = bd.getExecute(sql, list, RentInfo.class);
		if(lr.size() > 0)
			r = lr.get(0);
		return r;
	}

	@Override
	public boolean returnRentInfo(int id, Date d)
	{
		boolean flag = false;
		List<Object> list = new ArrayList<Object>();
		java.sql.Date jd = new java.sql.Date(d.getTime());
		list.add(jd);
		list.add(id);
		String sql = "update rentinfo set returntime = ? where id = ?";
		flag = bd.getUpdate(sql, list);
		return flag;
	}

	@Override
	public boolean rebookRentInfo(int id, Date d)
	{
		boolean flag = false;
		List<Object> list = new ArrayList<Object>();
		java.sql.Date jd = new java.sql.Date(d.getTime());
		list.add(jd);
		list.add(id);
		String sql = "update rentinfo set booktime = ? where id = ?";
		flag = bd.getUpdate(sql, list);
		return flag;
	}

}
