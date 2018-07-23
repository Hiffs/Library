package cn.dao;

import java.util.Date;
import java.util.List;
import cn.bean.RentInfo;

public interface RentInfoDao
{
	public List<RentInfo> getAllRentInfo();  //��ѯ���н��ļ�¼
	
	public List<RentInfo> getAllRentInfoByReadId(int Readerid);  //���ݶ���id��ѯ�ö������н��ļ�¼
	
	public List<RentInfo> getRentInfoByReadId(int Readerid);  //���ݶ���id��ѯ�ö����ڽ��鱾��¼
	
	public List<RentInfo> getRentInfoByBook(int bookid);  //�����鱾id��ѯ���鱾�������
	
	public boolean delAllRentInfoByReadId(int readerid); //���ݶ���idɾ���ö������н��ļ�¼
	
	public boolean addRentInfo(int readerid, int bookid);
	
	public RentInfo getRentInfoById(int id);
	
	public boolean returnRentInfo(int id, Date d);
	
	public boolean rebookRentInfo(int id, Date d);
}
