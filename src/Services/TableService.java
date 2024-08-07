package Services;

import java.util.List;

import Model.TableModel;
import Repository.TableRepository;

public class TableService {
    TableRepository tRepo =new TableRepository();
	public int isAddTable(TableModel tmodel)
	{
		return tRepo.isAddTable(tmodel)?0:1;
		
	}
	public List<TableModel> getAllTables()
	{
		return tRepo.getAllTables();
		
	}
	public boolean deleteTable(int dId)
	{
		
		return tRepo.deleteTable(dId)?true:false;
	}
	
}
