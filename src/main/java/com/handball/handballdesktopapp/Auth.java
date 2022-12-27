package com.handball.handballdesktopapp;

import com.handball.handballdesktopapp.services.env;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Auth implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public Button seConnecter = new Button();

    public TextField usernameField = new TextField();
    public TextField passwordField = new TextField();

    public env myEnv = new env();

    public void seConnecter(ActionEvent event) throws IOException {
        System.out.println(myEnv._USERNAME);
        String username = usernameField.getCharacters().toString();
        String password = passwordField.getCharacters().toString();
        if(username.equals(myEnv._USERNAME) && password.equals(myEnv._PASSWORD)){
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        seConnecter.setStyle("-fx-background-color: #007bff;-fx-text-base-color:white; ");
    }
}
