package careplus.DB;

import careplus.features.*;
import java.sql.*;

public class Appointment_DB {
	private static Appointment_DB ins=new Appointment_DB();
	
	public static Appointment_DB getObject()
	{
		return ins;
	}
	public void viewPatientAppointment(String PatientId)
	{
		try 
		{
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
	        Statement stmt = con.createStatement();

	        String query = "SELECT a.id AS appointment_id, a.appointment_date, a.Time, a.doctor_id, " +
                    "d.name AS doctor_name FROM appointment a " +
                    "JOIN doctor d ON a.doctor_id = d.id " +
                    "WHERE a.patient_id = '" + PatientId  + "'";

	        ResultSet rs = stmt.executeQuery(query);

	        boolean found = false;
	        while (rs.next()) {
	            found = true;
	            System.out.println("Appointment ID: " + rs.getInt("appointment_id"));
	            System.out.println("Doctor Name: " + rs.getString("doctor_name"));
	            System.out.println("Date: " + rs.getDate("Appointment_Date"));
	            System.out.println("Time Slot: " + rs.getInt("Time"));
	            System.out.println("--------------------------------------");
	        }

	        if (!found) {
	            System.out.println("No appointments found for patient ID: " + PatientId);
	        }

	        con.close();
	    } 
		catch (Exception e) 
		{
			System.out.println(e);
	    }
	}
	public void viewSpecificDoctorAppointment(String DoctorId)
	{
		try 
		{
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
	        Statement stmt = con.createStatement();

	        String query = "SELECT a.id AS appointment_id, a.appointment_date, a.Time, a.patient_id, " +
                    "p.name AS patient_name FROM appointment a " +
                    "JOIN patient p ON a.patient_id = p.id " +
                    "WHERE a.doctor_id = '" + DoctorId + "'";

	        ResultSet rs = stmt.executeQuery(query);

	        boolean found = false;
	        while (rs.next()) {
	            found = true;
	            System.out.println("Patient Name: " + rs.getString("Patient_Name"));
	            System.out.println("Appointment ID: " + rs.getInt("appointment_id"));
	            System.out.println("Date: " + rs.getDate("Appointment_Date"));
	            System.out.println("Time Slot: " + rs.getInt("Time"));
	            System.out.println("--------------------------------------");
	        }

	        if (!found) {
	            System.out.println("No appointments found for patient ID: " + DoctorId);
	        }

	        con.close();
	    } 
		catch (Exception e) 
		{
	        System.out.println(e);
	    }
	}
	public void viewAllDoctorAppointment()
	{
		try 
		{
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
	        Statement stmt = con.createStatement();

	        String query = "SELECT a.id AS appointment_id, a.appointment_date, a.Time, a.patient_id, " +
                    "p.name AS patient_name FROM appointment a " +
                    "JOIN patient p ON a.patient_id = p.id";

	        ResultSet rs = stmt.executeQuery(query);

	        boolean found = false;
	        while (rs.next()) {
	            found = true;
	            System.out.println("Patient Name: " + rs.getString("Patient_Name"));
	            System.out.println("Appointment ID: " + rs.getInt("appointment_id"));
	            System.out.println("Date: " + rs.getDate("Appointment_Date"));
	            System.out.println("Time Slot: " + rs.getInt("Time"));
	            System.out.println("--------------------------------------");
	        }

	        if (!found) {
	            System.out.println("No appointments found");
	        }

	        con.close();
	    } 
		catch (Exception e) 
		{
	        System.out.println(e);
	    }
	}
}
