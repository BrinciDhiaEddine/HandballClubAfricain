package com.handball.handballdesktopapp;
import com.handball.handballdesktopapp.DAO.joueurDetails;
import com.handball.handballdesktopapp.DAO.joueurDetails;
import com.handball.handballdesktopapp.Models.Session;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Statistiques implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;



    H2DatabaseConnector connectNow = new H2DatabaseConnector();

    H2DatabaseConnector connectH2Now = new H2DatabaseConnector();
    Connection connectDB ;

    Connection connectH2DB ;
    Statement stmt;

    Statement H2stmt;
    ResultSet resultSet = null;

    @FXML
    TableView table = new TableView();

    @FXML
    Button deleteSessionById ;
    TableColumn colId = new TableColumn("session_id");
    TableColumn colNomSession = new TableColumn("Nom session");

    TableColumn colDate = new TableColumn("Date");

    TableColumn colNomJoueur = new TableColumn("Joueur");

    TableColumn colLevel = new TableColumn("level");
    TableColumn col25 = new TableColumn("A2+A5");
    TableColumn col35 = new TableColumn("A3+A5");
    TableColumn col24 = new TableColumn("A2+A4");
    TableColumn col34 = new TableColumn("A3+A4");
    TableColumn col2_5 = new TableColumn("A2*A5");
    TableColumn col3_5 = new TableColumn("A3*A5");
    TableColumn col2_4 = new TableColumn("A2*A4");
    TableColumn col3_4 = new TableColumn("A3*A4");



    public Button switchToHelloButton = new Button() ;

    ObservableList<Session> data = FXCollections.observableArrayList();
   // ObservableList<JoueurBySession> data2 = FXCollections.observableArrayList();
    ObservableList<joueurDetails> dataList = FXCollections.observableArrayList();


    public Button showSessions = new Button() ;

    public Button exportToExcel = new Button() ;

    public Button chercherBySessionId = new Button();

    public TextField chercherBySessionIdText = new TextField();
    public TextField deleteSession = new TextField();

    public void chercheryBySessionId(){
        table.getColumns().clear();
        //data2.clear();
        String idString ="'"+ chercherBySessionIdText.getCharacters().toString()+"'";
        try {
            connectDB = connectNow.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt = connectDB.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        };
        try {
            resultSet = stmt.executeQuery("SELECT * FROM resJoueur WHERE session_id="+idString+";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (resultSet.next()){
                    String session_id = resultSet.getString("session_id");
                    String session_date = resultSet.getString("session_date");
                    String nom_session = resultSet.getString("nom_session");
                    String nom_joueur = resultSet.getString("nom_joueur");
                    String level = resultSet.getString("level");
                    String A25 = resultSet.getString("A25");
                    String A35 = resultSet.getString("A35");
                    String A24 = resultSet.getString("A24");
                    String A34 = resultSet.getString("A34");
                    String A2_5 = resultSet.getString("A2_5");
                    String A3_5 = resultSet.getString("A3_5");
                    String A2_4 = resultSet.getString("A2_4");
                    String A3_4 = resultSet.getString("A3_4");
                    dataList.add(new joueurDetails(session_id,session_date,nom_session,nom_joueur,level,A25,A35,A24,A34,A2_5,A3_5,A2_4,A3_4));
                }
                else{break;};
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        for (joueurDetails jd : dataList) System.out.println(jd.toString());
        colId.setCellValueFactory(
                new PropertyValueFactory<joueurDetails,String>("session_id"));
        colNomSession.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("nom_session"));
        colDate.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("session_date"));
        colNomJoueur.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("nom_joueur"));
        colLevel.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("level"));
        col25.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("A25"));
        col2_5.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("A2_5"));
        col2_4.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("A2_4"));
        col34.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("A34"));
        col35.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("A35"));
        col3_5.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("A3_5"));
        col3_4.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("A3_4"));
        col24.setCellValueFactory(
                new PropertyValueFactory<joueurDetails, String>("A24"));
        table.setItems(dataList);
        table.getColumns().addAll(colId, colNomSession, colDate,colNomJoueur,colLevel,col25, col35, col24, col34, col2_5, col3_5, col2_4, col3_4);
    }
    public void testBD() throws SQLException {

        table.getColumns().clear();
        data.clear();
        try {
            connectDB = connectNow.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt = connectDB.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        };
        try {
            resultSet = stmt.executeQuery("SELECT * FROM SESSION");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String date = resultSet.getString("data");
                    data.add(new Session(id,nom,date));
                }
                else{break;};
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        for(Session session : data){

        System.out.println(session.toString());}
        colId.setCellValueFactory(
                new PropertyValueFactory<Session, Number>("id"));
        colNomSession.setCellValueFactory(
                new PropertyValueFactory<Session, String>("nom"));
        colDate.setCellValueFactory(
                new PropertyValueFactory<Session, String>("date"));
        table.setItems(data);
        table.getColumns().addAll(colId, colNomSession, colDate);


    }

    public void switchToHelloPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateJoueur() throws SQLException {
        connectDB = connectNow.getConnection();
        connectH2DB = connectH2Now.getConnection();
        stmt = connectDB.createStatement();
        H2stmt = connectH2DB.createStatement();
        resultSet=stmt.executeQuery("SELECT * FROM joueur;");
        while(resultSet.next()){
            String session_id = "'"+resultSet.getInt("session_id")+"'";
            String nom = "'"+resultSet.getString("nom")+"'";
            String moyenne = "'"+resultSet.getFloat("moyenne")+"'";
            H2stmt.executeUpdate("Insert into joueur(nom,session_id,moyenne) values("+nom+","+session_id+","+moyenne+");");
        }
    }
    public void writeExcel() throws Exception {
        System.out.println(table.getItems().get(0).getClass());
        ObservableList data = table.getItems();
        Writer writer = null;
        if(table.getItems().get(0).getClass().toString().equals("class com.handball.handballdesktopapp.Models.Session")){
           System.out.println("true");
            writeExcelSession();
        } else if (table.getItems().get(0).getClass().toString().equals("class com.handball.handballdesktopapp.DAO.joueurDetails")){
            writeExcelJoueur();
        } 
        /*try {
            File file = new File("C:\\Person.csv.");
            writer = new BufferedWriter(new FileWriter(file));
            for (elm : data) {

                String text = person.getFirstName() + "," + person.getLastName() + "," + person.getEmail() + "\n";



                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }*/
    }

    public void writeExcelSession() throws IOException {
        Writer writer = null;
        try {
            File file = new File("C:\\Users\\MSI\\Session.csv.");
            writer = new BufferedWriter(new FileWriter(file));
            for (Session elm : data) {

                String text = elm.getId() + "," + elm.getNom() + "," + elm.getId() + "\n";

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
    public void writeExcelJoueur() throws IOException {
        Writer writer = null;
        try {
            File file = new File("C:\\Users\\MSI\\session.csv.");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("session_id,session_date,nom_session,nom_joueur,level,A2+A5,A3+A5,A2+A4,A3+A4,A2*A5,A3*A5,A2*A4,A3*A4\n");
            for (joueurDetails elm : dataList) {

                String text = elm.getSession_id() + "," + elm.getSession_date() + "," + elm.getNom_session() + "," + elm.getNom_joueur()+","+elm.getLevel()+","+elm.getA25()+","+elm.getA35()+","+elm.getA24()+","+elm.getA34()+","+elm.getA2_5()+","+elm.getA3_5()+","+elm.getA2_4()+","+elm.getA3_4()+ "\n";

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
    public void deleteSessionCtrl() throws SQLException {
        String id = "'"+deleteSession.getCharacters().toString()+"'";
        connectDB = connectNow.getConnection();
        stmt = connectDB.createStatement();
        stmt.executeUpdate("Delete from session where id = "+id+";");
        for(Session session:data){
            if(("'"+session.getId()+"'").equals(id)){
                data.remove(session);
            }
        }
        stmt.executeUpdate("Delete from joueur where session_id = "+id+";");
        table.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        showSessions.setStyle("-fx-background-color: #007bff;-fx-text-base-color:white;");
        chercherBySessionId.setStyle("-fx-background-color:  #28a745;-fx-text-base-color:white; ");
        switchToHelloButton.setStyle("-fx-background-color: #dc3545;-fx-text-base-color:white; ");
        exportToExcel.setStyle("-fx-background-color:  #28a745;-fx-text-base-color:white; ");
        deleteSessionById.setStyle("-fx-background-color: #dc3545;-fx-text-base-color:white; ");
    }
}
