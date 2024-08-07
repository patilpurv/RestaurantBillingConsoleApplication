package Services;

import java.util.List;

import Repository.BillRepository;

public class BillService {
	BillRepository bRepo=new BillRepository();
    public  List TotalBill(String Cname)
      {
    	 return bRepo.TotalBill(Cname);
//    	  return null;
      }
}
