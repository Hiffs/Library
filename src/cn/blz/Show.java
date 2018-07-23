package cn.blz;

import java.util.List;

import cn.bean.RentInfo;
import cn.dao.AuthorDao;
import cn.dao.BookInfoDao;
import cn.dao.PublishDao;
import cn.dao.RentInfoDao;
import cn.dao.TypeDao;
import cn.dao.Impl.AuthorDaoImpl;
import cn.dao.Impl.BookInfoDaoImpl;
import cn.dao.Impl.PublishDaoImpl;
import cn.dao.Impl.RentInfoDaoImpl;
import cn.dao.Impl.TypeDaoImpl;
import cn.dbc.Base;

public class Show
{
	Base ba = new Base();
	AuthorDao ad = new AuthorDaoImpl();
	BookInfoDao bod = new BookInfoDaoImpl();
	PublishDao pd = new PublishDaoImpl();
	RentInfoDao rd = new RentInfoDaoImpl();
	TypeDao td = new TypeDaoImpl();
	
	
	public void showAllRent()
	{
		List<RentInfo> li = rd.getAllRentInfo();
		int i = 1;
		if(li.size() > 0)
		{
			for(RentInfo r : li)
			{
				System.out.println((i++)+"\t"+bod.showBookById(r.getBookid()).getName()
						+"\t"+r.getBookid()+"\t"+r.getBooktime()+"\t"+r.getReturntime());
			}
		}
	}
}
