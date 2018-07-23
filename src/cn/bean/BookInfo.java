package cn.bean;

public class BookInfo {
	private int id;
	private String name;
	private int authorid;
	private int typeid;
	private String position;
	private int publishid;
	private int status;
	private String info;
	public BookInfo(String name, int authorid, int typeid, String position,
			int publishid, int status, String info) {
		super();
		this.name = name;
		this.authorid = authorid;
		this.typeid = typeid;
		this.position = position;
		this.publishid = publishid;
		this.status = status;
		this.info = info;
	}
	public BookInfo() {
		super();
	}
	public BookInfo(int id, String name, int authorid, int typeid,
			String position, int publishid, int status, String info) {
		super();
		this.id = id;
		this.name = name;
		this.authorid = authorid;
		this.typeid = typeid;
		this.position = position;
		this.publishid = publishid;
		this.status = status;
		this.info = info;
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
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getPublishid() {
		return publishid;
	}
	public void setPublishid(int publishid) {
		this.publishid = publishid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
