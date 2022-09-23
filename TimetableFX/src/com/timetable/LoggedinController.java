/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timetable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 *
 * @author soura
 */
public class LoggedinController implements Initializable{
    @FXML
    private Button logout;
    @FXML
    private Label welcome;
    @FXML
    private Label username;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event, "Welcome.fxml", "Log in!", null, null);
            }
        });
    }
    public void setUserInformation(String name, String usrnm){
        welcome.setText("Hi "+ name);
        username.setText("Emp ID: "+ usrnm);
    }
}

