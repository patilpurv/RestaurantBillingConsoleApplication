package Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import Model.TableModel;

public class TableRepository extends DBConfig {

//	public int getTableId()
//	{
//		try {
//			stmt=conn.prepareStatement("select max(Tid) from DTable");
//			rs=stmt.executeQuery();
//			if(rs.next())
//			{
//				Tid=rs.getInt(1);
//			}
//			++Tid;
//			
//		}
//		catch(Exception ex)
//	    {
//	      System.out.println(ex);
//	     }
//		return Tid;
//	}
	public boolean isAddTable(TableModel tmodel)
	{ 
		
		try {
//			if(Tid==3)//limited tables
//			{
			  stmt=conn.prepareStatement("insert into dtable values(?,?,?)");
			  stmt.setInt(1,tmodel.getTid());
			  stmt.setString(2,tmodel.getTtype());
			  stmt.setString(3,tmodel.getStatus());
			  
			  return stmt.executeUpdate()>0?true:false;
			//}
//			else
//			{
//				System.out.println("Table not available");
//				
//			}
		} 		
		catch (SQLException ex) 
		{
			// TODO Auto-genertated catch block
		System.out.println("error is"+ex);
		}
		
	   return true;  
	}
	public List<TableModel> getAllTables() 
	{
	try
	{
		List<TableModel> listTables=new ArrayList<TableModel>();
	
		stmt=conn.prepareStatement("select * from dtable");
		rs=stmt.executeQuery();
		while(rs.next())
		{
			TableModel model= new TableModel();
			model.setTid(rs.getInt(1));
			model.setTtype(rs.getString(2));
			model.setStatus(rs.getString(3));
			listTables.add(model);
		}
		return listTables.size()>0?listTables:null;	
		
	
	}
	catch(Exception ex)
	{
		System.out.println("Error in getAllTables"+ex);
	}
	return null;
	}

	public boolean deleteTable(int dId)
	{
		try {
			stmt=conn.prepareStatement("delete from dtable where Tid=?");
			stmt.setInt(1,dId);
			return stmt.executeUpdate()>0?true:false;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
