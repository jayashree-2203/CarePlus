package careplus.features;

import java.util.*;

import careplus.DB.*;
public class AdminView {
	Scanner sc=new Scanner(System.in);
	Doctor_DB doc_obj=Doctor_DB.getObject();
	User_DB user_obj=User_DB.getObject();
	public void admin()
	{
		while(true)
		{
			System.out.println("1.Add Doctor\n2.Manage Recetionist\n"
					+ "3.Remove Doctor\n4.Logout");
			switch(sc.nextInt())
			{
			case 1:
				System.out.println("Enter name");
				sc.nextLine();
				String name=sc.nextLine();
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
				System.out.println("Enter Specialisation");
				String specialisation=sc.next();
				System.out.println("Enter available time in 24 hours format\nFrom: ");
				int start=sc.nextInt();
				System.out.println("To: ");
				int end=sc.nextInt();
				doc_obj.addDoctor("Doc"+ (++DataBase.doctor_id),name,number,specialisation,start,end);
				System.out.println("Doctor added successfully and id is doc"+DataBase.doctor_id);
				break;
			case 2:
				System.out.println("1.Add Receptionist\n2.Remove receptionist access");
				switch(sc.nextInt())
				{
				case 1:
					System.out.println("Enter Username");
					sc.nextLine();
					String name_recep=sc.nextLine();
					System.out.println("Enter Password");
					String password=sc.nextLine();
					user_obj.addUser(name_recep,password,"receptionist");
					break;
				case 2:
					System.out.println("Enter UserId to remove access");
					int id=sc.nextInt();
					if(user_obj.removeUser(id))
					{
						System.out.println("Removed Scuccessfully");
					}
					else
					{
						System.out.println("Enter valid id");
					}
					break;
				}
				break;
			case 3:
				System.out.println("Enter doctor id to remove");
				if(doc_obj.removeDoctor(sc.next()))
				{
					System.out.println("Removed Scuccessfully");
				}
				else
				{
					System.out.println("Enter valid id");
				}
				break;
			case 4:
				System.out.println("Thankyou!!");
				return;
			default:
				System.out.println("Enter valid option");
				break;
			}
		}
	}
}
