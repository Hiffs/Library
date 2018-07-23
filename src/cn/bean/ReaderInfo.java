package cn.bean;

public class ReaderInfo {
	private int id;
	private String name;
	private String password;
	private String sex;
	private String tel;
	public ReaderInfo()
	{
		super();
	}
	public ReaderInfo(int id, String name, String password, String sex,
			String tel)
	{
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.tel = tel;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
}
