package com.jdbc;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EmployeeMain {
	static char ch;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		EmployeeService es = new EmployeeService();

		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter your Choice");
			System.out.println("1: Add Employee");
			System.out.println("2: Upadate Employee");
			System.out.println("3: View Employee");
			System.out.println("4: View All Employees");
			System.out.println("5: Delete Employee");
			System.out.println("6: Stastics");
			System.out.println("7: import");
			System.out.println("8: export");
			System.out.println("9: Exit");
			System.out.println("\n");

			switch (sc.nextInt()) {
			case 1:
				es.add();
				break;
			case 2:
				System.out.println("Please enter id");
				int id = sc.nextInt();
				System.out.println("Enter Designation");
            	String designation= sc.next();
				es.update(new Employee(id,designation));
				break;
			case 3:
				System.out.println("Please enter id");
				int key = sc.nextInt();
				Employee emp=es.getEmployee(key);
				/*if (trm.containsKey(key)) {
					Object value = trm.get(key);
					System.out.println("Key : " + key + " value :" + value);
				} else {
					System.out.println("Employee id is not found");
				}*/
				System.out.println(emp);
				break;
			case 4:
			 TreeMap<Integer,Employee> tr=es.getEmployee();
				if(tr!=null) {
				for (Map.Entry<Integer, Employee> entry : tr.entrySet()) {
					System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
				}
				}
				else
				{
					System.out.println("Employee list is empty");
				}
			// System.out.println(emp1);
				
				break;
			case 5:
				System.out.println("Please enter id");
				int key1 = sc.nextInt();
				es.delete(key1);
				break;
			case 6:
				es.statistics();
				break;
			case 7:

				Callable<Boolean> importsThreads = new Callable<Boolean>() {
					public Boolean call() throws Exception {
						System.out.println(Thread.currentThread() + "Waiting for 5s");
						Thread.sleep(5000);
						es.imports();
						return null;
					}

				};
				ExecutorService executors = Executors.newFixedThreadPool(3);
				@SuppressWarnings("unused")
				Future<Boolean> importfutures = executors.submit(importsThreads);
				System.out.println(Thread.currentThread().getName() + "Export process triggered");
				break;
			case 8:
				Callable<Boolean> importsThreads1 = new Callable<Boolean>() {
					public Boolean call() throws Exception {
						System.out.println(Thread.currentThread() + "Waiting for 5s");
						Thread.sleep(5000);
						es.export();
						return null;
					}

				};
				ExecutorService executors1 = Executors.newFixedThreadPool(3);
				@SuppressWarnings("unused")
				Future<Boolean> importfutures1 = executors1.submit(importsThreads1);
				System.out.println(Thread.currentThread().getName() + "Import process triggered");
				break;
			case 9:
				System.exit(0);

			}
			System.out.println("Do you want to continue Y/N:");
			ch = sc.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
	}

}
