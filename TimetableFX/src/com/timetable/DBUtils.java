/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timetable;

//import com.sun.jdi.connect.spi.Connection;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author soura
 */
public class DBUtils {
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static void changeScene(ActionEvent event, String fxmlfile, String title, String name, String username){
        Parent root = null; //base class for other nodes that have children in the "Scene"
        
        if(name != null && username != null)
        {
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlfile)); //loads the scene from an FXML Documnet
                root = loader.load();
                LoggedinController loggedincontroller = loader.getController();
                loggedincontroller.setUserInformation(name, username);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else{
            try{
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlfile));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
//    public static void main(String []args)
//    {
//        signupuser(null, null, null, null);
//    }
    public static void signupuser(ActionEvent event, String name,String username,String password,long contact,String email, String f_type){
        Connection con = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection(
                    "jdbc:mysql://database-faculty-vit.cfk9lvrbeabj.ap-south-1.rds.amazonaws.com:3306/sys?allowPublicKeyRetrieval=true&useSSL=false", "admin", "password");
  
            psCheckUserExists = con.prepareStatement("SELECT * FROM users WHERE username = ?"); //check if there already exists any user with same username
            psCheckUserExists.setString(1, username); // the '?' is username
            rs = psCheckUserExists.executeQuery();
            if(rs.isBeforeFirst()) //func. to check if there exists any user
            {
                System.out.println("User already EXISTS");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Use a different username!");
                alert.show();
            }
            else
            {
                psInsert = con.prepareStatement("INSERT into users(name, username, password, contact, email, faculty_type VALUES(?, ?, ?, ?, ?, ?))");
                psInsert.setString(1, name);
                psInsert.setString(2, username);
                psInsert.setString(3, password);
                psInsert.setLong(4, contact);
                psInsert.setString(5, email);
                psInsert.executeUpdate(); //execute and update the db
                
                changeScene(event, "HelloWorld.fxml","Welcome",name, username);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            if(rs != null)
            {
                try{
                    rs.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null)
            {
                try{
                    psCheckUserExists.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null)
            {
                try{
                    psInsert.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(con != null)
            {
                try{
                    con.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void loginuser(ActionEvent event, String username, String password) throws ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pslogin = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection(
                    "jdbc:mysql://database-faculty-vit.cfk9lvrbeabj.ap-south-1.rds.amazonaws.com:3306/sys?allowPublicKeyRetrieval=true&useSSL=false", "admin", "password");
            pslogin = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            rs = pslogin.executeQuery();
            
            if(!rs.isBeforeFirst()){
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid credentials");
                alert.show();
            }
            else
            {
                while(rs.next())
                {
                    String db_nm = rs.getString("name");
                    String db_pswd = rs.getString("password");
                   if(db_pswd.equals(password))
                       changeScene(event, "Welcomeuser.fxml", "Welcome", db_nm, username);
                   else{
                       System.out.println("Password incorrect!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid credentials");
                        alert.show();
                   }
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            if(rs != null)
            {
                try{
                    rs.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(pslogin != null)
            {
                try{
                    pslogin.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(con != null)
            {
                try{
                    con.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
