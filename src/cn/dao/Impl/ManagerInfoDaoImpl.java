package cn.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import cn.bean.ManagerInfo;
import cn.bean.ReaderInfo;
import cn.dao.ManagerInfoDao;
import cn.dbc.Base;

public class ManagerInfoDaoImpl implements ManagerInfoDao
{
    Base bs = new Base();
	@Override
	public boolean AddAReader(ReaderInfo reader)//增加读者
	{
		String sql = "insert into readerinfo values(?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(reader.getId());
		list.add(reader.getName());
		list.add(reader.getPassword());
		list.add(reader.getSex());
		list.add(reader.getTel());
		boolean flage = bs.getUpdate(sql, list);
		return flage;
		
	}

	@Override
	public boolean DelAReader(int readerid)//删除读者
	{
		String sql = "delete from readerinfo where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(readerid);
		boolean flage = bs.getUpdate(sql, list);
		return flage;
	}

	@Override
	public boolean UpdaAReader(ReaderInfo reader)//修改读者
	{
		String sql = "update readerinfo set name=?,password=?,sex=?,tel=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(reader.getName());
		list.add(reader.getPassword());
		list.add(reader.getSex());
		list.add(reader.getTel());
		list.add(reader.getId());
		boolean flage = bs.getUpdate(sql, list);
		return flage;
		
	}

	@Override
	public List<ReaderInfo> findAllReader()//查找
	{
		String sql = "select * from readerinfo";
		List<Object> list = new ArrayList<Object>();
		List<ReaderInfo> list1 = bs.getExecute(sql,list,ReaderInfo.class);
		return list1;
	}

	@Override
	public ManagerInfo Login(int id,String password)//登录
	{
		String sql="select * from managerinfo where id=? and password=?";
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		list.add(password);
		List<ManagerInfo> ma=bs.getExecute(sql, list, ManagerInfo.class);
		return ma.get(0);
		
	}

	@Override
	public ManagerInfo register(ManagerInfo ma)//注册
	{
		ManagerInfo ma2=new ManagerInfo();
		String sql = "insert into managerinfo(name,password) values(?,?)";
		List<Object> list=new ArrayList<Object>();
		list.add(ma.getName());
		list.add(ma.getPassword());
		if(bs.getUpdate(sql,list))
		{
			ma2=ma;
		}
		return ma2;
	}

	@Override
	public ManagerInfo manager(int id) {
		String sql="select * from managerinfo where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		List<ManagerInfo> ma=bs.getExecute(sql, list, ManagerInfo.class);
		return ma.get(0);
	}

	@Override
	public boolean getUpdateManager(ManagerInfo reader) {
		String sql = "update managerinfo set name=?,password=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(reader.getName());
		list.add(reader.getPassword());
		list.add(reader.getId());
		boolean flage = bs.getUpdate(sql, list);
		return flage;
	}

}
