package cn.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import cn.bean.Publish;
import cn.dao.PublishDao;
import cn.dbc.Base;

public class PublishDaoImpl implements PublishDao {
	Base bs=new Base();
	
	@Override
	public List<Publish> showAllPublish() {
		String sql="select * from publish";
		List<Object> list=new ArrayList<Object>();
		List<Publish> li=bs.getExecute(sql, list, Publish.class);
		return li;
	}

	@Override
	public String getPublishName(int publishid) {
		String sql="select * from Publish where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(publishid);
		List<Publish> names=bs.getExecute(sql, list, Publish.class);
		return names.get(0).getPublish();
	}

	@Override
	public int getPublishId(String publish) {
		String sql="select * from Publish where publish=?";
		List<Object> list=new ArrayList<Object>();
		list.add(publish);
		List<Publish> li=bs.getExecute(sql, list, Publish.class);
		return li.get(0).getid();
	}

}
