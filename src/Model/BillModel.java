package Model;

import java.sql.Date;

public class BillModel {
  private int Tid;
  private int Cid;
  private String Cname;
  private int Oid;
  private Date Odate;
  private int Totalamount;
public int getTid() {
	return Tid;
}
public void setTid(int tid) {
	Tid = tid;
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
public int getOid() {
	return Oid;
}
public void setOid(int oid) {
	Oid = oid;
}
public Date getOdate() {
	return Odate;
}
public void setOdate(Date odate) {
	Odate = odate;
}
public int getTotalamount() {
	return Totalamount;
}
public void setTotalamount(int totalamount) {
	Totalamount = totalamount;
}
  
}
