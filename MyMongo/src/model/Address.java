package model;

public class Address {

	public String State;
	public String Street;
	public int Num;
	public Address(String state, String street, int num) {
		super();
		State = state;
		Street = street;
		Num = num;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public int getNum() {
		return Num;
	}
	public void setNum(int num) {
		Num = num;
	}
	
}
