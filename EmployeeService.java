package com.jdbc;
import com.jdbc.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class EmployeeService implements EmployeeDao{

	Connection conn = DB_Connection.get_connection();
	TreeMap<Integer, Employee> tr = new TreeMap();
	Scanner sc = new Scanner(System.in);
	Employee emp=new Employee();
	@Override
	public void add() {
		try {
		System.out.println("Enter Name");
		String name = sc.next();
		boolean flag = false;
		String name1 = "[a-zA-Z ]+";
		while (!flag) {
			if ((name.matches(name1))) {

				flag = true;

			} else {
				System.out.println("enter only alphabets");
				name = sc.next();
			}
		}
       
		System.out.println("Enter Designation");
		String designation = sc.next();
		boolean flag1 = false;
		String name2 = "[a-zA-Z]+";
		while (!flag1) {
			if ((designation.matches(name2))) {

				flag1 = true;

			} else {
				System.out.println("enter only alphabets");
				designation = sc.next();
			}
		}
		System.out.println("Enter Age");
		String age=sc.next();
		boolean flag4 = false;
		String name4 = "[0-9]+";
		while (!flag4) {
			if ((age.matches(name4))) {

				flag4 = true;

			} else {
				System.out.println("enter only numerics");
				age=sc.next() ;
			}
		}
		int age1 = Integer.parseInt(age);
		System.out.println("Enter Salary");
		String sal=sc.next();
		boolean flag5 = false;
		String name5 = "[0-9]+";
		while (!flag5) {
			if ((sal.matches(name5))) {

				flag5 = true;

			} else {
				System.out.println("enter only numerics");
				sal=sc.next() ;
			}
		}
		double salary = Double.parseDouble(sal);
		System.out.println("Enter Department");
		String department = sc.next();
		boolean flag2 = false;
		String name3 = "[a-zA-Z]+";
		while (!flag2) {
			if ((department.matches(name3))) {

				flag2 = true;

			} else {
				System.out.println("enter only alphabets");
				department = sc.next();
			}
		}
		emp = new Employee(emp.getId(), name,age1, salary,designation,department);
		tr.put(emp.getId(), emp);
		PreparedStatement pstm = conn.prepareStatement("insert into employee(name,age,salary,designation,department) values(?,?,?,?,?)");
		pstm.setString(1, emp.getName());
		pstm.setInt(2, emp.getAge());
		pstm.setDouble(3, emp.getSalary());
		pstm.setString(4, emp.getDesignation());
		pstm.setString(5, emp.getDepartment());
		int i = pstm.executeUpdate();
        System.out.println(i + " Data Inserted Successfully");
        Instant currentTimeIns = Instant.now();
		LocalDate ld = LocalDate.now();
		System.out.println("Enter DOB");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String date = sc.next();
		LocalDate date1 = LocalDate.parse(date, format);
		Period p = Period.between(date1, ld);
		int localdate = p.getYears();
		System.out.println(localdate);
		}
		catch(Exception e) {
        }
	}

	@Override
	public void update(Employee emp) {
		
		try {
            if (emp.getId() != 0) {
            	
                PreparedStatement pstm = conn.prepareStatement("update employee set designation=? where id=?");
                pstm.setString(1, emp.getDesignation());
                pstm.setInt(2, emp.getId());
                int i = pstm.executeUpdate();
                System.out.println(i + " Data Updated Successfully");
            }
        } catch (Exception e) {
        }	
	}

	@Override
	public void delete(int id) {
		try {
            if (id!= 0) {
                PreparedStatement pstm = conn.prepareStatement("delete from employee where id=?");
                pstm.setInt(1, id);
                 int i=pstm.executeUpdate();
                System.out.println(i+" Data Deleted Successfully");
            }
        } catch (Exception e) {
        }
		if (tr.containsKey(id)) {
			tr.remove(id);
		} 
    }
		
	

	@Override
	public Employee getEmployee(int id) {
		Employee emp = null;
        try {
            PreparedStatement pstm = conn.prepareStatement("select * from employee where id=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                
                emp = new Employee();
                emp.setId(rs.getInt(1));
                emp.setName(rs.getString(2));
                emp.setAge(rs.getInt(3));
                emp.setSalary(rs.getDouble(4));
                emp.setDesignation(rs.getString(5));
                emp.setDepartment(rs.getString(6));
                tr.put(emp.getId(),emp);
                
            }

        } catch (Exception e) {
        }
		return emp;
	}

	@Override
	public TreeMap<Integer,Employee> getEmployee() {
		Employee emp= null;
        try {
            PreparedStatement pstm = conn.prepareStatement("select * from employee");
                 ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                
                emp = new Employee();
                emp.setId(rs.getInt(1));
                emp.setName(rs.getString(2));
                emp.setAge(rs.getInt(3));
                emp.setSalary(rs.getDouble(4));
                emp.setDesignation(rs.getString(5));
                emp.setDepartment(rs.getString(6));
                tr.put(emp.getId(),emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       
		return tr;
		
	}

	@Override
	public void statistics() {
		long count = tr.values().stream().filter(map -> emp.getAge() > 30).count();

		List<Integer> count1 = tr.values().stream().filter(map -> emp.getAge() > 30).map(emp -> emp.getId())
				.collect(Collectors.toList());
		System.out.println("No of Employees having age>30");
		System.out.println(count);
		System.out.println("\n");
		System.out.println("Display the employees age>30");
		System.out.println(count1);
		System.out.println("\n");
		/*
		 * long count2 = tr.values().stream().filter(map ->
		 * emp.getDepartment().equals()).count();
		 * System.out.println("count ofemployees for particular department");
		 * System.out.println(count2);
		 */

		Map<String, Long> count3 = tr.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("count of employees by department wise");
		System.out.println(count3);
		System.out.println("\n");
		double avgSalary = tr.values().stream().collect(Collectors.averagingDouble(Employee::getSalary)).doubleValue();
		System.out.println("average salary of employees");
		System.out.println(avgSalary);
		System.out.println("\n");
		Map<String, Double> avgAge = tr.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt((Employee::getAge))));
		System.out.println(avgAge);
		System.out.println("\n");


		Map<String, Long> order = tr.values().stream().sorted((p1, p2) -> p1.department.compareTo(p2.department))
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("sorted based on department ");
		System.out.println(order);
		System.out.println("\n");
		List<String> totalEmp = tr.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).entrySet().stream()
				.filter(value -> value.getValue() > 2).map(Map.Entry::getKey).collect(Collectors.toList());
		System.out.println("print based on no of employees >2 in department");
		System.out.println(totalEmp);
		System.out.println("\n");
		List<Employee> emp = tr.values().parallelStream().filter(e -> e.getName().startsWith("p"))
				.collect(Collectors.toList());

		System.out.println("Employee name starts with p");
		System.out.println(emp);
		System.out.println("\n");
		
	}

	@Override
	public synchronized void imports() throws SQLException {

		String file = "C:\\Users\\jibm07\\Desktop\\Employee.txt";

		try {
			Employee emp = new Employee();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;

			while ((line = br.readLine()) != null) {
				String[] array = line.split(",");
				// int id=Integer.parseInt(array[0]);
				String name = array[1];
				String designation = array[2];
				double salary = Double.parseDouble(array[3]);
				int age = Integer.parseInt(array[4]);
				String department = array[5];
				
				emp = new Employee(emp.getId(), name,age, salary, designation,department);
				tr.put(emp.getId(), emp);
				PreparedStatement pstm = conn.prepareStatement("insert into employee(name,age,salary,designation,department) values(?,?,?,?,?)");
				pstm.setString(1, emp.getName());
				pstm.setInt(2, emp.getAge());
				pstm.setDouble(3, emp.getSalary());
				pstm.setString(4, emp.getDesignation());
				pstm.setString(5, emp.getDepartment());
				int i = pstm.executeUpdate();
		       // System.out.println(i + " Data Inserted Successfully");

			}
			System.out.println("imported successfully");
			br.close();

		} catch (IOException e) {
			System.out.println("ERROR: unable to read file " + file);
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void export() throws IOException {
		BufferedWriter bw = null;
		String str = "";
		try {

			File file = new File("C:\\\\Users\\\\jibm07\\\\Desktop\\\\Employee2.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);

			bw = new BufferedWriter(fw);

			for (Map.Entry<Integer, Employee> entry : tr.entrySet()) {
				str = "Key: " + entry.getKey() + ". Value: " + entry.getValue();
				bw.write(str);
				bw.newLine();
				bw.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("File written Successfully");
	}

}
