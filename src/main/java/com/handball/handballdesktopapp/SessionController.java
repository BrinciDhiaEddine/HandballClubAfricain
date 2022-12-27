package com.handball.handballdesktopapp;

import com.handball.handballdesktopapp.services.SerialPortService;
import com.handball.handballdesktopapp.services.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SessionController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox myChoiceBox;

    public Button switchToHelloButton = new Button() ;
    public Button backButton = new Button() ;

    public Button connectButton = new Button() ;
    public Button startButton = new Button() ;

    public Button resultButton = new Button() ;

    UserData userData =UserData.getUserData();

    SerialPortService serialPortService ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serialPortService = new SerialPortService();
        myChoiceBox.getItems().add("level 1");
        myChoiceBox.getItems().add("level 2");
        myChoiceBox.getItems().add("level 3");
        myChoiceBox.getItems().add("level 4");
        myChoiceBox.getItems().add("level 5");
        switchToHelloButton.setStyle("-fx-background-color: #dc3545;-fx-text-base-color:white; ");
        backButton.setStyle("-fx-background-color:  #28a745;-fx-text-base-color:white; ");
        connectButton.setStyle("-fx-background-color:  #28a745;-fx-text-base-color:white; ");
        startButton.setStyle("-fx-background-color:  #007bff;-fx-text-base-color:white; ");
        resultButton.setStyle("-fx-background-color: #ffda24;-fx-text-base-color:white; ");
        System.out.println("nom : " +userData.getNom_joueur());
        System.out.println("session : " +userData.getSession_id());



    }

    public void switchToResultsPage(ActionEvent event) throws IOException {
        serialPortService.saveResults();
        root = FXMLLoader.load(getClass().getResource("results.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPlayerPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("newPlayer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToHelloPage(ActionEvent event) throws IOException {
        serialPortService.test();
        /*root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }

    public void start() throws InterruptedException, IOException, SQLException {
       // Double average=serialPortService.start(myChoiceBox.getValue().toString());
        //System.out.println("Average : "+average);
        serialPortService.setLevel(myChoiceBox.getValue().toString());
        System.out.println(serialPortService.activePort.isOpen());
        //serialPortService.readDate();

    }
    public void connecter(){
        try {
            serialPortService.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
