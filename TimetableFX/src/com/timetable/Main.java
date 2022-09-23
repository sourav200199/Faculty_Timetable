/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timetable;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author soura
 */
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
        //System.setProperty("javafx.platform", "Desktop");
        //URL url = getClass().getClassLoader().getResource("login.fxml");
        //FXMLLoader loader = new FXMLLoader(url);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign_up.fxml"));
        fxmlLoader.setRoot(new AnchorPane());
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String []args) throws Exception
    {
        //System.setProperty("javafx.preloader", Main.class.getCanonicalName());
        Application.launch(args);
    }
}
