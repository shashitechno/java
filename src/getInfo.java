import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class getInfo {
	 long foldersize;

	String[] getContent(String path)
	{
		File f =new File(path);
		return f.list();
	}
	DefaultTableModel addContent(String dir,DefaultTableModel dtm)
	{
		DefaultTableModel productsModel;
		   String str[]=getContent(dir);
		Object[][] data= new Object[str.length][9];
		String[] columnNames = {"Name","Size","Modified","Created","Accessed","Attributes","Packed Size","Comment"};
		
		
		
		for(int i=0;i<str.length;i++)
		{
			for(int j=0;j<9;j++)
			{
				switch(j)
				{        
				case 0 :data[i][j]=str[i];    break;
				case 1 :data[i][j]= "40MB";  break;//getFolderSize(dir+str[i]); break;
				case 2 :data[i][j]=lastModify(new File(dir+str[i]));    break;
				case 3 :data[i][j]= DateCreated(dir+str[i]);    break;
				case 4 :data[i][j]="20MB";    break;
				case 5 :data[i][j]="10MB";    break;
				case 6 :data[i][j]="5MB";     break;
				case 7 :data[i][j]="2MB";     break;
				case 8 :data[i][j]="1MB";     break;
				 				
				}
			}
		}
		
		productsModel = new DefaultTableModel(data,columnNames)
		{
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
		   return false;
		}};
		
		return productsModel;
	}
	long getFolderSize(String path){                    
	    
	    try
	    {
	    File folder=new File(path);
	    if(folder.isFile())
	    {
	     foldersize+=folder.length();
	        return foldersize;
	        
		
	    }
	    	else
	    	{
	    File[] filelist = folder.listFiles();  
	    
	   
	  
	   for(int  i =0; i<filelist.length; i++)   
	     {  
	    	  // System.out.print(filelist[i].toString());
	    
	        getFolderSize(filelist[i].toString());  
	          
	     }  
	        
	    	}
	    }
	    catch(Exception e)
	    {
	    	JOptionPane.showMessageDialog(null,e+" "+path+ "not avail");
	    }
	    return foldersize;
	   
	}  
	
	long lastModify(File f)
	{
		return f.lastModified();
	}
	String DateCreated(String path)
	{
	
		     
		//String file = BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
			//FileTime creationTime = attributes.creationTime();
			return "";
				
	}
	
}