package cn.bean;

import java.util.Date;

public class RentInfo {
	private int id;
	private int bookid;
	private int readerid;
	private Date booktime;
	private Date returntime;
	public RentInfo()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public RentInfo(int id, int bookid, int readerid, Date booktime, Date returntime)
	{
		super();
		this.id = id;
		this.bookid = bookid;
		this.readerid = readerid;
		this.booktime = booktime;
		this.returntime = returntime;
	}
	public RentInfo(int id, int bookid, int readerid, Date booktime)
	{
		super();
		this.id = id;
		this.bookid = bookid;
		this.readerid = readerid;
		this.booktime = booktime;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getBookid()
	{
		return bookid;
	}
	public void setBookid(int bookid)
	{
		this.bookid = bookid;
	}
	public int getReaderid()
	{
		return readerid;
	}
	public void setReaderid(int readerid)
	{
		this.readerid = readerid;
	}
	public Date getBooktime()
	{
		return booktime;
	}
	public void setBooktime(Date booktime)
	{
		this.booktime = booktime;
	}
	public Date getReturntime()
	{
		return returntime;
	}
	public void setReturntime(Date returntime)
	{
		this.returntime = returntime;
	}
}
