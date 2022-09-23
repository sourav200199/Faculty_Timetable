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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author soura
 */
public class SignupController implements Initializable{
    @FXML
    private Button signup;
    @FXML
    private Button login;
    @FXML
    private RadioButton dean;
    @FXML
    private RadioButton admin;
    @FXML
    private RadioButton hag;
    @FXML
    private RadioButton assoc;
    @FXML
    private RadioButton assist;
    @FXML
    private RadioButton sp;
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField contact;
    @FXML
    private TextField email;
    
    @Override
    public void initialize(URL Location, ResourceBundle resources){
        ToggleGroup tg = new ToggleGroup();
        dean.setToggleGroup(tg);
        admin.setToggleGroup(tg);
        hag.setToggleGroup(tg);
        sp.setToggleGroup(tg);
        assoc.setToggleGroup(tg);
        assist.setToggleGroup(tg);
        
        assoc.setSelected(true);
        
        signup.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                String toggle = ((RadioButton) tg.getSelectedToggle()).getText();
                if(!username.getText().trim().isEmpty() && password.getText().trim().isEmpty()){
                    DBUtils.signupuser(event, name.getText(), username.getText(), password.getText(), Long.parseLong(contact.getText()), email.getText(), toggle);
                }
                else{
                    System.out.println("Please fill all the information!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all the information!");
                    alert.show();
                }
            }
        });
        
        login.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event, "Welcome.fxml", "Welcome User", null, null);
            }
        });
    }
}
