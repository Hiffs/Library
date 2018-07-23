package cn.dao;

import java.util.Date;
import java.util.List;
import cn.bean.RentInfo;

public interface RentInfoDao
{
	public List<RentInfo> getAllRentInfo();  //查询所有借阅记录
	
	public List<RentInfo> getAllRentInfoByReadId(int Readerid);  //根据读者id查询该读者所有借阅记录
	
	public List<RentInfo> getRentInfoByReadId(int Readerid);  //根据读者id查询该读者在借书本记录
	
	public List<RentInfo> getRentInfoByBook(int bookid);  //根据书本id查询该书本借阅情况
	
	public boolean delAllRentInfoByReadId(int readerid); //根据读者id删除该读者所有借阅记录
	
	public boolean addRentInfo(int readerid, int bookid);
	
	public RentInfo getRentInfoById(int id);
	
	public boolean returnRentInfo(int id, Date d);
	
	public boolean rebookRentInfo(int id, Date d);
}
