package cn.dao;

import java.util.List;

import cn.bean.Publish;

public interface PublishDao {
	//չʾ���г�����
	List<Publish> showAllPublish();
	//������id���س���������
	String getPublishName(int publishid);
	//ͨ�����������ҳ�����id
	int getPublishId(String publish);
}
