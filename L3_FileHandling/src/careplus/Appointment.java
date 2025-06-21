package careplus;

import java.time.LocalDate;
import java.util.*;
public class Appointment {
	private Integer id;
	private String doctor_id;
	private String patient_id;
	private LocalDate date;
	private Integer time;
	Appointment(int id,String doctor_id,String patient_id,LocalDate date2,int time)
	{
		this.id=id;
		this.doctor_id=doctor_id;
		this.patient_id=patient_id;
		this.date=date2;
		this.time=time;
	}
	Appointment(){}
	public String toString()
	{
		return "Appoinment id "+this.id+
				"\nDoctor id: "+this.doctor_id+
				"\nDate "+date+
				"\ntime "+time;
	}
	public String toStringPatient()
	{
		return "\nAppoinment id "+this.id+
				"\nPatient id: "+this.patient_id+
				"\nDate "+date+
				"\ntime "+time;
	}
}
