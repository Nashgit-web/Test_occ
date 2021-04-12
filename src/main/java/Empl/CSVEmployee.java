package main.java.Empl;

import com.opencsv.bean.CsvBindByPosition;

public class CSVEmployee {

    @CsvBindByPosition(position = 0)
    private String firstName;
    @CsvBindByPosition(position = 1)
	private String lastName;
    @CsvBindByPosition(position = 2)
	private String age;
    @CsvBindByPosition(position = 3)
	private String address;
    @CsvBindByPosition(position = 4)
	private String salary;
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

    //  getters, setters, toString
}