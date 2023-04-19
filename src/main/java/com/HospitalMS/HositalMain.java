package com.HospitalMS;

import java.util.*;

public class HositalMain {

	public static void main(String[] args) throws Exception {
		
		Scanner ps=new Scanner(System.in);
		HospitalPa pa=new HospitalPa();
		Patients p1=new Patients();
		
		System.out.println("\t\t\t********Welcome to JDBC Hospital***********");
		
		System.out.println("Press 1 for Add New Patients \nPress 2 for Login");
		int op=ps.nextInt();
		
		switch(op) {
		
		case 1-> {
			System.out.println("Enter Patients Id");
			int pid=ps.nextInt();
			ps.nextLine();
			System.out.println("Enter Patients Name");
			String pname=ps.nextLine();
			System.out.println("Enter Patients Disease");
			String pdisease=ps.nextLine();
			System.out.println("Enter Patients Gender");
			String pgender=ps.nextLine();
			System.out.println("Enter Patients Admit Status");
			String padmitstatus=ps.nextLine();
			System.out.println("Enter Patients Age");
			int page=ps.nextInt();

			p1.PID=pid;
			p1.PName=pname;
			p1.PDisease=pdisease;
			p1.PGender=pgender;
			p1.PAdmitStatus=padmitstatus;
			p1.PAge=page;
			
			//getting connection to db
			pa.dbconnection();
			//inserting user details into db
			int res=pa.registerpatients(p1);
			
			//if insertion is success response is 1 otherwise 0
			if(res==1) {
				System.out.println("Add Patients details successfully");
			}
			else {
				System.out.println("Something went wrong");
			}
		}

		case 2-> {
			System.out.println("Welcome to Login Page");
			//reading patients name and password for login
			System.out.println("Enter Patient Name");
			ps.nextLine();
			String pname=ps.nextLine();
			System.out.println("Enter Password");
			int pwd=ps.nextInt();
			
			//connecting to db
			pa.dbconnection();
			
			//login method
			int res=pa.login(pname, pwd);
			//handling the response
			if(res==0) {
				System.out.println("username/password are incorrect");
			}
			else if(res==-1) {
				System.out.println("Unable to find the details");
			}
			else {
				System.out.println("Login successful");
				System.out.println("Press 1 for Changing Disease\nPress 2 for Changing Admit Status\nPress 3 for change password\nPress 4 for Delete Patients Account");
				int ops=ps.nextInt();
				switch(ops) {
				case 1->{
					System.out.println("Enter Previous Disease");
					ps.nextLine();
					String currentdise=ps.nextLine();
					System.out.println("Enter New Disease");
				
					String newdise=ps.nextLine();
					System.out.println(res);
					int status=pa.changedise(currentdise, newdise, res);
					if(status==1) {
						System.out.println("Disease changed Sucessfully.....");
					}
					else {
						System.out.println("Something went wrong");
					}
				}
				
			case 2->{
				System.out.println("Enter Previous Admit Status");
				ps.nextLine();
				String currentadmits=ps.nextLine();
				System.out.println("Enter New Admit Status");
			
				String newadmits=ps.nextLine();
				System.out.println(res);
				int status=pa.changeadmits(currentadmits, newadmits, res);
				if(status==1) {
					System.out.println("Admit Status changed Sucessfully.....");
				}
				else {
					System.out.println("Something went wrong");
				}
			}
			
			case 3->{
				System.out.println("Enter current password");
				int currentpwd=ps.nextInt();
				System.out.println("Enter new password");
				int newpwd=ps.nextInt();
				
				int status=pa.changepwd(currentpwd, newpwd, res);
				if(status==1) {
					System.out.println("Password changed...");
				}
				else {
					System.out.println("Something went wrong");
				}
				
		}
			
			case 4->{
				System.out.println("Enter password to delete");
				int pass=ps.nextInt();
				int status=pa.DeleteAccount(pass, res);
				if(status==1) {
					System.out.println("Patient account is deleted\n Good Bye!....");
				}
				else {
					System.out.println("Something went wrong");
				}
				
				
				
				
				
		}
		
			
			}
			}
		}
		}
	}
}
