package careplus;

import java.io.*;

public class SerializeDemo {
    public void createFile() throws IOException
    {
    	try
    	{
    		File file=new File("Doctor.dat");
	    	if (file.createNewFile()) {
	            System.out.println("File created: " + file.getName());
	        }
	        else {
	            System.out.println("File already exists.");
	        }
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error occured");
    	}
    }
    public void writeFile(Doctor d)
    {
    	 try
    	 {
    		FileOutputStream file = new FileOutputStream("Doctor.dat",true);
 			ObjectOutputStream write = new ObjectOutputStream(file);
 			write.writeObject(d);
 			System.out.println("Done");
 			write.close();
 			file.close();
    	 }
    	 catch (IOException e) 
    	 {
    		 System.out.println("Eror 111");
    	 }
     }
    public void readFile()
    {
    	
    	try 
    	{
    		File file = new File("Doctor.dat");
    		FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
        	if(!file.exists()) file.createNewFile();
        	Doctor obj = (Doctor) objIn.readObject();
        } 
    	catch (IOException | ClassNotFoundException e) {
    		System.out.println("Eror");
        }
    }
}