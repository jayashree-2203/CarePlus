package careplus;

import java.util.*;
public class Patient {
	private String name;
	private String id;
	private Integer age;
	private Long phone_number;
	ArrayList<Appointment> appointments=new ArrayList();
	Patient(String name,String id,int age,long ph_no)
	{
		this.name=name;
		this.id=id;
		this.age=age;
		this.phone_number=ph_no;
	}
	public String getName()
	{
		return this.name;
	}
	public int getAge()
	{
		return this.age;
	}
	public long getPhoneNumber()
	{
		return this.phone_number;
	}
	public String getId()
	{
		return this.id;
	}
	public String toString()
	{
		return "Patient id :"+this.id+
				"\nName: "+this.name+
				"\nAge: "+this.age+
				"\nPhone number "+this.phone_number;
	}
}
