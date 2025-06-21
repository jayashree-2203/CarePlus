package careplus;

public class User {
	private String id;
	private String name;
	private String password;
	private String role;
	User(String id,String name,String password,String role)
	{
		this.id=id;
		this.name=name;
		this.password=password;
		this.role=role;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getName()
	{
		return this.name;
	}
	public String getRole()
	{
		return this.role;
	}
}
