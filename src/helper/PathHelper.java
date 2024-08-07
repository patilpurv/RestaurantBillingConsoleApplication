package helper;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class PathHelper {

	public static  String completePath =" ";
	   public static Properties p=new Properties();
	public PathHelper()
	{
		try
		  {
			 Path currentDirectoryPath=FileSystems.getDefault().getPath("");
			 String currentDirectoryName=currentDirectoryPath.toAbsolutePath().toString();
		     completePath=currentDirectoryName+"\\src\\Resources\\db.properties";
		     System.out.println(currentDirectoryName);
		     System.out.println(completePath);
		     FileInputStream finf=new FileInputStream(completePath);
		     p.load(finf);
		  }
		catch(Exception ex)
		{
			System.out.println("Exception is "+ex);
		}
	}
}


