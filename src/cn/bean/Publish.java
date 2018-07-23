package cn.bean;

public class Publish {
	private int id;
	private String publish;
	public Publish()
	{
	}
	public Publish(int id, String publish)
	{
		this.id = id;
		this.publish = publish;
	}
	public int getid()
	{
		return id;
	}
	public void setid(int id)
	{
		this.id = id;
	}
	public String getPublish()
	{
		return publish;
	}
	public void setPublish(String publish)
	{
		this.publish = publish;
	}
}
