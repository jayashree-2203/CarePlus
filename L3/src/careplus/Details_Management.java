package careplus;

import java.util.*;
public class Details_Management {
	
	private static Details_Management ins=new Details_Management();
	
	public static Details_Management getObject()
	{
		return ins;
	}
	
	
	Map<Integer,User> user=new HashMap<>(); 
	Map<String,Doctor> doctor=new HashMap<>();
	Map<String,Patient> patient=new HashMap<>();
	static Integer id=0;
	static Integer doctor_id=0,patient_id=0;
	Scanner sc=new Scanner(System.in);
	public void addUser(String name,String password,String role)
	{
		user.put(++id,new User(role+id,name,password,role));
	}
	public void loginUser(int id,String password)
	{
		if(user.containsKey(id))
		{
			if(user.get(id).getPassword().equals(password)) 
			{
				System.out.println("Welcome "+user.get(id).getName()+"!!\nRole: "+user.get(id).getRole());
				if(user.get(id).getRole().equals("Admin"))
				{
					AdminDTO admin_obj=new AdminDTO();
					admin_obj.admin();
				}
				else
				{
					ReceptionistDTO recep_obj=new ReceptionistDTO();
					recep_obj.receptionist();
				}
			}
			else
			{
				System.out.println("Wrong password");
			}
		}
		else
		{
			System.out.println("User not exist");
		}
	}
}
//	public void admin()
//	{
//		while(true)
//		{
//			System.out.println("1.Add Doctor\n2.Manage Existing Doctor details\n3.Manage Recetionist\n4.Logout");
//			switch(sc.nextInt())
//			{
//			case 1:
////				System.out.println(doctor.size());
//				addDoctor();
//				break;
//			case 2:
//				break;
//			case 3:
//				break;
//			case 4:
//				System.out.println("Thankyou!!");
//				return;
//			default:
//				System.out.println("Enter valid option");
//				break;
//			}
//		}
//	}
//	public void addDoctor()
//	{
//		System.out.println("Enter name");
//		sc.nextLine();
//		String name=sc.nextLine();
//		System.out.println("Enter Mobile number");
//		long number=sc.nextLong();
//		System.out.println("Enter Specialisation");
//		String specialisation=sc.next();
//		System.out.println("Enter available time in 24 hours format\nFrom: ");
//		int start=sc.nextInt();
//		System.out.println("To: ");
//		int to=sc.nextInt();
//		doctor.put("doc"+(++doctor_id),new Doctor("doc"+doctor_id,name,number,specialisation,start,to));
//		System.out.println("Added Successfully");
//	}
//	public void receptionist()
//	{
//		while(true)
//		{
//			System.out.println("Enter\n1.Add Patient\n2.Search Patient\n3.BookAppointment\n4.Logout");
//			switch(sc.nextInt())
//			{
//			case 1:
//				System.out.println("Enter name");
//				sc.nextLine();
//				String name=sc.nextLine();
//				System.out.println("Enter age");
//				int age=sc.nextInt();
//				System.out.println("Enter phone number");
//				long ph_no=sc.nextLong();
//				addPatient(name,age,ph_no);
//				break;
//			case 2:
//				searchPatient();
//				break;
//			case 3:
//				bookAppointment();
//				break;
//			case 4:
//				System.out.println("ThankYou!!");
//				return;
//			default:
//				System.out.println("Enter valid option");
//			}
//		}
//	}
//	public void addPatient(String name,int age,long ph_no)
//	{
//		
//		patient.put("pat"+(++patient_id),new Patient(name,"pat"+patient_id,age,ph_no));
//	}
//	public void searchPatient()
//	{
//		System.out.println("Enter Patient name to search");
//		sc.nextLine();
//		String name=sc.nextLine();
//		for(Map.Entry<String,Patient> i:patient.entrySet())
//		{
//			if(i.getValue().getName().equals(name))
//			{
//				System.out.println(i.getValue().toString());
//				System.out.println("\n------------------\n\n");
//			}
//		}
//	}
//	public void bookAppointment()
//	{
//		System.out.println("Enter name");
//		sc.nextLine();
//		String name=sc.nextLine();
//		System.out.println("Enter age");
//		int age=sc.nextInt();
//		System.out.println("Enter phone number");
//		long ph_no=sc.nextLong();
//		for(Map.Entry<String,Patient> i:patient.entrySet())
//		{
//			if(i.getValue().getName().equals(name) && i.getValue().getAge()==age && 
//					i.getValue().getPhoneNumber()==ph_no)
//			{
//				
//			}
//		}
//		
//	}