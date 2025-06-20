package careplus;

import java.util.Scanner;

import careplus.DB.*;

public class Main {

	public static void main(String[] args) {
		//Admin Id:1//Receptionist id:1,2// Password for all:12345
		Scanner sc=new Scanner(System.in);
		DataBase db_obj=DataBase.getObject();
		User_DB user_obj=User_DB.getObject();
//		db_obj.connect();
//		user_obj.addUser("Ram","12345","Admin");
//		user_obj.addUser("Shiva","12345","Receptionist");
//		user_obj.addUser("Priya","12345","Receptionist");
		db_obj.getDoctorId();
		db_obj.getPatientId();
		MainLoop:
		while(true)
		{
			System.out.println("1.Login\n2.Exit");
			switch(sc.nextInt())
			{
			case 1:
				System.out.println("Enter UserId");
				int id=sc.nextInt();
				System.out.println("Enter Password");
				String password=sc.next();
				user_obj.loginUser(id, password);
				break;
			case 2:
				System.out.println("ThankYou!!");
				break MainLoop;
			default:
				System.out.println("Enter valid choice");
				break;
			}
		}
	}
}