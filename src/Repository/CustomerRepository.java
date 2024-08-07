package Repository;

import java.util.ArrayList;
import java.util.List;
import Model.CustomerModel;
import Model.TableModel;

public class CustomerRepository extends DBConfig {

	TableModel tm = new TableModel();
	String str;

	public boolean isAddCustomer(CustomerModel cmodel, int rtId) {
		try {

			int rTableId = rtId;
			stmt = conn.prepareStatement("select status from dtable where Tid=?");
			stmt.setInt(1, rTableId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
			String Str1 = "Booked";
			if (!str.equals(Str1)) {
				stmt = conn.prepareStatement("insert into customer values(?,?,?)");
				stmt.setInt(1, cmodel.getCid());
				stmt.setString(2, cmodel.getCname());
				stmt.setString(3, cmodel.getContact());
				stmt.executeUpdate();
				// Add customer details in customer table;

				stmt = conn.prepareStatement("insert into customertablejoin values(?,?)");
				stmt.setInt(1, cmodel.getCid());
				stmt.setInt(2, rTableId);

				// add data in customer join table;
				int value1 = stmt.executeUpdate();
				if (value1 > 0) {
					System.out.println("table get reserved with customer");
					return true;
				} else {
					System.out.println("Some problem is there");
				}
			} else {
				System.out.println("select another table id,its already reserved");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public List<CustomerModel> getAllCustomerList() {
		try {
			List<CustomerModel> ListCustomers = new ArrayList<CustomerModel>();
			stmt = conn.prepareStatement("Select * from Customer");
			rs = stmt.executeQuery();
			while (rs.next()) {

				CustomerModel cmodel = new CustomerModel();
				cmodel.setCid(rs.getInt(1));
				cmodel.setCname(rs.getString(2));
				cmodel.setContact(rs.getString(3));
				ListCustomers.add(cmodel);
			}
			System.out.println("size:" + ListCustomers.size());
//		    return ListCustomers.size();
			return ListCustomers.size() > 0 ? ListCustomers : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// return null;

	}
//	public int getCOuntofCustomer()
//	{
//		int value1=this.getAllCustomerList();
//		System.out.println("value"+value1);
//		return 1;
//		
//	}
}
