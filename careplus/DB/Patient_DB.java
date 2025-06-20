package careplus.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Patient_DB {
	private static Patient_DB ins=new Patient_DB();
	
	public static Patient_DB getObject()
	{
		return ins;
	}
	
	public String addPatient(String id,String name,int age,long ph_no)
	{
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
			Statement s = con.createStatement();
			String query = "INSERT INTO Patient(Id,Name,Age,Ph_No) VALUES ('" + id + "', '" + name + "', '" + age + "', '" + ph_no + "')";
			s.executeUpdate(query);
			con.close();
			return id;
		}
		catch(Exception e)
		{
			System.out.println("Patient error");
			System.out.println(e);
			return ""+e;
		}
	}
	
	public List<String> searchPatient(String Name) {
		List<String> ans=new ArrayList();
		try 
        {
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
			Statement s = con.createStatement();
			String query = "SELECT * FROM Patient where name= '"+Name+"'";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) 
            {
                String id = rs.getString("Id");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                long phone = rs.getLong("Ph_No");
                ans.add("Id: "+id+"\nName: "+name+
                		"\nAge: "+age+"\nPhone: "+phone);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
		return ans;
    }
	
	public String bookAppointment(String Name,int Age,long Ph_No)
	{
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
			Statement s = con.createStatement();
			String query = "SELECT * FROM patient";
	        ResultSet rs = s.executeQuery(query);
	        while (rs.next()) 
	        {
	            String id = rs.getString("Id");
	            String name = rs.getString("Name");
	            int age = rs.getInt("Age");
	            long phone = rs.getLong("Ph_No");
	            if(phone==Ph_No && Name.equals(name) && Age==age)
	            {
	            	return id;
	            }
	        }
	        String Id="pat"+(++DataBase.patient_id);
	        String id=addPatient(Id,Name,Age,Ph_No);
	        return id;
		}
		catch(Exception e)
		{
			return ""+e;
		}
	}
	public boolean removePatient(String id)
	{
		try 
		{	
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
	        Statement s = con.createStatement();
	        String query = "SELECT * FROM patient WHERE id = '" + id + "'";
	        ResultSet rs = s.executeQuery(query);
	        if (rs.next()) 
	        {
	            String updateQuery = "UPDATE patient SET status = 'inactive' WHERE id = '" + id + "'";
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
