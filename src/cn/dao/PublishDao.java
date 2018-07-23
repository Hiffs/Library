package cn.dao;

import java.util.List;

import cn.bean.Publish;

public interface PublishDao {
	//展示所有出版社
	List<Publish> showAllPublish();
	//出版社id返回出版社名称
	String getPublishName(int publishid);
	//通过出版社名找出版社id
	int getPublishId(String publish);
}
