package com.jdbc;

import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
	private static AtomicInteger idGenerator = new AtomicInteger(1);
private int id;
private String name;
private int age;
private double salary;
private String designation;
String department;
public Employee(int id, String name,  int age, double salary, String designation,String department) {
	this.id=idGenerator.getAndIncrement();
	this.name=name;
	this.age=age;
	this.salary=salary;
	this.designation=designation;
	this.department=department;
}
public Employee(int id,String designation) {
	this.id=id;
	this.designation=designation;
}

@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + ", designation="
			+ designation + ", department=" + department + "]";
}
public Employee()
{
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}

}
