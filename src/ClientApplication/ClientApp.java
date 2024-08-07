 package ClientApplication;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.BillModel;
import Model.CustomerModel;
import Model.ItemModel;
import Model.OrderModel;
import Model.TableModel;
import Services.CustomerService;
import Services.ItemService;
import Services.TableService;
import helper.PathHelper;
import Services.BillService;

public class ClientApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TableService ts = new TableService();
		CustomerService cs = new CustomerService();
		ItemService iservice = new ItemService();
		BillService bservice = new BillService();

		// PathHelper p1=new PathHelper();
		int Itid = 0;
		String Iname;
		int totalbill;
		int qty;
		do {
			System.out.println("====================================");
			System.out.println(
					"Enter your choice:\n1:Table Info\n2:Customer Info\n3:Place order\n4:Get Specific Order Bill\n5:View Daily Collection\n6:View Daily orders\n7:Print Bill\n8:Want to add Bulk orders");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				sc.nextLine();
				System.out.println("====================================");
				System.out.println("1:Add Table\n2:View tables\n3:Delete table");
				int c = sc.nextInt();
				switch (c) {
				case 1:
					sc.nextLine();
					System.out.println("Enter Table Id");
					int Tid = sc.nextInt();
					System.out.println("Enter Table name");
					sc.nextLine();
					String Ttype = sc.nextLine();
					System.out.println("Enter table status");
					String status = sc.nextLine();
					TableModel tmodel = new TableModel(Tid, Ttype, status);
					int result = ts.isAddTable(tmodel);
					System.out.println(result == 0 ? "Table reserved" : "Table not get reserved");
					break;
				case 2:
					sc.nextLine();
					System.out.println("====================================");
					List<TableModel> list = ts.getAllTables();
					for (TableModel tm : list) {
						System.out.println(tm.getTid() + "\t" + tm.getTtype() + "\t\t" + tm.getStatus());
					}
					break;
				case 3:
					sc.nextLine();
					System.out.println("====================================");
					System.out.println("Enter Table Id to delete table");
					int dId = sc.nextInt();
					System.out.println(ts.deleteTable(dId) ? "Successfully deleted" : "Not able to delete table");
					break;
				}
				break;

			case 2:
				sc.nextLine();
				System.out.println("====================================");
				System.out.println("1:Add Customer\n2:View Customers");
				c = sc.nextInt();
				switch (c) {
				case 1:
					sc.nextLine();
					System.out.println("====================================");
					System.out.println("Enter Customer Id");
					int Cid = sc.nextInt();
					System.out.println("Enter Customer name");
					sc.nextLine();
					String Cname = sc.nextLine();
					System.out.println("Enter contact");
					String Contact = sc.nextLine();
					System.out.println("Enter Table id want to reserve");
					int rtId = sc.nextInt();
					CustomerModel cmodel = new CustomerModel(Cid, Cname, Contact);
					System.out.println(
							cs.isAddCustomer(cmodel, rtId) ? "Customer added successfully" : "Some Problem is there");
//	            	  System.out.println(b?"Customer Added Successfully":"Not able to add customer");
					break;
				case 2:
					sc.nextLine();
					System.out.println("====================================");
					List<CustomerModel> Customerlist = cs.getAllCustomerList();
//	            	  if(Customerlist.size()<=0)
//	            	  {
//	            		  System.out.println("No customer present");
//	            	  }
					for (CustomerModel cm : Customerlist) {
						System.out.println(cm.getCid() + "\t" + cm.getCname() + "\t" + cm.getContact());
					}
//					int value=cs.getCountofCustomer();
//					System.out.println("Total no of customer==>"+value);
					break;
				}
				break;

			case 3:
				String str = "Stop";

				sc.nextLine();
				System.out.println("====================================");
				System.out.println("Enter customer id");
				int Cid = sc.nextInt();
				System.out.println("Enter order id");
				int Oid = sc.nextInt();
				System.out.println("Enter date ");
				String Odate = sc.next();// 2024/04/13

//              List Items=new ArrayList();

				System.out.println("Enter Item Id");
				Itid = sc.nextInt();
				System.out.println("Enter dish");
				sc.nextLine();
				Iname = sc.nextLine();
				System.out.println("Enter Quantity");
				qty = sc.nextInt();

				ItemModel imodel = new ItemModel(Itid, Iname, qty);
//				Items.add(imodel);
				boolean b = iservice.isItemAdd(imodel);
				Date d = Date.valueOf(Odate);// convert string date into sql date
//same simple logic in dbconfig class
				if (b)// Item added successfuly proceed to bill
				{
					OrderModel omodel = new OrderModel(Oid, d, 0);
					boolean b2 = iservice.isOrderAdd(omodel);
					if (b2)// Order Added Successfuly
					{

						boolean b1 = iservice.orderitemjoin(Oid, Itid);
						if (b1)// OrderItem joined
						{

							totalbill = iservice.setToatlInorder(imodel, Oid);
							System.out.println("Order placed");
							System.out.println("Total Bill :" + totalbill);
							iservice.CustomerorderJoin(Cid, Oid);// add into customerorderjoin;
						} else {
							System.out.println("there is pblm in order item join");
						}

					} else {
						System.out.println("There is pblm in order add");
					}
				} else {
					System.out.println("there is some pblm in item add");
				}
				break;

			case 4:
				System.out.println("====================================");
				System.out.println("Enter Order Id you want to calculate bill");
				int checkOrderId = sc.nextInt();
				int totalBill = iservice.getSpecificOrder(checkOrderId);
				System.out.println("TotalBill: " + totalBill);

				break;
			case 5:

				List<OrderModel> Datewiseorder = iservice.getDatewiseCollection();
				for (OrderModel o : Datewiseorder) {
					System.out.println(o.getDate() + "---->" + o.getPrice());
				}
				break;
			case 6:
				List<OrderModel> DailyOrder = iservice.getDailyOrders();
				System.out.println("Id\t  Date\t    Amount");
				for (OrderModel o : DailyOrder) {
					System.out.println(o.getOid() + "\t" + o.getDate() + "\t" + o.getPrice());
				}
				break;
			case 7:// It will show all the bills ordered on that name ,
				System.out.println("Final Bill");
				System.out.println("Enter Customer name of which you want bill");
				System.out.println("============================================================");
				sc.nextLine();
				String Cname = sc.nextLine();
				List<BillModel> Totalbill = bservice.TotalBill(Cname);

				for (BillModel bm : Totalbill) {
					System.out.println("//////////////////////////////////////");
					System.out.println("Date:- " + bm.getOdate() + "\n" + "Table-id:- " + bm.getTid() + "\n"
							+ "Customer-Id:- " + bm.getCid() + " " + "Order-id:- " + bm.getOid() + "\n"
							+ "Customer-name:- " + bm.getCname());
					List<ItemModel> AllItem = iservice.getorderItems(Cname);
					System.out.println("Items\tQty\tPrice");
					for (ItemModel i : AllItem) {
						System.out.println(i.getIname() + "\t" + i.getQty() + "\t" + i.getPrice());
					}
					System.out.println("\nTotalAmount:- " + bm.getTotalamount());
				}
				System.out.println("//////////////////////////////////////");

				// List TotalBill=Bservice.

				break;

			// ===============================================================================

			case 8:
//				String str = "Stop";

				sc.nextLine();
				System.out.println("Enter customer id");
				Cid = sc.nextInt();
				System.out.println("Enter order id");
				Oid = sc.nextInt();
				System.out.println("Enter date ");
				Odate = sc.next();// 2024/04/13

				d = Date.valueOf(Odate);// convert string date into sql date
				// same simple logic in dbconfig class

				OrderModel omodel = new OrderModel(Oid, d, 0);
				boolean b2 = iservice.isOrderAdd(omodel);
				// List Items=new ArrayList();//create list to add bulk items in order once;
				String s = null, s1 = ("yes");
				do {
					System.out.println("Enter Item Id");
					Itid = sc.nextInt();
					System.out.println("Enter dish");
					sc.nextLine();
					Iname = sc.nextLine();
					System.out.println("Enter Quantity");
					qty = sc.nextInt();

					ItemModel imodels = new ItemModel(Itid, Iname, qty);
					b = iservice.isItemAdd(imodels);
					boolean b1 = iservice.orderitemjoin(Oid, Itid);

					totalbill = iservice.setToatlInorder(imodels, Oid);
					System.out.println("want to add more:yes to add,No to stop");
					sc.nextLine();
					s = sc.nextLine();
				} while (s.equals(s1));

				iservice.CustomerorderJoin(Cid, Oid);// add into customerorderjoin;
				System.out.println("Total Bill:  " + totalbill);
//					if (b2)// Order Added Successfuly
//					{
//						
//						
//					//	if (b1)// OrderItem joined
//						{
//							
//						ItemModel imodels=new ItemModel();
//							
//							System.out.println("Order placed");
//							// System.out.println("Total Bill :" + totalbill);
//													} else {
//							System.out.println("there is pblm in order item join");
//						}
//
//					} else {
//						System.out.println("There is pblm in order add");
//					}
//				} else {
//					System.out.println("there is some pblm in item add");
//				}
				break;
			}
		} while (true);
	}

}
