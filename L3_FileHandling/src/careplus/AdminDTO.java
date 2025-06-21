package careplus;

import java.util.*;
public class AdminDTO {
	Scanner sc=new Scanner(System.in);
	Details_Management object=Details_Management.getObject();
	public void admin()
	{
		while(true)
		{
			System.out.println("1.Add Doctor\n2.Manage Recetionist\n3.Logout");
			switch(sc.nextInt())
			{
			case 1:
				addDoctor();
				break;
			case 2:
				System.out.println("1.Add Receptionist\n2.Remove receptionist access");
				switch(sc.nextInt())
				{
				case 1:
					System.out.println("Enter Username");
					sc.nextLine();
					String name=sc.nextLine();
					System.out.println("Enter Password");
					String password=sc.nextLine();
					object.user.put(Details_Management.id+1,
								new User("receptionist"+Details_Management.id,name,password,"receptionist"));
					break;
				case 2:
					System.out.println("Enter UserId to remove access");
					int id=sc.nextInt();
					try
					{
						object.user.remove(id);
					}
					catch(Exception e)
					{
						System.out.println("Enter valid id");
					}
					break;
				}
				break;
			case 3:
				System.out.println("Thankyou!!");
				return;
			default:
				System.out.println("Enter valid option");
				break;
			}
		}
	}
	public void addDoctor()
	{
		System.out.println("Enter name");
		sc.nextLine();
		String name=sc.nextLine();
		System.out.println("Enter Mobile number");
		long number=sc.nextLong();
		System.out.println("Enter Specialisation");
		String specialisation=sc.next();
		System.out.println("Enter available time in 24 hours format\nFrom: ");
		int start=sc.nextInt();
		System.out.println("To: ");
		int to=sc.nextInt();
		int doc_id=++object.doctor_id;
		object.doctor.put("doc"+(doc_id),new Doctor("doc"+doc_id,name,number,specialisation,start,to));
		System.out.println("Added Successfully");
	}
}
