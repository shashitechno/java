import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;



public class Operations
{

    File sourceLocation;
	File targetLocation;
	String s;
	
   public void CopyDirectory(String s,String s1)
   {
        this.s=s;
		 
		System.out.print(s+" "+s1);
         sourceLocation=new File(s);
		 targetLocation=new File(s1);
		 try
		 {
		 copy(sourceLocation,targetLocation);
		 }
		 catch(Exception e){}
   }
      
   public void copy(File sourceLocation , File targetLocation)throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir(); 
            }

            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
			
                copy(new File(sourceLocation, children[i]),new File(targetLocation, children[i]));
			}
            
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
		
			
            while ((len = in.read(buf)) > 0) {
		  
		  
                out.write(buf, 0, len);
            }	
            in.close();
            out.close();
        }
		
    }
   public void deleteContent(String path)
   {	

   	File directory = new File(path);

   	//make sure directory exists
   	if(!directory.exists()){

         JOptionPane.showMessageDialog(null,"Directory does not exist.");
          System.exit(0);

       }else{

          try{

              delete(directory);

          }catch(IOException e){
              e.printStackTrace();
              System.exit(0);
          }
       }

   	System.out.println("Done");
   }

   public void delete(File file)
   	throws IOException{

   	if(file.isDirectory()){

   		//directory is empty, then delete it
   		if(file.list().length==0){

   		   file.delete();
   		   System.out.println("Directory is deleted : " 
                                                + file.getAbsolutePath());

   		}else{

   		   //list all the directory contents
       	   String files[] = file.list();

       	   for (String temp : files) {
       	      //construct the file structure
       	      File fileDelete = new File(file, temp);

       	      //recursive delete
       	     delete(fileDelete);
       	   }

       	   //check the directory again, if empty then delete it
       	   if(file.list().length==0){
          	     file.delete();
       	     System.out.println("Directory is deleted : " 
                                                 + file.getAbsolutePath());
       	   }
   		}

   	}else{
   		//if file, then delete it
   		file.delete();
   		System.out.println("File is deleted : " + file.getAbsolutePath());
   	}
   }
	
}
 
