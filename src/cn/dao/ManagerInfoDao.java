package cn.dao;

import java.util.List;

import cn.bean.ManagerInfo;
import cn.bean.ReaderInfo;

public interface ManagerInfoDao
{
	//增加一个读者信息
	public boolean AddAReader(ReaderInfo reader);
	//删除一个读者信息
    public boolean DelAReader(int readerid);
    //修改一个读者信息
    public boolean UpdaAReader(ReaderInfo reader);
    //查询所有读者
	public List<ReaderInfo> findAllReader();
	//登陆
    public ManagerInfo Login(int id,String password);
    //注册
	public ManagerInfo register(ManagerInfo ma);
	
	public ManagerInfo manager(int id);
	
	public boolean getUpdateManager(ManagerInfo manager);
}
