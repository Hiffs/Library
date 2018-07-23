package cn.bean;

public class ManagerInfo {
	private int id;
    private String name;
    private String password;
    
	public ManagerInfo()
	{
		
	}
	public ManagerInfo(int id, String name, String password)
	{
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
