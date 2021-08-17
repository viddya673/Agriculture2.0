package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class Login extends Application {

	 
	
	public void start(Stage primaryStage) {
		
		
        primaryStage.setTitle("Welcome FARMER data ");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    
        Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        
        Label userName = new Label("Farmer ID:");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        
        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
     
        Button btn = new Button("  Sign  in  ");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,4);
        
        
        Button bttn = new Button(" Sign  up ");
        HBox hbBttn = new HBox(10);
        hbBttn.getChildren().add(bttn);
        grid.add(bttn, 1, 4);
        
     
        Button btnn = new Button("    Exit   ");
        HBox hbBtnn = new HBox(10);
        hbBtnn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtnn.getChildren().add(btnn);
        grid.add(hbBtnn,0,4);

       
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        grid.setColumnSpan(actiontarget, 1);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");
        
       
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String selectUserNumber = String.valueOf(userTextField.getText()); 
                String UserPassword = String.valueOf((pwBox).getText()); 
                if(userTextField.getText().equals("")||(pwBox).getText().equals("")){
                	actiontarget.setText( "Login failed!");
                }
                else{
                	System.out.println("\r\n" + 
                			"Enter user information: Farmer ID: " +selectUserNumber+  " password"+ UserPassword);
                    int user = JDBC.selectUserNumber(selectUserNumber);
                    int Password = JDBC.selectPassword(selectUserNumber,UserPassword);
                    JDBC.select(selectUserNumber);
                    if ((user != 0)&&(Password != 0)) {
                    	actiontarget.setText( "login successful");
                    	SignIn (primaryStage);
                    	primaryStage.close();
                    }
                    else{
                      	actiontarget.setText( "login failed");
                   
                 }
                }
                
        }});
        
       
        bttn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
            	 System.out.println("run       ");
        		 String InsertUserNumber = String.valueOf(userTextField.getText()); 
                 String InsertUserPassword = String.valueOf((pwBox).getText()); 
                 if(userTextField.getText().equals("") && pwBox.getText().equals("")){
                	 SignUp (primaryStage);
                	 primaryStage.close();
                 }
                 else{
                	 System.out.println("Enter the registration information: Farmer ID: " +InsertUserNumber+  "  password "+ InsertUserPassword);
            		 int rest = JDBC.InsertUserNumber1( InsertUserNumber,InsertUserPassword) ;
            		 if(rest == 0)
            			 actiontarget.setText("ID already exists");
            		 else
            		 {  
            		     SignUp (primaryStage);
            		     primaryStage.close();
            		     }
                 }
            }
        });
        
       
        btnn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
            	System.exit(0);
            }
        });
        
        
        Scene scene = new Scene(grid, 400, 260);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        primaryStage.show();
        System.out.println("The login interface runs ");
        //JDBC.SelectUser();
    }
	 public void SignIn (Stage primaryStage) {
		 GridPane grid1 = new GridPane();
		 Stage secondWindow = new Stage();
		 grid1.setId("Login-Successful");
		 Scene scene = new Scene(grid1,400,260);
		 secondWindow.setTitle("Sign In");
		 secondWindow.setScene(scene);
		 secondWindow.show();
		
	     grid1.setAlignment(Pos.CENTER);
	     grid1.setHgap(10);
	     grid1.setVgap(10);
	     grid1.setPadding(new Insets(25, 25, 25, 25));

	     Text scenetitle = new Text("Login Successful");
	     scenetitle.setId("Login-Successful-text");
	     grid1.add(scenetitle, 0, 0, 2, 1);

	     Label userID = new Label("Farmer ID:        " + JDBC.ID);
	     grid1.add(userID, 0, 1);

	     Label userName = new Label("Farmer Name:  " + JDBC.Name);
	     grid1.add(userName, 0, 2);
	        
	     Label usercrop = new Label("Farmer crop:      " + JDBC.crop);
	     grid1.add(usercrop, 0, 3);

	     Button btnn1 = new Button("  Logout ");
		 HBox hbBtnn1 = new HBox(10);
		 hbBtnn1.setAlignment(Pos.BOTTOM_LEFT);
		 hbBtnn1.getChildren().add(btnn1);
		 grid1.add(hbBtnn1,0,5);
		 
		 Button btnn2 = new Button("     Edit     ");
	     HBox hbBtnn2 = new HBox(10);
	     hbBtnn2.setAlignment(Pos.BOTTOM_RIGHT);
	     hbBtnn2.getChildren().add(btnn2);
	     grid1.add(hbBtnn2,5,5);
	     
	     Button btnn3 = new Button("     Price     ");
	     HBox hbBtnn3 = new HBox(10);
	     hbBtnn3.setAlignment(Pos.BOTTOM_CENTER);
	     hbBtnn3.getChildren().add(btnn3);
	     grid1.add(hbBtnn3,1,5);
		 
		 btnn1.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 secondWindow.close();
            	 start(primaryStage);
             }
         });
		     
		 btnn2.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 Edit (primaryStage);
            	 secondWindow.close();
             }
         });
		 btnn3.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 Price (primaryStage);
            	 secondWindow.close();
             }
         });
		 
		 scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
	 }
	 
	
     public void SignUp (Stage primaryStage) {
    	 GridPane grid2 = new GridPane();
    	 Stage secondWindow = new Stage();
    	 grid2.setId("SignUp-Successful");
		 Scene scene = new Scene(grid2,400,260);
		 secondWindow.setTitle("Sign Up");
		 secondWindow.setScene(scene);
		 secondWindow.show();
    	 grid2.setAlignment(Pos.CENTER);
	     grid2.setHgap(10);
	     grid2.setVgap(10);
	     grid2.setPadding(new Insets(25, 25, 25, 25));

	     Text scenetitle = new Text("Sign Up");
	     scenetitle.setId("Sign-Up-text");
	     grid2.add(scenetitle, 0, 0, 2, 1);

	     Label userID = new Label("farmer ID:");
	     grid2.add(userID, 0, 1);
	     
	     TextField userIDTextField = new TextField();
	     grid2.add(userIDTextField, 1, 1);
	        
	     Label Password = new Label("Password:");
	     grid2.add(Password, 0, 2);
	     
	     PasswordField Password1 = new PasswordField();
	     grid2.add(Password1, 1, 2);

	     Label userName = new Label("farmer name:");
	     grid2.add(userName, 0, 3);
	     
	     TextField userNameTextField = new TextField();
	     grid2.add(userNameTextField, 1, 3);
	        
	     Label userSex = new Label("crops produced:");
	     grid2.add(userSex, 0, 4);
	     
	     TextField userSexTextField = new TextField();
	     grid2.add(userSexTextField, 1, 4);
	     
	     Button btn2 = new Button(" Sign  Up ");
	     HBox hbBtn = new HBox(10);
	     hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	     hbBtn.getChildren().add(btn2);
	     grid2.add(hbBtn,1,5);
	     
	     Button btnn2 = new Button("  Return ");
	     HBox hbBtnn2 = new HBox(10);
	     hbBtnn2.setAlignment(Pos.BOTTOM_LEFT);
	     hbBtnn2.getChildren().add(btnn2);
	     grid2.add(hbBtnn2,0,5);
	       
	        
	     final Text actiontarget2 = new Text();
	     grid2.add(actiontarget2, 0, 6);
	     actiontarget2.setId("actiontarget2");
         
         btn2.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
             	 System.out.println("\r\n" + 
             	 		"Register to run      ");
             	 String InsertuserID = String.valueOf(userIDTextField.getText()); 
                 String InsertUserPassword = String.valueOf(Password1.getText()); 
       	         String InsertUserNumber = String.valueOf(userNameTextField.getText()); 
                 String InsertUsercrop = String.valueOf(userSexTextField.getText()); 
                  System.out.println("Enter the registration information: Farmer ID: " +InsertuserID+" Farmer Name:" 
                                      + InsertUserNumber +  "  gender:" + InsertUsercrop 
                                      + "   password" + InsertUserPassword);
                 if((userIDTextField.getText().equals("") )||( Password1.getText().equals("") )||( userNameTextField.getText().equals("") )||( userSexTextField.getText().equals(""))){
                	 actiontarget2.setText( "\r\n" + 
                	 		"The content is not empty!");}
                 else
                 {
      		        int rest =JDBC.InsertUserNumber( InsertuserID,InsertUserNumber,InsertUsercrop,InsertUserPassword) ;
      		     if(rest == 0)
      			     actiontarget2.setText("User already exists!");
      		     else{
      		         try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
      		         secondWindow.close();
      		         start(primaryStage);
      		        }
      		     }
             }
         });
	        
         btnn2.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 secondWindow.close();
            	 start(primaryStage);
             }
         });
         
         scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
		 
	 }
     
   
     public void Edit (Stage primaryStage) {
    	 GridPane grid3 = new GridPane();
    	 Stage secondWindow = new Stage();
    	 grid3.setId("Update-Data");
		 Scene scene = new Scene(grid3,400,260);
		 secondWindow.setTitle("Update Data");
		 secondWindow.setScene(scene);
		 secondWindow.show();
    	 grid3.setAlignment(Pos.CENTER);
	     grid3.setHgap(10);
	     grid3.setVgap(10);
	     grid3.setPadding(new Insets(25, 25, 25, 25));

	     Text scenetitle = new Text("Update Data");
	     scenetitle.setId("Update-Data-text");
	     grid3.add(scenetitle, 0, 0, 2, 1);

	     Label userID = new Label("User ID:");
	     grid3.add(userID, 0, 1);
	     
	     TextField userIDTextField = new TextField();
	     grid3.add(userIDTextField, 1, 1);
	        
	     Label Password = new Label("Password:");
	     grid3.add(Password, 0, 2);
	     
	     PasswordField Password1 = new PasswordField();
	     grid3.add(Password1, 1, 2);

	     Label userName = new Label("Farmer Name:");
	     grid3.add(userName, 0, 3);
	     
	     TextField userNameTextField = new TextField();
	     grid3.add(userNameTextField, 1, 3);
	        
	     Label usercrop = new Label("crop produce:");
	     grid3.add(usercrop, 0, 4);
	     
	     TextField usercropTextField = new TextField();
	     grid3.add(usercropTextField, 1, 4);
	     
	     Button btn3 = new Button("   Save   ");
	     HBox hbBtn3 = new HBox(10);
	     hbBtn3.setAlignment(Pos.BOTTOM_RIGHT);
	     hbBtn3.getChildren().add(btn3);
	     grid3.add(hbBtn3,1,5);
	     
	     Button btnn3 = new Button("  Return ");
	     HBox hbBtnn3 = new HBox(10);
	     hbBtnn3.setAlignment(Pos.BOTTOM_LEFT);
	     hbBtnn3.getChildren().add(btnn3);
	     grid3.add(hbBtnn3,0,5);
	       
	     Button bttn3 = new Button("   Delete    ");
	     HBox hbBttn3 = new HBox(10);
	     hbBttn3.getChildren().add(bttn3);
	     grid3.add(bttn3, 1, 5);
	     
	     final Text actiontarget3 = new Text();
	     grid3.add(actiontarget3, 0, 6);
	     actiontarget3.setId("actiontarget3");
         
         btn3.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
             	 System.out.println("\r\n" + 
             	 		"Update data run      ");
             	 String UpdateuserID = String.valueOf(userIDTextField.getText()); 
                 String UpdateUserPassword = String.valueOf(Password1.getText()); 
       	         String UpdateUserNumber = String.valueOf(userNameTextField.getText()); 
                 String UpdateUsercrop = String.valueOf(usercropTextField.getText()); 
                 System.out.println( "Enter update information: farmer ID" + UpdateuserID + "   name" + UpdateUserNumber +  "   gender" + UpdateUsercrop 
                                      + "   password" + UpdateUserPassword);
                 if(( userIDTextField.getText().equals("") )||( Password1.getText().equals("") )&&( userNameTextField.getText().equals("") )&&( usercropTextField.getText().equals(""))){
                	 actiontarget3.setText( "The content is not empty!");}
                 else
                 {
      		        int rest = JDBC.UpdateUserNumber( UpdateuserID,UpdateUserNumber,UpdateUsercrop,UpdateUserPassword) ;
      		        if(rest == 1){
      			        try {
  					       Thread.sleep(1000);
  					       secondWindow.close();
  				        } catch (InterruptedException e1) {
  					       e1.printStackTrace();
  				     }
      			        start(primaryStage);
      			        }
      		        else{
      		            actiontarget3.setText( "ID is wrong!");}
		        }
             }
         });
	        
         btnn3.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 secondWindow.close();
            	 start(primaryStage);
             }
         });
     
         bttn3.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 System.out.println("Delete data run      ");
             	 String UpdateuserID = String.valueOf(userIDTextField.getText()); 
             	 System.out.println("\r\n" + 
             	 		"Enter delete information: Farmer ID " + UpdateuserID );
                 if( userIDTextField.getText().equals("") ){
                	 actiontarget3.setText( "\r\n" + 
                	 		"ID is empty!");
                 }
                 else{
                	 int rest = JDBC.DeleteUserNumber(UpdateuserID);
                	 if(rest == 1){
       			        try {
   					       Thread.sleep(1000);
   					    actiontarget3.setText( "\r\n" + 
   	       		            	"data deleted successfully");
   					       secondWindow.close();
   				        } catch (InterruptedException e1) {
   					       e1.printStackTrace();
   				     }
       			        start(primaryStage);
       			        }
       		        // else{
       		          //  actiontarget3.setText( "\r\n" + 
       		            	//	"Insufficient permissions!");}
 		        }
                 }
            	
         });

         scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
		 
	 }  
     private ObservableList<ObservableList> data;
     
     private TableView tableview;
  
     public void buildData() {
         Connection c;
         data = FXCollections.observableArrayList();
         try {
             c = DBConnect.connect();
             //SQL FOR SELECTING ALL OF CUSTOMER
             String SQL = "SELECT * from PRICE";
             //ResultSet
             ResultSet rs = c.createStatement().executeQuery(SQL);
  
           
             for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                 //We are using non property style for making dynamic table
                 final int j = i;
                 TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                 col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                     public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                         return new SimpleStringProperty(param.getValue().get(j).toString());
                     }
                 });
  
                 tableview.getColumns().addAll(col);
                 System.out.println("Column [" + i + "] ");
             }
  
             
             while (rs.next()) {
                 //Iterate Row
                 ObservableList<String> row = FXCollections.observableArrayList();
                 for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                     //Iterate Column
                     row.add(rs.getString(i));
                 }
                 System.out.println("Row [1] added " + row);
                 data.add(row);
  
             }
  
             //FINALLY ADDED TO TableView
             tableview.setItems(data);
         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("Error on Building Data");
         }
     }
  
    // @Override
     public void Price(Stage primaryStage)  {
         //TableView
         tableview = new TableView();
         buildData();
  
         GridPane grid4 = new GridPane();
    	 Stage secondWindow = new Stage();
    	 grid4.setId("PRICE");
		// Scene scene = new Scene(grid4,400,260);
		 secondWindow.setTitle("PRICES");
		// secondWindow.setScene(scene);
		 secondWindow.show();
    	 grid4.setAlignment(Pos.CENTER);
	     grid4.setHgap(10);
	     grid4.setVgap(10);
	     grid4.setPadding(new Insets(25, 25, 25, 25));
	     
	     Button btnn2 = new Button("  Return ");
	     HBox hbBtnn2 = new HBox(10);
	     hbBtnn2.setAlignment(Pos.BOTTOM_LEFT);
	     hbBtnn2.getChildren().add(btnn2);
	     grid4.add(hbBtnn2,0,5);
         //Main Scene
         Scene scene = new Scene(tableview);
         secondWindow.setScene(scene);
  
         primaryStage.setScene(scene);
         primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
             @Override
             public void handle(WindowEvent event) {
                 Platform.exit();
                 System.exit(0);
             }
         });
         primaryStage.show();
         
     } 
     public static void main(String[] args) {
        launch(args);
    }
        
}