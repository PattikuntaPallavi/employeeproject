package com.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeMap;

public interface EmployeeDao {

	public void add();
	 public void update(Employee emp);
	 public void delete(int id);
	 public Employee getEmployee(int id);
	 public TreeMap<Integer,Employee> getEmployee();
	 public void statistics();
	 public void imports() throws IOException, SQLException;
	 public void export() throws IOException;
}
