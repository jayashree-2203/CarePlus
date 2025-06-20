package careplus.features;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import careplus.DB.*;
public class ReceptionistModel {
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
	        String id=new Patient_DB().addPatient(Id,Name,Age,Ph_No);
	        return id;
		}
		catch(Exception e)
		{
			return ""+e;
		}
	}
	
	public void bookSlot(String patient_id, String doctor_id, String date) {
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203")) {

	        String timeQuery = "SELECT start_time, end_time FROM doctor WHERE id = ?";
	        PreparedStatement getTime = con.prepareStatement(timeQuery);
	        getTime.setString(1, doctor_id);
	        ResultSet rsTimes = getTime.executeQuery();

	        if (!rsTimes.next()) {
	            System.out.println("Doctor not found.");
	            return;
	        }

	        int start_time = rsTimes.getInt("start_time");
	        int end_time = rsTimes.getInt("end_time");

	        String countQuery = "SELECT COUNT(*) FROM appointment WHERE doctor_id = ? AND Appointment_Date = ?";
	        PreparedStatement getCount = con.prepareStatement(countQuery);
	        getCount.setString(1, doctor_id);
	        getCount.setString(2, date);
	        ResultSet rsCount = getCount.executeQuery();

	        int count = 0;
	        if (rsCount.next()) {
	            count = rsCount.getInt(1);
	        }

	        int time = start_time + count;
	        if (time >= end_time) {
	            System.out.println("Appointments full on that date");
	            return;
	        }

	        String insertQuery = "INSERT INTO appointment (doctor_id, patient_id, Appointment_Date, time) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(insertQuery);
	        ps.setString(1, doctor_id);
	        ps.setString(2, patient_id);
	        ps.setString(3, date);
	        ps.setInt(4, time);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Appointment booked at time: " + time);
	        } else {
	            System.out.println("Failed to book appointment.");
	        }

	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}
}
