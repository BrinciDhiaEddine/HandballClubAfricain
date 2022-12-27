package com.handball.handballdesktopapp;

import com.handball.handballdesktopapp.services.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class NewSessionController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;

    public Button StartButton = new Button() ;
    public Button backButton = new Button() ;

    public TextField nomSession = new TextField();
    public DatePicker dateSession = new DatePicker();

    H2DatabaseConnector connectNow = new H2DatabaseConnector();
    Connection connectDB = connectNow.getConnection();

    public NewSessionController() throws SQLException {
    }

    public void switchToScene1(ActionEvent event) throws IOException, SQLException {
        String nom = nomSession.getCharacters().toString();
        String nomString = "'"+nom+"'";
        String data = dateSession.getValue().toString();
        String dateString = "'"+data+"'";
        Statement stmt = stmt = connectDB.createStatement();;
        ResultSet resultSet = null;
        int s = stmt.executeUpdate("insert into session(nom,data) values("+nomString+","+dateString+");");
        UserData userData = UserData.getUserData() ;
        resultSet = stmt.executeQuery("Select id from session where nom ="+nomString+" and data ="+dateString+";");
        resultSet.next();
        userData.setSession_id(resultSet.getInt("id"));
        userData.setDate_session(data);
        userData.setNom_session(nom);
        root = FXMLLoader.load(getClass().getResource("newPlayer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToHelloPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StartButton.setStyle("-fx-background-color: #007bff;-fx-text-base-color:white; ");
        backButton.setStyle("-fx-background-color:  #28a745;-fx-text-base-color:white; ");
    }
}
