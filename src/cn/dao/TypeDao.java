package cn.dao;

import java.util.List;

import cn.bean.Type;

public interface TypeDao {
	//չʾ��������
	List<Type> showAllType();
	//����id������������
	String getTypeName(int typeid);
	//ͨ��������������id
	int getTypeId(String Type);
}
