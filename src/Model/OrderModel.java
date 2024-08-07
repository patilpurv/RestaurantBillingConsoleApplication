package Model;

import java.sql.Date;

public class OrderModel {	
	private int Oid;
	private Date Odate;
	private int price;
	public OrderModel()
	{
		
	}
	public OrderModel(int Oid,Date  odate2,int price)
	{
		this.Oid=Oid;
		this.Odate=odate2;
	}
	public int getOid() {
		return Oid;
	}
	public void setOid(int oid) {
		Oid = oid;
	}
	public java.sql.Date getDate() {
		return Odate;
	}
	public void setDate(Date Odate) {
		this.Odate = Odate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
