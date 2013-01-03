import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import java.util.zip.ZipEntry;



	public class Actions {

		
		public static final void writeFile(InputStream in, OutputStream out)throws IOException {
			byte[] buffer = new byte[1024];
			int len;

			while ((len = in.read(buffer)) >= 0)
				out.write(buffer, 0, len);

			in.close();
			out.close();
		}

		public  void unzipMyZip(String zipFileName,String directoryToExtractTo) {
			Enumeration entriesEnum;
			ZipFile zipFile;
			try {
				zipFile = new ZipFile(zipFileName);
				entriesEnum = zipFile.entries();

				File directory= new File(directoryToExtractTo);

				/**
				 * Check if the directory to extract to exists
				 */
				if(!directory.exists())
				{
					/**
					 * If not, create a new one.
					 */
					new File(directoryToExtractTo).mkdir();
					System.err.println("...Directory Created -"+directoryToExtractTo);
				}
				while (entriesEnum.hasMoreElements()) {
					try {
						ZipEntry entry = (ZipEntry) entriesEnum.nextElement();

						if (entry.isDirectory()) {
							/**
							 * Currently not unzipping the directory structure.
							 * All the files will be unzipped in a Directory
							 *
							 **/
						} else {

							System.err.println("Extracting file: "+ entry.getName());
							/**
							 * The following logic will just extract the file name
							 * and discard the directory
							 */
							int index = 0;
							String name = entry.getName();
							index = entry.getName().lastIndexOf("/");
							if (index > 0 && index != name.length())
								name = entry.getName().substring(index + 1);

							System.out.println(name);

							writeFile(zipFile.getInputStream(entry),new BufferedOutputStream(new FileOutputStream(directoryToExtractTo + name)));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				zipFile.close();
			} catch (IOException ioe) {
				System.err.println("Some Exception Occurred:");
				ioe.printStackTrace();
				return;
			}
		
		}
		     /*
		     * Zip function zip all files and folders
		     */
		   
		   public boolean zipFiles(String srcFolder, String destZipFile)
		  {
		      boolean result=false;
		      try
		      {
		          System.out.println("Program Start zipping the given files");
		          /*
		           * send to the zip procedure
		           */
		          zipFolder(srcFolder,destZipFile);
		          result=true;
		          System.out.println("Given files are successfully zipped");
		      }
		      catch(Exception e)
		      {
		          System.out.println("Some Errors happned during the zip process"+e);
		      }
		      finally
		      {
		          return result;
		      }
		  }
		  /*
		   * zip the folders  
		   */
		  private void zipFolder(String srcFolder, String destZipFile) throws Exception
		  {
		    ZipOutputStream zip = null;
		    FileOutputStream fileWriter = null;
		    /*
		     * create the output stream to zip file result
		     */
		    fileWriter = new FileOutputStream(destZipFile);
		    zip = new ZipOutputStream(fileWriter);
		    /*
		     * add the folder to the zip
		     */
		    addFolderToZip("", srcFolder, zip);
		    /*
		     * close the zip objects
		     */
		    zip.flush();
		    zip.close();
		  }
		  /*
		   * recursively add files to the zip files
		   */
		  private void addFileToZip(String path, String srcFile, ZipOutputStream zip,boolean flag)throws Exception 
		  {
		     /*
		      * create the file object for inputs  
		      */
		    File folder = new File(srcFile);

		    /*
		     * if the folder is empty add empty folder to the Zip file
		     */
		    if (flag==true)
		    {
		        zip.putNextEntry(new ZipEntry(path + "/" +folder.getName() + "/"));
		    }
		    else
		    {     /*
		         * if the current name is directory, recursively traverse it to get the files
		         */
		        if (folder.isDirectory() ) 
		        {
		            /*
		             * if folder is not empty 
		             */
		            addFolderToZip(path, srcFile, zip);
		        }
		        else
		        {
		           /*
		            * write the file to the output
		           */
		           byte[] buf = new byte[1024];
		           int len;
		           FileInputStream in = new FileInputStream(srcFile);
		           zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
		           while ((len = in.read(buf)) > 0) 
		            {
		              /*
		               * Write the Result 
		              */
		              zip.write(buf, 0, len);
		            }
		        }
		    }
		  }
		/*
		 * add folder to the zip file 
		 */
		 private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
		      throws Exception 
		 {
		    File folder = new File(srcFolder);

		    /*
		     * check the empty folder
		     */
		    if (folder.list().length == 0)
		    {
		         System.out.println(folder.getName());  
		         addFileToZip(path , srcFolder, zip,true);
		    }
		    else
		    {
		        /*
		         * list the files in the folder 
		         */
		        for (String fileName : folder.list())
		        {
		          if (path.equals(""))
		          {
		            addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip,false);
		          } 
		          else 
		             {
		                 addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip,false);
		             }
		       }
		   }
		  }
		 boolean isValid(final File file) {
			    ZipFile zipfile = null;
			    try {
			        zipfile = new ZipFile(file);
			        return true;
			    } catch (ZipException e) {
			        return false;
			    } catch (IOException e) {
			        return false;
			    } finally {
			        try {
			            if (zipfile != null) {
			                zipfile.close();
			                zipfile = null;
			            }
			        } catch (IOException e) {
			        }
			    }
			}

	}

