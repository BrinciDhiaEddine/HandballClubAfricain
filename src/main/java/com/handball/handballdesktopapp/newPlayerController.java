package com.handball.handballdesktopapp;

import com.handball.handballdesktopapp.services.UserData;
import javafx.event.ActionEvent;
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

public class newPlayerController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public Button playButton = new Button() ;
    public Button backButton = new Button() ;

    UserData userData =UserData.getUserData();

    public TextField nomJoueur = new TextField();

    public void switchToSessionScreen(ActionEvent event) throws IOException {
        userData.setNom_joueur(nomJoueur.getText());
        root = FXMLLoader.load(getClass().getResource("session.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchSessionSettingsPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("nouvelle-session.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playButton.setStyle("-fx-background-color: #007bff;-fx-text-base-color:white; ");
        backButton.setStyle("-fx-background-color:  #28a745;-fx-text-base-color:white; ");
        System.out.println("session  :"+userData.getSession_id());
        System.out.println("session  :"+userData.getDate_session());
    }
}
