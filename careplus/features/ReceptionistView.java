package careplus.features;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.*;
import careplus.DB.*;

public class ReceptionistView {
	Scanner sc=new Scanner(System.in);
	ReceptionistModel rcp_obj=new ReceptionistModel();
	Patient_DB pat_obj=Patient_DB.getObject();
	Appointment_DB ap_obj=Appointment_DB.getObject();
	public void receptionist()
	{
		while(true)
		{
			System.out.println("Enter\n1.Add Patient\n2.Search Patient\n3.BookAppointment\n"
					+ "4.view appointments for specific patients\n"
					+ "5.view appointments for Specific doctor\n"
					+ "6.view appointments for all doctor\n7.Remove Patient\n8.Logout");
			switch(sc.nextInt())
			{
			case 1:
				System.out.println("Enter name");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println("Enter age");
				int age=sc.nextInt();
				long number;
				while(true)
				{
					System.out.println("Enter Mobile number");
					number=sc.nextLong();
					if(number>599999999 && number<10000000000L)
					{
						break;
					}
					else
					{
						System.out.println("Enter valid number");
					}
				}
				String id=new Patient_DB().addPatient("pat"+(++DataBase.patient_id+1),name,age,number);
				System.out.println("Patient added successfully: "+id);
				break;
			case 2:
				System.out.println("Enter Patient name to search");
				sc.nextLine();
				String search_name=sc.nextLine();
				List<String> ans=new Patient_DB().searchPatient(search_name);
				for(int i=0;i<ans.size();i++)
				{
					System.out.println(ans.get(i));
				}
				if(ans.size()==0)
					System.out.println("No records found");
				break;
			case 3:
				System.out.println("Enter name");
				sc.nextLine();
				String name_app=sc.nextLine();
				System.out.println("Enter age");
				int age_app=sc.nextInt();
				long ph_number;
				while(true)
				{
					System.out.println("Enter Mobile number");
					ph_number=sc.nextLong();
					if(ph_number>599999999 && ph_number<10000000000L)
					{
						break;
					}
					else
					{
						System.out.println("Enter valid number");
					}
				}
				String patient_id=rcp_obj.bookAppointment(name_app,age_app,ph_number);
				printDoctor();
				System.out.println("Enter Doctor_id");    
			    String doctor_id = sc.next();
			    String date;
			    while(true)
			    {
			    	System.out.println("Enter date yyyy-MM-DD"); 
				    date=sc.next();
			    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(date);

			        try 
			        {
			            LocalDate.parse(date, formatter);
			            break;
			        } 
			        catch (Exception e) 
			        {
			             System.out.println("Enter valid date");
			        }
			    }
			    rcp_obj.bookSlot(patient_id, doctor_id,date);
				break;
			case 4:
				System.out.println("Enter Patient_id");
				String Patient_id=sc.next();
				ap_obj.viewPatientAppointment(Patient_id);
				break;
			case 5:
				System.out.println("Enter Doctor_id");
				ap_obj.viewSpecificDoctorAppointment(sc.next());
				break;
			case 6:
				ap_obj.viewAllDoctorAppointment();
				break;
			case 7:
				System.out.println("Enter patient to remove");
				if(pat_obj.removePatient(sc.next()))
				{
					System.out.println("Removed Scuccessfully");
				}
				else
				{
					System.out.println("Enter valid id");
				}
				break;
			case 8:
				System.out.println("ThankYou!!");
				return;
			default:
				System.out.println("Enter valid option");
			}
		}
	}
	public void printDoctor()
	{
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Careplus", "root", "#Js@2203");
			Statement s = con.createStatement();
			String query = "SELECT * FROM doctor";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) 
            {
            	if(rs.getString(7).equals("Active"))
            	{
            		String id=rs.getString("id");
            		String name = rs.getString("Name");
            		String specialisation = rs.getString("Specialisation");
            		System.out.println("Id: "+id+"\nName: "+name+"\nSpecialisation"+specialisation);
            	}
            }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}