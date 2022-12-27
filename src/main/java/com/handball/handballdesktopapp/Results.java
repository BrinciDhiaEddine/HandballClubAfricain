package com.handball.handballdesktopapp;


import com.handball.handballdesktopapp.DAO.resultats;
import com.handball.handballdesktopapp.services.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.io.*;
import java.net.URL;

public class Results implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public Button anotherPlayer = new Button();
    public Button rejouer = new Button();
    public Button endSession = new Button();

    public Button exportToExcel = new Button() ;
    public Button saveResults = new Button() ;
    H2DatabaseConnector connectNow = new H2DatabaseConnector();
    Connection connectDB ;

    public Results() throws SQLException {
    }

    public void save() throws SQLException {

            System.out.println(UserData.getUserData().getSession_id());
            String session_id = "'" + UserData.getUserData().getSession_id() + "'";
            System.out.println(UserData.getUserData().getDate_session());
            String session_date = "'" + UserData.getUserData().getDate_session() + "'";
            System.out.println(UserData.getUserData().getNom_session());
            String nom_session = "'" + UserData.getUserData().getNom_session() + "'";
            System.out.println(UserData.getUserData().getNom_joueur());
            String nom_joueur = "'" + UserData.getUserData().getNom_joueur() + "'";
            System.out.println(UserData.getUserData().getLevel());
            String level = "'" + UserData.getUserData().getLevel() + "'";
            System.out.println(dataExcelList);
            if (UserData.getUserData().isLevel5() == false) {
                Statement stmt = connectDB.createStatement();
                for (resultats res : dataExcelList) {
                    stmt.executeUpdate("Insert into resJoueur(session_id,session_date,nom_session,nom_joueur,level,\n" +
                            "A25,A35,A24,A34,A2_5,A3_5,A2_4,A3_4) values(" + session_id + "," + session_date + "," + nom_session + "\n" +
                            "," + nom_joueur + "," + level + ",'" + res.getA25() + "','" + res.getA35() + "','" + res.getA24() + "','" + res.getA34() + "'\n" +
                            ",'" + res.getA2_5() + "','" + res.getA3_5() + "','" + res.getA2_4() + "','" + res.getA3_4() + "');");
                }
            }
        }




    public void changerDeJoueur(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("newPlayer.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    public void rejouer(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("session.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void quitterLaSession(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    ObservableList<resultats> data = FXCollections.observableArrayList();
    ObservableList<resultats> dataList = FXCollections.observableArrayList();
    ObservableList<resultats> dataExcelList = FXCollections.observableArrayList();
    @FXML
    TableView table = new TableView();

    TableColumn col25 = new TableColumn("A2+A5");
    TableColumn col35 = new TableColumn("A3+A5");
    TableColumn col24 = new TableColumn("A2+A4");
    TableColumn col34 = new TableColumn("A3+A4");
    TableColumn col2_5 = new TableColumn("A2*A5");
    TableColumn col3_5 = new TableColumn("A3*A5");
    TableColumn col2_4 = new TableColumn("A2*A4");
    TableColumn col3_4 = new TableColumn("A3*A4");

    public void showResults() {
        col25.setCellValueFactory(
                new PropertyValueFactory<resultats, String>("A25"));
        col2_5.setCellValueFactory(
                new PropertyValueFactory<resultats, String>("A2_5"));
        col2_4.setCellValueFactory(
                new PropertyValueFactory<resultats, String>("A2_4"));
        col34.setCellValueFactory(
                new PropertyValueFactory<resultats, String>("A34"));
        col35.setCellValueFactory(
                new PropertyValueFactory<resultats, String>("A35"));
        col3_5.setCellValueFactory(
                new PropertyValueFactory<resultats, String>("A3_5"));
        col3_4.setCellValueFactory(
                new PropertyValueFactory<resultats, String>("A3_4"));
        col24.setCellValueFactory(
                new PropertyValueFactory<resultats, String>("A24"));
        if (UserData.getUserData().isLevel5())
            table.setItems(dataList);
        else table.setItems(data);
        table.getColumns().addAll(col25, col35, col24, col34, col2_5, col3_5, col2_4, col3_4);
    }


        public void writeExcel () throws IOException {
            System.out.println(table.getItems().get(0).getClass());
            ObservableList data = table.getItems();
            Writer writer = null;
            writeExcelSession();
        }

        public void writeExcelSession() throws IOException {
            Writer writer = null;
            try {
                File file = new File("C:\\Users\\MSI\\"+UserData.getUserData().getNom_joueur()+UserData.getUserData().getSession_id()+".csv.");
                writer = new BufferedWriter(new FileWriter(file));
                String colonnes = "A2+A4,A2*A4,A3+A4,A3*A4,A2+A5,A2*A5,A3+A5,A3*A5\n";
                writer.write(colonnes);
                for (resultats elm : dataExcelList) {

                    String text = elm.getA24() + "," + elm.getA2_4() + "," + elm.getA34()+","+elm.getA3_4() + "," + elm.getA25() + "," + elm.getA2_5() + "," + elm.getA35()+","+elm.getA3_5()+"\n";

                    writer.write(text);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                writer.flush();
                writer.close();
            }
        }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connectDB=connectNow.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        endSession.setStyle("-fx-background-color: #dc3545;-fx-text-base-color:white; ");
        anotherPlayer.setStyle("-fx-background-color:  #28a745;-fx-text-base-color:white; ");
        saveResults.setStyle("-fx-background-color:  #28a745;-fx-text-base-color:white; ");
        rejouer.setStyle("-fx-background-color:  #007bff;-fx-text-base-color:white; ");
        exportToExcel.setStyle("-fx-background-color: #ffda24;-fx-text-base-color:white; ");
        data = UserData.getUserData().getResultatsJoueur();
        dataList = UserData.getUserData().getResultatsJoueurList();
        if(data==null){dataExcelList=dataList;}
        else {dataExcelList=data;}

        showResults();
    }
}
