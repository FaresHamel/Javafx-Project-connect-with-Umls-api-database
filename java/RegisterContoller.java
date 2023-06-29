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

public class RegisterContoller {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label signUpLabelTextField;

    @FXML
    private Button closeButton;
    public void SignUpButton(ActionEvent e){

        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false && lastnameTextField.getText().isBlank() == false && firstnameTextField.getText().isBlank() == false){
            validationSignUpData();
        } else{
            signUpLabelTextField.setText("Please enter your Data!");
        }
    }

    public void closeButtonSignupAction (ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void validationSignUpData(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDb = connectNow.getConnection();

        String insertSignUp = "INSERT INTO `masterProject`.`users` (`firstname`, `lastname`, `username`, `password`) VALUES ('"+firstnameTextField.getText()+"', '"+lastnameTextField.getText()+"', '"+lastnameTextField.getText()+"', '"+passwordTextField.getText()+"')";

        try{
            Statement statement = connectionDb.createStatement();
            statement.executeUpdate(insertSignUp);
            HomePage();
        }catch (Exception e){
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
