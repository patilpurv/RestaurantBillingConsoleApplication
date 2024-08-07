package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.BillModel;


public class BillRepository extends DBConfig{

	public List TotalBill(String Cname)
	{
		List SpecificTable=new ArrayList();
		try {
			stmt=conn.prepareStatement("select T.Tid,c.cid,c.cname,O.oid,O.odate,O.totalamount from dtable T inner join customertablejoin ct on T.tid=ct.Tid inner join customer c on ct.cid=c.cid inner join customerorderjoin co on c.cid=co.cid inner join orders O on co.oid=o.oid where c.cname=?");
		    stmt.setString(1, Cname);
		    rs=stmt.executeQuery();
		    while(rs.next())
		    {
		    	BillModel bm=new BillModel();
		    	bm.setTid(rs.getInt(1));
		    	bm.setCid(rs.getInt(2));
		    	bm.setCname(rs.getString(3));
		    	bm.setOid(rs.getInt(4));
		    	bm.setOdate(rs.getDate(5));
		    	bm.setTotalamount(rs.getInt(6));
		    	SpecificTable.add(bm);		    	
		    }
		    return SpecificTable.size()>0?SpecificTable:null;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
}
