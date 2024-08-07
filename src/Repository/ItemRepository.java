package Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.ItemModel;
import Model.OrderModel;

public class ItemRepository extends DBConfig{
	public int findPriceofItem(String Iname,int qty)
	{ 
		int price=0;
		try {
			stmt=conn.prepareStatement("select price from menu where Mname=?");
		    stmt.setString(1,Iname);
		    rs=stmt.executeQuery();
		    while(rs.next())
		    {
		    	 price=rs.getInt(1);
		    }
		
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		int total=price*qty;
		return total;
		
	}
	public boolean isItemAdd(ItemModel imodel)
	{
		try
		{
			int total=this.findPriceofItem(imodel.getIname(),imodel.getQty());
			if(total!=0)
			{
			stmt=conn.prepareStatement("insert into items values(?,?,?,?)");
			stmt.setInt(1,imodel.getItid());
			stmt.setString(2,imodel.getIname());
			stmt.setInt(3,total);
			stmt.setInt(4,imodel.getQty() );
			return stmt.executeUpdate()>0?true:false;
			}
			else
			{
				System.out.println("Not able to fetch price from menu");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}

    public boolean isOrderAdd(OrderModel omodel) 
    {
    	try
    	{
    	   stmt=conn.prepareStatement("insert into orders values(?,?,?)");
    	   stmt.setInt(1,omodel.getOid());
    	   Date sqlDate=omodel.getDate();
    	   stmt.setDate(2,sqlDate);
//    	   stmt.setDate(2, sqlDate);
    	   stmt.setInt(3,omodel.getPrice());
    	   //.setDate(0, null);
    	   return stmt.executeUpdate()>0?true:false;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return false;
    	
    }
	public int getTotalpriceoforder(ItemModel imodel,int Oid)
	{ 
		int total = 0;
		//stmt=conn.prepareStatement("select sum()");
		try {
			stmt=conn.prepareStatement("select sum(i.price) from items i inner join orderitemjoin oj on i.Itid=oj.Itid inner join orders o on oj.Oid=o.Oid where oj.Oid=?;");
		    stmt.setInt(1, Oid);
		    rs=stmt.executeQuery();
		    while(rs.next())
		    {
		    	total=rs.getInt(1);
		    }
		    return total;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	
	}
	public int setToatlInorder(ItemModel imodel,int Oid)
	{
		try
		{  
			int total=this.getTotalpriceoforder(imodel,Oid);
			if(total!=0)
			{
			stmt=conn.prepareStatement("update orders set TotalAmount=? where Oid=?");
			stmt.setInt(1, total);
			stmt.setInt(2,Oid);
			 return stmt.executeUpdate()>0?total:0;
			}
			else
			{
				System.out.println("Total not get calculated in getTotalofOrder");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
		
	}
    public boolean orderitemjoin(int Oid,int Itid)
    {
    	try {
    	   
    		stmt=conn.prepareStatement("insert into orderitemjoin values(?,?)");  	     
			stmt.setInt(1,Oid);
			stmt.setInt(2,Itid);
			
		  	return stmt.executeUpdate()>0?true:false;
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	     
		return false;
    	
    }
    public List<OrderModel> getUniqueOrderList()
    {
    	List<OrderModel> uOrderList=new ArrayList();
		try {
			stmt=conn.prepareStatement("select Odate from orders  group by Odate order by Odate asc");
			rs=stmt.executeQuery();
			while(rs.next())
			{
			OrderModel omodel=new OrderModel();
			
			omodel.setDate(rs.getDate(1));
			
			uOrderList.add(omodel);
			}
			return uOrderList.size()>0?uOrderList:null;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    }
	public List<OrderModel> getAllOrderList() {
		List<OrderModel> OrderList=new ArrayList();
		try {
			stmt=conn.prepareStatement("select * from orders order by Odate asc");
			rs=stmt.executeQuery();
			while(rs.next())
			{
			OrderModel omodel=new OrderModel();
			omodel.setOid(rs.getInt(1));
			omodel.setDate(rs.getDate(2));
			omodel.setPrice(rs.getInt(3));
			OrderList.add(omodel);
			}
			return OrderList.size()>0?OrderList:null;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	public int getSpecificOrder(int checkOrderId)
	{ 
		
		int TotalAmount=0;
		try
		{
			stmt=conn.prepareStatement("select TotalAmount from orders where Oid=?");
			stmt.setInt(1,checkOrderId);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				 TotalAmount=rs.getInt(1);
			}
			return TotalAmount;
		}
		catch(Exception ex)
		{
		 ex.printStackTrace();
		}
		return 0;
	}
	public void CustomerorderJoin(int Cid,int Oid)
	{
		try {
	    	   
    		stmt=conn.prepareStatement("insert into customerorderjoin values(?,?)");  	     
			stmt.setInt(1,Cid);
			stmt.setInt(2,Oid);
            stmt.executeUpdate();
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	     
	}
	public List<ItemModel> getorderItems(String Cname)
	{
		int cid = 0,oid = 0;
		List<ItemModel> itemList=new ArrayList();
		try {
			stmt=conn.prepareStatement("select cid from customer where cname=?");
			stmt.setString(1,Cname);
			rs=stmt.executeQuery();
			while(rs.next())
			{
			   cid=rs.getInt(1);
			}
			stmt=conn.prepareStatement("select oid from customerorderjoin where cid=?");
			stmt.setInt(1,cid);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				oid=rs.getInt(1);
			}
			stmt=conn.prepareStatement("select Iname,qty,price from items i inner join orderitemjoin o on i.itid=o.itid where oid=?");
			stmt.setInt(1, oid);
			rs=stmt.executeQuery();
			while(rs.next())
			{
			ItemModel imodel1=new ItemModel();
			imodel1.setIname(rs.getString(1));
			imodel1.setQty(rs.getInt(2));
			imodel1.setPrice(rs.getInt(3));
			itemList.add(imodel1);
			}
			return itemList.size()>0?itemList:null;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
		
		
	}
}
