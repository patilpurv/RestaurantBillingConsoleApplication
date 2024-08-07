package Model;

public class CustomerModel {	
	private int Cid;
	private String Cname;
	private String contact;
	public CustomerModel()
	{
		
	}
	public CustomerModel(int Cid,String Cname,String contact)
	{
		this.Cid=Cid;
		this.Cname=Cname;
		this.contact=contact;
	}
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
