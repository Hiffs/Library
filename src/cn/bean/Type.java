package cn.bean;

public class Type {
	private int id;
	private String type;
	public Type()
	{
	}
	public Type(int id, String type)
	{
		this.id = id;
		this.type = type;
	}
	public int getid()
	{
		return id;
	}
	public void setid(int id)
	{
		this.id = id;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
}
