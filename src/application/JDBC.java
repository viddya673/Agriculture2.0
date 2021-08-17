package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	private static final String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/farm";
			private static final String USERNAME = "root";
	private static final String PASSWORD = "karmal11";
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	protected static String ID = null;
	private static  String ID2 ;
	protected static String Name = null;
	protected static String crop = null;
	private static  String  Administrators = String.valueOf(110); 
	protected static String Password = null; 

	  
    public static Connection getConnection() {
    	   Connection conn = threadLocal.get();
    	   Statement stmt = null;
    	   try{
    	     
    	      Class.forName("com.mysql.cj.jdbc.Driver");

    	      System.out.println("Connecting to a database...");
    	      conn = DriverManager.getConnection(URL , USERNAME, PASSWORD);
    	      System.out.println("Connected database successfully...");
    	      
    	      
    	      System.out.println("Creating table in given database...");
    	      stmt = conn.createStatement();
    	      
    	     /* String sql = "CREATE TABLE farmer1 " +
    	                   "(ID INTEGER not NULL, " +
    	                   " NAME VARCHAR(255), " + 
    	                   " CROP VARCHAR(255), " + 
    	                   " PASSWORD INTEGER, " + 
    	                   " PRIMARY KEY ( id ))"; 

    	      stmt.executeUpdate(sql);
    	      System.out.println("Created table in given database...");*/
    	   }catch(SQLException se){
    
    	      se.printStackTrace();
    	   }catch(Exception e){
    	      
    	      e.printStackTrace();
    	   }finally{
    	     
    	     /* try{
    	         if(stmt!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	      }
    	      try{
    	         if(conn!=null)
    	            conn.close();
    	      }catch(SQLException se){
    	         se.printStackTrace();
    	      }*/
    	   }
    	   System.out.println("Goodbye!");
    	   return conn;
    	}
		
	
   
      
     /* public static Connection getConnection() {

		 Connection conn = threadLocal.get();
		   if (conn == null) {
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

				threadLocal.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("database connection successful "); 
		return conn;
	}*/

	 
	  public static boolean closeConnection() {
		boolean isClosed = true;
		Connection conn = threadLocal.get();
		threadLocal.set(null);
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				isClosed = false;
				e.printStackTrace();
			}
		}
		System.out.println("database closed successfully "); 
		return isClosed;
	}
	
	 
	 
	  public static int selectUserNumber(String selectUserNumber) {
		 Connection conn = getConnection(); 
	     int id = 0; 
	     ID2 = selectUserNumber;
	     try {
	          Statement  statment = conn.createStatement(); 
	          String sql = "select id from farmer where id  = '" + selectUserNumber + "'"; 
	          ResultSet rest = statment.executeQuery(sql); // 
	          System.out.println("the id is" +selectUserNumber + id );
	          while (rest.next()){
	            id = rest.getInt(1); 
	            System.out.println("database query returns result " + id);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return id; 
	    }
	  
	  
	  public static int selectPassword(String selectUserNumber,String Password) {
	     Connection conn = getConnection(); 
		  int id = 0; 
		   try {
		       Statement statment = conn.createStatement(); 
		       String sql = "select password from farmer where password = '" + Password + "' and id  = '" + selectUserNumber + "'"; 
		       ResultSet rest = statment.executeQuery(sql);
		       System.out.println("the database query " +Password + id);
		       while (rest.next()){
		            id = rest.getInt(1); 
		            System.out.println("Database query returns results: " + id);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return id; 
		    }
	  

	  public static void select(String selectUserNumber) {
	      Connection conn = getConnection(); 
	      ID2 = selectUserNumber;
		   try {
		       Statement statment = conn.createStatement(); 
		       String sql = "select * from farmer where id  = '" + selectUserNumber + "'"; 
		       ResultSet rest = statment.executeQuery(sql); 
		       while (rest.next()){
		            ID = rest.getString(1); 
		            Name = rest.getString(2);
		            crop = rest.getString(3);
		            Password = rest.getString(4);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    } 
	  	  
	  
	  public static int InsertUserNumber1(String InsertuserID,String InsertPassword) {
		   @SuppressWarnings("unused")
		Connection conn = getConnection(); 
		   if(selectUserNumber(InsertuserID)!=0){
		    	System.out.println("User already exists");
		        return 0 ; 
		    }
		    else{
	            return 1;
		    }
	  } 
	  
	 
	  public static int InsertUserNumber(String InsertuserID,String InsertUserNumber,String InsertUsercrop,String InsertPassword) {
		   Connection conn = getConnection(); 
		   if(selectUserNumber(InsertUserNumber)!=0){
		    	System.out.println("The user already exists");
		     return 0 ; 
		    }
		    else{
	            try {
	            PreparedStatement statement = conn
	                    .prepareStatement("insert into farmer values('" + InsertuserID + "','" + InsertUserNumber + "','" + InsertUsercrop + "','" + InsertPassword + "')");
	            statement.executeUpdate(); 
	            System.out.println("registration successful");
	            }catch (SQLException e) {
	            e.printStackTrace();
	            }
	        return 1;
		    }
	  }
    
	  	  public static int UpdateUserNumber(String updateUserID,String updateUserNumber, String updateUsercrop, String updateUserPassword) {
		Connection conn = getConnection(); 
		if(updateUserID.equals(ID2) == true){
            try {
            	PreparedStatement statement1 = conn
      	                  .prepareStatement("update farmer set name = '" + updateUserNumber + "' where id = '" + updateUserID + "'");
            	PreparedStatement statement2 = conn
              	          .prepareStatement("update farmer set crop  = '" + updateUsercrop + "' where id = '" + updateUserID + "'");
              	PreparedStatement statement3 = conn
            	          .prepareStatement("update farmer set password  = '" + updateUserPassword + "' where id = '" + updateUserID + "'");
              	statement1.executeUpdate();
              	statement2.executeUpdate();
            	statement3.executeUpdate(); 
	            System.out.println("update sucessful");
	            }catch (SQLException e) {
	            e.printStackTrace();
	            }
            return 1;
		}
		else
			return 0;

	}
	  
	  
	  public static int DeleteUserNumber(String DeleteuserID) {
		   Connection conn = getConnection(); 
			//if(Administrators.equals(ID2) == true){
	            try {
	            	PreparedStatement statement1 = conn
	      	                  .prepareStatement("delete from farmer where id = '" + DeleteuserID + "'"); 
	              	statement1.executeUpdate(); 
		            System.out.println("delete successful");
		            }catch (SQLException e) {
		            e.printStackTrace();
		            }
	            return 1;
			}
//			else
	//			return 0;

		//}
	  
      
	  public static int SelectUser() {
		   Connection conn = getConnection();
				try {
				       Statement statment = conn.createStatement(); 
				       String sql = "select * from farmer ";  
				       ResultSet rest = statment.executeQuery(sql); 
				       while (rest.next()){
				            ID = rest.getString(1); 				            Name = rest.getString(2);
				            crop = rest.getString(3);
				            Password = rest.getString(4);
				            System.out.println("farmer_id" + ID + "   name" + Name + "        crop" + crop + "        password" + Password);
				            }
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
	            return 1;
			
		}
	  
	  
	   
	        
	       
	    
	  
	   //**********************************************************************  
}
