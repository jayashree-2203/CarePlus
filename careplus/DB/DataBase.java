package careplus.DB;

import java.sql.*;

public class DataBase {
	private static DataBase db_obj=new DataBase();
	
	public static Integer recep_id=0;
	public static Integer doctor_id=0,patient_id=0;
	
	public static DataBase getObject()
	{
		return db_obj;
	}
	
	public void getDoctorId()
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus","root","#Js@2203");
			Statement s=con.createStatement();
			String query= "Select count(*) from Doctor";
			ResultSet rs=s.executeQuery(query);
			if(rs.next()) {
				doctor_id = rs.getInt(1);
			}else {
				doctor_id = 0;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void getPatientId()
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus","root","#Js@2203");
			Statement s=con.createStatement();
			String query= "Select count(*) from Patient";
			ResultSet rs=s.executeQuery(query);
			if(rs.next()) {
				patient_id = rs.getInt(1)+1;
			}else {
				patient_id = 0;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void connect()
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus","root","#Js@2203");
			Statement s=con.createStatement();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
}
