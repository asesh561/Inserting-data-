package com.abc.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertApp 
{
     public static void main(String[] args) 
     {
    	 Connection connection = null;
 		PreparedStatement pstmt =null;
 		ResultSet resultset = null;
 		Scanner scan ;
 		String url="jdbc:mysql:///abc";
 		String username="root";
 		String password="root123";
 		try
		{
		connection=	DriverManager.getConnection(url,username,password);
			if(connection!=null)
			{
				String SqlQuery="insert into employee(eid,name,address,salary) values(?,?,?,?)";
				pstmt=connection.prepareStatement(SqlQuery);
				if(pstmt!=null)
				{
					scan = new Scanner(System.in);
					System.out.println("Enter the ID");
					int eid= scan.nextInt();
					System.out.println("Enter the name");
					String name= scan.next();
					System.out.println("Enter the address");
					String address= scan.next();
					System.out.println("Enter the salary");
					int sal=scan.nextInt();
					pstmt.setInt(1, eid);
					pstmt.setString(2, name);
					pstmt.setString(3, address);
					pstmt.setInt(4, sal);
					int rowCount = pstmt.executeUpdate();
					if(rowCount!=0)
					{
						System.out.println("No of record inserted"+rowCount);
					}
				}
			}
			
     }
 		catch(SQLException e)
		{
			if(e.getErrorCode()==1406)
			{
				System.out.println("Data too log for column");
			}
			else if(e.getErrorCode()==1062)
			{
				System.out.println("Duplicate primary key value");
			}
			else if(e.getErrorCode()==1136)
			{
				System.out.println("Insufficient values specified");
			}
			else
			{
				System.out.println("Some sql exception");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(resultset!=null)
			{
				try
				{
				resultset.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
				if(pstmt!=null)
				{
					try
					{
					pstmt.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
		}
	}
}
