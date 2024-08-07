package Repository;

import java.sql.*;

import helper.PathHelper;
public class DBConfig {
	 protected Connection conn;
	  protected PreparedStatement stmt;
	  protected ResultSet rs;
	  
	  public DBConfig()
	  {
		  try
		  {
			  PathHelper phelp=new PathHelper();
			  Class.forName(phelp.p.getProperty("driver"));
			  conn=DriverManager.getConnection(phelp.p.getProperty("url"),phelp.p.getProperty("user"),phelp.p.getProperty("pass"));
		 
		  }
		  catch(Exception ex)
		  {
			System.out.println("Error is "+ex);  
		  }
	  }
}
//System.out.println(b?"Item added successfuly proceed to bill":"You are facing some issues placing order");
////   
////Date d=Date.valueOf(Odate);//covert into sql date
////   
//OrderModel omodel=new OrderModel(Oid,d,0);//add if else nested if following operation it
//boolean b2=iservice.isOrderAdd(omodel);
//System.out.println(b2?"Order Added Successfuly":"facing issue in order adding ");
//
//boolean  b1=iservice.orderitemjoin(Oid, Itid);
//System.out.println(b1?"OrderItem joined ":"Problem in orderitemjoin");
//int totalbill=iservice.setToatlInorder(imodel, Oid);
//System.out.println("Total Bill :"+totalbill);
