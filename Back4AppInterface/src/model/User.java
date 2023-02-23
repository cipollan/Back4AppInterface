package model;
public class User {
	public String objectId;
	public String createdAt;
	public String updatedAt;
	
	public synchronized String getUpdatedAt() {
		return updatedAt;
	}
	public synchronized void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public synchronized String getCreatedAt() {
		return createdAt;
	}
	public synchronized void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public synchronized String getObjectId() {
		return objectId;
	}
	public synchronized void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	int Id;
	String name;
	String nome;
	public synchronized String getNome() {
		return nome;
	}
	public synchronized void setNome(String nome) {
		this.nome = nome;
	}
	String lastName;
	int age;
	String phone;
	String tel;
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
	public synchronized String getTel() {
		return tel;
	}
	public synchronized void setTel(String tel) {
		this.tel = tel;
	}
	 

}
