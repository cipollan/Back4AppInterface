package model;
public class User {
	int Id;
	String name;
	String lastName;
	int age;
	String phone;
	public synchronized int getId() {
		return Id;
	}
	public synchronized void setId(int i) {
		Id = i;
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized String getLastName() {
		return lastName;
	}
	public synchronized void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public synchronized int getAge() {
		return age;
	}
	public synchronized void setAge(int i) {
		this.age = i;
	}
	public synchronized String getPhone() {
		return phone;
	}
	public synchronized void setPhone(String string) {
		this.phone = string;
	}
	 

}
