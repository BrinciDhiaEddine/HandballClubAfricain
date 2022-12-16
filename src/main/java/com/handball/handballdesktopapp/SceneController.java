package com.handball.handballdesktopapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public Button newSessionButton = new Button() ;
    public Button statisticsButton = new Button() ;
    public Button infoButton = new Button() ;


    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("nouvelle-session.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newSessionButton.setStyle("-fx-background-color: #007bff;-fx-text-base-color:white; ");
        statisticsButton.setStyle("-fx-background-color: #007bff;-fx-text-base-color:white; ");
        infoButton.setStyle("-fx-background-color: #007bff;-fx-text-base-color:white; ");

    }
}