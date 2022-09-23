/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timetable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author soura
 */
public class Controller implements Initializable{
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    
    @Override
    public void initialize(URL Location, ResourceBundle resources){
        
        login.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{
                DBUtils.loginuser(event, username.getText(), password.getText());
                }catch (ClassNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        signup.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event, "sign_up.fxml", "Sign up", name.getText(), username.getText());
            }
        });
    }
}
