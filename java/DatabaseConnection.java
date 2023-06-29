package com.example.project_master_utillisation_relation_semantique_dumls;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName ="masterProject";
        String databaseUser="root";
        String databasePassword="testfares";
        String url = "jdbc:mysql://localhost:3306/"+databaseName;

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");

        databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        } catch(Exception e ){
            e.printStackTrace();

//            throw new RuntimeException(e);
        }

        return databaseLink;
    }


}
