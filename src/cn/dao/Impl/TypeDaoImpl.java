package cn.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import cn.bean.Type;
import cn.dao.TypeDao;
import cn.dbc.Base;

public class TypeDaoImpl implements TypeDao {
	Base bs=new Base();
	
	@Override
	public List<Type> showAllType() {
		String sql="select * from Type";
		List<Object> list=new ArrayList<Object>();
		List<Type> li=bs.getExecute(sql, list, Type.class);
		return li;
	}

	@Override
	public String getTypeName(int typeid) {
		String sql="select * from Type where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(typeid);
		List<Type> names=bs.getExecute(sql, list, Type.class);
		return names.get(0).getType();
	}

	@Override
	public int getTypeId(String Type) {
		String sql="select * from Type where type=?";
		List<Object> list=new ArrayList<Object>();
		list.add(Type);
		List<Type> li=bs.getExecute(sql, list, Type.class);
		return li.get(0).getid();
	}

}
