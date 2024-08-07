package Model;

public class TableModel {
    private int Tid;
    private String Ttype;
    private String Status;
   public TableModel()
    {
    	
    }
     public  TableModel(int Tid,String Ttype,String Status )
     {
    	 this.Tid=Tid;
     	this.Status=Status;
     	this.Ttype=Ttype;
     }
	public int getTid() {
		return Tid;
	}
	public void setTid(int Tid) {
		this.Tid = Tid;
	}
	public String getTtype() {
		return Ttype;
	}
	public void setTtype(String ttype) {
		Ttype = ttype;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
    
}
