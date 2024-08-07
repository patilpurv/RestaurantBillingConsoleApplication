package Services;
import java.util.List;
import Model.CustomerModel;
import Repository.CustomerRepository;

public class CustomerService
{
	CustomerRepository cRepo=new CustomerRepository();
	public boolean isAddCustomer(CustomerModel cmodel, int rtId)
	{
		return cRepo.isAddCustomer(cmodel, rtId);	 
	}
	public List<CustomerModel> getAllCustomerList()
	{
		return cRepo.getAllCustomerList();		
	}
//	public int getCountofCustomer()
//	{
//		return cRepo.getCOuntofCustomer();
//		//return 0;
//	}
}
