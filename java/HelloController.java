package com.example.project_master_utillisation_relation_semantique_dumls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginmessagelabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    public void LoginButton(ActionEvent e){


        //VERIFICATION DE DONNEES
        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            validationLoginDta();
        } else{
            loginmessagelabel.setText("Please enter your username and password!");
        }
    }

    public void CancelButtonOnAction (ActionEvent e){
        Stage  stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();
    }
    public void validationLoginDta(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDb = connectNow.getConnection();

        String verifyLogin = "SELECT count(3)  FROM masterProject.users WHERE username='"+usernameTextField.getText()+"' AND password='"+passwordTextField.getText()+"';";

        try{
            Statement statement = connectionDb.createStatement();
            //EXUCUTE
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    HomePage();
                }else{
                    loginmessagelabel.setText("please try agine");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createAccountForm(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650, 440);
            Stage registration = new Stage();
            registration.initStyle(StageStyle.UNDECORATED);
            registration.setScene(scene);
            registration.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void HomePage(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650, 440);
            Stage registration = new Stage();
            registration.initStyle(StageStyle.UNDECORATED);
            registration.setScene(scene);
            registration.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}