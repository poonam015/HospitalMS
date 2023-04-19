package com.HospitalMS;

//controller which is communicating with db

import java.sql.*;
public class HospitalPa {

	
	Connection con=null;
	
	//method to get connection to db
	public void dbconnection()throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalMS","root","Poonu@15");
		
	}
	
	
	//method to register customer to db
	public int registerpatients(Patients p1)throws Exception {
		
		String query="insert into Patients values(?,?,?,?,?,?)";
		
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,p1.PID);
		pst.setString(2,p1.PName);
		pst.setString(3, p1.PDisease);
		pst.setString(4, p1.PGender);
		pst.setString(5, p1.PAdmitStatus);
		pst.setInt(6, p1.PAge);
		
		int res=pst.executeUpdate();
		return res;
	}
	
public int login(String pname,int pwd)throws Exception {
		
		//fetching the user details based on Patientname
		String query="select * from Patients where PName= '"+pname+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		//checking whether we user details or not
		if(rs.next()) {
			//fetching the password from db
			int password=rs.getInt(6);
			
			//matching the password
			if(password==pwd) {
				//login success
				return rs.getInt(1);
			}
			else {
				//bad password
				return 0;
			}
		}
		else {
			//unable to fetch user details
			return -1;
		}
	}

public int changedise(String currentdise,String newdise,int pdisease)throws Exception{
	//fetching Patients details
	String 	query2="select  * from Patients where PID="+pdisease;
	
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(query2);
	rs.next();
	System.out.println(rs.getString(3));
	//confirming existing Disease
	if(currentdise.equals(rs.getString(3))) {
		//update new Disease in database
		String query="update Patients set PDisease='"+newdise+"' where PID="+pdisease;
		
			PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
			return 1;
	}
	else {
		return 0;
	}
}


public int changeadmits(String currenadmits,String newadmits,int padmitstatus)throws Exception{
	//fetching Patients details
	String 	query2="select  * from Patients where PID="+padmitstatus;
	
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(query2);
	rs.next();
	System.out.println(rs.getString(5));
	//confirming existing Disease
	if(currenadmits.equals(rs.getString(5))) {
		//update new Disease in database
		String query="update Patients set PAdmitStatus='"+newadmits+"' where PID="+padmitstatus;
		
			PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
			return 1;
	}
	else {
		return 0;
	}
}

public int changepwd(int currentpwd,int newpwd, int PID)throws Exception{
	//fetching user details
	String 	query2="select  * from Patients where PID="+PID;
	
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(query2);
	rs.next();
	//confirming existing password
	if(currentpwd==rs.getInt(6)) {
		//update new pwd in db
		String query="update Patients set PAge="+newpwd+" where PID="+PID;
		
			PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
			return 1;
	}
	else {
		return 0;
	}
}

public int DeleteAccount(int pwd, int PID)throws Exception{
	//fetching user details
	String 	query2="select  * from Patients where PID="+PID;
	
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(query2);
	rs.next();
	//confirming pwd
	if(pwd==rs.getInt(6)) {
	
		//delete the account
		String query="delete from Patients where PID="+PID;
		
		PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
		return 1;
	}
	else {
		return 0;
	}
}
	
}
