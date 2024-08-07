package Model;

public class ItemModel {

	private int Itid;
	private String Iname;
	private int qty;
	private int price;
	public ItemModel()
	{
		
	}
	public ItemModel(int Itid,String Iname,int qty)
	{
		this.Itid=Itid;
		this.Iname=Iname;
		this.qty=qty;
	}
	public int getItid() {
		return Itid;
	}
	public void setItid(int itid) {
		Itid = itid;
	}
	public String getIname() {
		return Iname;
	}
	public void setIname(String iname) {
		Iname = iname;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setPrice(int price)
	{
		this.price=price;
		
	}
	public int getPrice()
	{
		return price;
	}
	
}
