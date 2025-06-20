package careplus.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor_DB {
	private static Doctor_DB ins=new Doctor_DB();
		
	public static Doctor_DB getObject()
	{
		return ins;
	}
	public void addDoctor(String id,String name,long number,String specialisation,int start,int end)
	{
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
			Statement s = con.createStatement();
			String query = "INSERT INTO doctor(Id, Name, Ph_no, Specialisation, Start_Time, End_Time) VALUES ('" + id + "', '" + name + "', '" + number + "', '" + specialisation + "', '" + start + "', '" + end + "')";
			s.executeUpdate(query);
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public boolean removeDoctor(String id)
	{
		try 
		{	
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
	        Statement s = con.createStatement();
	        String query = "SELECT * FROM doctor WHERE id = '" + id + "'";
	        ResultSet rs = s.executeQuery(query);
	        if (rs.next()) 
	        {
	            String updateQuery = "UPDATE doctor SET status = 'inactive' WHERE id = '" + id + "'";
	            int rows = s.executeUpdate(updateQuery);
	            if (rows > 0) 
	            {
	                return true;
	            } 
	        } 
	        con.close();
	    }
		catch (Exception e) 
		{
	        System.out.println(e);
	    }

	    return false;
	}
}
