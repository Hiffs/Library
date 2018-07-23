package cn.dao;

import java.util.List;

import cn.bean.Type;

public interface TypeDao {
	//展示所有类型
	List<Type> showAllType();
	//类型id返回类型名称
	String getTypeName(int typeid);
	//通过类型名找类型id
	int getTypeId(String Type);
}
