package careplus;

import java.io.IOException;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		Details_Management obj=new Details_Management();
		obj.addUser("Ram","12345","Admin");
		obj.addUser("Shiva","12345","Receptionist");
		obj.addUser("Priya","12345","Receptionist");
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
				obj.loginUser(id,password);
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