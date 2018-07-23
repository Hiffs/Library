package cn.dao;

import java.util.List;

import cn.bean.ManagerInfo;
import cn.bean.ReaderInfo;

public interface ManagerInfoDao
{
	//����һ��������Ϣ
	public boolean AddAReader(ReaderInfo reader);
	//ɾ��һ��������Ϣ
    public boolean DelAReader(int readerid);
    //�޸�һ��������Ϣ
    public boolean UpdaAReader(ReaderInfo reader);
    //��ѯ���ж���
	public List<ReaderInfo> findAllReader();
	//��½
    public ManagerInfo Login(int id,String password);
    //ע��
	public ManagerInfo register(ManagerInfo ma);
	
	public ManagerInfo manager(int id);
	
	public boolean getUpdateManager(ManagerInfo manager);
}
