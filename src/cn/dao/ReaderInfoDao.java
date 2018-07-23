package cn.dao;

import cn.bean.BookInfo;
import cn.bean.ReaderInfo;

public interface ReaderInfoDao
{
	//登录
	public ReaderInfo login(int id,String pwd);
	//注册
	public ReaderInfo register(ReaderInfo reader);
	//检查密码是否一致
	public boolean checkPwd(String pwd1,String pwd2);
	//检查手机号码是否已被绑定
	public boolean checkTel(String tel);
	//通过电话号码查询读者id
	public int queryId(String tel);
	//借书
	public boolean borrow(BookInfo book);//方法中修改书籍信息，status改为1
	//还书
	public boolean returnbook(BookInfo book);//方法中修改书籍信息，status改为0
	//读者查看个人信息
	public ReaderInfo queryIndi(int id);
	//读者修改个人信息
	public boolean updIndi(ReaderInfo reader);
	//检查原密码
	public boolean checkOPwd(String opwd,String pwd);
}
