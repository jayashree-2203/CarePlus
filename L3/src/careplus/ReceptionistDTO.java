package careplus;

import java.util.*;
import java.util.Map.Entry;

public class ReceptionistDTO {
	Scanner sc=new Scanner(System.in);
	Details_Management object=Details_Management.getObject();
	SerializeDemo file_object=new SerializeDemo();
	static int id=0;
	public void receptionist()
	{
		while(true)
		{
			System.out.println("Enter\n1.Add Patient\n2.Search Patient\n3.BookAppointment\n"
					+ "4.view appointments for specific patients\n"
					+ "5.view appointments for all doctor\n"
					+ "6.view appointments for specific doctor\n7.Logout");
			switch(sc.nextInt())
			{
			case 1:
				System.out.println("Enter name");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println("Enter age");
				int age=sc.nextInt();
				System.out.println("Enter phone number");
				long ph_no=sc.nextLong();
				addPatient(name,age,ph_no);
				break;
			case 2:
				searchPatient();
				break;
			case 3:
				bookAppointment();
				break;
			case 4:
				viewPatientAppointment();
				break;
			case 5:
				viewDoctorAppointment();
				break;
			case 6:
				viewAllDoctorAppointment();
				break;
			case 7:
				System.out.println("ThankYou!!");
				return;
			default:
				System.out.println("Enter valid option");
			}
		}
	}
	public void addPatient(String name,int age,long ph_no)
	{
		int pat_id=++object.patient_id;
		object.patient.put("pat"+(pat_id),new Patient(name,"pat"+pat_id,age,ph_no));
	}
	public void searchPatient()
	{
		System.out.println("Enter Patient name to search");
		sc.nextLine();
		String name=sc.nextLine();
		for(Map.Entry<String,Patient> i:object.patient.entrySet())
		{
			if(i.getValue().getName().equals(name))
			{
				System.out.println(i.getValue().toString());
				System.out.println("\n------------------\n\n");
			}
		}
	}
	public void bookAppointment()
	{
		System.out.println("Enter name");
		sc.nextLine();
		String name=sc.nextLine();
		System.out.println("Enter age");
		int age=sc.nextInt();
		System.out.println("Enter phone number");
		long ph_no=sc.nextLong();
		for(Map.Entry<String,Patient> i:object.patient.entrySet())
		{
			if(i.getValue().getName().equals(name) && i.getValue().getAge()==age && 
					i.getValue().getPhoneNumber()==ph_no)
			{
				bookSlot(i.getValue().getId());
				return;
			}
		}
		addPatient(name,age,ph_no);
		bookSlot("pat"+Details_Management.patient_id);
	}
	public void bookSlot(String patient_id)
	{
			for(Map.Entry<String,Doctor> i:object.doctor.entrySet())
			{
				System.out.println(i.getValue().toString());
				System.out.println("\n---------------------------\n");
			}
			System.out.println("Enter Doctor_id");	
			String doctor_id=sc.next();
			Doctor temp=object.doctor.get(doctor_id);
			System.out.println(temp);
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			int time;
			if(temp.app_list.containsKey(date) && temp.app_list.get(date).size()<temp.getEndTime())
			{
				time=temp.getStartTime()+temp.app_list.get(date).size();
				System.out.println(time);
				Appointment obj=new Appointment(++id,doctor_id,patient_id,date,time);
				temp.app_list.get(date).add(obj);
				object.patient.get(patient_id).appointments.add(obj);
			}
			else
			{
				time=temp.getStartTime();
				millis = System.currentTimeMillis();
				date = new java.sql.Date(millis+86400000);
				temp.app_list.put(date, new ArrayList<>());
				Appointment obj=new Appointment(++id,doctor_id,patient_id,date,time);
				temp.app_list.get(date).add(obj);
				object.patient.get(patient_id).appointments.add(obj);
			}
//			file_object.writeFile(temp);
			System.out.println("Appointment booked and your appointment id is "+id);
	}
	public void viewPatientAppointment()
	{
		System.out.println("Enter Patient_id");
		String patient_id=sc.next();
		Patient patient_obj=object.patient.get(patient_id);
		for(Appointment i:patient_obj.appointments)
		{
			System.out.println(i.toString());
		}
	}
	public void viewDoctorAppointment()
	{
		System.out.println("Enter Doctor_id");
		for(Map.Entry<String,Doctor> i:object.doctor.entrySet())
		{
			for(Entry<Date, ArrayList<Appointment>> j:i.getValue().app_list.entrySet())
			{
				System.out.println(j.toString());
				System.out.println("\n\n-------------------\n");
			}
		}
	}
	public void viewAllDoctorAppointment()
	{
		System.out.println("Enter Doctor_id");
		String doctor_id=sc.next();
		Doctor doctor_obj=object.doctor.get(doctor_id);
		for(Entry<Date, ArrayList<Appointment>> j:doctor_obj.app_list.entrySet())
		{
			System.out.println(j.toString());
			System.out.println("\n\n-------------------\n");
		}
	}
}