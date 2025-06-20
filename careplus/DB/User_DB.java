package careplus.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import careplus.features.AdminView;
import careplus.features.ReceptionistView;

public class User_DB {
	private static User_DB ins=new User_DB();
	
	public static User_DB getObject()
	{
		return ins;
	}
	
	public void addUser(String name,String password,String role)
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus","root","#Js@2203");
			Statement s=con.createStatement();
			String query = "INSERT INTO user(Name, Password, Role) VALUES ('" + name + "', '" + password + "', '" + role + "')";
			s.executeUpdate(query);
			con.close();
			System.out.println("Added successfully");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public void loginUser(int id, String password) 
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus","root","#Js@2203");
			Statement s=con.createStatement();
			String query="Select * from user where id = '" + id + "' ";
			ResultSet rs=s.executeQuery(query);
			if(rs.next())
			{
				if(rs.getString(3).equals(password))
				{
					System.out.println("Login successfully");
					if(rs.getString(4).equals("Admin"))
					{
						new AdminView().admin();
					}
					else
					{
						new ReceptionistView().receptionist();
					}
				}
				else
				{
					System.out.println("Password Wrong");
				}
			}
			else
			{
				System.out.println("User not found");
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	public boolean removeUser(int id)
	{
		try {
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
	        Statement s = con.createStatement();
	        String query = "SELECT * FROM user WHERE id = " + id;
	        ResultSet rs = s.executeQuery(query);

	        if (rs.next()) 
	        {
	            String deleteQuery = "DELETE FROM user WHERE id = " + id;
	            int row= s.executeUpdate(deleteQuery);
	            if (row > 0) 
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
