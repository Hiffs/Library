package cn.dao;

import cn.bean.BookInfo;
import cn.bean.ReaderInfo;

public interface ReaderInfoDao
{
	//��¼
	public ReaderInfo login(int id,String pwd);
	//ע��
	public ReaderInfo register(ReaderInfo reader);
	//��������Ƿ�һ��
	public boolean checkPwd(String pwd1,String pwd2);
	//����ֻ������Ƿ��ѱ���
	public boolean checkTel(String tel);
	//ͨ���绰�����ѯ����id
	public int queryId(String tel);
	//����
	public boolean borrow(BookInfo book);//�������޸��鼮��Ϣ��status��Ϊ1
	//����
	public boolean returnbook(BookInfo book);//�������޸��鼮��Ϣ��status��Ϊ0
	//���߲鿴������Ϣ
	public ReaderInfo queryIndi(int id);
	//�����޸ĸ�����Ϣ
	public boolean updIndi(ReaderInfo reader);
	//���ԭ����
	public boolean checkOPwd(String opwd,String pwd);
}
