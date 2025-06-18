package careplus;

import java.util.*;
public class Doctor {
	private String id;
	private String name;
	private Long number;
	private String specialisation;
	private Integer start;
	private Integer end;
	private Integer total_hour;
	Map<Date,ArrayList<Appointment>> app_list=new HashMap<Date,ArrayList<Appointment>>();
	Doctor(String id,String name,long number,String specialisation,int start,int end)
	{
		this.id=id;
		this.name=name;
		this.number=number;
		this.specialisation=specialisation;
		this.start=start;
		this.end=end;
		total_hour=end-start;
	}
	public int getEndTime()
	{
		return total_hour;
	}
	public int getStartTime()
	{
		return this.start;
	}
	public String toString()
	{
		return "Doctor id: "+id+
				"\nDoctor Name: "+name+
				"\nSpecialisation: "+specialisation;
	}
}