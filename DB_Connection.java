package com.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
public class DB_Connection {
public static void main(String[] args) {
  
  System.out.println(DB_Connection.get_connection());
}
public static Connection get_connection(){
 Connection connection=null;
try {
  Class.forName("com.mysql.jdbc.Driver");              
  connection = DriverManager.getConnection("jdbc:mysql://localhost/assessment","root", "pass@word1");
} catch (Exception e) {
  System.out.println(e);
}
  return connection;
}
}