package com.handball.handballdesktopapp;
import com.handball.handballdesktopapp.services.SerialPortService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws SQLException, InterruptedException {
       SerialPortService serialPortService = new SerialPortService();
        H2DatabaseConnector connectNow = new H2DatabaseConnector();
        Connection connectDB = connectNow.getConnection();
        Statement stmt = connectDB.createStatement();
        ResultSet resultSet;
        stmt.executeUpdate(" CREATE TABLE IF NOT EXISTS `joueur` (\n" +
                "  `nom` varchar(20) DEFAULT NULL,\n" +
                "  `session_id` int DEFAULT NULL,\n" +
                "  `moyenne` double DEFAULT NULL\n" +
                ");");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `session` (\n" +
                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                "  `nom` varchar(20) DEFAULT NULL,\n" +
                "  `data` date DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `resJoueur` (\n" +
                "`session_id` varchar(20) ,`session_date` varchar(20), `nom_session` varchar(30),`nom_joueur` varchar(30),\n" +
                "`level` varchar(10),`A25` varchar(30),`A35` varchar(30),`A24` varchar(30),`A34` varchar(30),\n" +
                "`A2_5` varchar(30),`A3_5` varchar(30),`A2_4` varchar(30),`A3_4` varchar(30));");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("statistiques.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}